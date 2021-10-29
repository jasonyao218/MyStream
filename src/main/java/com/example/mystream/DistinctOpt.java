package com.example.mystream;

import java.util.HashSet;
import java.util.Set;

public class DistinctOpt<T> implements ISink<T> {
    private Set set;
    private final ISink<T> downstream;

    public DistinctOpt(ISink<T> downstream) {
        this.downstream = downstream;
    }

    @Override
    public void begin(long siAze) {
        set = new HashSet();
    }

    @Override
    public void end() {
        downstream.begin(-1);
        for(int i = 0; i < set.toArray().length; i++) {
            downstream.accept((T)set.toArray()[i]);
        }
        downstream.end();
    }
    @Override
    public void accept(T t) {
        set.add(t);
    }
}
