package jp.cafebabe.fritter.validators.impl.smallentities;

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

public class LinesOfClassValidatorTest {
    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorService service = new LinesOfClassValidatorService();
        validator = service.build(Parameter.of(50));
    }

    @DisplayName("LinesOfClassValidator StatsValues.java")
    @Test
    public void case1() {
        DataSource source = ViolationsHelper.dataSource(Paths.get("src/test/resources/projects/examples/StatsValues.java"));
        Violations violations = validator.validate(source);
        assertEquals(0, violations.accept(ViolationsHelper.violationCollector()).size());
    }

    @DisplayName("LinesOfClassValidator Stats.java")
    @Test
    public void case2() {
        DataSource source = ViolationsHelper.dataSource(Paths.get("src/test/resources/projects/examples/Stats.java"));
        Violations violations = validator.validate(source);
        assertEquals(0, violations.accept(ViolationsHelper.violationCollector()).size());
    }

    @DisplayName("LinesOfClassValidator Primes.java")
    @Test
    public void case3() {
        DataSource source = ViolationsHelper.dataSource(Paths.get("src/test/resources/projects/examples/Primes.java"));
        Violations violations = validator.validate(source);
        List<Violation> list = violations.accept(ViolationsHelper.violationCollector());
        assertEquals(1, list.size());
    }

    @DisplayName("LinesOfClassValidator HelloWorld.java")
    @Test
    public void case4() {
        DataSource source = ViolationsHelper.dataSource(Paths.get("src/test/resources/projects/examples/HelloWorld.java"));
        Violations violations = validator.validate(source);
        List<Violation> list = violations.accept(ViolationsHelper.violationCollector());
        assertEquals(0, list.size());
    }
}
