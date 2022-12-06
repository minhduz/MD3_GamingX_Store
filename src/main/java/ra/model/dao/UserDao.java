package ra.model.dao;

import java.util.List;

public interface UserDao<T,V> extends StoreDao<T,V>{
    List<T> getUsersByUserName(String name);
}
