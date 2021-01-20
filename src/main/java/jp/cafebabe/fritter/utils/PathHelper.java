package jp.cafebabe.fritter.utils;

import io.vavr.control.Try;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.spi.FileSystemProvider;
import java.util.Optional;

public class PathHelper {
    public static boolean endsWith(Path path, String suffix) {
        return path.toString()
                .endsWith(suffix);
    }

    public static Optional<BasicFileAttributes> readAttributes(Path path) {
        return readAttributes(path, path.getFileSystem());
    }

    public static Optional<BasicFileAttributes> readAttributes(Path path, FileSystem system) {
        return readAttributes(path, system.provider());
    }
    public static Optional<BasicFileAttributes> readAttributes(Path path, FileSystemProvider provider) {
        return Try.of(() -> provider.readAttributes(path, BasicFileAttributes.class))
                .toJavaOptional();
    }

    public static DirectoryStream<Path> directoryStream(Path path) throws IOException {
        return directoryStream(path, path.getFileSystem());
    }

    public static DirectoryStream<Path> directoryStream(Path path, FileSystem system) throws IOException {
        return directoryStream(path, system.provider());
    }

    public static DirectoryStream<Path> directoryStream(Path path, FileSystemProvider provider) throws IOException {
        return provider.newDirectoryStream(path, p -> true);
    }
}
