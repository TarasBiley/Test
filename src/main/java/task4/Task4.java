package task4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) throws FileNotFoundException {
        File fileName = new File("C:/task4.txt");

        List<Double> listElement = new ArrayList<>();
        Scanner scanner = new Scanner(fileName);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            listElement.add(Double.parseDouble(line));
        }

        double sum = 0;
        for (double el : listElement) {
            sum = sum + el;
        }

        double sr = sum / listElement.size();
        double min = 0;
        for (double el : listElement) {
            if (el > sr) {
                min = min + el - sr;
            }
            if (el < sr) {
                min = min + sr - el;
            }
        }
        System.out.println(min);
    }
}

