package org.gmnz.vega.web.command;


import org.gmnz.vega.web.context.RequestContext;


/**
 * creato da simone in data 08/09/2018.
 *
 * @deprecated non ha senso usarlo poiché il front controller deve gestire
 *             richieste che rappresentano comandi specifici, come se fosse una
 *             api rest
 */
@Deprecated
class CommandGetFile extends AbstractVegaCommand {

	private String targetFile;



	public CommandGetFile(RequestContext requestContext) {
		super(requestContext);
	}



	@Override
	protected void initialize(RequestContext requestContext) {
		targetFile = requestContext.getParameter(VegaCommand.TARGET_FILE);
	}



	@Override
	protected void process() {
		model.setAttribute("OUTCOME", 200);
		model.setParameter(VegaCommand.TARGET_FILE, targetFile);
	}

}
