package jp.cafebabe.fritter.entities;

import java.util.Objects;

public class Count implements Comparable<Count> {
    private long count;

    public Count(long count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Count
                && this.count == ((Count)other).count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), count);
    }

    @Override
    public int compareTo(Count o) {
        if(count < o.count) return -1;
        else if(count == o.count) return 0;
        return 1;
    }

    @Override
    public String toString() {
        return Long.toString(count);
    }
}
