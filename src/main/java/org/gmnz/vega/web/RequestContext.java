package org.gmnz.vega.web;

/**
 * creato da simone in data 03/09/2018.
 */
public interface RequestContext {

	Object getProperty(String propertyName);



	void setProperty(String name, Object value);

}
