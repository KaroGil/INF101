package inf101v22.book;

import java.util.Arrays;

/**
 * This code is referenced in question 4. It is provided here for you
 * to investigate, and you may play with it as you want; but you should provide
 * the answer to this question in Inspera.
 */
public class Main {

    /*
     * The code below has a compilation error. Explain what the error is, and what
     * can be done to fix it without making any changes to this file.
     */
    public static void main(String[] args) {
        Book haroldPoter = new Book(Arrays.asList(
                new Page("Why doesn't Voldemort wear glasses?"),
                new Page("Nobody nose.")));

        for (Page page : haroldPoter) {
            String text = page.getText();
            System.out.println(text);
        }
    }
}
