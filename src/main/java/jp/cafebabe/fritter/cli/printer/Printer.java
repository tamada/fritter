package jp.cafebabe.fritter.cli.printer;

import io.vavr.control.Try;
import jp.cafebabe.fritter.entities.ResultsSet;
import jp.cafebabe.fritter.validators.Validators;

import java.io.PrintWriter;

public interface Printer {
    Summarizer summarizer();

    ValidatorsConverter validatorsConveter();

    default boolean print(Validators validators, ResultsSet rs) {
        return Try.withResources(() -> new PrintWriter(System.out))
                .of(out -> print(out, validators, rs))
                .isSuccess();
    }

    default boolean print(PrintWriter out, Validators validators, ResultsSet rs) {
        printHeader(out, rs);
        printValidators(out, validators);
        printResults(out, rs);
        printSummary(out, rs);
        printFooter(out, rs);
        return true;
    }

    void printValidators(PrintWriter out, Validators validators);

    void printSummary(PrintWriter out, ResultsSet rs);

    void printResults(PrintWriter out, ResultsSet rs);

    default void printHeader(PrintWriter out, ResultsSet rs) {
    }

    default void printFooter(PrintWriter out, ResultsSet rs) {
    }
}
