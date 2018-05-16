package org.gmnz.vega.base;


/**
 * creato da simone in data 04/12/2017.
 */
public class NamedEntity {

	private String nome;

	public static final String DEFAULT_CATEGORY_NAME = "DEFAULT_CATEGORY";


	public NamedEntity(String nome) {
		this.nome = nome;
	}



	public String getNome() {
		return nome;
	}



	@Override
	public int hashCode() {
		return getNome().hashCode();
	}
}
