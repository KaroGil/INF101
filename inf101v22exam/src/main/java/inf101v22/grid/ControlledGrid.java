package inf101v22.grid;

import java.util.function.Function;

import inf101v22.observable.ControlledObservable;
import inf101v22.observable.Observable;
import inf101v22.utils.IteratorWrapper;

public class ControlledGrid<E> implements IGrid<E> {

    private int rows;
    private int cols;
    private IGrid<ControlledObservable<E>> grid;

    /**
     * Constructs a new grid with {@code rows} rows and {@code cols} columns.
     * Every element in the constructed grid is initially null.
     * 
     * @param rows number of rows, non-negative
     * @param cols number of columns, non-negative
     */
    public ControlledGrid(int rows, int cols) {
        this(rows, cols, null);
    }

    /**
     * Constructs a new grid with the given number of rows and columns.
     * Every element in the constructed grid will be set to {@code defaultValue}.
     * 
     * @param rows         number of rows, non-negative
     * @param cols         number of columns, non-negative
     * @param defaultValue the initial value for each cell
     */
    public ControlledGrid(int rows, int cols, E defaultValue) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new ListOfListsGrid<>(rows, cols, (forEachCoordinate) -> new ControlledObservable<E>(defaultValue));
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
        this.grid.get(coordinate).setValue(value);
    }

    @Override
    public E get(Coordinate coordinate) {
        return this.grid.get(coordinate).getValue();
    }

    /**
     * Gets an observable for the given coordinate. This allows
     * to add an observer for the specific coordinate in the grid.
     * Note that the observable will notify observers when the
     * object in the grid is replaced, but not if the object mutates.
     * 
     * @param coordinate to be observed
     * @return an observable for the value at the given coordinate
     */
    public Observable<E> getObservable(Coordinate coordinate) {
        return this.grid.get(coordinate);
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
