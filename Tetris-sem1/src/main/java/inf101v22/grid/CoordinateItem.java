package inf101v22.grid;

public class CoordinateItem <E> {

    //class variables.
    public final Coordinate coordinate;
    public final E item;

    //constructor.
    public CoordinateItem(final Coordinate c, final E e){
        this.coordinate = c;
        this.item = e;
    }


    /**
     * Calculates the hashcode value of the object. 
     * @return hashcode value
     */
    @Override
    public int hashCode() {
        return (int)  item.hashCode() * coordinate.hashCode();
    }


    /**
     * Calculates whether the object are equal.
     * @return boolean truth values of if the values are equal.
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        CoordinateItem<E> coordinateItem = (CoordinateItem<E>) obj;
        return this.coordinate.equals(coordinateItem.coordinate) &&  this.item.equals(coordinateItem.item);
    }


    /**
     * Transforms the object into a string. 
     * @return string format of the object.
     */
    @Override
    public String toString() {
        return "{ coordinate='{ row='"+ coordinate.row+"', col='"+coordinate.col+"' }', item='"+this.item+"' }";
    }
}
