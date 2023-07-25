package com.safety.alerts.repository;

import java.util.List;

public interface ICrudRepository<T> {

    void saveAll(List<T> list);

    List<T> getAll();

    T save(T t);

    T update(T t);

    boolean delete(T t);


}
