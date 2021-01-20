package jp.cafebabe.fritter.utils;

import io.vavr.control.Try;

import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class DirectoryTraverser {
    private Predicate<Path> predicate;

    public DirectoryTraverser(Predicate<Path> predicate) {
        this.predicate = predicate;
    }

    public DirectoryTraverser() {
        this(path -> true);
    }

    public Stream<Path> traverse(Path path) {
        List<Path> list = new ArrayList<>();
        collectFiles(path, list);
        return list.stream();
    }

    private void collectFiles(Path path, List<Path> list) {
        PathHelper.readAttributes(path)
                .ifPresent(attr -> collectFiles(path, attr, list));
    }

    private void collectFiles(Path path, BasicFileAttributes attr, List<Path> list) {
        if(attr.isDirectory())
            walkDirectory(path, list);
        else if(attr.isRegularFile() && predicate.test(path))
            list.add(path);
    }

    private void walkDirectory(Path path, List<Path> list) {
        Try.withResources(() -> PathHelper.directoryStream(path))
                .of(stream -> findAll(stream, list));
    }

    private List<Path> findAll(DirectoryStream<Path> stream, List<Path> list) {
        stream.forEach(path -> collectFiles(path, list));
        return list;
    }
}
