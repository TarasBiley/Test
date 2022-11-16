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

        int[] arrN = new int[n];
        int[] arrM = new int[m];

        boolean endArr = false;

        //Заполнение первого массива
        for (int i = 0; i < n; i++) {
            arrN[i] = i + 1;
        }

        //Заполнение второго массива
        for (int i = 0; i < m; i++) {
            arrM[i] = arrN[i];
        }

        while (endArr == false) {

            int[] arr = new int[m];

            arr = arrM;

            arr[0] = arrM[arrM.length - 1];

            if (arrN[0] != arrM[m - 1]) {

                for (int z = 1; z < m; z++) {

                    int previouesEl = arr[z - 1];
                    for (int j = 0; j < n; j++) {
                        int currentEl = arrN[j];
                        if (currentEl - previouesEl == 1) {
                            arr[z] = currentEl;
                        }
                        if (previouesEl == arrN[n - 1]) {
                            arr[z] = arrN[0];
                        }
                    }
                }

                arrM = arr;
                list.add(arrM[0]);
                endArr = false;
            }

            if (arrN[0] == arrM[m - 1]) {
                endArr = true;
            }
        }

        //Вывод ответа
        for (int el : list) {
            System.out.print(el);
        }

    }
}




