package jp.cafebabe.fritter.cli;

import io.vavr.control.Try;
import jp.cafebabe.fritter.cli.options.Arguments;
import jp.cafebabe.fritter.entities.ResultSet;
import jp.cafebabe.fritter.entities.sources.DataSource;
import jp.cafebabe.fritter.validators.Validators;
import jp.cafebabe.fritter.validators.Violations;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;

import java.util.stream.Stream;

@Command(name="fritter", versionProvider = VersionProvider.class, mixinStandardHelpOptions = true,
        description="Small object programming checker.")
public class Main implements Runnable {
    @Mixin
    private Arguments args;

    public void run() {
        Validators validator = args.createValidators();
        ResultSet rs = execute(validator, new ResultSet());
        args.print(validator, rs);
    }

    private ResultSet execute(Validators validators, ResultSet rs) {
        validateEach(validators)
                .forEach(violations -> rs.put(violations));
        return rs;
    }

    private Stream<Violations> validateEach(Validators validators) {
        return args.createStream()
                .map(source -> validate(validators, source));
    }

    private Violations validate(Validators validators, DataSource source){
        return Try.of(() -> validators.validate(source))
                .get();
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Main())
                .execute(args);
        System.exit(exitCode);
    }
}