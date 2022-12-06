package ra.model.dao;

import java.util.List;

public interface StoreDao<T,V> {
    List<T> getAll();
    T getById(V id);
    boolean save(T t);
    boolean update(T t);
    boolean delete(V id);
}
