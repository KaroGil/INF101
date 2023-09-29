package inf101v22.grid;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ListOfListsGridTest implements IGridTests {

    @Override
    public IGrid<String> getStringGrid(int rows, int cols) {
        return new ListOfListsGrid<>(rows, cols);
    }

    @Test
    void testToString() {
        ListOfListsGrid<Integer> grid = new ListOfListsGrid<>(2, 3,
                (coordinate) -> coordinate.col + coordinate.row * 3 + 1);
        assertEquals("ListOfListsGrid[[1, 2, 3], [4, 5, 6]]", grid.toString());
    }
}
