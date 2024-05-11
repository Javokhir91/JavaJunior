package ru.JavaJunior.lesson4.homework;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "groupStudents")
public class Groups {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String groupStudent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupStudent() {
        return groupStudent;
    }

    public void setGroupStudent(String groupStudent) {
        this.groupStudent = groupStudent;
    }

    public Groups(Long id, String groupStudent) {
        this.id = id;
        this.groupStudent = groupStudent;
    }

    public Groups(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Groups{" +
                "id=" + id +
                ", groupStudent='" + groupStudent + '\'' +
                '}';
    }
}
