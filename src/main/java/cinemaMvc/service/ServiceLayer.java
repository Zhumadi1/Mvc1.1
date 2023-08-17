package cinemaMvc.service;

import java.util.List;

public interface ServiceLayer<T> {
    T findById(int id);
    List<T> findAll();
    T save(T t);
    T updateById(int id, T t);
    void deleteById(int id);

}

