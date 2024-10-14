package com;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Person {
  private String name;
  private int age;
  private Person spouse;
  private List<Person> children = new ArrayList<>();
  private String address;

  public Person(String name, int age, String address) {
    this.name = name;
    this.age = age;
    this.address = address;
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

  public Person getSpouseOrNull() {
    return spouse;
  }

  public Optional<Person> getSpouse() {
    return Optional.ofNullable(spouse);
  }

  public void setAsSpouseWith(Person spouse) {
    this.spouse = spouse;
    if (spouse != null) {
      spouse.spouse = this;
    }
  }

  public List<Person> getChildren() {
    return children;
  }

  public void addChild(Person child) {
    children.add(child);
  }

  public void removeChild(Person child) {
    children.remove(child);
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String toString() {
    return String.format("%s,%d,%s\n", name, age, address);
  }
}
