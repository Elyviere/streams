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

  public List<Person> findAllChildrenFromParents() {
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

  public Set<Person> findAllSpouses() {
    Set<Person> spouses = new HashSet<>();
    for (Person person : people) {
      if (person.getSpouseOrNull() != null) {
        spouses.add(person.getSpouseOrNull());
      }
    }
    return spouses;
  }

  public Map<String, List<Person>> groupPeopleByAddress() {
    Map<String, List<Person>> result = new HashMap<>();
    for (Person person : people) {
      result.computeIfAbsent(person.getAddress(), k -> new ArrayList<>()).add(person);
    }
    return result;
  }

  public Map<String, List<Integer>> groupAgesByAddress() {
    Map<String, List<Integer>> result = new HashMap<>();
    for (Person person : people) {
      result.computeIfAbsent(person.getAddress(), k -> new ArrayList<>()).add(person.getAge());
    }
    return result;
  }

  public double getAverageAge() {
    int sum = 0;
    for (Person person : people) {
      sum += person.getAge();
    }
    return (double) sum / people.size();
  }

  public String getPersonCsv() {
    StringBuilder sb = new StringBuilder("Name,Age,Address\n");
    for (Person person : people) {
      sb.append(person.toString());
    }
    return sb.toString();
  }

  public void setAddressOfPeople(String address) {
    String newAddress = address != null ? address : "Missing address";

    for (Person person : people) {
      person.setAddress(newAddress);
    }
  }
}
