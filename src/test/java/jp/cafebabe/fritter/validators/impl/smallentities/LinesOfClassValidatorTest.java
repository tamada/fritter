package jp.cafebabe.fritter.validators.impl.smallentities;

import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.entities.sources.DataSource;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.Violation;
import jp.cafebabe.fritter.validators.Violations;
import jp.cafebabe.fritter.validators.impl.Helper;
import jp.cafebabe.fritter.validators.impl.variables.LocalVariableCountValidatorService;
import jp.cafebabe.fritter.validators.spi.ValidatorService;
import org.junit.jupiter.api.*;

import java.nio.file.Paths;
import java.util.List;

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
        DataSource source = Helper.dataSource(Paths.get("src/test/resources/projects/examples/StatsValues.java"));
        Violations violations = validator.validate(source);
        Assertions.assertEquals(0, violations.accept(Helper.violationCollector()).size());
    }

    @DisplayName("LinesOfClassValidator Stats.java")
    @Test
    public void case2() {
        DataSource source = Helper.dataSource(Paths.get("src/test/resources/projects/examples/Stats.java"));
        Violations violations = validator.validate(source);
        Assertions.assertEquals(0, violations.accept(Helper.violationCollector()).size());
    }

    @DisplayName("LinesOfClassValidator Primes.java")
    @Test
    public void case3() {
        DataSource source = Helper.dataSource(Paths.get("src/test/resources/projects/examples/Primes.java"));
        Violations violations = validator.validate(source);
        List<Violation> list = violations.accept(Helper.violationCollector());
        Assertions.assertEquals(1, list.size());
    }

    @DisplayName("LinesOfClassValidator HelloWorld.java")
    @Test
    public void case4() {
        DataSource source = Helper.dataSource(Paths.get("src/test/resources/projects/examples/HelloWorld.java"));
        Violations violations = validator.validate(source);
        List<Violation> list = violations.accept(Helper.violationCollector());
        Assertions.assertEquals(0, list.size());
    }
}
