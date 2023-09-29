package inf101v22.grid;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import org.junit.jupiter.api.Test;

public interface IGridTests {

    /**
     * Many of the tests in this file are performed on multiple dimensons on the
     * grid. They are listed here.
     */
    public static enum GridDimensionsToTest {
        ONEONE(1, 1),
        ONETWO(1, 2),
        TWOONE(2, 1),
        TWOTWO(2, 2),
        THREETHREE(3, 3),
        THREEFIVE(3, 5),
        FIVETHREE(5, 3),
        FOURFOUR(4, 4);

        public int rows, cols;

        private GridDimensionsToTest(int rows, int cols) {
            this.rows = rows;
            this.cols = cols;
        }
    }

    /**
     * Helper method used to produce multiple grids of a variety of dimensions on
     * which to run the given test
     * 
     * @param <E>          type of element in grid
     * @param test         test to be run
     * @param gridProducer function which produce the grid on which to run tests
     */
    default <E> void testOnAllDimensions(Consumer<IGrid<E>> test, BiFunction<Integer, Integer, IGrid<E>> gridProducer) {
        for (GridDimensionsToTest dim : GridDimensionsToTest.values()) {
            IGrid<E> grid = gridProducer.apply(dim.rows, dim.cols);
            test.accept(grid);
        }
    }

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////// Grid producers //////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////

    /**
     * Any test that implements IGridTest must implement this method, which creates
     * a new grid of strings of the given dimensions.
     * 
     * @param rows number of rows
     * @param cols number of columns
     * @return a new grid
     */
    public IGrid<String> getStringGrid(int rows, int cols);

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    ////////////////////////////// The tests ////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////

    @Test
    default void testRowsAndColsMatch() {
        for (GridDimensionsToTest dim : GridDimensionsToTest.values()) {
            IGrid<String> grid = getStringGrid(dim.rows, dim.cols);
            assertEquals(dim.rows, grid.getRows());
            assertEquals(dim.cols, grid.getCols());
        }
    }

    @Test
    default void testSetGet() {
        testOnAllDimensions(IGridTests::testSetGet, this::getStringGrid);
    }

    static void testSetGet(IGrid<String> grid) {
        int rows = grid.getRows();
        int cols = grid.getCols();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Coordinate coordinate = new Coordinate(row, col);
                grid.set(coordinate, row + " " + col);
            }
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Coordinate coordinate = new Coordinate(row, col);
                String s = grid.get(coordinate);
                assertEquals(row + " " + col, s);
            }
        }
    }

    @Test
    default void testOutOfBounds() {
        testOnAllDimensions(IGridTests::testOutOfBounds, this::getStringGrid);
    }

    static void testOutOfBounds(IGrid<String> grid) {
        int rows = grid.getRows();
        int cols = grid.getCols();

        assertFalse(grid.coordinateIsOnGrid(new Coordinate(-1, 0)));
        assertFalse(grid.coordinateIsOnGrid(new Coordinate(rows, 0)));
        assertFalse(grid.coordinateIsOnGrid(new Coordinate(0, -1)));
        assertFalse(grid.coordinateIsOnGrid(new Coordinate(0, cols)));
        assertTrue(grid.coordinateIsOnGrid(new Coordinate(0, 0)));
        assertTrue(grid.coordinateIsOnGrid(new Coordinate(0, cols - 1)));
        assertTrue(grid.coordinateIsOnGrid(new Coordinate(rows - 1, 0)));
        assertTrue(grid.coordinateIsOnGrid(new Coordinate(rows - 1, cols - 1)));

        assertThrows(IndexOutOfBoundsException.class, () -> grid.set(new Coordinate(-1, 0), "X"));
        assertThrows(IndexOutOfBoundsException.class, () -> grid.set(new Coordinate(rows, 0), "X"));
        assertThrows(IndexOutOfBoundsException.class, () -> grid.set(new Coordinate(0, -1), "X"));
        assertThrows(IndexOutOfBoundsException.class, () -> grid.set(new Coordinate(0, cols), "X"));

        assertThrows(IndexOutOfBoundsException.class, () -> grid.get(new Coordinate(-1, 0)));
        assertThrows(IndexOutOfBoundsException.class, () -> grid.get(new Coordinate(rows, 0)));
        assertThrows(IndexOutOfBoundsException.class, () -> grid.get(new Coordinate(0, -1)));
        assertThrows(IndexOutOfBoundsException.class, () -> grid.get(new Coordinate(0, cols)));
    }

    @Test
    default void testCoordinatesIteration() {
        testOnAllDimensions(IGridTests::testCoordinatesIteration, this::getStringGrid);
    }

    static void testCoordinatesIteration(IGrid<String> grid) {
        int rows = grid.getRows();
        int cols = grid.getCols();

        List<Coordinate> coordinates = new ArrayList<>();
        for (Coordinate coordinate : grid.coordinates()) {
            coordinates.add(coordinate);
        }

        assertEquals(rows * cols, coordinates.size());
        for (int i = 0; i < coordinates.size(); i++) {
            Coordinate a = coordinates.get(i);
            for (int j = i + 1; j < coordinates.size(); j++) {
                Coordinate b = coordinates.get(j);
                assertFalse(a.row == b.row && a.col == b.col);
            }
        }
    }

    @Test
    default void testItemsIterable() {
        testOnAllDimensions(IGridTests::testCoordinatesIteration, this::getStringGrid);
    }

    static void testItemsIterable(IGrid<String> grid) {
        int rows = grid.getRows();
        int cols = grid.getCols();

        List<String> expectedValues = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Coordinate coordinate = new Coordinate(row, col);
                String s = row + " " + col;
                grid.set(coordinate, s);
                expectedValues.add(s);
            }
        }

        List<String> actualValues = new ArrayList<>();
        for (String s : grid.values()) {
            actualValues.add(s);
        }

        assertEquals(rows * cols, actualValues.size());
        for (String s : actualValues) {
            assertTrue(expectedValues.contains(s));
        }
        for (String s : expectedValues) {
            assertTrue(actualValues.contains(s));
        }
    }

    @Test
    default void testPrettyPrint() {
        IGrid<String> grid = getStringGrid(2, 3);
        grid.set(new Coordinate(0, 0), "1");
        grid.set(new Coordinate(0, 1), "2");
        grid.set(new Coordinate(0, 2), "3");
        grid.set(new Coordinate(1, 0), "a");
        grid.set(new Coordinate(1, 1), "b");
        grid.set(new Coordinate(1, 2), "c");
        assertEquals("1-2-3 | a-b-c", grid.toPrettyString("-", " | "));
        assertEquals("123\nabc", grid.toPrettyString("", "\n"));
    }
}
