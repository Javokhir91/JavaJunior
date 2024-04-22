
package ru.JavaJunior.lesson2.homework;

import ru.JavaJunior.lesson2.homework.RandomDateAPI.RandomDate;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class MyClass {
    @RandomDate()
    private Date date;

    public String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }

    @RandomDate()
    private LocalDateTime localDateTime;

    public String getLocalDateTime() {
        Date dateFormat = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return simpleDateFormat.format(dateFormat);
    }

    @RandomDate()
    //Integer не пройдет в форе , внутри класса RandomDateProcessor, так как метод работает только с типами даты
    private Integer integer = -1;

    @RandomDate(min = 1262304000000L, max = 1293753600000L)
    private Date date1;

    // Создал геттеры что бы форматировать даты день-месяй-год
    public String getDate1() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date1);
    }

    // String так же не войдет в условию который прописан в классе RandomProcessor и по этому вернет изначальную значение
    @RandomDate()
    private String str = "str";

    // Тут все аналогично с выше приведением в формате SimpleDateTime
    @RandomDate()
    private LocalDate localDate;

    public String getLocalDate() {
        Date dateFormat = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return simpleDateFormat.format(dateFormat);
    }

    @RandomDate()
    private Instant instant;

    public String getInstant() {
        Date instantFormat = Date.from(instant.atZone(ZoneId.systemDefault()).toInstant());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(instantFormat);
    }
}
