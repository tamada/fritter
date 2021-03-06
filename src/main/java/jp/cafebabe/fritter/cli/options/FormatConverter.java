package jp.cafebabe.fritter.cli.options;

import jp.cafebabe.fritter.config.Format;
import jp.cafebabe.fritter.utils.CamelCaseUtils;
import picocli.CommandLine.ITypeConverter;

public class FormatConverter implements ITypeConverter<Format> {
    @Override
    public Format convert(String type) {
        String lowerType = type.toLowerCase();
        return Format.valueOf(
                CamelCaseUtils.upperCamelCase(lowerType));
    }
}
