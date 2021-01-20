package jp.cafebabe.fritter.config;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
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
    public final Optional<Level> parse(String name) {
        return findResource(name)
                .map(this::load)
                .orElseThrow(() -> new IllegalArgumentException(name));
    }

    public Optional<Level> load(Path path) {
        return load(() -> Files.newBufferedReader(path));
    }

    private Optional<Level> load(URL location) {
        return load(() -> new BufferedReader(new InputStreamReader(location.openStream())));
    }

    private Optional<Level> load(ExceptionableSupplier<BufferedReader, IOException> supplier) {
        return Try.withResources(() -> supplier.get())
                .of(reader -> loadImpl(reader))
                .toJavaOptional();
    }

    private Level loadImpl(BufferedReader in) throws IOException {
        Gson gson = new Gson();
        JsonObject object = gson.fromJson(in, JsonObject.class);
        return new LevelJsonParser().toLevel(object);
    }

    private Optional<URL> findResource(String name) {
        return Optional.ofNullable(
                getClass().getResource(
                String.format("/resources/%s.json", name)));
    }
}
