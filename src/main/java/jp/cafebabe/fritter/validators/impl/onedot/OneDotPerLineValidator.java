package jp.cafebabe.fritter.validators.impl.onedot;

import io.vavr.control.Try;
import jp.cafebabe.fritter.config.CheckerType;
import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.entities.Location;
import jp.cafebabe.fritter.entities.Message;
import jp.cafebabe.fritter.entities.Pair;
import jp.cafebabe.fritter.entities.Value;
import jp.cafebabe.fritter.entities.sources.DataSource;
import jp.cafebabe.fritter.validators.AbstractValidator;
import jp.cafebabe.fritter.validators.Violation;
import jp.cafebabe.fritter.validators.Violations;
import jp.cafebabe.fritter.validators.spi.ValidatorService;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class OneDotPerLineValidator extends AbstractValidator {
    private static final String formatter = "many dot per line (%d dots founds)";
    private final StringFilterManager manager = new StringFilterManager();

    public OneDotPerLineValidator(ValidatorService service, Parameter param) {
        super(service, param);
    }

    @Override
    public Violations validate(DataSource source, Violations violations) {
        return Try.withResources(() -> dotCountStream(source))
                .of(stream -> addTo(violations, stream))
                .getOrElse(() -> violations);
    }

    private Violations addTo(Violations violations, Stream<Pair<Location.LineNumber, Integer>> stream) {
        stream.map(this::toViolation)
                .forEach(violation -> violations.add(violation));
        return violations;
    }

    private Violation toViolation(Pair<Location.LineNumber, Integer> pair) {
        return new Violation(pair.reduce((l, r) -> l), name(),
                Message.format(formatter, new Object[] { pair.reduce((l, r) -> r) }));
    }

    private Stream<Pair<Location.LineNumber, Integer>> dotCountStream(DataSource source) throws IOException {
        return source.lines()
                .map(this::prepareLine)
                .map(this::dotCount)
                .filter(pair -> pair.test((num, count) -> parameter().lessThan(new Value(count))));
    }

    private Pair<Location.LineNumber, String> prepareLine(Pair<Location.LineNumber, String> pair) {
        return pair.map(l -> l, manager::filter);
    }

    private Pair<Location.LineNumber, Integer> dotCount(Pair<Location.LineNumber, String> pair) {
        return pair.map(number -> number, this::countDot);
    }

    private int countDot(String line) {
        Pattern pattern = Pattern.compile("[^.]\\.[^0-9]]");
        return countDotImpl(pattern.matcher(line));
    }

    private int countDotImpl(Matcher matcher) {
        int value = 0;
        while(matcher.find())
            value++;
        return value;
    }
}
