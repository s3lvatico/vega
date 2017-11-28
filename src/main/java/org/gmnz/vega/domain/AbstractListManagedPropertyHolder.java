package org.gmnz.vega.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

abstract class AbstractListManagedPropertyHolder<T> implements ListManagedPropertyHolder<T> {

	protected List<T> listProperty;

	AbstractListManagedPropertyHolder() {
		listProperty = new ArrayList<>();
	}

	protected List<T> getListProperty() {
		return Collections.unmodifiableList(listProperty);
	}

	@Override
	public int add(T t) {
		listProperty.add(t);
		return listProperty.size();
	}

	@Override
	public boolean contains(T t) {
		return listProperty.contains(t);
	}

	@Override
	public boolean remove(T t) {
		return listProperty.remove(t);
	}

	@Override
	public void clear() {
		listProperty.clear();
	}
}
