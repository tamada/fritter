package jp.cafebabe.fritter.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectoryTraverserTest {
    @DisplayName("list jsons")
    @Test
    public void case1() {
        DirectoryTraverser traverser = new DirectoryTraverser((path) -> path.toString().endsWith(".json"));
        List<Path> list = traverser.traverse(Paths.get("src/main/resources/resources"))
                .sorted()
                .collect(Collectors.toList());

        assertEquals(4, list.size());
        assertEquals("default.json", list.get(0).getFileName().toString());
        assertEquals("general.json", list.get(1).getFileName().toString());
        assertEquals("rough.json", list.get(2).getFileName().toString());
        assertEquals("strict.json", list.get(3).getFileName().toString());
    }
}
