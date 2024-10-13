public class Person {
  private Optional<Person> spouse;


    public static void main(String[] args) {
        findBy("Jack", null);
    }

  public void setSpouse(Person spouse) {
    Optional.ofNullable(spouse)
      .ifPresent(s -> this.spouse = s);
  }

  public Optional<Person> findBy(String name) {

    name.orElseThrow("John Doe");

    person = findByName(name)
              .orElseThrow(
                () -> new PersonNotFoundException(name));

    person = findByName(name).orElseGet(() -> new Person(name));


    if (person.isPresent()) {
      ...
    }

    person
      .flatMap(Person::getSpouse)
      .map(Person::getName)
      .orElse("Unknown");

    if (name.isPresent()) {
      if (age.isPresent()) {
        ...
      }
    }
  }
}
