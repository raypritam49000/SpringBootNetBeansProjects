package com.data.structures;

import java.util.Scanner;

public class IfElseLogic {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter any number : ");
        int num = sc.nextInt();
        
        if(num%2!=0){
            System.out.println("Weird");
        }
        else{
            
            for(int i=2;i<=5;i++){
                if(num==i){
                    System.out.println("Not Weird");
                    break;         
                }
            }
            
            for(int i=6;i<=20;i++){
                if(num==i){
                    System.out.println("Weird");
                }
            }
            
            if(num>20){
                System.out.println("Not Weird");
            }
            
            
        }
    }
}
