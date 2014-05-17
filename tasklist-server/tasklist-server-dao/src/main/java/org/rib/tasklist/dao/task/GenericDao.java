package org.rib.tasklist.dao.task;

import java.util.List;

import org.rib.tasklist.api.ManagedItem;

/**
 * Common methods supported by a DAO layer.
 * @author beat
 *
 * @param <T>
 */
public interface GenericDao<T extends ManagedItem> {
	T create(T item);
	void delete(String id);
    T update(T item);
    boolean exists(String id);
	T get(String id);
	List<T> getAll();
}
