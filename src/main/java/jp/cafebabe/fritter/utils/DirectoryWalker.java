package jp.cafebabe.fritter.utils;

import java.io.IOException;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.spi.FileSystemProvider;
import java.util.Optional;

public class DirectoryWalker {
    private FileVisitor<Path> visitor;
    public DirectoryWalker(FileVisitor<Path> visitor) {
        this.visitor = visitor;
    }

    public void walk(Path path, FileSystemProvider provider) {
        Optional<BasicFileAttributes> opt = PathHelper.readAttributes(path, provider);
        opt.ifPresent(attr -> visit(path, attr, provider));
    }

    private void visit(Path path, BasicFileAttributes attr, FileSystemProvider provider) {
        if(attr.isDirectory())
            visitDirectory(path, attr, provider);

    }

    private void visitDirectory(Path path, BasicFileAttributes attr, FileSystemProvider provider) {
        try {
            visitor.preVisitDirectory(path, attr);
            visitDirectory(path, attr, provider);
            visitor.postVisitDirectory(path, null);
        } catch(IOException e) {
            try {
                visitor.visitFileFailed(path, e);
            } catch(IOException ee) {
            }
        }
    }
}
