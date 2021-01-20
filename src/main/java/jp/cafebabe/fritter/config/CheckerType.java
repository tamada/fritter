package jp.cafebabe.fritter.config;

import jp.cafebabe.fritter.entities.Name;

public class CheckerType extends Name {
    private CheckerType(String name) {
        super(name);
    }

    public static CheckerType of(String name) {
        return new CheckerType(name);
    }
}
