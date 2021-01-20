package jp.cafebabe.fritter.cli.options;

import jp.cafebabe.fritter.config.Level;
import jp.cafebabe.fritter.config.LevelParser;
import picocli.CommandLine.ITypeConverter;

import java.util.Optional;

public class LevelConverter implements ITypeConverter<Optional<Level>> {
    private LevelParser parser = new LevelParser();

    @Override
    public Optional<Level> convert(String type) {
        return parser.parse(type.toLowerCase());
    }
}
