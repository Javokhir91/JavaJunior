package ru.JavaJunior.lesson2.homework;

import ru.JavaJunior.lesson2.rand.RandomInteger;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class RandomDateProcessor {

    public static void processRandomDate(Object obj) {
        for (Field declaredField : obj.getClass().getDeclaredFields()) {
            RandomDate annotation = declaredField.getAnnotation(RandomDate.class);
//            if (annotation != null) {
            if (declaredField.isAnnotationPresent(RandomDate.class)) {
                long min = annotation.min();
                long max = annotation.max();

                declaredField.setAccessible(true);

                try {
                    declaredField.set(obj, new Date(ThreadLocalRandom.current().nextLong(min, max)));
                } catch (IllegalAccessException e) {
                    System.err.println("Не удалось подставить рандомное значение: " + e);
                }
            }
        }
    }
}
