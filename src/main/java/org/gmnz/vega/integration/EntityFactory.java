package org.gmnz.vega.integration;


import org.gmnz.vega.base.VegaUtil;

import java.util.UUID;


/**
 * Classe helper per creare entità base.
 * <p>Usare {@link #getInstance()}</p>
 */
public class EntityFactory {

	private static final EntityFactory INSTANCE = new EntityFactory();



	private EntityFactory() {
	}



	public static EntityFactory getInstance() {
		return INSTANCE;
	}



	/**
	 * Crea una nuova entità {@link AllergenEntity} con il solo nome definito.
	 * <p>Viene eseguito un controllo di validità sul nome specificato. L'entità viene di fatto creata se e solo se il nome è una stringa non nulla e non vuota</p>
	 *
	 * @param nome valore dell'atributo <code>nome</code> della nuova entità
	 * @return una nuova entità, oppure <code>null</code> se si specifica un nome inadeguato.
	 */
	public AllergenEntity createAllergeneEntity(String nome) {
		AllergenEntity e = null;
		if (!VegaUtil.stringNullOrEmpty(nome)) {
			e = new AllergenEntity(nome.trim());
			e.setUuid(UUID.randomUUID().toString());
		}
		return e;
	}



	/**
	 * Crea una nuova entità {@link CategoryEntity} con il solo nome definito.
	 * <p>Viene eseguito un controllo di validità sul nome specificato. L'entità viene di fatto creata se e solo se il nome è una stringa non nulla e non vuota</p>
	 *
	 * @param nome valore dell'atributo <code>nome</code> della nuova entità
	 * @return una nuova entità, oppure <code>null</code> se si specifica un nome inadeguato.
	 */
	public CategoryEntity createCategoriaEntity(String nome) {
		CategoryEntity e = null;
		if (!VegaUtil.stringNullOrEmpty(nome)) {
			e = new CategoryEntity(nome.trim());
			e.setId(UUID.randomUUID().toString());
		}
		return e;
	}

}
