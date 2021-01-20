package jp.cafebabe.fritter.entities.visitors;

import jp.cafebabe.fritter.config.CheckerType;
import jp.cafebabe.fritter.entities.Location;
import jp.cafebabe.fritter.entities.Message;

public interface ViolationsVisitor {
    void visitViolation(Location location, CheckerType type, Message msssage);
}
