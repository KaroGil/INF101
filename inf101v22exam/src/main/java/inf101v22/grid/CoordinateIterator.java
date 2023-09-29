package inf101v22.grid;

import java.util.Iterator;

public class CoordinateIterator implements Iterator<Coordinate> {

    private int cols;
    private int rows;
    private int nextRow;
    private int nextCol;

    /**
     * An iterator which iterates over all the coordinates in a grid of the given
     * dimension. It will iterate through the coordinates in row-major order, moving
     * first across the rows, and then down to the next row.
     * 
     * @param rows number of rows to iterate over
     * @param cols number of columns to iterate over
     */
    public CoordinateIterator(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Dimensions of GridIterator must be positive");
        }
        this.rows = rows;
        this.cols = cols;
        this.nextRow = 0;
        this.nextCol = 0;
    }

    @Override
    public boolean hasNext() {
        return nextRow < rows;
    }

    @Override
    public Coordinate next() {
        Coordinate theNextCoordinate = new Coordinate(nextRow, nextCol);
        this.nextCol++;
        if (this.nextCol >= this.cols) {
            this.nextCol = 0;
            this.nextRow++;
        }
        return theNextCoordinate;
    }
}
