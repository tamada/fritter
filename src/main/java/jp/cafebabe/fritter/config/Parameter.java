package jp.cafebabe.fritter.config;

import jp.cafebabe.fritter.entities.Value;

public class Parameter extends Value {
    public static final EmptyParameter EMPTY = new EmptyParameter();

    public Parameter(int value) {
        super(value);
    }

    public boolean isEmpty() {
        return false;
    }
}

