package com;

import java.util.*;
//

public class PersonUtil {
  private List<Person> people;

  public PersonUtil(List<Person> people) {
    this.people = people;
  }

  public Person findPersonByName(String name) {
    for (Person person : people) {
      if (person.getName().equals(name)) {
        return person;
      }
    }
    return null;
  }

  public List<Person> findPeopleByAgeRange(int min, int max) {
    List<Person> result = new ArrayList<>();
    for (Person person : people) {
      if (person.getAge() >= min && person.getAge() <= max) {
        result.add(person);
      }
    }
    return result;
  }

  public List<Person> findAllChildren() {
    List<Person> children = new ArrayList<>();
    for (Person person : people) {
      if (person.getAge() < 18) {
        children.add(person);
      }
    }
    return children;
  }

  public List<Person> findAllChildrenFromTheirParent() {
    Set<Person> children = new HashSet<>();
    for (Person person : people) {
      children.addAll(person.getChildren());
    }
    return new ArrayList<>(children);
  }

  public Set<Person> findAllChildrenAsSet() {
    Set<Person> children = new HashSet<>();
    for (Person person : people) {
      children.addAll(person.getChildren());
    }
    return children;
  }

  public Map<String, Person> findPeopleByAddress() {
    Map<String, Person> result = new HashMap<>();
    for (Person person : people) {
      result.put(person.getAddress(), person);
    }
    return result;
  }

  public Map<String, Integer> findAgesByAddress() {
    Map<String, Integer> result = new HashMap<>();
    for (Person person : people) {
      result.put(person.getAddress(), person.getAge());
    }
    return result;
  }

  public String getPersonCsv() {
    StringBuilder sb = new StringBuilder("Name,Age,Address\n");
    for (Person person : people) {
      sb.append(person.toString());
    }
    return sb.toString();
  }
}
