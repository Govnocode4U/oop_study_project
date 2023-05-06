package org.utils;

import javax.persistence.*;


@MappedSuperclass()
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected int id;

    @Column(name = "full_name")
    protected String fullName;
    public Person() {

    }

    public Person(String fullName) {
        this.fullName = fullName;
    }



    public String getFullName() {
        return fullName;
    }

    public int getId() {
        return id;
    }
}
