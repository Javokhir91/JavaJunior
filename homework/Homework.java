package ru.JavaJunior.lesson2.homework;

import ru.JavaJunior.lesson2.homework.RandomDateAPI.RandomDateProcessor;

public class Homework {

    /*
     *  1. Создать аннотацию RandomDate со следующими возможностями:
     *  1.1 Если параметры не заданы, то в поле должна вставляться рандомная дата в диапазоне min, max.
     *  1.2 Аннотация должна работать с полем типа java.util.Date.
     *  1.3 Должна генерить дату в диапазоне [min, max)
     *  1.4 ** Научиться работать с полями LocalDateTime, LocalDate, Instant, ... (классы java.time.*)

     *  Реализовать класс RandomDateProcessor по аналогии с RandomIntegerProcessor, который обрабатывает аннотацию.
     */
    public static void main(String[] args) {

        MyClass myClass = new MyClass();
        RandomDateProcessor.processRandomDate(myClass);

        System.out.println(myClass.getDate() + " <- Type Date");
        System.out.println(myClass.getDate1() + " <- Type Date with parameters(min, max)");
        System.out.println(myClass.getLocalDateTime() + " <- Type LocalDateTime");
        System.out.println(myClass.getLocalDate() + " <- Type LocalDate");
        System.out.println(myClass.getInstant() + " <- Type Instant");

    }
}
