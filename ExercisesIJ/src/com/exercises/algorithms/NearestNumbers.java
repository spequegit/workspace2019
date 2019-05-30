package com.exercises.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class NearestNumbers {


    public NearestNumbers() {


        doThat();
        doThat();
        doThat();
        doThat();
        doThat();
    }

    private void doThat() {
        int[] list = new int[10];
        for (int i = 0; i < list.length; i++) {
            list[i] = new Random().nextInt(100);
        }
        show(list);

        int[] result = findNearestNumber(list);

        for (int i = 0; i < result[0]; i++) {
            System.out.print("\t");

        }
        show(result);
    }

    private void show(int[] list) {
        for (int i = 0; i < list.length; i++) {
            System.out.print(list[i] + "\t");
        }
        System.out.println();
    }


    public int[] findNearestNumber(int[] list) {


        int a = 0;
        int b = 0;
        int distance = 9999;

        for (int i = 0; i < list.length; i++) {

            if (i + 1 < list.length) {

                int dis = list[i] - list[i + 1];

                if (Math.abs(dis) < Math.abs(distance)) {
                    a = i;
                    b = i+1;
                    distance = dis;
                }
            }

        }


        return new int[]{a,b};
    }

    public static void main(String[] args) {
        new NearestNumbers();
    }
}
