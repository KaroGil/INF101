package inf101v22.tasks123;

/**
 * This code is referenced in question 3. It is provided here for your
 * convenience, and you may play with it as you want; but the answer to this
 * question should be provided in Inspera.
 */
public class Main {

    public static void main(String[] args) {
        Person x = new Person("Tor", 1999);
        Person y = new Person("To" + "r", 1999);
        System.out.println(x == y);
    }
}
