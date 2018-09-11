package org.gmnz.vega.web.command;


import org.gmnz.vega.web.context.RequestContext;
import org.gmnz.vega.web.context.ResponseContext;


/**
 * creato da simone in data 08/09/2018.
 *
 * @deprecated non ha senso usarlo poiché il front controller deve gestire
 * richieste che rappresentano comandi specifici, come se fosse una api rest
 */
@Deprecated
class CommandGetFile extends AbstractVegaCommand {

	private String targetFile;



	public CommandGetFile(RequestContext requestContext) {
		super(requestContext);
	}



	@Override
	public ResponseContext execute() {
		responseContext.setAttribute("OUTCOME", 200);
		responseContext.setParameter(VegaCommand.TARGET_FILE, targetFile);
		return responseContext;
	}



	@Override
	protected void initialize(RequestContext requestContext) {
		targetFile = requestContext.getParameter(VegaCommand.TARGET_FILE);
	}

}
