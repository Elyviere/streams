package com;

import java.util.*;
import java.util.stream.*;

public class PersonUtilStreams {
  List<Person> people;

  public PersonUtilStreams(List<Person> people) {
    this.people = people;
  }

  Optional<Person> findPersonByName(String name) {
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
  public List<Person> findAllChildrenWithoutUsingAge() {
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
  public Map<String, Person> findPeopleByAddress() {
    return people.stream()
        .collect(Collectors.toMap(Person::getAddress, person -> person));
  }

  //
  //
  //
  public Map<String, Integer> findAgesByAddress() {
    return people.stream()
        .collect(Collectors.toMap(Person::getAddress, Person::getAge));
  }

  //
  //
  //
  public String getPersonCsv() {
    return people.stream()
        .map(Person::toString)
        .reduce(new StringBuilder("Name,Age,Address\n"), (sb, person) -> sb.append(person), StringBuilder::append)
        .toString();
  }
  //
}
