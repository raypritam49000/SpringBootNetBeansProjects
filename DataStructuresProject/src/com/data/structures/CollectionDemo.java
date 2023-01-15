package com.data.structures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionDemo {

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Pritam Ray", "Ropar", "50000"));
        students.add(new Student(2, "Omi Verma", "Rail", "50000"));
        students.add(new Student(3, "Amit Kumar", "RajPura", "50000"));
        students.add(new Student(4, "Parveen Kumar", "RupNaagr", "50000"));
        students.add(new Student(5, "Montu Mehta", "Ropar", "50000"));
        students.add(new Student(6, "Ajit Sahani", "Ropar", "50000"));
        students.add(new Student(7, "Suarj Mehta", "Ropar", "50000"));
        students.add(new Student(8, "Gautam Sahani", "Ropar", "50000"));

        Iterator iterator = students.iterator();
        while (iterator.hasNext()) {
            System.out.println((Student) iterator.next());
        }
        System.out.println("<<<===========Forword Data fetch=========>>>>>");
        ListIterator listIterator = students.listIterator();
        while (listIterator.hasNext()) {
            System.out.println((Student) listIterator.next());
        }
        System.out.println("<<<===========PreViews Data fetch=========>>>>>");
        while (listIterator.hasPrevious()) {
            System.out.println((Student) listIterator.previous());
        }

        System.out.println(students);

        System.out.println("<<<<==========For Each Loop======>>>");
        students.forEach((student) -> {
            System.out.println(student);
        });

        System.out.println("<<<<==========For Each Functions ======>>>");
        students.forEach(student -> System.out.println(student));

        List<Integer> list = students.stream().map(student -> {
            return student.getId() * 2;
        }).collect(Collectors.toList());

        System.out.println(list);

        List<Integer> list1 = students.stream().map(student -> student.getId()).collect(Collectors.toList());
        System.out.println(list1);

        List<Integer> list5 = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            list5.add(i);
        }

        Stream<Integer> stream = list.stream();
        Integer[] evenNumbersArr = stream.filter(i -> i % 2 == 0).toArray(Integer[]::new);
        System.out.println(Arrays.toString(evenNumbersArr));

        List<Integer> myList = Arrays.asList(2, 4, 6, 8, 10, 12, 14, 16);
        myList.stream().reduce((x, y) -> {
            return x + y;
        }).ifPresent(s -> System.out.println(s));
        
        

    }
}
