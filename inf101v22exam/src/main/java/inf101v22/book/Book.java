package inf101v22.book;

import java.util.List;

/**
 * This code is referenced in question 4. It is provided here for you
 * to investigate, and you may play with it as you want; but the answer to this
 * question should be provided in Inspera.
 */
public class Book {

    private List<Page> pages;

    public Book(List<Page> pages) {
        this.pages = pages;
    }

    public int length() {
        return pages.size();
    }

    public Page getPage(int pageNumber) {
        if (pageNumber >= length())
            throw new IndexOutOfBoundsException("Index out of bounds");

        return pages.get(pageNumber);
    }
}
