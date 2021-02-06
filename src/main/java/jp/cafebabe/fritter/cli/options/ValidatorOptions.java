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
    private Level level;

    @Option(names={"-t", "--with-threads"}, description="use threads for validations.")
    private boolean withThread = false;

    private Level loadLevel() {
        LevelParser parser = new LevelParser();
        return fileName.map(path -> parser.parse(path))
                .orElseGet(() -> findLevel(parser));
    }

    private Level findLevel(LevelParser parser) {
        return Optional.of(level)
                .orElseGet(() -> defaultLevel(parser));
    }

    private Level defaultLevel(LevelParser parser) {
        return parser.parse("default");
    }

    public Validators createValidators() {
        ValidatorsBuilder builder = new ValidatorsBuilder();
        return builder.build(loadLevel());
    }

    public boolean isWithThread() {
        return withThread;
    }
}
