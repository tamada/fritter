package jp.cafebabe.fritter.validators.impl.nostatic;

import java.util.function.Consumer;

class Flag {
    private boolean value;

    private Flag(boolean flag) {
        this.value = flag;
    }

    public Flag then(Consumer<Boolean> action) {
        if(value)
            action.accept(value);
        return this;
    }

    public Flag not(Consumer<Boolean> action) {
        if(!value)
            action.accept(value);
        return this;
    }

    public boolean value() {
        return value;
    }

    public static Flag of(boolean value) {
        return new Flag(value);
    }
}
