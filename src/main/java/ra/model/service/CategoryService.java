package ra.model.service;

import java.util.List;

public interface CategoryService<T,V> extends StoreService<T,V> {
    List<T> getCategoriesByName(String name);
}
