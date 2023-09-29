package inf101v22.grid;

import java.util.function.Function;

public interface IGrid<E> {

    /** Gets the number of rows in grid. */
    int getRows();

    /** Gets the number of columns in the grid. */
    int getCols();

    /**
     * Sets the value of a postion in the grid. A subsequent call to get with an
     * equal coordinate as argument will return the value which was set. The method
     * will overwrite any previous value that was stored at the location.
     * 
     * @param coordinate the location in which to store the value
     * @param value      the new value
     * @throws IndexOutOfBoundsException if the coordinate is not within bounds of
     *                                   the grid
     */
    void set(Coordinate coordinate, E value);

    /**
     * Gets the current value at the given coordinate.
     * 
     * @param coordinate the location to get
     * @return the value stored at the coordinate
     * @throws IndexOutOfBoundsException if the coordinate is not within bounds of
     *                                   the grid
     */
    E get(Coordinate coordinate);

    /**
     * Reports whether the coordinate is within bounds for this grid.
     * 
     * @param coordinate coordinate to check
     * @return true if the coordinate is within bounds, false otherwise
     */
    boolean coordinateIsOnGrid(Coordinate coordinate);

    /** Gets an iterable of all the coordinates present in this gird. */
    Iterable<Coordinate> coordinates();

    /** Gets an iterable of all the values present in this grid. */
    Iterable<E> values();

    /**
     * Fills the board with the given value. After the call, every coordinate on the
     * grid will contain the given object.
     * 
     * @param value
     */
    void fill(E value);

    /**
     * Fills the board with values given by the value-producing function. The input
     * provided to the function is the coordinate it will fill.
     * 
     * For example, if you have defined a method
     * 
     * <pre>
     * class MyClass {
     *     static String myFunction(Coordinate coordinate) {
     *         return coordinate.toString();
     *     }
     * }
     * </pre>
     * 
     * you may use this method to fill in a grid {@code IGrid<String> myGrid}
     * as follows: {@code myGrid.fill(MyClass::myFunction)}. Then the grid will be
     * filled with String representations of the different coordinates.
     * </pre>
     * 
     * @param value
     */
    void fill(Function<Coordinate, E> valueProducer);

    /**
     * Creates a pretty String representation of this grid. Will use the toString()
     * -methods of the elements in the grid, organized in row-major order and
     * combined with the delimiters provied as arguments. For example, if
     * {@code myGrid} is a 2x3 IGrid of the Integers one through six organized in
     * row-major order, then a call to
     * {@code myGrid.toPrettyString("-", " | ")} will yield the String
     * {@code "1-2-3 | 4-5-6"}.
     * 
     * @param columnDelimiter will be inserted between consecutive elements in the
     *                        same row
     * @param rowDelimiter    will be inserted between rows
     * @return pretty representation of this grid
     */
    String toPrettyString(String columnDelimiter, String rowDelimiter);
}
