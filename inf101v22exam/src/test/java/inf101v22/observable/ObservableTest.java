package inf101v22.observable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ObservableTest {

    private boolean flag0 = false;
    private boolean flag1 = false;
    private boolean flag2 = false;
    private boolean flag3 = false;
    private boolean flag4 = false;

    void setFlag0ToTrue() {
        this.flag0 = true;
    }

    private void setFlag1ToTrue() {
        this.flag1 = true;
    }

    private void setFlag2ToTrue() {
        this.flag2 = true;
    }

    private void setFlag3ToTrue() {
        this.flag3 = true;
    }

    @Test
    void sanityTest() {
        final ControlledObservable<String> s = new ControlledObservable<>("Hello");
        assertEquals("Hello", s.getValue());

        // Five ways to create an observer, sorted by increasing compactness:
        // Way 0: Create a separate class which implements the Observer interface (see
        // class definition in separate file)
        Observer observer0 = new SeparateFileObserver(this);

        // Way 1: Create an inner class which implements the Observer interface (see
        // class definition below)
        Observer observer1 = new NamedInnerClassObserver();

        // Way 2: An anonymous inner class. This is essentially the same as way 1, but
        // the inner class does not get to have a name.
        Observer observer2 = new Observer() {

            @Override
            public void update() {
                setFlag2ToTrue();
                // Alteratively, use the full name of the method (to highlight that it actually
                // is called on the TestObservable object):
                // TestObservable.this.setFlag1ToTrue();
            }

        };

        // Way 3: With :: syntax to wrap a named method as an instance of a functional
        // interface
        // Under the hood, this will create an anonymous inner class exactly as way 2.
        Observer observer3 = this::setFlag3ToTrue;

        // Way 4: With () -> {} syntax to wrap an anonymous method as an instance of a
        // functinonal interface
        // Under the hood, this will create an anonymous inner class similar to way 3,
        // but here also the method being called is anonymous.
        Observer observer4 = () -> {
            this.flag4 = true;
        };

        s.addObserver(observer0);
        s.addObserver(observer1);
        s.addObserver(observer2);
        s.addObserver(observer3);
        s.addObserver(observer4);

        this.flag0 = false;
        this.flag1 = false;
        this.flag2 = false;
        this.flag3 = false;
        this.flag4 = false;

        assertFalse(this.flag0);
        assertFalse(this.flag1);
        assertFalse(this.flag2);
        assertFalse(this.flag3);
        assertFalse(this.flag4);
        s.setValue("World");
        assertTrue(this.flag0);
        assertTrue(this.flag1);
        assertTrue(this.flag2);
        assertTrue(this.flag3);
        assertTrue(this.flag4);

        assertEquals("World", s.getValue());
    }

    // A non-static inner class is a class which is defined anew FOR EACH OBJECT of
    // the enclosing class. Thus, every object whoose class is an inner class has
    // access to its parent object. For example, an instance of a
    // NamedInnerClassObserver can see the instance variables of its parent
    // TestObserbable object, thus accessing its variables and methods (such as the
    // setFlag1ToTrue method).
    private class NamedInnerClassObserver implements Observer {

        @Override
        public void update() {
            setFlag1ToTrue();
            // Alteratively, use the full name of the method (to highlight that it actually
            // is called on the TestObservable object):
            // TestObservable.this.setFlag1ToTrue();
        }
    }
}