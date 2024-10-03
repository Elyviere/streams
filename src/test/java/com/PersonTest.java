import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PersonTest {

    @Test
    public void testFindPersonByNameWithoutStreamOptional() {
        List<Person> people = TestDataUtil.createTestPeople();

        Person foundPerson = findPersonByName(people, "Bob");
        assertEquals("Bob", foundPerson.getName());

        Person notFoundPerson = findPersonByName(people, "David");
        assertNull(notFoundPerson);
    }

    private Person findPersonByName(List<Person> people, String name) {
        for (Person person : people) {
            if (person.getName().equals(name)) {
                return person;
            }
        }
        return null;
    }
}
