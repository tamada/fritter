package jp.cafebabe.fritter.validators.impl.accessor;

import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.entities.sources.DataSource;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.Violation;
import jp.cafebabe.fritter.validators.Violations;
import jp.cafebabe.fritter.validators.impl.Helper;
import jp.cafebabe.fritter.validators.impl.elsestatement.NoElseValidatorService;
import jp.cafebabe.fritter.validators.spi.ValidatorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.List;

public class NoAccessorValidatorTest {
    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorService service = new NoAccessorValidatorService();
        validator = service.build(Parameter.EMPTY);
    }

    @DisplayName("NoAccessorValidator StatsValues.java")
    @Test
    public void case1() {
        DataSource source = Helper.dataSource(Paths.get("src/test/resources/projects/examples/StatsValues.java"));
        Violations violations = validator.validate(source);
        Assertions.assertEquals(0, violations.accept(Helper.violationCollector()).size());
    }

    @DisplayName("NoAccessorValidator Stats.java")
    @Test
    public void case2() {
        DataSource source = Helper.dataSource(Paths.get("src/test/resources/projects/examples/Stats.java"));
        Violations violations = validator.validate(source);
        Assertions.assertEquals(0, violations.accept(Helper.violationCollector()).size());
    }

    @DisplayName("NoAccessorValidator Primes.java")
    @Test
    public void case3() {
        DataSource source = Helper.dataSource(Paths.get("src/test/resources/projects/examples/Primes.java"));
        Violations violations = validator.validate(source);
        List<Violation> list = violations.accept(Helper.violationCollector());
        Assertions.assertEquals(0, list.size());
    }

    @DisplayName("NoAccessorValidator HelloWorld.java")
    @Test
    public void case4() {
        DataSource source = Helper.dataSource(Paths.get("src/test/resources/projects/examples/HelloWorld.java"));
        Violations violations = validator.validate(source);
        List<Violation> list = violations.accept(Helper.violationCollector());
        Assertions.assertEquals(1, list.size());
    }
}
