package jp.cafebabe.fritter.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class CamelCaserTest {
    @Nested
    @DisplayName("to camel case")
    class camelCase {
        @Test
        public void case1() {
            Assertions.assertEquals("Json", CamelCaser.upperCamelCase("json"));
            Assertions.assertEquals("JsonString", CamelCaser.upperCamelCase("jsonString"));
            Assertions.assertEquals("jsonString", CamelCaser.lowerCamelCase("JsonString"));
        }
    }
    @Nested
    @DisplayName("split")
    class split {
        @Test
        public void case1() {
            String[] data = CamelCaser.split("testDBObjectString");

            Assertions.assertEquals(4, data.length);
            Assertions.assertEquals("test", data[0]);
            Assertions.assertEquals("DB", data[1]);
            Assertions.assertEquals("Object", data[2]);
            Assertions.assertEquals("String", data[3]);
        }
    }
}
