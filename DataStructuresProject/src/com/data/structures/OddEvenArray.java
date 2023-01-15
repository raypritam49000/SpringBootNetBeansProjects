package com.data.structures;

import java.util.ArrayList;
import java.util.List;

public class OddEvenArray {

    public static void main(String[] args) {
        List<Integer> odd_even_set = new ArrayList<>();
        int[] odd_even_arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (Integer num : odd_even_arr) {
            if (num % 2 != 0) {
                odd_even_set.add(num);
            }
        }
        
        System.out.println(odd_even_set);
    }
}
