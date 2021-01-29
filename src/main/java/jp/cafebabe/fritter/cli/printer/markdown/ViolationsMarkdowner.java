package jp.cafebabe.fritter.cli.printer.markdown;

import jp.cafebabe.fritter.entities.sources.DataSource;
import jp.cafebabe.fritter.entities.visitors.ViolationsVisitor;
import jp.cafebabe.fritter.validators.ValidateException;
import jp.cafebabe.fritter.validators.Violation;
import jp.cafebabe.fritter.validators.Violations;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ViolationsMarkdowner extends Markdowner<Violations> implements ViolationsVisitor<String> {
    private ViolationMarkdowner markdowner;
    private List<ValidateException> exceptions = new ArrayList<>();

    public ViolationsMarkdowner(PrintWriter out) {
        super(out);
        markdowner = new ViolationMarkdowner(out);
    }

    @Override
    public String convert(Violations item) {
        item.accept(this);
        return "";
    }

    @Override
    public void visitDataSource(DataSource source) {
        out.printf("%n#### %s%n%n##### Violations%n%n", source.relativePath());
    }

    @Override
    public void visitViolation(Violation violation) {
        markdowner.convert(violation);
    }

    @Override
    public void visitValidateException(ValidateException e) {
        exceptions.add(e);
    }

    @Override
    public String visitEnd() {
        if(exceptions.size() > 0)
            printExceptions();
        return "";
    }

    private void printExceptions() {
        out.printf("##### Exceptions%n%n");
        exceptions.forEach(exp -> printException(exp));
    }

    private void printException(ValidateException e) {
        out.printf("* %s%n    * ", e.getClass().getName());
        e.printStackTrace(out);
    }
}
