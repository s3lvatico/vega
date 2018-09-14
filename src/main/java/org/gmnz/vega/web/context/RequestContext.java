package org.gmnz.vega.web.context;

/**
 * creato da simone in data 03/09/2018.
 */
public interface RequestContext extends ContextObject {


	Object getSessionAttribute(String name);

	void setSessionAttribute(String name, Object value);

	String getCommandName();

}
