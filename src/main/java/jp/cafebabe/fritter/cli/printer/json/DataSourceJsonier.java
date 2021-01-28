package jp.cafebabe.fritter.cli.printer.json;

import jp.cafebabe.fritter.entities.Pair;
import jp.cafebabe.fritter.entities.sources.DataSource;
import jp.cafebabe.fritter.validators.ValidateException;
import jp.cafebabe.fritter.validators.Violations;

import java.util.Optional;
import java.util.stream.Stream;

public class DataSourceJsonier implements Jsonier<Pair<DataSource, Violations>> {
    private ViolationsJsonier jsonier = new ViolationsJsonier();

    @Override
    public String convert(Pair<DataSource, Violations> pair) {
        return pair.reduce((left, right) -> convert(left, right));
    }

    public String convert(DataSource source, Violations violations) {
        return String.format("{%s:%s,%s:%s%s}",
                string("file"), string(source.relativePath()),
                string("message"), jsonier.convert(violations),
                exceptionsIfExists(violations));
    }

    private String exceptionsIfExists(Violations violations) {
        return ifHasExceptions(violations)
                .map(message -> String.format(",%s:%s", string("exceptions"), message))
                .orElseGet(() -> "");
    }

    private Optional<String> ifHasExceptions(Violations violations) {
        if(violations.hasExceptions())
            return Optional.of(exceptionMessages(violations));
        return Optional.empty();
    }

    private String exceptionMessages(Violations violations) {
        return toJsonArray(violations.exceptions()
                .map(exception -> string(exception.getLocalizedMessage())));
    }
}
