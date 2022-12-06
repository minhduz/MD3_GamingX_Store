package ra.model.service;

import java.util.List;

public interface StoreService<T,V> {
    List<T> getAll();
    T getById(V id);
    boolean save(T t);
    boolean update(T t);
    boolean delete(V id);
}

