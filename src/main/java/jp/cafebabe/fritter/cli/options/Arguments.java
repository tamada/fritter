package jp.cafebabe.fritter.cli.options;

import jp.cafebabe.fritter.entities.ResultsSet;
import jp.cafebabe.fritter.entities.sources.DataSource;
import jp.cafebabe.fritter.entities.sources.SourcePool;
import jp.cafebabe.fritter.entities.sources.SourcePoolFactory;
import jp.cafebabe.fritter.validators.Validators;
import picocli.CommandLine.Mixin;
import picocli.CommandLine.Parameters;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Arguments {
    @Mixin
    private ValidatorOptions validatorOpts = new ValidatorOptions();
    @Mixin
    private ResultOptions results = new ResultOptions();

    @Parameters(arity="1..*", paramLabel="SOURCE_FILEs|SOURCE_DIRs", description="Java source files and/or directories containing Java source files.")
    private List<Path> parameters = new ArrayList<>();

    public Stream<Path> parameters() {
        return parameters.stream();
    }

    public void print(Validators validators, ResultsSet rs) {
        results.print(validators, rs);
    }

    public Stream<DataSource> createStream() {
        return parallelOrSequentialStream(stream()
                .flatMap(pool -> pool.stream()));
    }

    private Stream<DataSource> parallelOrSequentialStream(Stream<DataSource> stream) {
        if(validatorOpts.isWithThread())
            return stream.parallel();
        return stream.sequential();
    }

    public Validators createValidators() {
        return validatorOpts.createValidators();
    }

    public Stream<SourcePool> stream() {
        SourcePoolFactory factory = new SourcePoolFactory();
        return parameters().map(path -> factory.create(path));
    }
}
