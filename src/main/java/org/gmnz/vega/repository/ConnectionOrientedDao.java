package org.gmnz.vega.repository;

/**
 * creato da simone in data 03/08/2018.
 * In questa versione i DAO usano connessioni gestite dal DataSource. Ciò significa che non è necessario gestirne la
 * chiusura manualmente, pertanto questa gerarchia di classi sarà resa deprecata
 * * @deprecated verrà rimosso in futuro
 */
@Deprecated
public interface ConnectionOrientedDao {

	void close();

}
