package inf101v22.grid;

import java.util.List;
import java.util.function.Function;

import inf101v22.utils.IteratorWrapper;

import java.util.ArrayList;

public class ListOfListsGrid<E> implements IGrid<E> {

    private int rows;
    private int cols;
    private List<List<E>> board;

    /**
     * Constructs a new grid with {@code rows} rows and {@code cols} columns.
     * Every element in the constructed grid is initially null.
     * 
     * @param rows number of rows, non-negative
     * @param cols number of columns, non-negative
     */
    public ListOfListsGrid(int rows, int cols) {
        this(rows, cols, (c) -> null);
    }

    /**
     * Constructs a new grid with the given number of rows and columns.
     * Every element in the constructed grid will be set to {@code defaultValue}.
     * 
     * @param rows         number of rows, non-negative
     * @param cols         number of columns, non-negative
     * @param defaultValue the initial value for each cell
     */
    public ListOfListsGrid(int rows, int cols, E defaultValue) {
        this(rows, cols, (c) -> defaultValue);
    }

    /**
     * Constructs a new grid with the given number of rows and columns.
     * For each coordinate in the grid, the value will be set to the value
     * returned by the {@code valueProducer}.
     * 
     * @param rows         number of rows, non-negative
     * @param cols         number of columns, non-negative
     * @param defaultValue the initial value for each cell
     */
    public ListOfListsGrid(int rows, int cols, Function<Coordinate, E> valueProducer) {
        this.rows = rows;
        this.cols = cols;
        this.board = new ArrayList<>(rows);
        for (int r = 0; r < rows; r++) {
            List<E> row = new ArrayList<>(cols);
            for (int c = 0; c < cols; c++) {
                row.add(valueProducer.apply(new Coordinate(r, c)));
            }
            this.board.add(row);
        }
    }

    @Override
    public int getRows() {
        return this.rows;
    }

    @Override
    public int getCols() {
        return this.cols;
    }

    @Override
    public void set(Coordinate coordinate, E value) {
        this.board.get(coordinate.row).set(coordinate.col, value);
    }

    @Override
    public E get(Coordinate coordinate) {
        return this.board.get(coordinate.row).get(coordinate.col);
    }

    @Override
    public boolean coordinateIsOnGrid(Coordinate coordinate) {
        return coordinate.row >= 0 && coordinate.row < this.getRows() &&
                coordinate.col >= 0 && coordinate.col < this.getCols();
    }

    @Override
    public Iterable<Coordinate> coordinates() {
        return () -> new CoordinateIterator(this.getRows(), this.getCols());
    }

    @Override
    public Iterable<E> values() {
        return () -> new IteratorWrapper<>(this.coordinates().iterator(), this::get);
    }

    @Override
    public void fill(E value) {
        this.fill((coordinate) -> value);
    }

    @Override
    public void fill(Function<Coordinate, E> valueProducer) {
        for (Coordinate coordinate : this.coordinates()) {
            this.set(coordinate, valueProducer.apply(coordinate));
        }
    }

    @Override
    public String toPrettyString(String columnDelimiter, String rowDelimiter) {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < this.getRows(); row++) {
            if (row != 0) {
                sb.append(rowDelimiter);
            }
            for (int col = 0; col < this.getCols(); col++) {
                if (col != 0) {
                    sb.append(columnDelimiter);
                }
                sb.append(this.get(new Coordinate(row, col)));
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append("[[");
        sb.append(toPrettyString(", ", "], ["));
        sb.append("]]");
        return sb.toString();
    }
}
