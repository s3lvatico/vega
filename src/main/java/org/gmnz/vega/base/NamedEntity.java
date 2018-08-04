package org.gmnz.vega.base;

/**
 * creato da simone in data 04/12/2017.
 */




/**
 * @deprecated  da togliere
 */
@Deprecated
public class NamedEntity {

	private String name;

	public static final String DEFAULT_CATEGORY_NAME = "DEFAULT_CATEGORY";



	public NamedEntity(String name) {
		this.name = name;
	}



	public String getName() {
		return name;
	}



	@Override
	public int hashCode() {
		return getName().hashCode();
	}
}
