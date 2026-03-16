package business;

import java.util.Scanner;

public interface CRUDService<T> {
    void showData();
    T findById(int id);
    void createData(Scanner sc);
    void updateData(T t);
    void deleteData(int id);
    void sortData(int option);
}
