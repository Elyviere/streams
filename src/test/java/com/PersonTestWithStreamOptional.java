
package compackage com.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PersonTestWithStreamOptional {
@Test
    public void testFindPersonByNameWithStreamOptional() {
        List<Person> people = TestDataUtil.createTestPeople();

        Optional<Person> foundPerson = findPersonByName(people, "Bob");
        assertTrue(foundPerson.isPresent());
        assertEquals("Bob", foundPerson.get().getName());

        Optional<Person> notFoundPerson = findPersonByName(people, "David");
        assertTrue(notFoundPerson.isEmpty());
    }

    private Optional<Person> findPersonByName(List<Person> people, String name) {
        return people.stream()
                     .filter(person -> person.getName().equals(name))
                     .findFirst();
    }
}
