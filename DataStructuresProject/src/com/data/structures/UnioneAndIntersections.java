package com.data.structures;

import java.util.HashSet;
import java.util.Set;

public class UnioneAndIntersections {

    public Set<Integer> unione(int[] arr1, int[] arr2) {
        Set<Integer> list = new HashSet<>();

        for (int i = 0; i < arr1.length; i++) {
            list.add(arr1[i]);
        }

        for (int j = 0; j < arr2.length; j++) {
            list.add(arr2[j]);
        }

        return list;
    }

    public void intersection(int[] arr1, int[] arr2) {
        Set<Integer> list = new HashSet<>();
        System.out.print("Intersections of Two Arrays : ");
        for (int i = 0; i < arr1.length; i++) {
            list.add(arr1[i]);
        }

        for (int j = 0; j < arr2.length; j++) {
            if (list.contains(arr2[j])) {
                System.out.print(" " + arr2[j]);
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr1 = {10, 30, 40, 60, 80, 300, 100};
        int[] arr2 = {200, 5, 40, 60, 50, 800, 300};
        UnioneAndIntersections unioneAndIntersections = new UnioneAndIntersections();
        System.out.println("Union of Two Arrays : " + unioneAndIntersections.unione(arr1, arr2));
        unioneAndIntersections.intersection(arr1, arr2);
    }

}
