package jp.cafebabe.fritter.entities;

import jp.cafebabe.fritter.entities.visitors.LocationVisitor;

import java.util.Arrays;

public interface Location {
    <S> S accept(LocationVisitor<S> visitor);

    static Location.LineNumber of(int number) {
        return new LineNumber(number);
    }

    static Location.LineNumbers of(int[] numbers) {
        return new LineNumbers(numbers);
    }

    static Location.PackageName of(String name) {
        return new PackageName(name);
    }

    public static class PackageName implements Location {
        private String name;

        public PackageName(String name) {
            this.name = name;
        }

        @Override
        public <S> S accept(LocationVisitor<S> visitor) {
            return visitor.visitLocation(name);
        }
    }

    public static class LineNumbers implements Location {
        private int[] numbers;

        public LineNumbers(int[] numbers) {
            this.numbers = numbers;
        }

        @Override
        public <S> S accept(LocationVisitor<S> visitor) {
            return visitor.visitLocation(Arrays.copyOf(numbers, numbers.length));
        }
    }

    public static class LineNumber implements Location {
        private int number;

        public LineNumber(int num) {
            this.number = num;
        }

        @Override
        public <S> S accept(LocationVisitor<S> visitor) {
            return visitor.visitLocation(number);
        }
    }
}

