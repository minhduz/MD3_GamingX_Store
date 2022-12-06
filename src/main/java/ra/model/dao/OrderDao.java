package ra.model.dao;

import java.util.List;

public interface OrderDao<T,V> extends StoreDao<T,V> {
    List<T> getOrderByUsername(String name);
}
