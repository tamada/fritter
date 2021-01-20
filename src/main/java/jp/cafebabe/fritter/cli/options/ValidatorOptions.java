package jp.cafebabe.fritter.cli.options;

import jp.cafebabe.fritter.config.Level;
import jp.cafebabe.fritter.config.LevelParser;
import jp.cafebabe.fritter.validators.Validators;
import jp.cafebabe.fritter.validators.ValidatorsBuilder;
import picocli.CommandLine.Option;

import java.nio.file.Path;
import java.util.Optional;

public class ValidatorOptions {
    @Option(names={"-c", "--config"}, paramLabel="CONFIG", description="specifies the configuration file.")
    private Optional<Path> fileName;

    @Option(names={"-l", "--level"}, paramLabel="LEVEL",
            description={"specifies the strict level. Default is default.",
                    "Available values: strict, general, rough, and default."},
            converter = LevelConverter.class)
    private Optional<Level> level = Optional.empty();

    @Option(names={"-t", "--with-threads"}, description="use threads for validations.")
    private boolean withThread = false;

    private Level loadLevel() {
        LevelParser parser = new LevelParser();
        return fileName.flatMap(path -> parser.load(path))
                .orElseGet(() -> defaultLevel(parser));
    }

    private Level defaultLevel(LevelParser parser) {
        return level.orElseGet(() ->
                parser.parse("default").get());
    }

    public Validators createValidators() {
        ValidatorsBuilder builder = new ValidatorsBuilder();
        return builder.build(loadLevel());
    }

    public boolean isWithThread() {
        return withThread;
    }
}
