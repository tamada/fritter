package jp.cafebabe.fritter.validators;

import jp.cafebabe.fritter.config.CheckerType;
import jp.cafebabe.fritter.entities.Message;
import jp.cafebabe.fritter.entities.Location;
import jp.cafebabe.fritter.entities.visitors.ViolationVisitor;

public class Violation {
    private Location location;
    private CheckerType type;
    private Message message;

    public Violation(Location location, CheckerType type, Message message) {
        this.location = location;
        this.type = type;
        this.message = message;
    }

    public <S> S accept(ViolationVisitor<S> visitor) {
        return visitor.visitViolation(location, type, message);
    }
}
