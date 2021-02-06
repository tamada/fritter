package jp.cafebabe.fritter.config;

import jp.cafebabe.fritter.entities.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LevelParserTest {
    @DisplayName("parse default.json")
    @Test
    public void case1() {
        LevelParser parser = new LevelParser();
        Level level = parser.parse("default");

        assertNotNull(level);
        assertEquals(Name.of("default"), level.name());
        assertTrue(level.available(CheckerType.of("indent_level")));
        assertTrue(level.available(CheckerType.of("primitive_wrapping")));
        assertTrue(level.available(CheckerType.of("dot_count_per_line")));
        assertTrue(level.available(CheckerType.of("no_abbrev")));
        assertTrue(level.available(CheckerType.of("lines_of_class")));
        assertTrue(level.available(CheckerType.of("lines_of_method")));
        assertTrue(level.available(CheckerType.of("classes_in_package")));
        assertTrue(level.available(CheckerType.of("field_count")));
        assertTrue(level.available(CheckerType.of("variable_count")));
        assertTrue(level.available(CheckerType.of("first_class_collection")));
        assertTrue(level.available(CheckerType.of("no_accessor")));
        assertTrue(level.available(CheckerType.of("no_static_method")));
        assertTrue(level.available(CheckerType.of("no_system_exit")));
        assertTrue(level.available(CheckerType.of("no_new_array")));
        assertTrue(level.available(CheckerType.of("no_return_code_in_printf")));
    }

    @DisplayName("invalid level name")
    @Test
    public void case2() {
        LevelParser parser = new LevelParser();
        assertThrows(IllegalArgumentException.class, () -> parser.parse("unknown"));
    }
}
