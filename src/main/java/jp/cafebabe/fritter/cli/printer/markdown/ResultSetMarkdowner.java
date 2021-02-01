package jp.cafebabe.fritter.cli.printer.markdown;

import jp.cafebabe.fritter.entities.Pair;
import jp.cafebabe.fritter.entities.ResultsSet;
import jp.cafebabe.fritter.entities.sources.DataSource;
import jp.cafebabe.fritter.entities.sources.SourcePool;
import jp.cafebabe.fritter.validators.Violations;

import java.io.PrintWriter;
import java.util.stream.Stream;

public class ResultSetMarkdowner extends Markdowner<ResultsSet> {
    private ViolationsMarkdowner markdowner;

    public ResultSetMarkdowner(PrintWriter out) {
        super(out);
        this.markdowner = new ViolationsMarkdowner(out);
    }

    @Override
    public String convert(ResultsSet item) {
        out.printf("## Results%n%n");
        printPools(item, item.pools());
        return "";
    }

    private void printPools(ResultsSet rs, Stream<SourcePool> pools) {
        pools.peek(pool -> out.printf("### %s%n%n", pool.base()))
                .forEach(pool -> printViolations(rs.stream(pool)));
    }

    private void printViolations(Stream<Pair<DataSource, Violations>> stream) {
        stream.map(pair -> pair.reduce((left, right) -> right))
                .forEach(violations -> markdowner.convert(violations));
    }
}
