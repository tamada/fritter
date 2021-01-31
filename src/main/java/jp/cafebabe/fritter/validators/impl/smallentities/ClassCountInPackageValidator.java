package jp.cafebabe.fritter.validators.impl.smallentities;

import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.entities.Location;
import jp.cafebabe.fritter.entities.Message;
import jp.cafebabe.fritter.entities.sources.DataSource;
import jp.cafebabe.fritter.entities.sources.DefaultDataSource;
import jp.cafebabe.fritter.validators.AbstractValidator;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.Violation;
import jp.cafebabe.fritter.validators.Violations;
import jp.cafebabe.fritter.validators.spi.ValidatorService;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class ClassCountInPackageValidator extends AbstractValidator {
    private static final String FORMATTER = "class count is %s, more than %s";

    private Map<Path, JavaSourceCount> classCount = new HashMap<>();

    public ClassCountInPackageValidator(ValidatorService service, Parameter parameter) {
        super(service, parameter);
    }

    @Override
    public Violations validate(DataSource source, Violations violations) {
        Path packagePath = source.relativePath().getParent();
        Optional<Violations> opts = Optional.empty();
        if(!classCount.containsKey(packagePath))
            opts = checkViolation(source, packagePath);
        return opts.orElseGet(() -> new Violations(new DefaultDataSource(source.sourcePool(), source.path().getParent())));
    }

    private Optional<Violations> checkViolation(DataSource source, Path packagePath) {
        JavaSourceCount count = countJavaSource(source.path().getParent());
        classCount.put(packagePath, count);
        return checkViolationImpl(source, packagePath, count);
    }

    private JavaSourceCount countJavaSource(Path path) {
        return new JavaSourceCount(DirectoryUtils.countJavaFiles(path));
    }

    private Optional<Violations> checkViolationImpl(DataSource source, Path packagePath, JavaSourceCount count) {
        if(count.greaterThan(parameter()))
            return Optional.of(createViolations(source, packagePath, count));
        return Optional.empty();
    }

    private Violations createViolations(DataSource source, Path packagePath, JavaSourceCount count) {
        return new Violations(new DefaultDataSource(source.sourcePool(), source.path().getParent()),
                Stream.of(new Violation(Location.of(packagePath), name(),
                        Message.format(FORMATTER, count, parameter()))));
    }
}
