package com.util.beans;

public interface Observable {
	void addListener(InvalidationListener listener);

	void removeListener(InvalidationListener listener);
}
