package jp.cafebabe.fritter.entities;

import jp.cafebabe.fritter.entities.visitors.LocationVisitor;

import java.nio.file.Path;
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

    static Location.PackageName of(Path path) {
        return new PackageName(path.toString()
                .replaceAll("/", "."));
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

        public String toString() {
            return name;
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

        public String toString() {
            return Arrays.toString(numbers);
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

        public String toString() {
            return Integer.toString(number);
        }
    }
}

