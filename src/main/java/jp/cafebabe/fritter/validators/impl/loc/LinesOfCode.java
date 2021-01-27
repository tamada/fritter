package jp.cafebabe.fritter.validators.impl.loc;

import jp.cafebabe.fritter.entities.Value;

public class LinesOfCode extends Value {
    public LinesOfCode(long value) {
        super(value);
    }

    public static LinesOfCode of(long count) {
        return new LinesOfCode(count);
    }

    public String toString() {
        return Long.toString(value());
    }
}
