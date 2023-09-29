package inf101v22.observable;

/**
 * An Observable is a wrapper around a value which allows someone in possesion
 * of the object to register a method (called an observer) to be called every
 * time the value changes.
 * 
 * <p>
 * This can be useful in a wide variety of settings; for example in the
 * model-view-controller design pattern, where a view can observe updates in the
 * model which that particular view cares about.
 * 
 * <p>
 * Example usage:
 * 
 * <pre>{@code
 * class MyDynamicIntegerLabel extends JLabel {
 * 
 *     private final Observable<Integer> myInteger;
 * 
 *     MyDynamicIntegerLabel(Observable<Integer> myInteger) {
 *         this.myInteger = myInteger;
 *         this.myInteger.addObserver(this::onMyIntegerValueChanged);
 *         this.onMyIntegerValueChanged();
 *     }
 * 
 *     private void onMyIntegerValueChanged() {
 *         int newValue = this.myInteger.getValue();
 *         this.setText("" + newValue);
 *     }
 * }
 * }</pre>
 * 
 * In the above example, the method {@code onMyIntegerValueChanged} will be
 * called every time the value in the observable changes.
 */
public interface Observable<E> {

    /**
     * Adds a (non-null) observer. The {@link Observer#update update} method on the
     * added observer will be called every time the value of the observed variable
     * changes. It will not be called immediately on the observer being installed,
     * only when the variable changes. To get the current value, use the
     * {@link #getValue} method.
     * 
     * @param observer to be added.
     */
    public void addObserver(Observer observer);

    /**
     * Removes a (non-null) observer.
     * 
     * @param observer to be removed.
     * @return true if the observer was removed, and false if the observer was not
     *         found.
     */
    public boolean removeObserver(Observer observer);

    /** Gets the current value. */
    public E getValue();
}
