package utils.tableConfig;

import model.StatisticalCourse;

import java.util.List;

public class StatisticalTableView {
    public static void printLisStatisticalCourse (List<StatisticalCourse> list) {
        System.out.println("------------------------------------------------------------------");
        System.out.printf("| %-8s | %-35s | %-13s |\n", "ID", "Course Name", "Total Student");
        System.out.println("------------------------------------------------------------------");

        for (StatisticalCourse c : list) {
            c.displayData();
        }

        System.out.println("------------------------------------------------------------------");
    }
}
