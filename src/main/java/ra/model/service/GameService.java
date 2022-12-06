package ra.model.service;

import java.util.List;

public interface GameService<T,V> extends StoreService<T,V> {
    List<T> getGamesByName(String name);
}
