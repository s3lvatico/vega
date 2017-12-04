package org.gmnz.vega.domain;

import org.gmnz.vega.base.AbstractListManagedPropertyHolder;

import java.util.List;

public class Categoria extends AbstractListManagedPropertyHolder<Allergene> {


	public Categoria(String nome) {
		super(nome);
	}


	public List<Allergene> getAllergeni() {
		return getListProperty();
	}

	@Override
	public int hashCode() {
		return getNome().hashCode();
	}

	@Override
	public String toString() {
		return "Categoria{" +
				"nome='" + getNome() + '\'' +
				", allergeni=" + listProperty +
				'}';
	}
}
