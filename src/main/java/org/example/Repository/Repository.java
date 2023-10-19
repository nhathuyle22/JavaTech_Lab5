package org.example.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Interface for creating repository classes
 * Please create your own repository and implement this interface
 * @param <T> type of object managed by the repository (e.g. Product)
 * @param <K> type of the object's primary key (e.g. Integer or String)
 */
public interface Repository <T extends Serializable, K>{
    T add(T e);
    T get(K id);
    List<T> getAll();

    //    create to method remove with E, and ID
    boolean remove(T e);
    boolean  remove(K id);
    boolean update(T e);


}
