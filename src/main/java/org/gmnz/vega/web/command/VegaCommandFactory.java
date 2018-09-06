package org.gmnz.vega.web.command;


/**
 * creato da simone in data 05/09/2018.
 */
class VegaCommandFactory extends CommandFactory {
	/*
	teoricamente qui dentro ci stanno le mappe che permettono di associare il nome del comando con l'oggetto Command vero
	e proprio.
	*/

	/*
	segue che deve esistere un oggetto COMMAND per ogni operazione (o macrooperazione nel caso di comandi compositi) su
	ogni tipologia di entità gestita dal sistema
	 */

	/*
	nell'oggetto Command devono essere trasferiti tutti i parametri necessari ad eseguire il comando
	 */
	@Override
	public Command createCommand(String commandName) {
		// TODO fare


		return null;
	}
}
