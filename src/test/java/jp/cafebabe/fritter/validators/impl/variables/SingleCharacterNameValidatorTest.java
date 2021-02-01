package jp.cafebabe.fritter.validators.impl.variables;

import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.entities.sources.DataSource;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.Violations;
import jp.cafebabe.fritter.validators.impl.Helper;
import jp.cafebabe.fritter.validators.spi.ValidatorService;
import org.junit.jupiter.api.*;

import java.nio.file.Paths;

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
        DataSource source = Helper.dataSource(Paths.get("src/test/resources/projects/examples/StatsValues.java"));
        Violations violations = validator.validate(source);
        Assertions.assertEquals(0, violations.accept(Helper.violationCollector()).size());
    }

    @DisplayName("SingleCharacterNameValidator Stats.java")
    @Test
    public void case2() {
        DataSource source = Helper.dataSource(Paths.get("src/test/resources/projects/examples/Stats.java"));
        Violations violations = validator.validate(source);
        Assertions.assertEquals(1, violations.accept(Helper.violationCollector()).size());
    }

    @DisplayName("SingleCharacterNameValidator Primes.java")
    @Test
    public void case3() {
        DataSource source = Helper.dataSource(Paths.get("src/test/resources/projects/examples/Primes.java"));
        Violations violations = validator.validate(source);
        Assertions.assertEquals(0, violations.accept(Helper.violationCollector()).size());
    }

    @DisplayName("SingleCharacterNameValidator HelloWorld.java")
    @Test
    public void case4() {
        DataSource source = Helper.dataSource(Paths.get("src/test/resources/projects/examples/HelloWorld.java"));
        Violations violations = validator.validate(source);
        Assertions.assertEquals(0, violations.accept(Helper.violationCollector()).size());
    }
}
