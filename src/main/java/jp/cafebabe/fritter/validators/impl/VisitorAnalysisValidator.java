package jp.cafebabe.fritter.validators.impl;

import com.github.javaparser.ast.CompilationUnit;
import io.vavr.control.Try;
import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.entities.sources.DataSource;
import jp.cafebabe.fritter.validators.AbstractValidator;
import jp.cafebabe.fritter.validators.ValidateException;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.Violations;
import jp.cafebabe.fritter.validators.spi.ValidatorService;

import java.util.function.Function;

public class VisitorAnalysisValidator extends AbstractValidator {
    private Function<Validator, FritterASTVisitor> visitorFactory;

    public VisitorAnalysisValidator(ValidatorService service, Parameter parameter,
                                    Function<Validator, FritterASTVisitor> factory) {
        super(service, parameter);
        this.visitorFactory = factory;
    }

    @Override
    public Violations validate(DataSource source, Violations violations) {
        return Try.of(() -> source.ast())
                .map(unit -> toViolations(unit, violations))
                .getOrElseGet(throwable -> new Violations(source, new ValidateException(throwable.getMessage())));
    }

    private Violations toViolations(CompilationUnit unit, Violations violations) {
        FritterASTVisitor visitor = visitorFactory.apply(this);
        unit.accept(visitor, violations);
        return violations;
    }
}
