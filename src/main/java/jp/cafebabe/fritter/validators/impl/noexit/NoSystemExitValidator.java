package jp.cafebabe.fritter.validators.impl.noexit;

import io.vavr.control.Try;
import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.entities.sources.DataSource;
import jp.cafebabe.fritter.validators.ValidateException;
import jp.cafebabe.fritter.validators.Violations;
import jp.cafebabe.fritter.validators.AbstractValidator;
import jp.cafebabe.fritter.validators.impl.FritterASTVisitor;
import jp.cafebabe.fritter.validators.spi.ValidatorService;
import org.eclipse.jdt.core.dom.CompilationUnit;

public class NoSystemExitValidator extends AbstractValidator {
    public NoSystemExitValidator(ValidatorService service, Parameter param) {
        super(service, param);
    }

    @Override
    public Violations validate(DataSource source) {
        return Try.of(() -> source.ast())
                .map(unit -> toViolations(unit, source))
                .getOrElseGet(throwable -> new Violations(source, new ValidateException(throwable)));
    }

    private Violations toViolations(CompilationUnit unit, DataSource source) {
        FritterASTVisitor visitor = new NoSystemExitVisitor(this);
        unit.accept(visitor);
        return new Violations(source, visitor.violations());
    }
}
