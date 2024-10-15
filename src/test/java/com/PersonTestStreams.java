package com;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PersonTestStreams {
  PersonUtilStreams util;
  List<Person> people;

  @BeforeEach
  void setup() {
    people = TestDataUtil.createTestPeople();
    util = new PersonUtilStreams(people);
  }

  @Test
  void testFindPersonByName() {
    Optional<Person> foundPerson = util.findPersonByName("Bob");
    assertTrue(foundPerson.isPresent());
    assertEquals("Bob", foundPerson.get().getName());

    Optional<Person> notFoundPerson = util.findPersonByName("David");
    assertTrue(notFoundPerson.isEmpty());
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
    Optional<Person> bob = util.findPersonByName("Bob");
    Optional<Person> alice = util.findPersonByName("Alice");
    assertAll(
        () -> assertTrue(bob.isPresent()),
        () -> assertTrue(alice.isPresent()));
    assertAll(() -> assertEquals(alice, bob.get().getSpouse().orElse(null)),
        () -> assertEquals(bob, bob.get().getSpouse().orElseThrow(() -> new NullPointerException()).getSpouse().get()));
  }

  @Test
  void testFindAllChildren() {
    List<Person> children = util.findAllChildren();
    assertEquals(2, children.size());
  }

  @Test
  void testFindAllChildrenWithoutUsingAge() {
    List<Person> children = util.findAllChildrenFromParents();
    assertEquals(2, children.size());
  }

  @Test
  void testFindAllChildrenAsSet() {
    List<Person> children = util.findAllChildren();
    assertEquals(2, children.size());
  }

  @Test
  void testFindAllSpouses() {
    assertEquals(2, util.findAllSpouses().size());
  }

  @Test
  void testGroupPeopleByAddress() {
    var actual = util.groupPeopleByAddress();
    assertTrue(actual.containsKey("Main street"));
    assertEquals(3, actual.get("Main street").size());
    assertTrue(actual.containsKey("Secondary street"));
    assertEquals(2, actual.get("Secondary street").size());
  }

  @Test
  void testGroupAgesByAddress() {
    var actual = util.groupAgesByAddress();
    assertTrue(actual.containsKey("Main street"));
    assertEquals(3, actual.get("Main street").size());
    assertTrue(actual.containsKey("Secondary street"));
    assertEquals(2, actual.get("Secondary street").size());
    assertTrue(actual.get("Secondary street").containsAll(List.of(2, 35)));
  }

  @Test
  void testGetOldestPerson() {
    Optional<Person> oldestPerson = util.getOldestPerson();
    assertTrue(oldestPerson.isPresent());
    assertEquals("Charlie", oldestPerson.get().getName());
  }

  @Test
  void testGetAverageAge() {
    assertEquals(18.4, util.getAverageAge());
  }

  @Test
  void testGetPersonCsv() {
    assertEquals(
        "Name,Age,Address\nAlice,30,Main street\nBob,25,Main street\nCharlie,35,Secondary street\nDaphne,2,Secondary street\nEric,0,Main street\n",
        util.getPersonCsv());
  }

  @Test
  void testSetAddressOfPeople() {
    util.setAddressOfPeople("New street");
    assertEquals("New street", people.get(0).getAddress());
  }
}
