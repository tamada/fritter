package jp.cafebabe.fritter.entities;

import jp.cafebabe.fritter.entities.visitors.LocationVisitor;

import java.util.Arrays;

public interface Location {
    void accept(LocationVisitor visitor);

    static Location of(int number) {
        return new LineNumber(number);
    }

    static Location of(int[] numbers) {
        return new LineNumbers(numbers);
    }

    static Location of(String name) {
        return new PackageName(name);
    }

    public static class PackageName implements Location {
        private String name;

        public PackageName(String name) {
            this.name = name;
        }

        public void accept(LocationVisitor visitor) {
            visitor.visitLocation(name);
        }
    }

    public static class LineNumbers implements Location {
        private int[] numbers;

        public LineNumbers(int[] numbers) {
            this.numbers = numbers;
        }

        public void accept(LocationVisitor visitor) {
            visitor.visitLocation(Arrays.copyOf(numbers, numbers.length));
        }
    }

    public static class LineNumber implements Location {
        private int number;

        public LineNumber(int num) {
            this.number = num;
        }

        @Override
        public void accept(LocationVisitor visitor) {
            visitor.visitLocation(number);
        }
    }
}

