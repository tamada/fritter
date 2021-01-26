package jp.cafebabe.fritter.validators.impl.noexit;

import io.vavr.control.Try;
import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.entities.sources.DataSource;
import jp.cafebabe.fritter.validators.ValidateException;
import jp.cafebabe.fritter.validators.Violations;
import jp.cafebabe.fritter.validators.AbstractValidator;
import jp.cafebabe.fritter.validators.impl.FritterASTVisitor;
import jp.cafebabe.fritter.validators.spi.ValidatorService;

public class NoSystemExitValidator extends AbstractValidator {
    public NoSystemExitValidator(ValidatorService service, Parameter param) {
        super(service, param);
    }

    @Override
    public Violations validate(DataSource source) throws ValidateException {
        FritterASTVisitor visitor = new NoSystemExitVisitor(this);
        Try.of(() -> source.ast())
                .andThen(unit -> unit.accept(visitor));
        return new Violations(source, visitor.violations());
    }
}
