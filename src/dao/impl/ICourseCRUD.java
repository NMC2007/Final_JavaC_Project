package dao.impl;

import java.util.List;
import java.util.Scanner;

public interface ICourseCRUD<T> {
    void insert(T t);
    void update(int id, Scanner sc);
    void delete(int id, Scanner sc);
    T findById(int id);
    List<T> findAll();
}
