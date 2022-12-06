package ra.model.service;

import java.util.List;

public interface UserService<T,V> extends StoreService<T,V>{
    List<T> getUsersByUserName(String name);
}
