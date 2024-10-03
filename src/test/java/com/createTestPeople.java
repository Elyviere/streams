package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestDataUtil {

    public static List<Person> createTestPeople() {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 30, Optional.empty(), new ArrayList<>()));
        people.add(new Person("Bob", 25, Optional.empty(), new ArrayList<>()));
        people.add(new Person("Charlie", 35, Optional.empty(), new ArrayList<>()));
        return people;
    }
}
