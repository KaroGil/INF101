package inf101v22.grid;

import java.util.*;

public class Grid<E> implements IGrid<E>{

    //class variables.
    private List<E> cells;
    private int rows, cols;

    //constructor for two values.
    public Grid(int rows,int cols){
        this.rows = rows;
        this.cols = cols;

        cells = new ArrayList<E>(cols * rows);

		for (int i = 0; i < cols * rows; ++i) {
			cells.add(null);
		}
    }

    //constructor for three values.
    public Grid(int rows, int cols, E element){
        if(rows <= 0 || cols <= 0){
            throw new IllegalArgumentException();
        }
        this.rows = rows;
        this.cols = cols;

		cells = new ArrayList<E>(cols * rows);

		for (int i = 0; i < cols * rows; ++i) {
			cells.add(element);
		}
    }


    /**
     * Iterates through the values of the grid.
     * @return Iterator<CoordinateItem<E>>
     */
    @Override
    public Iterator<CoordinateItem<E>> iterator() {
        List<CoordinateItem<E>> itemList;
        itemList = new ArrayList<CoordinateItem<E>>();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Coordinate newCoord = new Coordinate(row, col);
                itemList.add(new CoordinateItem<E>(newCoord, this.get(newCoord)));
            }   
        }
        return itemList.iterator();
    }


    /**
     * @return row amount of the grid
     */
    @Override
    public int getRows() {
        return this.rows;
    }


    /**
     * @return column amount of the grid
     */
    @Override
    public int getCols() {
        return this.cols;
    }


    /**
     * Sets the value of a coordinate.
     */
    @Override
    public void set(Coordinate coordinate, E value) {
        if (!coordinateIsOnGrid(coordinate)) {
			throw new IndexOutOfBoundsException("Row and column indices must be within bounds");
		}
        else{
            cells.set(toIndex(coordinate), value);
        }
    }


    /**
     * Gets the value of the coordinate. 
     * @return the element from that coordinate.
     */
    @Override
    public E get(Coordinate coordinate){
        if (!coordinateIsOnGrid(coordinate)) {
			throw new IndexOutOfBoundsException("Row and column indices must be within bounds");
		}
        else{
            return cells.get(toIndex(coordinate));
        }
    }


    /**
     * Calculates if the coordinate is on the grid or out of its bounds. 
     * @return the boolean value of the calculation. 
     */
    @Override
    public boolean coordinateIsOnGrid(Coordinate coordinate) {
        if (!(coordinate.row < 0 || coordinate.row >= rows || coordinate.col<0 || coordinate.col >= cols)) {
            return true;
        }
        return false;
    }


    /** 
     * Method that calculates the index in the arraylist of the coordinte row and col values
     * @return index of the arraylist of the coordinates row and col values. 
     */
    private int toIndex(Coordinate cord){
        return cord.row * cols + cord.col;
    }
}
