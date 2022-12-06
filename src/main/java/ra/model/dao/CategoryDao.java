package ra.model.dao;

import java.util.List;

public interface CategoryDao<T,V> extends StoreDao<T,V> {
    List<T> getCategoriesByName(String name);
 }
