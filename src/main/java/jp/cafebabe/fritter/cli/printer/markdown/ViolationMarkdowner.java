package jp.cafebabe.fritter.cli.printer.markdown;

import jp.cafebabe.fritter.config.CheckerType;
import jp.cafebabe.fritter.entities.Location;
import jp.cafebabe.fritter.entities.Message;
import jp.cafebabe.fritter.entities.visitors.LocationVisitor;
import jp.cafebabe.fritter.entities.visitors.ViolationVisitor;
import jp.cafebabe.fritter.validators.Violation;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ViolationMarkdowner extends Markdowner<Violation> implements ViolationVisitor<String>, LocationVisitor<String> {
    public ViolationMarkdowner(PrintWriter out) {
        super(out);
    }

    @Override
    public String convert(Violation item) {
        item.accept(this);
        return "";
    }

    @Override
    public String visitViolation(Location location, CheckerType type, Message message) {
        out.printf("* %s (%s)%n", type, location.accept(this));
        out.printf("    * %s%n", message);
        return "";
    }

    @Override
    public String visitLocation(int lineNumber) {
        return String.format("line: %d", lineNumber);
    }

    @Override
    public String visitLocation(int[] lineNumbers) {
        return String.format("lines: %s", IntStream.of(lineNumbers)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(", ")));
    }

    @Override
    public String visitLocation(String packageName) {
        return String.format("package: %s", packageName);
    }
}
