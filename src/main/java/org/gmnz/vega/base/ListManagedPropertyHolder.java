package org.gmnz.vega.base;


/**
 * da togliere
 * @param <T> tipo generico contenuto in una lista
 *
 * @deprecated verrà presto cancellato
 */
@Deprecated
interface ListManagedPropertyHolder<T> {

	int add(T t);



	boolean contains(T t);



	boolean remove(T t);



	void clear();



	int getListSize();
}
