package jp.cafebabe.fritter.entities;

import java.util.Comparator;

public class ValueComparator implements Comparator<Value> {
    @Override
    public int compare(Value o1, Value o2) {
        return o1.compareTo(o2);
    }
}
