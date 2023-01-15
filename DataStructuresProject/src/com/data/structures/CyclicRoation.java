package com.data.structures;

import java.util.Arrays;

public class CyclicRoation {

    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(Arrays.toString(arr));
        
        int x = arr[arr.length - 1];
        
        System.out.println(x);
        for (int i = arr.length - 1; i > 0; i--) {
            System.out.println(arr[i] + " " + arr[i - 1]);
            arr[i] = arr[i - 1];
        }

        arr[0] = x;

        System.out.println(Arrays.toString(arr));

    }
}
