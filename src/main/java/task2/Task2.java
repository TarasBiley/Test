package task2;

import com.sun.jdi.CharType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {

        float x = 0;
        float y = 0;
        float r = 0;
        float x1 = 0;
        float y1 = 0;

        String xy = null;

        List<String> listXY = new ArrayList<>();

        File fileName1 = new File("C:/file1.txt");

        //Чтение из файла 1
        List<String> listElement1 = new ArrayList<>();
        try (Scanner scanner = new Scanner(fileName1)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                listElement1.add(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        //Присвоение значения r
        int countR = 0;
        for (String el : listElement1) {
            if (countR == 0) {
                xy = el;
            }
            if (countR == 1) {
                r = Float.parseFloat(el);
            }
            countR++;
        }

        //Присвоение значени1 x и y
        int countXY = 0;
        for (String el : xy.split(" ")) {
            if (countXY == 0) {
                x = Float.parseFloat(el);
            }
            if (countXY == 1) {
                y = Float.parseFloat(el);
            }
            countXY++;
        }

        //Чтение из файла 2
        File fileName2 = new File("C:/file2.txt");
        List<String> listElement2 = new ArrayList<>();

        try (Scanner scanner = new Scanner(fileName2)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                listElement2.add(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        int pointNumber =0;
            for (String listX1Y1 : listElement2) {
                if(pointNumber<=99) {

                int count2 = 0;
                for (String e : listX1Y1.split(" ")) {

                    if (count2 == 0) {
                        x1 = Float.parseFloat(e);
                    }
                    if (count2 == 1) {
                        y1 = Float.parseFloat(e);
                    }
                    count2++;
                }

                if ((x1 - x) * (x1 - x) + (y1 - y) * (y1 - y) < (r * r)) {
                    System.out.println(pointNumber + " - точка внутри");
                }

                if ((x1 - x) * (x1 - x) + (y1 - y) * (y1 - y) == (r * r)) {
                    System.out.println(pointNumber + " - точка лежит на окружности");
                }

                if ((x1 - x) * (x1 - x) + (y1 - y) * (y1 - y) > (r * r)) {
                    System.out.println(pointNumber + " - точка снаружи");
                }
                    pointNumber++;
            }
        }

    }
}
