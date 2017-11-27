package org.gmnz.a2.domain;

import java.util.Collections;
import java.util.List;

public class Categoria extends AbstractListManagedPropertyHolder<Allergene> {

	private String nome;


	public Categoria(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public List<Allergene> getAllergeni() {
		return getListProperty();
	}


	@Override
	public String toString() {
		return "Categoria{" +
				"nome='" + nome + '\'' +
				", allergeni=" + listProperty +
				'}';
	}
}
