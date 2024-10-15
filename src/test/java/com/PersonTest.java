package com;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PersonTest {
  PersonUtil util;
  List<Person> people;

  @BeforeEach
  void setup() {
    people = TestDataUtil.createTestPeople();
    util = new PersonUtil(people);
  }

  @Test
  void findSpousesName() {
    Person bob = util.findPersonByName("Bob");
    String spouseName = util.findSpousesName(bob);
    assertEquals("Alice", spouseName);
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
    assertEquals(3, actual.get("Main street").size());
    assertEquals(2, actual.get("Secondary street").size());
  }

  @Test
  void testGroupAgesByAddress() {
    var actual = util.groupAgesByAddress();
    assertEquals(3, actual.get("Main street").size());
    assertEquals(2, actual.get("Secondary street").size());
    assertTrue(actual.get("Secondary street").containsAll(List.of(2, 35)));
  }

  @Test
  void testGetOldestPerson() {
    assertEquals("Charlie", util.getOldestPerson().getName());
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
