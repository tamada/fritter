package jp.cafebabe.fritter.validators.impl.variables;

import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.entities.Location;
import jp.cafebabe.fritter.entities.sources.DataSource;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.Violation;
import jp.cafebabe.fritter.validators.Violations;
import jp.cafebabe.fritter.validators.impl.Helper;
import jp.cafebabe.fritter.validators.spi.ValidatorService;
import org.junit.jupiter.api.*;

import java.nio.file.Paths;
import java.util.List;

public class LocalVariableCountValidatorTest {
    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorService service = new LocalVariableCountValidatorService();
        validator = service.build(Parameter.of(1));
    }

    @DisplayName("LocalVariableCountValidator StatsValues.java")
    @Test
    public void case1() {
        DataSource source = Helper.dataSource(Paths.get("src/test/resources/projects/examples/StatsValues.java"));
        Violations violations = validator.validate(source);
        Assertions.assertEquals(0, violations.accept(Helper.violationCollector()).size());
    }

    @DisplayName("LocalVariableCountValidator Stats.java")
    @Test
    public void case2() {
        DataSource source = Helper.dataSource(Paths.get("src/test/resources/projects/examples/Stats.java"));
        Violations violations = validator.validate(source);
        Assertions.assertEquals(0, violations.accept(Helper.violationCollector()).size());
    }

    @DisplayName("LocalVariableCountValidator Primes.java")
    @Test
    public void case3() {
        DataSource source = Helper.dataSource(Paths.get("src/test/resources/projects/examples/Primes.java"));
        Violations violations = validator.validate(source);
        List<Violation> list = violations.accept(Helper.violationCollector());
        Assertions.assertEquals(3, list.size());
        var lines = list.stream()
                .map(v -> v.accept((l, t, m) -> l.toString()))
                .sorted().toArray(size -> new String[size]);
        Assertions.assertEquals("10", lines[0]);
        Assertions.assertEquals("26", lines[1]);
        Assertions.assertEquals("4", lines[2]);
    }

    @DisplayName("LocalVariableCountValidator HelloWorld.java")
    @Test
    public void case4() {
        DataSource source = Helper.dataSource(Paths.get("src/test/resources/projects/examples/HelloWorld.java"));
        Violations violations = validator.validate(source);
        Assertions.assertEquals(0, violations.accept(Helper.violationCollector()).size());
    }

}
