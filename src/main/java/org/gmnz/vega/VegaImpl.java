package org.gmnz.vega;


import org.gmnz.vega.domain.Allergen;
import org.gmnz.vega.domain.Category;

import java.util.List;


public class VegaImpl implements Vega {
	@Override
	public List<Allergen> elencaAllergeni() {
		return null;
	}



	@Override
	public void creaAllergene(String nome) {

	}



	@Override
	public Allergen selezionaAllergene(String nome) {
		return null;
	}



	@Override
	public void rinominaAllergene(String vecchioNome, String nuovoNome) {

	}



	@Override
	public void eliminaAllergene(String nome) {

	}



	@Override
	public List<Category> elencaCategorie() {
		return null;
	}



	@Override
	public void creaCategoria(String nome) {

	}



	@Override
	public Category selezionaCategoria(String nome) {
		return null;
	}



	@Override
	public void categorizzaAllergene(String nomeAllergene, String nomeCategoria) {

	}
}
