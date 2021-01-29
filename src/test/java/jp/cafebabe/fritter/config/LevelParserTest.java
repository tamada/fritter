package jp.cafebabe.fritter.config;

import jp.cafebabe.fritter.entities.Name;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class LevelParserTest {
    @Nested
    @DisplayName("parse default.json")
    class defaultJson {
        @Test
        public void case1() {
            LevelParser parser = new LevelParser();
            Optional<Level> optional = parser.parse("default");

            Assertions.assertTrue(optional.isPresent());

            Level level = optional.get();
            Assertions.assertNotNull(level);
            Assertions.assertEquals(Name.of("default"), level.name());
            Assertions.assertTrue(level.available(CheckerType.of("indent_level")));
            Assertions.assertTrue(level.available(CheckerType.of("primitive_wrapping")));
            Assertions.assertTrue(level.available(CheckerType.of("dot_count_per_line")));
            Assertions.assertTrue(level.available(CheckerType.of("no_abbrev")));
            Assertions.assertTrue(level.available(CheckerType.of("lines_of_class")));
            Assertions.assertTrue(level.available(CheckerType.of("lines_of_method")));
            Assertions.assertTrue(level.available(CheckerType.of("classes_in_package")));
            Assertions.assertTrue(level.available(CheckerType.of("field_count")));
            Assertions.assertTrue(level.available(CheckerType.of("variable_count")));
            Assertions.assertTrue(level.available(CheckerType.of("first_class_collection")));
            Assertions.assertTrue(level.available(CheckerType.of("no_accessor")));
            Assertions.assertTrue(level.available(CheckerType.of("no_static_method")));
            Assertions.assertTrue(level.available(CheckerType.of("no_system_exit")));
            Assertions.assertTrue(level.available(CheckerType.of("no_new_array")));
            Assertions.assertTrue(level.available(CheckerType.of("no_return_code_in_printf")));
        }
    }

    @Nested
    @DisplayName("invalid level name")
    class notFound{
        @Test
        public void case1() {
            LevelParser parser = new LevelParser();
            Assertions.assertThrows(IllegalArgumentException.class, () -> parser.parse("unknown"));
        }
    }
}
