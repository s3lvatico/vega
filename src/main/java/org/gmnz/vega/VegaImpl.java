package org.gmnz.vega;

import org.gmnz.vega.domain.Allergene;
import org.gmnz.vega.domain.Categoria;

import java.util.List;

public class VegaImpl implements Vega {
	@Override
	public List<Allergene> elencaAllergeni() {
		return null;
	}

	@Override
	public void creaAllergene(String nome) {

	}

	@Override
	public Allergene selezionaAllergene(String nome) {
		return null;
	}

	@Override
	public void rinominaAllergene(String vecchioNome, String nuovoNome) {

	}

	@Override
	public void eliminaAllergene(String nome) {

	}

	@Override
	public List<Categoria> elencaCategorie() {
		return null;
	}

	@Override
	public void creaCategoria(String nome) {

	}

	@Override
	public Categoria selezionaCategoria(String nome) {
		return null;
	}

	@Override
	public void categorizzaAllergene(String nomeAllergene, String nomeCategoria) {

	}
}
