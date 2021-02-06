package jp.cafebabe.fritter.validators;

import static org.junit.jupiter.api.Assertions.assertEquals;

import jp.cafebabe.fritter.config.Level;
import jp.cafebabe.fritter.config.LevelParser;
import jp.cafebabe.fritter.entities.ResultsSet;
import jp.cafebabe.fritter.entities.sources.SourcePool;
import jp.cafebabe.fritter.entities.sources.SourcePoolFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

public class ValidatorsTest {
    @Test
    @DisplayName("default level validation")
    void case1() throws Exception {
        Level level = new LevelParser().parse("default");
        Validators validators = new ValidatorsBuilder().build(level);
        SourcePool pool = new SourcePoolFactory().create(Paths.get("src/test/resources/projects/examples"));
        ResultsSet rs = new ResultsSet();
        pool.stream()
                .map(source -> validators.validate(source))
                .forEach(violations -> rs.put(violations));

        assertEquals(1L, rs.pools().count());
    }
}
