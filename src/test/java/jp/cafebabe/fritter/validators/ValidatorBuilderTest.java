package jp.cafebabe.fritter.validators;

import jp.cafebabe.fritter.config.CheckerType;
import jp.cafebabe.fritter.config.Parameter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorBuilderTest {
    private ValidatorBuilder builder = new ValidatorBuilder();

    @Test
    @DisplayName("build certain type of validator")
    public void case1() {
        Optional<Validator> validator = builder.build(CheckerType.of("no_accessor"), Parameter.EMPTY);
        assertTrue(validator.isPresent());
    }

    @Test
    @DisplayName("unknown type of validator")
    public void case2() {
        Optional<Validator> validator = builder.build(CheckerType.of("unknown_type"), Parameter.EMPTY);
        assertTrue(validator.isEmpty());
    }
}
