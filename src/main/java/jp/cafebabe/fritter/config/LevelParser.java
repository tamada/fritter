package jp.cafebabe.fritter.config;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jp.cafebabe.fritter.utils.ExceptionableSupplier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class LevelParser {
    public final Level parse(String name) {
        return load(findResource(name))
                .getOrElseThrow(exp -> new IllegalArgumentException(exp));
    }

    public final Level parse(Path path) {
        return load(path)
                .getOrElseThrow(exp -> new IllegalArgumentException(exp));
    }

    private Either<Throwable, Level> load(Path path) {
        return load(() -> Files.newBufferedReader(path));
    }

    private Either<Throwable, Level> load(URL location) {
        return load(() -> new BufferedReader(new InputStreamReader(location.openStream())));
    }

    private Either<Throwable, Level> load(ExceptionableSupplier<BufferedReader, IOException> supplier) {
        return Try.withResources(supplier::get)
                .of(this::loadImpl)
                .toEither();
    }

    private Level loadImpl(BufferedReader in) throws IOException {
        Gson gson = new Gson();
        JsonObject object = gson.fromJson(in, JsonObject.class);
        return new LevelJsonParser().toLevel(object);
    }

    private URL findResource(String name) {
        return getClass().getResource(
                String.format("/resources/%s.json", name));
    }
}
