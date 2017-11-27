package org.gmnz.a2.domain;

interface ListManagedPropertyHolder<T> {

	int add(T t);

	boolean contains(T t);

	boolean remove(T t);

	void clear();
}
