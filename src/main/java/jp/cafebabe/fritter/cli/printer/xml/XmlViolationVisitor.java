package jp.cafebabe.fritter.cli.printer.xml;

import static jp.cafebabe.fritter.cli.printer.xml.XmlUtils.escape;

import jp.cafebabe.fritter.config.CheckerType;
import jp.cafebabe.fritter.entities.Location;
import jp.cafebabe.fritter.entities.Message;
import jp.cafebabe.fritter.entities.visitors.LocationVisitor;
import jp.cafebabe.fritter.entities.visitors.ViolationVisitor;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class XmlViolationVisitor implements ViolationVisitor<String>, LocationVisitor<String> {
    @Override
    public String visitViolation(Location location, CheckerType type, Message message) {
        return String.format("<violation><validator-type>%s</validator-type><location>%s</location><message>%s</message></violation>",
                type, location.accept(this), escape(message));
    }

    @Override
    public String visitLocation(int lineNumber) {
        return String.format("<line-number>%d</line-number>", lineNumber);
    }

    @Override
    public String visitLocation(int[] lineNumbers) {
        return String.format("<line-numbers>%s</line-numbers>", lineNumbers(lineNumbers));
    }

    private String lineNumbers(int[] nums){
        return IntStream.of(nums)
                .mapToObj(num -> String.format("<line-number>%d</line-number>", num))
                .collect(Collectors.joining());
    }

    @Override
    public String visitLocation(String packageName) {
        return String.format("<package-name>%s</package-name>", packageName);
    }
}
