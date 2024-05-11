package ru.JavaJunior.lesson4.homework;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;


public class Methods {

    public static void delete(SessionFactory sessionFactory, String id, Long userid) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "from Student where id = :id";
            Query<Student> query = session.createQuery(hql, Student.class);
            Student userUpdate = query.setParameter(id, userid).getSingleResult();
            session.beginTransaction();
            session.remove(userUpdate);
            session.getTransaction().commit();
        }
    }

    public static void update(SessionFactory sessionFactory, String param, Long argv, String update) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "from Student where id = :id";
            Query<Student> query = session.createQuery(hql, Student.class);
            Student userUpdate = query.setParameter(param, argv).getSingleResult();
            userUpdate.setLogin(update);
            Transaction transaction = session.beginTransaction();
            session.merge(userUpdate);
            transaction.commit();
        }
    }


    public static void insertUser(SessionFactory sessionFactory, Student student) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(student); // INSERT
            transaction.commit();
        }
    }

    public static void deleteUser(SessionFactory sessionFactory, String update, Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            String hql = "from Student where id = :id";
            Query<Student> query = session.createQuery(hql, Student.class);
            Student userUpdate = query.setParameter("id", id).getSingleResult();
            userUpdate.setLogin(update);
            transaction = session.beginTransaction();
            session.merge(userUpdate);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }

    }

    public static void selectUser(SessionFactory sessionFactory, Long id) {
        try (Session session = sessionFactory.openSession()) {
            Student savedStudent = session.find(Student.class, id);
            System.out.println(savedStudent);
        }
    }

    public static void selectAllUser(SessionFactory sessionFactory) {
        // Вывод всех юзеров
        try (Session session = sessionFactory.openSession()) {
            String hql = "select u from Student u";
            Query<Student> query = session.createQuery(hql, Student.class);
            List<Student> userList = query.getResultList();
            for (Student usr : userList) {
                System.out.println(usr);
            }
        }
    }

    public static void findGeneral(SessionFactory sessionFactory, Class<?> clazz, Long id) {
        try (Session session = sessionFactory.openSession()) {
            System.out.println(session.find(clazz, id));
        }
    }

    public static void findStudentById(SessionFactory sessionFactory, Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.find(Student.class, id);
        }
    }

    public static void findGroupById(SessionFactory sessionFactory, Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.find(Groups.class, id);
        }
    }

    public static void findStudentByLogin(SessionFactory sessionFactory, String name) {
        try (Session session = sessionFactory.openSession()) {
            // hql - запрос
            List<Student> userList = session.createQuery(
                    "select u from Student u where u.name in ('" + name + "')", Student.class).getResultList();
            System.out.println(userList);
        }
    }

    public static void selectCountRow(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            System.out.println(session.createNativeQuery(
                    "select count(1) from users", Long.class).getSingleResult());
        }
    }


}
