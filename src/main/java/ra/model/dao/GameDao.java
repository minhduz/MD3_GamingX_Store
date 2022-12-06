package ra.model.dao;

import java.util.List;

public interface GameDao<T,V> extends StoreDao<T,V> {
    List<T> getGamesByName(String name);
}
