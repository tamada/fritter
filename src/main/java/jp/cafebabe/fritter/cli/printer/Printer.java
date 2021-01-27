package jp.cafebabe.fritter.cli.printer;

import io.vavr.control.Try;
import jp.cafebabe.fritter.entities.ResultSet;
import jp.cafebabe.fritter.validators.Validators;

import java.io.PrintWriter;

public interface Printer {
    Summarizer summarizer();

    ValidatorsConverter validatorsConveter();

    default boolean print(Validators validators, ResultSet rs) {
        return Try.withResources(() -> new PrintWriter(System.out))
                .of(out -> print(out, validators, rs))
                .isSuccess();
    }

    default boolean print(PrintWriter out, Validators validators, ResultSet rs) {
        printHeader(out, rs);
        printValidators(out, validators);
        printResults(out, rs);
        printSummary(out, rs);
        printFooter(out, rs);
        return true;
    }

    void printValidators(PrintWriter out, Validators validators);

    void printSummary(PrintWriter out, ResultSet rs);

    void printResults(PrintWriter out, ResultSet rs);

    default void printHeader(PrintWriter out, ResultSet rs) {
    }

    default void printFooter(PrintWriter out, ResultSet rs) {
    }
}
