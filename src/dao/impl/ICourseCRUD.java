package dao.impl;

import java.util.List;

public interface ICourseCRUD<T> {
    void insert(T t);
    void update(int id, T t);
    void delete(int id);
    T findById(int id);
    List<T> findAll();

}
