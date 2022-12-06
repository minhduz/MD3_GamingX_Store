package ra.model.service;

import java.util.List;

public interface OrderService<T,V> extends StoreService<T,V> {
    List<T> getOrderByUsername(String name);
}
