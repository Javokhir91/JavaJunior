package ru.JavaJunior.lesson2.homework.RandomDateAPI;

import java.lang.reflect.Field;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class RandomDateProcessor {
//    long cubed = (long) Math.pow(442.08378, 3); // 86400000
    private static final long MILLISECONDS_DAY = 86400000L;

    public static void processRandomDate(Object obj) {
        for (Field declaredField : obj.getClass().getDeclaredFields()) {
            if (declaredField.isAnnotationPresent(RandomDate.class)) {
                RandomDate annotation = declaredField.getAnnotation(RandomDate.class);

                long min = annotation.min();
                long max = annotation.max();

                declaredField.setAccessible(true);

                try {
                    if (declaredField.getType().equals(Date.class)) {
                        declaredField.set(obj, new Date(ThreadLocalRandom.current().nextLong(min, max)));
                    } else if (declaredField.getType().equals(LocalDateTime.class)) {
                        declaredField.set(obj, LocalDateTime.ofEpochSecond(
                                ThreadLocalRandom.current()
                                        .nextLong(min, max) / 1000, 0, ZoneOffset.ofHours(-3)));

                    } else if (declaredField.getType().equals(LocalDate.class)) {
                        declaredField.set(obj, LocalDate.ofEpochDay(ThreadLocalRandom.current()
                                .nextLong(min, max) / MILLISECONDS_DAY));
                    } else if (declaredField.getType().equals(Instant.class)) {
                        declaredField.set(obj, Instant.ofEpochSecond(ThreadLocalRandom.current()
                                .nextLong(min, max) / 1000));
                    }
                } catch (IllegalAccessException e) {
                    System.err.println("Не удалось подставить рандомное значение: " + e);
                }
            }
        }
    }
}
