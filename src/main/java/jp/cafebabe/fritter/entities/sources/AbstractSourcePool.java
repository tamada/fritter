package jp.cafebabe.fritter.entities.sources;

import java.nio.file.Path;

public abstract class AbstractSourcePool implements SourcePool {
    private Path base;

    public AbstractSourcePool(Path base) {
        this.base = base;
    }

    @Override
    public Path base() {
        return base;
    }
}
