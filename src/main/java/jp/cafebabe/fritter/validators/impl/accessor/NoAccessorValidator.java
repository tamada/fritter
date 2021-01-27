package jp.cafebabe.fritter.validators.impl.accessor;

import io.vavr.control.Try;
import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.entities.sources.DataSource;
import jp.cafebabe.fritter.validators.AbstractValidator;
import jp.cafebabe.fritter.validators.ValidateException;
import jp.cafebabe.fritter.validators.Violations;
import jp.cafebabe.fritter.validators.impl.FritterASTVisitor;
import jp.cafebabe.fritter.validators.spi.ValidatorService;
import org.eclipse.jdt.core.dom.CompilationUnit;

public class NoAccessorValidator extends AbstractValidator {
    private AccessorChecker checker = new AccessorChecker();

    public NoAccessorValidator(ValidatorService service, Parameter parameter) {
        super(service, parameter);
    }

    @Override
    public Violations validate(DataSource source) {
        return Try.of(() -> source.ast())
                .map(unit -> toViolations(unit, source))
                .getOrElseGet(throwable -> new Violations(source, new ValidateException(throwable.getMessage())));
    }

    private Violations toViolations(CompilationUnit unit, DataSource source) {
        FritterASTVisitor visitor = new NoAccessorVisitor(this);
        unit.accept(visitor);
        return new Violations(source, visitor.violations());
    }
}