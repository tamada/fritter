package jp.cafebabe.fritter.cli.printer.json;

import jp.cafebabe.fritter.entities.Location;
import jp.cafebabe.fritter.entities.visitors.LocationVisitor;

import java.util.Arrays;
import java.util.stream.Collectors;

class JsonLocationVisitor implements LocationVisitor<String> {
    @Override
    public String visitLocation(int lineNumber) {
        StringBuilder sb = new StringBuilder();
        sb.append("\"line\":").append(lineNumber);
        return new String(sb);
    }

    @Override
    public String visitLocation(int[] lineNumbers) {
        StringBuilder sb = new StringBuilder();
        visitLocationImpl(lineNumbers, sb);
        return new String(sb);
    }

    private void visitLocationImpl(int[] lineNumbers, StringBuilder sb){
        sb.append("\"lines\":").append(Arrays.stream(lineNumbers)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(",", "[", "]")));
    }

    @Override
    public String visitLocation(String packageName) {
        StringBuilder sb = new StringBuilder();
        visitLocationImpl(packageName, sb);
        return new String(sb);
    }

    private void visitLocationImpl(String packageName, StringBuilder sb) {
        sb.append("\"package\":\"")
                .append(packageName)
                .append("\"");
    }
}
