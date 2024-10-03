package com;

import java.util.List;
import java.util.Optional;

public class Person {
    private String name;
    private int age;
    private Optional<Person> spouse;
    private List<Person> children;

    public Person(String name, int age, Optional<Person> spouse, List<Person> children) {
        this.name = name;
        this.age = age;
        this.spouse = spouse;
        this.children = children;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Optional<Person> getSpouse() {
        return spouse;
    }

    public void setSpouse(Optional<Person> spouse) {
        this.spouse = spouse;
    }

    public List<Person> getChildren() {
        return children;
    }

    public void setChildren(List<Person> children) {
        this.children = children;
    }
}

