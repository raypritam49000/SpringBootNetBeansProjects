package com.data.structures;

public class SortingOfArrays {
    
    public void sortingInAsc(int[] arr) {
        System.out.println("Ascending Order Sorting =====>>>> ");
        int temp = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
            
            System.out.print(" " + arr[i]);
        }
        System.out.println();
    }
    
    public void sortingInDescending(int[] arr) {
        System.out.println("Descending Order Sorting =====>>>> ");
        int temp = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] < arr[j]) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
            System.out.print(" " + arr[i]);
        }
        System.out.println();
    }
    
    public void sort(int[] arr) {
        int temp = 0;
        System.out.println("Sorting Element ====>>>>");
        for (int i = 1; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
            System.out.print(" " + arr[i]);
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        int[] arr = {50, 10, 40, 30, 80, 20};
        SortingOfArrays sortingOfArrays = new SortingOfArrays();
        sortingOfArrays.sortingInAsc(arr);
        sortingOfArrays.sortingInDescending(arr);
        int[] new_arr = new int[]{0, 1, 2, 2, 1, 1, 0, 2, 1, 2, 2, 0};
        sortingOfArrays.sort(new_arr);
        
        int[] neg_arr = new int[]{-1, 2, 4, -1, 0, 3, -10, -30, -20, -5, 100};
        sortingOfArrays.sort(neg_arr);
    }
}
