package jp.cafebabe.fritter.entities;

import java.util.Objects;

public class Name {
    private String name;

    protected Name(String name) {
        this.name = Objects.requireNonNull(name);
    }

    @Override
    public boolean equals(Object other) {
        return Objects.equals(getClass(), other.getClass())
                && Objects.equals(name, ((Name)other).name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), name);
    }

    @Override
    public String toString() {
        return name;
    }

    public static Name of(String name) {
        return new Name(name);
    }
}
