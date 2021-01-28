package jp.cafebabe.fritter.validators;

import io.vavr.control.Try;
import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.entities.sources.DataSource;
import jp.cafebabe.fritter.validators.impl.FritterASTVisitor;
import jp.cafebabe.fritter.validators.spi.ValidatorService;
import org.eclipse.jdt.core.dom.CompilationUnit;

import java.util.function.Function;

public class VisitorAnalysisValidator extends AbstractValidator {
    private Function<Validator, FritterASTVisitor> visitorFactory;

    public VisitorAnalysisValidator(ValidatorService service, Parameter parameter,
                                    Function<Validator, FritterASTVisitor> factory) {
        super(service, parameter);
        this.visitorFactory = factory;
    }

    @Override
    public Violations validate(DataSource source) {
        return Try.of(() -> source.ast())
                .map(unit -> toViolations(unit, source))
                .getOrElseGet(throwable -> new Violations(source, new ValidateException(throwable.getMessage())));
    }

    private Violations toViolations(CompilationUnit unit, DataSource source) {
        FritterASTVisitor visitor = visitorFactory.apply(this);
        unit.accept(visitor);
        return new Violations(source, visitor.violations());
    }

}
