package com.exercises.sorting;

import java.util.Arrays;
import java.util.List;

public class ExercisesIJ {

    public ExercisesIJ() {
        List<Integer> list = Arrays.asList(7, 3, 5, 4, 8, 2);
        System.out.println(list);
        doBubbleSort(list);
        System.out.println(list);
    }

    private List<Integer> doBubbleSort(List<Integer> list) {
        for (int j = 0; j < list.size(); j++) {
            for (int i = 0; i < list.size(); i++) {
                if (list.size() > i + 1) {
                    if (list.get(i) > list.get(i + 1)) {
                        int next = list.get(i + 1);
                        list.set(i + 1, list.get(i));
                        list.set(i, next);
                    }
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        new ExercisesIJ();
    }
}