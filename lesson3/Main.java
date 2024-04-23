package ru.JavaJunior.lesson3;

/* Повторить все, что было на семинаре на таблице Student с полями
1. id - bigint
2. first_name - varchar(256)
3. second_name - varchar(256)
4. group - varchar(128)

Написать запросы:
1. Создать таблицу
2. Наполнить таблицу данными (insert)
3. Поиск всех студентов
4. Поиск всех студентов по имени группы

Доп. задания:
1. ** Создать таблицу group(id, name); в таблице student сделать внешний ключ на group
2. ** Все идентификаторы превратить в UUID

Замечание: можно использовать ЛЮБУЮ базу данных: h2, postgres, mysql, ...*/

import java.sql.*;
import java.util.UUID;

import static ru.JavaJunior.lesson3.Keeys.*;

public class Main {
    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            createSchema(connection);
            createTableGroup(connection);
            createTableStudent(connection);
            insertData(connection);
            selectAllStudents(connection);
            System.out.println("=".repeat(100));
            selectStudentsByGroup(connection, "Backend Development");
        } catch (SQLException e) {
            System.err.println("Ошибка в работе с БД: " + e.getMessage());
        }
    }

    static void selectStudentsByGroup(Connection connection, String groupName) throws SQLException {
        // Инъекция против злоумышленников!!
        String sql = """
                SELECT
                id, first_name, second_name, student_group_name
                FROM
                students
                WHERE student_group_name = ?
                """;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, groupName);
            ResultSet resultSet = statement.executeQuery("""
                    SELECT id, first_name, second_name, student_group_name
                    FROM DB_students.students WHERE student_group_name = '""" + groupName + "';");

            while (resultSet.next()) {
                UUID id = UUID.fromString(resultSet.getString("id"));
                String first_name = resultSet.getString("first_name");
                String second_name = resultSet.getString("second_name");
                String group = resultSet.getString("student_group_name");
                System.out.printf("Прочитана строка: %s, %s, %s, %s\n", id, first_name, second_name, group);
            }
        }
    }

    static void selectAllStudents(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("""
                    SELECT
                    `id`, `first_name`, `second_name`, `student_group_name`
                    FROM
                    `DB_students`.`students`;
                    """);
            while (resultSet.next()) {
                UUID id = UUID.fromString(resultSet.getString("id"));
                String first_name = resultSet.getString("first_name");
                String second_name = resultSet.getString("second_name");
                String group = resultSet.getString("student_group_name");
                System.out.printf("Прочитана строка: %s, %s, %s, %s\n", id, first_name, second_name, group);
            }
        }
    }

    static void insertData(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            int count = statement.executeUpdate("""
                    INSERT INTO `DB_students`.`groups` (`id`, `group_name`)
                            VALUES 	(UUID() ,'Frontend Development'),
                                    (UUID() ,'Backend Development'),
                                    (UUID() ,'Full-stack Development'),
                                    (UUID() ,'Data Science'),
                                    (UUID() ,'Cybersecurity');
                    """);
            System.out.println("Количество вставленных строк: " + count);
        }
        try (Statement statement = connection.createStatement()) {
            int count = statement.executeUpdate("""
                    INSERT INTO `DB_students`.`students` (`id`,`first_name`, `second_name`, `student_group_name`)
                    VALUES 	(UUID(), 'Anna', 'Kurd', 'Frontend Development'),
                            (UUID(), 'Javokhir', 'Rakhmatov', 'Backend Development'),
                            (UUID(), 'Ivan', 'Tretyakov', 'Backend Development'),
                            (UUID(), 'Artem', 'Nesterov', 'Full-stack Development'),
                            (UUID(), 'Victoria', 'Soloveva', 'Data Science'),
                            (UUID(), 'Kristi', 'Loginova', 'Backend Development'),
                            (UUID(), 'Alex', 'Kuzmich', 'Backend Development'),
                            (UUID(), 'Emil', 'Batkov', 'Cybersecurity');
                    """);
            System.out.println("Количество вставленных строк: " + count);
        }
    }

    static void createTableGroup(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("""
                    CREATE TABLE `DB_students`.`groups` (
                      	`id` VARCHAR(36),
                      	`group_name` VARCHAR(128) NOT NULL,
                      	PRIMARY KEY (`group_name`)
                      	);
                    """);
        }
    }

    static void createTableStudent(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("""
                    CREATE TABLE `DB_students`.`students` (
                      	 `id` VARCHAR(36) PRIMARY KEY,
                      	 `first_name` VARCHAR(256) NOT NULL,
                      	 `second_name` VARCHAR(256) NOT NULL,
                      	 `student_group_name` VARCHAR(36) NOT NULL,
                      	 FOREIGN KEY (`student_group_name`) REFERENCES `groups`(`group_name`)
                      	);
                    """);
        }
    }

    static void createSchema(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DROP DATABASE `DB_students` ;");
            statement.execute("CREATE SCHEMA `DB_students` ;");
        }
    }
}
