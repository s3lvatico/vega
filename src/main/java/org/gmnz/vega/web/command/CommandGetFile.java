package org.gmnz.vega.web.command;


import org.gmnz.vega.web.context.RequestContext;
import org.gmnz.vega.web.context.ResponseContext;


/**
 * creato da simone in data 08/09/2018.
 */
class CommandGetFile extends AbstractVegaCommand {

	private String targetFile;



	public CommandGetFile(RequestContext requestContext) {
		super(requestContext);
	}

/*
questo è il più semplice

a priori non posso sapere se il file esiste oppure no (potrei andarlo a cercare,
ma me ne frego a questo livello).

Il risultato sarà sempre positivo (outcome interno sempre 200).
Come sapere dove andare? Parametro del nome noto, del tipo "viewName" ... che
per semplicità, se ho capito bene il modo
di ragionare, sarà un handle diretto.
*/

/*
Se ragiono per package, la particolare specializzazione del response context non
dovrebbe essere direttamente costruibile, perciò va fatta una nuova factory
apposita per questi oggetti
 */



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
