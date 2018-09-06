package org.gmnz.vega.web.command;


/**
 * creato da simone in data 05/09/2018.
 */
public abstract class CommandFactory {

	public static CommandFactory getFactory() {
		return new VegaCommandFactory();
	}



	public Command createCommand(String commandName) {
		return new Command() {
			@Override
			public void execute() {
				// TODO fare
			}
		};
	}

}
