package inf101v22.utils;

import java.util.Iterator;
import java.util.function.Function;

public class IteratorWrapper<A, B> implements Iterator<B> {
    
    private Iterator<A> base;
    private Function<A, B> map;

    public IteratorWrapper(Iterator<A> base, Function<A, B> map) {
        this.base = base;
        this.map = map;
    }

    @Override
    public boolean hasNext() {
        return this.base.hasNext();
    }

    @Override
    public B next() {
        return this.map.apply(this.base.next());
    }
}
