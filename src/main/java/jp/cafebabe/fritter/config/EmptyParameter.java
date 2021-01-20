package jp.cafebabe.fritter.config;

import jp.cafebabe.fritter.entities.Value;

class EmptyParameter extends Parameter {
    EmptyParameter() {
        super(-1);
    }

    public boolean greaterThan(Value other) {
        return false;
    }

    public boolean lessThan(Value other) {
        return false;
    }

    public boolean equalsTo(Value other) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    public int compareTo(Value other) {
        throw new UnsupportedMethodException("EmptyParameter#compareTo: not supported method");
    }
}
