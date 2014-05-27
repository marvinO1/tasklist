package org.rib.tasklist.dao.task;

import java.util.List;

import org.rib.tasklist.api.ManagedItem;

/**
 * Common methods supported by a DAO layer.
 *
 * @param <T>
 */
public interface GenericDao<T extends ManagedItem> {
	
	/**
	 * Creates the {@link ManagedItem}.
	 * 
	 * @param item
	 * @return T created item.
	 * @throws TasklistException in case the {@link ManagedItem} could not be created.
	 */
	T create(T item);
	
	/**
	 * Removes the {@link ManagedItem} referenced by the given id. In case the item does not exists no action takes place.
	 * @param id
	 * @throws TasklistException in case the {@link ManagedItem} exists but could not be deleted.
	 */
	void delete(String id);
	
	/**
	 * Update the given {@link ManagedItem}
	 * @param item
	 * @return T updated item
	 * @throws TasklistException in case the {@link ManagedItem} could not be updated.
	 */
    T update(T item);
    
    /**
     * @param id
     * @return answers true the {@link ManagedItem} exists, otherwise false is answered.
     */
    boolean exists(String id);
    
    /**
     * Gets the {@link ManagedItem} from the storage.
     * @param id
     * @return T 
     * @throws TasklistException in case the {@link ManagedItem} can not be found.
     */
	T get(String id);
	
	
	/**
	 * @return List<T> of all found {@link ManagedItem}, never null.
	 */
	List<T> getAll();
}
