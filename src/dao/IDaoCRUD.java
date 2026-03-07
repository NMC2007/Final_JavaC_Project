package dao;

import model.Course;

import java.util.List;
import java.util.Scanner;

public interface IDaoCRUD<T> {
    void insert(T t);
    void update(T t);
    void delete(int id);
    T findById(int id);
    List<T> findAll();
    List<T> sort(int sortOption);
}
