package com.data.structures;

public class MaxAndMinElementOfArrays {

    static class Pair {
        int max;
        int min;
    }

    static Pair getMinMax(int[] arr, int array_size) {
        Pair minmax = new Pair();

        if (array_size == 1) {
            minmax.max = arr[0];
            minmax.min = arr[0];
            return minmax;
        }

        if (arr[0] > arr[1]) {
            minmax.max = arr[0];
            minmax.min = arr[1];
        } else {
            minmax.max = arr[1];
            minmax.min = arr[0];
        }

        for (int i = 2; i < array_size; i++) {
            if (arr[i] > minmax.max) {
                minmax.max = arr[i];
            } else if (arr[i] < minmax.min) {
                minmax.min = arr[i];
            }
        }
        return minmax;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{20, 80, 30, 40, 203, 342, 244, 10, 5};
        int arr_size = arr.length;
        Pair minmax = MaxAndMinElementOfArrays.getMinMax(arr, arr_size);
        System.out.println("Minimum Element is : " + minmax.min);
        System.out.println("Maximum Element is : " + minmax.max);
    }
}
