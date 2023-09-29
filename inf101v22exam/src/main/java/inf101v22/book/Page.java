package inf101v22.book;

/**
 * This code is referenced in question 4. It is provided here for you
 * to investigate, and you may play with it as you want; but the answer to this
 * question should be provided in Inspera.
 */
public class Page {

    /** Max number of characters on a single page. */
    private static final int MAX_TEXT_SIZE = 3000;

    /** Text on the page. */
    private String text;

    /** Create a new page with the provided text. */
    public Page(String text) {
        if (text.length() > MAX_TEXT_SIZE)
            throw new IllegalArgumentException("Too much text");

        this.text = text;
    }

    /** Gets the text on this page. */
    public String getText() {
        return text;
    }
}
