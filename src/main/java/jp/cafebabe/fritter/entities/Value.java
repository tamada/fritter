package jp.cafebabe.fritter.entities;

public class Value implements Comparable<Value> {
    private int value;

    public Value(int value) {
        this.value = value;
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

    public int value() {
        return value;
    }
}
