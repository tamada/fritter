package jp.cafebabe.fritter.cli.printer.markdown;

import jp.cafebabe.fritter.cli.printer.Converter;
import jp.cafebabe.fritter.entities.Pair;
import jp.cafebabe.fritter.entities.ResultSet;
import jp.cafebabe.fritter.entities.sources.DataSource;
import jp.cafebabe.fritter.entities.sources.SourcePool;
import jp.cafebabe.fritter.validators.Violations;

import java.io.PrintWriter;
import java.util.stream.Stream;

public class ResultSetMarkdowner extends Markdowner<ResultSet> {
    private ViolationsMarkdowner markdowner;

    public ResultSetMarkdowner(PrintWriter out) {
        super(out);
        this.markdowner = new ViolationsMarkdowner(out);
    }

    @Override
    public String convert(ResultSet item) {
        out.printf("## Results%n%n");
        printPools(item, item.pools());
        return "";
    }

    private void printPools(ResultSet rs, Stream<SourcePool> pools) {
        pools.peek(pool -> out.printf("### %s%n%n", pool.base()))
                .forEach(pool -> printViolations(rs.stream(pool)));
    }

    private void printViolations(Stream<Pair<DataSource, Violations>> stream) {
        stream.map(pair -> pair.reduce((left, right) -> right))
                .forEach(violations -> markdowner.convert(violations));
    }
}
