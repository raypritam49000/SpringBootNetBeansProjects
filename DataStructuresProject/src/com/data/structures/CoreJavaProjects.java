package com.data.structures;

interface Java{
    default void show(){
        System.out.println("Hello!! This is Java Programming language");
    }
    
    static void display(){
         System.out.println("Hello!! This is Static Java Programming language");
    }
}


interface Python{
    
    default void show(){
        System.out.println("Hello!! This is Python Programming language");
    }
    
    static void display(){
         System.out.println("Hello!! This is Static Python Programming language");
    }
}

public class CoreJavaProjects implements Java,Python{

    @Override
    public void show(){
        Java.super.show();
        Java.display();
        Python.super.show();
        Python.display();
    }
    
    public static void main(String[] args) {
       CoreJavaProjects cj = new CoreJavaProjects();
       cj.show();
    }
    
}
