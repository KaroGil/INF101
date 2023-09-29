package inf101v22.observable;

public class SeparateFileObserver implements Observer {

    private ObservableTest parent;

    public SeparateFileObserver(ObservableTest parent) {
        this.parent = parent;
    }

    @Override
    public void update() {
        this.parent.setFlag0ToTrue();
    }
}
