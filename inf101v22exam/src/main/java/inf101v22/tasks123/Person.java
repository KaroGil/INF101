package inf101v22.tasks123;

/**
 * This class is referenced in question 2 and 3. It is provided here for your
 * convenience, and you may play with it as you want; but the answers to these
 * questions should be provided in Inspera.
 */
public class Person {

    private String name;
    private int yearOfBirth;

    public Person(String name, int yearOfBirth) {
        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }

    public String getName() {
        return this.name;
    }

    public int getYearOfBirth() {
        return this.yearOfBirth;
    }
}
