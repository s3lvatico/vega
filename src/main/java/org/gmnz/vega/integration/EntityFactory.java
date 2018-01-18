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
	 * Crea una nuova entità {@link AllergeneEntity} con il solo nome definito.
	 * <p>Viene eseguito un controllo di validità sul nome specificato. L'entità viene di fatto creata se e solo se il nome è una stringa non nulla e non vuota</p>
	 *
	 * @param nome valore dell'atributo <code>nome</code> della nuova entità
	 * @return una nuova entità, oppure <code>null</code> se si specifica un nome inadeguato.
	 */
	public AllergeneEntity createAllergeneEntity(String nome) {
		AllergeneEntity e = null;
		if (!VegaUtil.stringNullOrEmpty(nome)) {
			e = new AllergeneEntity(nome.trim());
			e.setId(UUID.randomUUID().toString());
		}
		return e;
	}



	/**
	 * Crea una nuova entità {@link CategoriaEntity} con il solo nome definito.
	 * <p>Viene eseguito un controllo di validità sul nome specificato. L'entità viene di fatto creata se e solo se il nome è una stringa non nulla e non vuota</p>
	 *
	 * @param nome valore dell'atributo <code>nome</code> della nuova entità
	 * @return una nuova entità, oppure <code>null</code> se si specifica un nome inadeguato.
	 */
	public CategoriaEntity createCategoriaEntity(String nome) {
		CategoriaEntity e = null;
		if (!VegaUtil.stringNullOrEmpty(nome)) {
			e = new CategoriaEntity(nome.trim());
			e.setId(UUID.randomUUID().toString());
		}
		return e;
	}

}
