package inf101v22.grid;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import inf101v22.observable.Observable;

public class ControlledGridTest implements IGridTests {

    private boolean flag = false;

    private void setFlagToTrue() {
        this.flag = true;
    }

    @Override
    public IGrid<String> getStringGrid(int rows, int cols) {
        return new ListOfListsGrid<>(rows, cols);
    }

    @Test
    void testGetObservable() {
        ControlledGrid<Integer> grid = new ControlledGrid<>(5, 3);
        Coordinate coordinate = new Coordinate(2, 1);
        grid.set(coordinate, 0);

        Observable<Integer> observable = grid.getObservable(coordinate);
        observable.addObserver(this::setFlagToTrue);

        // Change the value to something different
        this.flag = false;
        grid.set(coordinate, 1);
        assertTrue(this.flag);

        // Set the value again, this time to the same value it already had
        this.flag = false;
        grid.set(coordinate, 1);
        assertFalse(this.flag);
    }

    @Test
    void testToString() {
        ControlledGrid<Integer> grid = new ControlledGrid<>(2, 3);
        grid.set(new Coordinate(0, 0), 1);
        grid.set(new Coordinate(0, 1), 2);
        grid.set(new Coordinate(0, 2), 3);
        grid.set(new Coordinate(1, 0), 4);
        grid.set(new Coordinate(1, 1), 5);
        grid.set(new Coordinate(1, 2), 6);
        assertEquals("ControlledGrid[[1, 2, 3], [4, 5, 6]]", grid.toString());
    }
}
