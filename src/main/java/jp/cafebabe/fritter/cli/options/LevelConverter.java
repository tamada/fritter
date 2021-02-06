package jp.cafebabe.fritter.cli.options;

import jp.cafebabe.fritter.config.Level;
import jp.cafebabe.fritter.config.LevelParser;
import picocli.CommandLine.ITypeConverter;

public class LevelConverter implements ITypeConverter<Level> {
    private LevelParser parser = new LevelParser();

    @Override
    public Level convert(String type) {
        return parser.parse(type.toLowerCase());
    }
}
