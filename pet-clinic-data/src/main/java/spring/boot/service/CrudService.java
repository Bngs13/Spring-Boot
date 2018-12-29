package spring.boot.service;

import java.util.Set;

public interface CrudService<T,ID> {
    Set<T> findAll();
    T findById(ID id); //20181219
    T save(T object);
    void delete(T object);
    void deleteById(ID id);
}
