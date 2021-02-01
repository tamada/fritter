package jp.cafebabe.fritter.entities;

public class Value implements Comparable<Value> {
    private long value;

    public Value(long value) {
        this.value = value;
    }

    public static Value of(long value) {
        return new Value(value);
    }

    public boolean greaterThan(Value other) {
        return compareTo(other) == 1;
    }

    public boolean lessThan(Value other) {
        return compareTo(other) == -1;
    }

    public boolean equalsTo(Value other) {
        return compareTo(other) == 0;
    }

    public int compareTo(Value other) {
        if(value < other.value) return -1;
        else if(value == other.value) return 0;
        return 1;
    }

    public long value() {
        return value;
    }

    public String toString() {
        return Long.toString(value());
    }
}
