package jp.cafebabe.fritter.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CamelCaserTest {
    @Nested
    @DisplayName("to camel case")
    class CamelCase {
        @Test
        public void case1() {
            assertEquals("Json", CamelCaseUtils.upperCamelCase("json"));
            assertEquals("JsonString", CamelCaseUtils.upperCamelCase("jsonString"));
            assertEquals("jsonString", CamelCaseUtils.lowerCamelCase("JsonString"));
        }
    }
    @Nested
    @DisplayName("split")
    class Split {
        @Test
        public void case1() {
            String[] data = CamelCaseUtils.split("testDBObjectString");

            assertEquals(4, data.length);
            assertEquals("test", data[0]);
            assertEquals("DB", data[1]);
            assertEquals("Object", data[2]);
            assertEquals("String", data[3]);
        }
    }
}
