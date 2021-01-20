package jp.cafebabe.fritter.cli.printer;

import jp.cafebabe.fritter.entities.ResultSet;

import java.io.PrintStream;
import java.io.PrintWriter;

public interface Printer {
    boolean print(PrintWriter out, ResultSet rs);

    default boolean print(PrintStream out, ResultSet rs) {
        return print(new PrintWriter(out), rs);
    }

    default boolean print(ResultSet rs) {
        return print(System.out, rs);
    }
}
