package ru.JavaJunior.lesson1.hw;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Program {
    public static void main(String[] args) {
        List<Homework.Department> departments = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            departments.add(new Homework.Department("Department #" + i));
        }

        List<Homework.Person> persons = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            int randomDepartmentIndex = ThreadLocalRandom.current().nextInt(departments.size());
            Homework.Department department = departments.get(randomDepartmentIndex);

            Homework.Person person = new Homework.Person();
            person.setName("Person #" + i);
            person.setAge(ThreadLocalRandom.current().nextInt(20, 65));
            person.setSalary(ThreadLocalRandom.current().nextInt(20_000, 100_000) * 1.0);
            person.setDepartment(department);

            persons.add(person);
        }

//        maxSalaryByDepartment(persons).entrySet().forEach(System.out::println);
        Homework.groupByDepartment(persons).entrySet().forEach(System.out::println);
//        groupPersonNamesByDepartment(persons).entrySet().forEach(System.out::println);
//        minSalaryPersons(persons).forEach(System.out::println);

    }
}
