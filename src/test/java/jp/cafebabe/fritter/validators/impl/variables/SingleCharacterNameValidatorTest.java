package jp.cafebabe.fritter.validators.impl.variables;

import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.entities.sources.DataSource;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.Violations;
import jp.cafebabe.fritter.validators.impl.ViolationsHelper;
import jp.cafebabe.fritter.validators.spi.ValidatorService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SingleCharacterNameValidatorTest {
    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorService service = new SingleCharacterNameValidatorService();
        validator = service.build(Parameter.EMPTY);
    }

    @DisplayName("SingleCharacterNameValidator StatsValues.java")
    @Test
    public void case1() {
        DataSource source = ViolationsHelper.dataSource(Paths.get("src/test/resources/projects/examples/StatsValues.java"));
        Violations violations = validator.validate(source);
        assertEquals(0, violations.accept(ViolationsHelper.violationCollector()).size());
    }

    @DisplayName("SingleCharacterNameValidator Stats.java")
    @Test
    public void case2() {
        DataSource source = ViolationsHelper.dataSource(Paths.get("src/test/resources/projects/examples/Stats.java"));
        Violations violations = validator.validate(source);
        assertEquals(1, violations.accept(ViolationsHelper.violationCollector()).size());
    }

    @DisplayName("SingleCharacterNameValidator Primes.java")
    @Test
    public void case3() {
        DataSource source = ViolationsHelper.dataSource(Paths.get("src/test/resources/projects/examples/Primes.java"));
        Violations violations = validator.validate(source);
        assertEquals(0, violations.accept(ViolationsHelper.violationCollector()).size());
    }

    @DisplayName("SingleCharacterNameValidator HelloWorld.java")
    @Test
    public void case4() {
        DataSource source = ViolationsHelper.dataSource(Paths.get("src/test/resources/projects/examples/HelloWorld.java"));
        Violations violations = validator.validate(source);
        assertEquals(0, violations.accept(ViolationsHelper.violationCollector()).size());
    }
}
