package com;

import java.util.*;
import java.util.stream.*;

public class PersonUtilStreamsExample {
  List<Person> people;

  public PersonUtilStreamsExample(List<Person> people) {
    this.people = people;
  }

  public Optional<Person> findPersonByName(String name) {
    return people.stream()
        .filter(person -> person.getName().equals(name))
        .findAny();
  }

  //
  //
  //
  public List<Person> findPeopleByAgeRange(int min, int max) {
    return people.stream()
        .filter(person -> person.getAge() >= min && person.getAge() <= max)
        .toList();
  }

  //
  //
  //
  //
  public List<Person> findAllChildren() {
    return people.stream()
        .filter(person -> person.getAge() < 18)
        .toList();
  }

  //
  //
  //
  //
  public List<Person> findAllChildrenFromParents() {
    return people.stream()
        .flatMap(person -> person.getChildren().stream())
        .distinct()
        .toList();
  }

  //
  public Set<Person> findAllChildrenAsSet() {
    return people.stream()
        .filter(person -> person.getAge() < 18)
        .collect(Collectors.toSet());
  }

  //
  //
  public Set<Person> findAllSpouses() {
    return people.stream()
        .map(Person::getSpouse)
        .flatMap(Optional::stream)
        .collect(Collectors.toSet());
  }

  //
  //
  //
  public Map<String, List<Person>> groupPeopleByAddress() {
    return people.stream()
        .collect(Collectors.groupingBy(Person::getAddress));
  }

  //
  //
  //
  public Map<String, List<Integer>> groupAgesByAddress() {
    return people.stream()
        .collect(Collectors.groupingBy(
            Person::getAddress,
            Collectors.mapping(Person::getAge, Collectors.toList())));
  }

  //
  public Optional<Person> getOldestPerson() {
    return people.stream().max(Comparator.comparing(Person::getAge));
  }

  //
  //
  //
  //
  //
  //
  public double getAverageAge() {
    return people.stream()
        .mapToInt(Person::getAge)
        .average()
        .orElse(0);
  }

  //
  public String getPersonCsv() {
    return people.stream()
        .map(Person::toString)
        .reduce(new StringBuilder("Name,Age,Address\n"), (sb, person) -> sb.append(person), StringBuilder::append)
        .toString();
  }

  //
  //
  //
  public void setAddressOfPeople(String address) {
    String newAddress = address != null ? address : "Missing address";

    people.stream()
        .forEach(person -> person.setAddress(newAddress));
  }
}
