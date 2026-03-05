package validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputValidator {

    // nhập ID (int)
    public static int inputInt(Scanner sc, String title) {
        while (true) {
            try {
                System.out.print(title);
                int num = Integer.parseInt(sc.nextLine());

                if (num > 0) {
                    return num;
                }

                System.out.println("ID phải lớn hơn 0!");
            } catch (NumberFormatException e) {
                System.out.println("ID phải là số nguyên!");
            }
        }
    }


    // nhập name (không được rỗng)
    public static String inputString(Scanner sc, String title) {
        while (true) {
            System.out.print(title);
            String string = sc.nextLine().trim();

            if (!string.isEmpty()) {
                return string;
            }

            System.out.println("Tên không được để trống!");
        }
    }


    // nhập lựa chọn menu
    public static int inputMenu(Scanner sc, String title, int maxOption) {
        while (true) {
            try {
                System.out.print(title);
                int choice = Integer.parseInt(sc.nextLine());

                if (choice >= 1 && choice <= maxOption) {
                    return choice;
                }

                System.out.println("Chỉ được chọn từ 1 đến " + maxOption);
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số!");
            }
        }
    }


//    nhập ngày tháng năm
    public static LocalDate inputDate(Scanner sc, String message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (true) {
            try {
                System.out.print(message);
                String input = sc.nextLine();

                LocalDate date = LocalDate.parse(input, formatter);
                return date;

            } catch (DateTimeParseException e) {
                System.out.println("Ngày không hợp lệ! Nhập đúng định dạng dd/MM/yyyy");
            }
        }
    }

}
