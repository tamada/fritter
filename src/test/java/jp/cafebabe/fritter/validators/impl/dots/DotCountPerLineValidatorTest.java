package jp.cafebabe.fritter.validators.impl.dots;

import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.entities.sources.DataSource;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.Violation;
import jp.cafebabe.fritter.validators.Violations;
import jp.cafebabe.fritter.validators.impl.ViolationsHelper;
import jp.cafebabe.fritter.validators.spi.ValidatorService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DotCountPerLineValidatorTest {
    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorService service = new DotCountPerLineValidatorService();
        validator = service.build(Parameter.of(1));
    }

    @DisplayName("DotCountPerLineValidator StatsValues.java")
    @Test
    public void case1() {
        DataSource source = ViolationsHelper.dataSource(Paths.get("src/test/resources/projects/examples/StatsValues.java"));
        Violations violations = validator.validate(source);
        assertEquals(0, violations.accept(ViolationsHelper.violationCollector()).size());
    }

    @DisplayName("DotCountPerLineValidator Stats.java")
    @Test
    public void case2() {
        DataSource source = ViolationsHelper.dataSource(Paths.get("src/test/resources/projects/examples/Stats.java"));
        Violations violations = validator.validate(source);
        assertEquals(0, violations.accept(ViolationsHelper.violationCollector()).size());
    }

    @DisplayName("DotCountPerLineValidator Primes.java")
    @Test
    public void case3() {
        DataSource source = ViolationsHelper.dataSource(Paths.get("src/test/resources/projects/examples/Primes.java"));
        Violations violations = validator.validate(source);
        List<Violation> list = violations.accept(ViolationsHelper.violationCollector());
        assertEquals(0, list.size());
    }

    @DisplayName("DotCountPerLineValidator Primes.java")
    @Test
    public void case4() {
        DataSource source = ViolationsHelper.dataSource(Paths.get("src/test/resources/projects/examples/HelloWorld.java"));
        Violations violations = validator.validate(source);
        List<Violation> list = violations.accept(ViolationsHelper.violationCollector());
        assertEquals(0, list.size());
    }
}
