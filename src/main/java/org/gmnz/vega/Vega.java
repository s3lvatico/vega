package org.gmnz.vega;


import org.gmnz.vega.domain.Allergene;
import org.gmnz.vega.domain.Categoria;

import java.util.List;


public interface Vega {


	void creaAllergene(String nome);

	void aggiungiAllergene(String nomeAllergene, String nomeCategoria);

	List<Allergene> elencaAllergeni();

	List<Categoria> elencaCategorie();

	void creaCategoria(String nome);

}
