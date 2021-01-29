package jp.cafebabe.fritter.validators.impl.smallentities;

import io.vavr.control.Try;

import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.nio.file.spi.FileSystemProvider;
import java.util.stream.StreamSupport;

class DirectoryUtils {
    public static long countJavaFiles(Path path) {
        FileSystemProvider provider = path.getFileSystem()
                .provider();
        return countJavaFiles(path, provider);
    }

    private static long countJavaFiles(Path path, FileSystemProvider provider) {
        return Try.of(() -> countJavaFiles(
                provider.newDirectoryStream(path, DirectoryUtils::isJavaFile)))
                .get();
    }

    private static boolean isJavaFile(Path path) {
        return path.getFileName()
                .toString().endsWith(".java");
    }

    private static long countJavaFiles(DirectoryStream<Path> stream) {
        return StreamSupport.stream(stream.spliterator(), false)
                .count();
    }
}
