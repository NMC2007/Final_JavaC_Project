import persentation.*;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        run(sc);
    }

    private static void run(Scanner sc) {
//        CourseManagerMenu.showMenu(sc);
//        StudentManagerMenu.showMenu(sc);
//        AdminManagerMenu.showMenu(sc);
//        EnrollmentManagerMenu.showMenu(sc);
        LoginMenu.showMenu(sc);
    }
}