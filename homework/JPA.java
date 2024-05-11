package ru.JavaJunior.lesson4.homework;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


class JPA {

    public static void main(String[] args) {

        Configuration configuration = new Configuration().configure();
        try(SessionFactory sessionFactory = configuration.buildSessionFactory()){
            Student student1 = new Student(1L, "Javokhit", true);
            Student student2 = new Student(2L, "Ivan", true);
            Student student3 = new Student(3L, "Kristina", true);
            Student student4 = new Student(4L, "Artem", false);

            Groups groups = new Groups(1L, "Java Development");


            Methods.insertUser(sessionFactory, student1);
            Methods.insertUser(sessionFactory, student2);
            Methods.insertUser(sessionFactory, student3);
            Methods.insertUser(sessionFactory, student4);

            Methods.selectAllUser(sessionFactory);
            Methods.findGeneral(sessionFactory , Groups.class, 72727L);
            Methods.findGroupById(sessionFactory, 72727L);
            Methods.findStudentById(sessionFactory, 72727L);
            Methods.findStudentByLogin(sessionFactory, "John");
            Methods.update(sessionFactory, "id", 72727L, "Jac");
            Methods.delete(sessionFactory, "id", 3L);
            Methods.selectCountRow(sessionFactory);


        }


    }

}
