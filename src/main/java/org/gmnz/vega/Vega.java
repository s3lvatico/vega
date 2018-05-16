package org.gmnz.vega;


import org.gmnz.vega.domain.Allergene;
import org.gmnz.vega.domain.Categoria;

import java.util.List;


public interface Vega {

	List<Allergene> elencaAllergeni();

	void creaAllergene(String nome);

	Allergene selezionaAllergene(String nome);

	void rinominaAllergene(String vecchioNome, String nuovoNome);

	void eliminaAllergene(String nome);

	List<Categoria> elencaCategorie();

	void creaCategoria(String nome);

	Categoria selezionaCategoria(String nome);

	void categorizzaAllergene(String nomeAllergene, String nomeCategoria);


}
