package jp.cafebabe.fritter.entities;

import java.util.function.*;

public class Pair<L, R> {
    private L left;
    private R right;

    protected Pair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public Pair<R, L> swap() {
        return new Pair<>(right, left);
    }

    public boolean and(Predicate<L> lPredicate, Predicate<R> rPredicate) {
        return lPredicate.test(left) && rPredicate.test(right);
    }

    public boolean or(Predicate<L> lPredicate, Predicate<R> rPredicate) {
        return lPredicate.test(left) || rPredicate.test(right);
    }

    public boolean test(BiPredicate<L, R> predicate) {
        return predicate.test(left, right);
    }

    public <K> K reduce(BiFunction<L, R, K> function) {
        return function.apply(left, right);
    }

    public <LL, RR> Pair<LL, RR> map(Function<L, LL> leftFunction, Function<R, RR> rightFunction) {
        return new Pair<>(leftFunction.apply(left),
                rightFunction.apply(right));
    }

    public void accept(BiConsumer<L, R> consumer) {
        consumer.accept(left, right);
    }

    public static final <L, R> Pair<L, R> of(L left, R right) {
        return new Pair<>(left, right);
    }
}
