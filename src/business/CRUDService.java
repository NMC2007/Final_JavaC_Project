package business;

import java.util.Scanner;

public interface CRUDService {
    void showData();
    void createData(Scanner sc);
    void updateData(Scanner sc);
    void deleteData(Scanner sc);
    void sortData(Scanner sc);
}
