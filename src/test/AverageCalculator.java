package test;

import java.io.*;
import java.util.Scanner;

public class AverageCalculator {
    public static void main(String[] args) throws FileNotFoundException {
        calculateAverage("C:/Eroge/insert.txt");
        calculateAverage("C:/Eroge/search.txt");
        calculateAverage("C:/Eroge/update.txt");
        calculateAverage("C:/Eroge/query.txt");
        calculateAverage("C:/Eroge/delete.txt");
    }

    public static void calculateAverage(String filename) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filename));

        long totalOps = 0;
        long totalTime = 0;
        int count = 0;

        while (sc.hasNextLine()) {
            String[] data = sc.nextLine().split(" ");
            totalTime += Long.parseLong(data[0]);
            totalOps += Integer.parseInt(data[1]);
            count++;
        }

        System.out.println("Average for " + filename);
        System.out.println("Time: " + (totalTime / (double) count) + " ns");
        System.out.println("Operations: " + (totalOps / (double) count));
    }
}