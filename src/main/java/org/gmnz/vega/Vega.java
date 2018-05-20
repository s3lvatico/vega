package org.gmnz.vega;


import org.gmnz.vega.domain.Allergen;
import org.gmnz.vega.domain.Category;

import java.util.List;


public interface Vega {

	List<Allergen> elencaAllergeni();

	void creaAllergene(String nome);

	Allergen selezionaAllergene(String nome);

	void rinominaAllergene(String vecchioNome, String nuovoNome);

	void eliminaAllergene(String nome);

	List<Category> elencaCategorie();

	void creaCategoria(String nome);

	Category selezionaCategoria(String nome);

	void categorizzaAllergene(String nomeAllergene, String nomeCategoria);


}
