package jp.cafebabe.fritter.entities.sources;

import jp.cafebabe.fritter.utils.PathHelper;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Optional;

public class SourcePoolFactory {
    public SourcePool create(Path path) {
        Optional<BasicFileAttributes> opts = PathHelper.readAttributes(path);
        return opts.map(attr -> create(path, attr))
                .orElseGet(() -> new EmptySourcePool(path));
    }

    public SourcePool create(Path path, BasicFileAttributes attr) {
        if(attr.isDirectory())
            return new DirectoryPool(path);
        return new SourceFilePool(path);
    }
}
