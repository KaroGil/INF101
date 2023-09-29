package inf101v22.observable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * An ControlledObservable is a wrapper object around a variable with the
 * purpose that certain methods will be called whenever the value changes. In
 * particular, the {@link Observer#update update} method on every added Observer
 * is called when the variable changes its value.
 * 
 * <p>
 * The Observable pattern is for example useful in the model-view-controller
 * paradigm. Typically, the model will have wrapped their variables in
 * ControlledObservable wrappers, and give access to them through methods
 * returning the Observable type. For example:
 * 
 * <pre>{@code 
 * class MyModel {
 *      private final ControlledObservable<Integer> myValue = new ControlledObservable<>(0);
 * 
 *      public Observable<Integer> getMyValue() {
 *          return myValue;
 *      }
 * 
 *      private void someMethodWhichUpdatesMyValue() {
 *          Integer newValue = ...
 *          myValue.setValue(newValue);
 *      }
 * }}</pre>
 * 
 * The view will have access to the variables through the Obsevable type, and
 * will benefit from being able to get notifications whenever the value changes.
 * Since {@link Observer} is a functional interface, any method with void return
 * type and no parameters can be used as an observer. For example:
 * 
 * <pre>{@code
 * class MyView extends JPanel {
 *      private final MyModel model;
 *      
 *      MyView(MyModel model) {
 *          this.model = model;
 *          Observable<Integer> myValue = model.getMyValue();
 *          myValue.addObserver(this::onMyValueChanged);
 *      }
 * 
 *      private void onMyValueChanged() {
 *          Integer newValue = model.getMyValue().getValue();
 *          ...
 *          repaint();
 *      }
 * }}</pre>
 */
public class ControlledObservable<E> implements Observable<E> {

    private List<Observer> observers = new ArrayList<>();
    private E value;

    /** Create a new observable with the given initial value. */
    public ControlledObservable(E initialValue) {
        this.value = initialValue;
    }

    @Override
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public boolean removeObserver(Observer observer) {
        return this.observers.remove(observer);
    }

    @Override
    public E getValue() {
        return this.value;
    }

    /**
     * Sets the value of this observable variable. If the new value is
     * different from the old value, all observers will be notified that
     * the value has changed.
     * 
     * @param newValue
     * @return true if the the new value was different from the old value, false
     *         otherwise
     */
    public boolean setValue(E newValue) {
        if (Objects.equals(this.value, newValue)) {
            return false;
        }

        this.value = newValue;
        this.notifyObservers();
        return true;
    }

    private void notifyObservers() {
        for (Observer obs : this.observers) {
            obs.update();
        }
    }
}
