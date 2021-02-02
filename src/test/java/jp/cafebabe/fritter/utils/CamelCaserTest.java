package jp.cafebabe.fritter.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class CamelCaserTest {
    @Nested
    @DisplayName("to camel case")
    class CamelCase {
        @Test
        public void case1() {
            Assertions.assertEquals("Json", CamelCaseUtils.upperCamelCase("json"));
            Assertions.assertEquals("JsonString", CamelCaseUtils.upperCamelCase("jsonString"));
            Assertions.assertEquals("jsonString", CamelCaseUtils.lowerCamelCase("JsonString"));
        }
    }
    @Nested
    @DisplayName("split")
    class Split {
        @Test
        public void case1() {
            String[] data = CamelCaseUtils.split("testDBObjectString");

            Assertions.assertEquals(4, data.length);
            Assertions.assertEquals("test", data[0]);
            Assertions.assertEquals("DB", data[1]);
            Assertions.assertEquals("Object", data[2]);
            Assertions.assertEquals("String", data[3]);
        }
    }
}
