package dao;

import java.util.List;

public interface iDAO<T, K> {
    List<T> getAll() throws Exception;
    T getById(K id) throws Exception;
    T add(T e) throws Exception;
}
