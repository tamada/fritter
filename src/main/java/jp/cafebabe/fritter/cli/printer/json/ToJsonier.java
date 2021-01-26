package jp.cafebabe.fritter.cli.printer.json;

import jp.cafebabe.fritter.config.CheckerType;
import jp.cafebabe.fritter.entities.Location;
import jp.cafebabe.fritter.entities.Message;
import jp.cafebabe.fritter.entities.visitors.ViolationsVisitor;
import jp.cafebabe.fritter.validators.Violation;

class ToJsonier implements ViolationsVisitor<String> {
    @Override
    public String visitViolation(Location location, CheckerType type, Message message) {
        StringBuilder sb = new StringBuilder();
        visitViolationImpl(sb, location, type, message);
        return new String(sb);
    }

    public void visitViolationImpl(StringBuilder sb, Location location, CheckerType type, Message message) {
        sb.append("{");
        appendEach(sb, location, type, message);
        sb.append("}");
    }

    private void appendEach(StringBuilder sb, Location location, CheckerType type, Message message) {
        appendLocation(sb, location);
        appendType(sb, type);
        appendMessage(sb, message);
    }

    private void appendLocation(StringBuilder sb, Location location) {
        sb.append(location.accept(new ToLocationJsonier()));
    }

    private void appendType(StringBuilder sb, CheckerType type) {
        sb.append(",\"key\":\"")
                .append(type)
                .append("\"");
    }

    private void appendMessage(StringBuilder sb, Message message) {
        sb.append(",\"message\":\"")
                .append(message)
                .append("\"");
    }
}
