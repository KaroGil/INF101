package inf101v22.grid;

public class Coordinate {

    //class variables.
    public final int row;
    public final int col;

    //constructor.
    public Coordinate(final int row, final int col){
        this.row = row;
        this.col = col;
    }

    /**
     * calculates the hashcode of the coordinate.
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return (int) row*col;
    }


    /**
     * Calculates whether the coordinates are equal.
     * @return boolean truth values of if the values are equal.
     */
    @Override
    public boolean equals(Object a) {
        if(this == a){
            return true;
        }
        if(a == null || getClass() != a.getClass()){
            return false;
        }
        Coordinate coordinate = (Coordinate) a;
        return row == coordinate.row && col == coordinate.col;
    }


    /**
     * Transforms the coordinate into a string. 
     * @return string format of the coordinate.
     */
    @Override
    public String toString() {
        return "{ row='" + this.row +  "', col='"+ this.col +"' }";
    }
}
