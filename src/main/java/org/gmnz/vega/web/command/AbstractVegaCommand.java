package org.gmnz.vega.web.command;


import org.gmnz.vega.web.context.ContextProperty;
import org.gmnz.vega.web.context.RequestContext;
import org.gmnz.vega.web.context.ResponseContext;
import org.gmnz.vega.web.context.ResponseContextFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;


/**
 * creato da simone in data 08/09/2018.
 */
abstract class AbstractVegaCommand implements Command {

	protected ResponseContext model;

	private String commandName;



	protected abstract String setCommandName();

	protected abstract void initialize(RequestContext requestContext);



	public AbstractVegaCommand(RequestContext requestContext) {
		model = ResponseContextFactory.getFactory().createResponseContext();
		model.setAttribute(ContextProperty.COMMAND_NAME, requestContext.getCommandName());
		this.commandName = requestContext.getCommandName();
		HttpServletRequest httpRequest = (HttpServletRequest) requestContext.getAttribute(ContextProperty.ORIGINAL_REQUEST);
		model.setAttribute(ContextProperty.CONTEXT_ROOT, httpRequest.getContextPath());
		model.setAttribute(ContextProperty.COMMAND_NAME, setCommandName());
		initialize(requestContext);
	}



	static Command ERROR(RequestContext requestContext, String outcome, int errorCode, String errorMessage, Throwable t) {

		return new AbstractVegaCommand(requestContext) {

			@Override
			protected String setCommandName() {
				return "erroneous.command";
			}



			@Override
			protected void initialize(RequestContext requestContext) {

			}



			@Override
			protected void process() {
				markForError(outcome, errorCode, errorMessage, t);
			}
		};
	}



	protected String getCommandName() {
		return commandName;
	}



	@Override
	public final ResponseContext execute() {
		try {
			process();
		}
		catch (Exception e) {
			e.printStackTrace();
			String errorMessage = String.format("%s command error : %s", getCommandName(), e.getMessage());
			markForError("APPLICATION_ERROR", 500, errorMessage, e);
		}
		return model;
	}



	/**
	 * esegue il comando e popola adeguatamente il {@link #model}
	 */
	protected abstract void process() throws Exception;



	protected void markForError(String outcome, int errorCode, String errorMessage, Throwable t) {
		model.setAttribute(ContextProperty.OUTCOME, outcome);
		model.setAttribute(ContextProperty.STATUS_CODE, errorCode);
		model.setAttribute(ContextProperty.ERROR_MESSAGE, errorMessage);
		if (t != null) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			t.printStackTrace(pw);
			model.setAttribute(ContextProperty.THROWABLE_CLASS_NAME, t.getClass().getName());
			model.setAttribute(ContextProperty.STACK_TRACE, sw.toString());
		}
	}

}
