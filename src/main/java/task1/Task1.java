package task1;

import java.io.*;
import java.util.*;

public class Task1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();;

        File fileName = new File("C:/Users/79100/Desktop/task1.txt");

        //Лист в который записывается ответ
        List<Integer> list = new ArrayList<>();
        list.add(1);

        int[] arr1 = new int[n];
        int[] arr2 = new int[m];

        boolean a = false;

        //Заполнение первого массива
        for (int i = 0; i < n; i++) {
            arr1[i] = i + 1;
        }

        //Заполнение второго массива
        for (int i = 0; i < m; i++) {
            arr2[i] = arr1[i];
        }

        while (a == false) {

            int[] arr3 = new int[m];

            arr3 = arr2;

            arr3[0] = arr2[arr2.length - 1];

            if (arr1[0] != arr2[m - 1]) {

                for (int z = 1; z < m; z++) {

                    int x = arr3[z - 1];
                    for (int j = 0; j < n; j++) {
                        int y = arr1[j];
                        if (y - x == 1) {
                            arr3[z] = y;
                        }
                        if (x == arr1[n - 1]) {
                            arr3[z] = arr1[0];
                        }
                    }
                }

                arr2 = arr3;
                list.add(arr2[0]);


                a = false;
            }

            if (arr1[0] == arr2[m - 1]) {
                a = true;
            }
        }

        //Вывод ответа
        for (int el : list) {
            System.out.print(el);
        }

    }
}




