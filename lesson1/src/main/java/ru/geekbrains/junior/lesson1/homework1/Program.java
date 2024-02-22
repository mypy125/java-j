package ru.geekbrains.junior.lesson1.homework1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        //Напишите программу, которая использует Stream API для обработки списка чисел.
        //Программа должна вывести на экран среднее значение всех четных чисел в списке.

        List<Integer> listInts = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        double average = listInts.stream()
                .filter(f -> f % 2 == 0)
                        .mapToInt(Integer::intValue)
                                .average()
                                        .orElse(0);

        System.out.println("average: "+average);


        double summer = sumAverage(listInts);
        System.out.println("average: "+ summer);

    }

    public static double sumAverage(List<Integer> listInts){
        int count = 0; //5
        double sum = 0;
        for (Integer ints: listInts) {
            if(ints % 2 == 0){
                count++;
                sum += ints;
            }
        }
        return sum/count;
    }


}
