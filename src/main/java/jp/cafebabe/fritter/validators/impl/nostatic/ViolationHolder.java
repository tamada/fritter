package jp.cafebabe.fritter.validators.impl.nostatic;

import jp.cafebabe.fritter.validators.Violation;
import jp.cafebabe.fritter.validators.Violations;

import java.util.ArrayList;
import java.util.List;

class ViolationHolder {
    private List<Violation> list = new ArrayList<>();

    public void add(Violation violation) {
        list.add(violation);
    }

    public void addIfTo(AllStaticState state, Violations violations) {
        state.ng(() -> list.stream()
                .forEach(violations::add));
    }
}
