package inf101v22.grid;

import java.util.Objects;

public class Coordinate {

    public final int row;
    public final int col;

    /**
     * Constructs a new Coordinate with the given row and column.
     * 
     * @param row non-negative
     * @param col non-negative
     */
    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return "{" +
                " row='" + this.row + "'" +
                ", col='" + this.col + "'" +
                " }";
    }

    @Override
    public boolean equals(Object other) {
        if (other == this)
            return true;
        if (!(other instanceof Coordinate)) {
            return false;
        }
        Coordinate that = (Coordinate) other;
        return this.row == that.row && this.col == that.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.row, this.col);
    }
}
