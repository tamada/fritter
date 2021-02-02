package jp.cafebabe.fritter.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class DirectoryTraverserTest {
    @DisplayName("list jsons")
    @Test
    public void case1() {
        DirectoryTraverser traverser = new DirectoryTraverser((path) -> path.toString().endsWith(".json"));
        List<Path> list = traverser.traverse(Paths.get("src/main/resources/resources"))
                .sorted()
                .collect(Collectors.toList());

        Assertions.assertEquals(4, list.size());
        Assertions.assertEquals("default.json", list.get(0).getFileName().toString());
        Assertions.assertEquals("general.json", list.get(1).getFileName().toString());
        Assertions.assertEquals("rough.json", list.get(2).getFileName().toString());
        Assertions.assertEquals("strict.json", list.get(3).getFileName().toString());
    }
}
