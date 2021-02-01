package jp.cafebabe.fritter.entities.visitors;

import jp.cafebabe.fritter.config.CheckerType;
import jp.cafebabe.fritter.entities.Location;
import jp.cafebabe.fritter.entities.Message;
import jp.cafebabe.fritter.validators.ValidateException;

public interface ViolationVisitor<S> {
    S visitViolation(Location location, CheckerType type, Message message);
}
