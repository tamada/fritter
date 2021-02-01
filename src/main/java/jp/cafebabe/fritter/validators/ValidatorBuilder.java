package jp.cafebabe.fritter.validators;

import jp.cafebabe.fritter.config.CheckerType;
import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.spi.ValidatorService;

import java.util.Map;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public class ValidatorBuilder {
    private Map<CheckerType, ValidatorService> map;

    public ValidatorBuilder() {
        this.map = ServiceLoader.load(ValidatorService.class)
                .stream()
                .map(provider -> provider.get())
                .collect(Collectors.toMap(s -> s.name(), s -> s, (a, b) -> a));
    }

    public Optional<Validator> build(CheckerType type, Parameter param) {
        return Optional.ofNullable(map.get(type))
                .map(service -> service.build(param));
    }
}
