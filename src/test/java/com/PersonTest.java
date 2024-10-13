package com;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PersonTest {
  PersonUtil util;

  @BeforeEach
  void setup() {
    util = new PersonUtil(TestDataUtil.createTestPeople());
  }

  @Test
  void testFindPersonByName() {
    Person foundPerson = util.findPersonByName("Bob");
    assertEquals("Bob", foundPerson.getName());

    Person notFoundPerson = util.findPersonByName("David");
    assertNull(notFoundPerson);
  }

  @Test
  void testFindPersonByAge() {
    List<Person> foundPeople = util.findPeopleByAgeRange(0, 18);
    assertEquals(2, foundPeople.size());

    List<Person> notFoundPeople = util.findPeopleByAgeRange(150, 200);
    assertTrue(notFoundPeople.isEmpty());
  }

  @Test
  void testSpousesAreLinked() {
    Person bob = util.findPersonByName("Bob");
    Person alice = util.findPersonByName("Alice");
    assertEquals(alice, bob.getSpouseOrNull());
    assertEquals(bob, bob.getSpouseOrNull().getSpouseOrNull());
  }

}
