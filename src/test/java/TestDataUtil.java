import java.util.List;

/*
 * c (-) a     a - b 
 *    |          |
 *    d          e
 */

public class TestDataUtil {
  public static List<Person> createTestPeople() {
    Person alice = new Person("Alice", 30, "Main street");
    Person bob = new Person("Bob", 25, "Main street");
    Person charlie = new Person("Charlie", 35, "Secondary street");
    Person daphne = new Person("Daphne", 2, "Secondary street");
    Person eric = new Person("Eric", 0, "Main street");

    alice.setAsSpouseWith(bob);
    alice.addChild(daphne);
    alice.addChild(eric);
    bob.addChild(eric);
    charlie.addChild(daphne);
    return List.of(alice, bob, charlie, daphne, eric);
  }
}
