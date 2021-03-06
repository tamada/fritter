package jp.cafebabe.fritter.validators;

import jp.cafebabe.fritter.config.CheckerType;
import jp.cafebabe.fritter.config.Level;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class ValidatorsBuilder {
    private static final List<String> AVAILABLE_VALIDATORS = List.of(
            "indent_level","no_else", "primitive_wrapping", "dot_count_per_line",
            "no_abbrev", "lines_of_class", "lines_of_method", "classes_in_package",
            "field_count", "first_class_collection", "no_accessor", "variable_count",
            "no_static_method", "no_new_array", "no_system_exit", "no_return_code_in_printf",
            "single_character_name");

    public Validators build(Level level) {
        return new Validators(buildValidators(level)
                .flatMap(optional -> optional.stream()));
    }

    private Stream<Optional<Validator>> buildValidators(Level level) {
        ValidatorBuilder builder = new ValidatorBuilder();
        return availableValidatorNames(level)
                .map(type -> buildValidator(builder, level, type));
    }

    private Optional<Validator> buildValidator(ValidatorBuilder builder, Level level, CheckerType type) {
        return builder.build(type, level.parameter(type));
    }

    private Stream<CheckerType> availableValidatorNames(Level level) {
        return AVAILABLE_VALIDATORS.stream()
                .map(CheckerType::of)
                .filter(level::available);
    }
}
