package org.gmnz.vega.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractListManagedPropertyHolder<T> extends NamedEntity implements ListManagedPropertyHolder<T> {

	protected List<T> listProperty;

	public AbstractListManagedPropertyHolder() {
		super(null);
		listProperty = new ArrayList<>();
	}

	public AbstractListManagedPropertyHolder(String nome) {
		super(nome);
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

	@Override
	public int getListSize() {
		return listProperty.size();
	}
}
