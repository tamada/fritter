package jp.cafebabe.fritter.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValueTest {
    @Test
    @DisplayName("equality check")
    public void case1() {
        Value value1 = new Value(1);
        Value value2 = new Value(2);
        Value value3 = new Value(3);

        assertEquals(true, value1.equalsTo(value1));
        assertEquals(true, value2.equalsTo(value2));
        assertEquals(true, value3.equalsTo(value3));
    }

    @Test
    @DisplayName("greater check")
    public void case2() {
        Value value1 = new Value(1);
        Value value2 = new Value(2);
        Value value3 = new Value(3);

        assertEquals(true, value3.greaterThan(value1));
        assertEquals(true, value2.greaterThan(value1));
        assertEquals(true, value3.greaterThan(value2));
        assertEquals(false, value1.greaterThan(value2));
    }

    @Test
    @DisplayName("less check")
    public void case3() {
        Value value1 = new Value(1);
        Value value2 = new Value(2);
        Value value3 = new Value(3);

        assertEquals(true, value1.lessThan(value2));
        assertEquals(true, value1.lessThan(value3));
        assertEquals(true, value2.lessThan(value3));
        assertEquals(false, value2.lessThan(value1));
    }
}
