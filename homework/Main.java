package ru.JavaJunior.lesson2.homework;

import java.util.Date;

public class Main {
    public static void main(String[] args) {

        MyClass myClass = new MyClass(null);
        RandomDateProcessor.processRandomDate(myClass);
        System.out.println(myClass.date);
    }
    private static class MyClass {
        @RandomDate()
        private Date date;

        private MyClass(Date date) {
            this.date = date;
        }
    }
}
