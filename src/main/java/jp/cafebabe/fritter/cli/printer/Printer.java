package jp.cafebabe.fritter.cli.printer;

import io.vavr.control.Try;
import jp.cafebabe.fritter.entities.Pair;
import jp.cafebabe.fritter.entities.ResultSet;
import jp.cafebabe.fritter.entities.sources.DataSource;
import jp.cafebabe.fritter.entities.sources.SourcePool;
import jp.cafebabe.fritter.validators.Violation;
import jp.cafebabe.fritter.validators.Violations;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.stream.Stream;

public interface Printer {
    default boolean print(ResultSet rs) {
        return Try.withResources(() -> new PrintWriter(System.out))
                .of(out -> print(out, rs))
                .isSuccess();
    }

    default boolean print(PrintWriter out, ResultSet rs) {
        printHeader(out, rs);
        printBody(out, rs);
        printSummary(out, rs);
        printFooter(out, rs);
        return true;
    }

    default void printSummary(PrintWriter out, ResultSet rs) {
        out.print(summarizer().summary(rs));
    }

    default void printBody(PrintWriter out, ResultSet rs) {
    }

    default void printHeader(PrintWriter out, ResultSet rs) {
    }

    default void printFooter(PrintWriter out, ResultSet rs) {
    }

    Summarizer summarizer();

    Stream<Pair<DataSource, Violations>> stream(ResultSet rs, SourcePool pool);

    String convert(ResultSet rs);

    String convert(DataSource source, Violations violations);

    String convert(Violations violations);

    String convert(Violation violation);
}
