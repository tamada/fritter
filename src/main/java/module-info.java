import jp.cafebabe.fritter.validators.impl.fcc.NoPrimitivesValidatorService;

module jp.cafebabe.fritter {
    requires transitive com.github.javaparser.core;
    requires transitive io.vavr;
    requires info.picocli;
    requires com.google.gson;
    requires com.google.common;

    provides jp.cafebabe.fritter.cli.printer.PrinterService with
            jp.cafebabe.fritter.cli.printer.json.JsonPrinterService,
            jp.cafebabe.fritter.cli.printer.markdown.MarkdownPrinterService,
            jp.cafebabe.fritter.cli.printer.xml.XmlPrinterService;

    provides jp.cafebabe.fritter.validators.spi.ValidatorService with
            jp.cafebabe.fritter.validators.impl.fields.FieldCountValidatorService,
            jp.cafebabe.fritter.validators.impl.fcc.FirstClassCollectionValidatorService,
            jp.cafebabe.fritter.validators.impl.nest.NestLevelValidatorService,
            jp.cafebabe.fritter.validators.impl.accessor.NoAccessorValidatorService,
            jp.cafebabe.fritter.validators.impl.elsestatement.NoElseValidatorService,
            jp.cafebabe.fritter.validators.impl.noexit.NoSystemExitValidatorService,
            jp.cafebabe.fritter.validators.impl.nonewarray.NoNewArrayValidatorService,
            jp.cafebabe.fritter.validators.impl.nort.NoReturnCodeInPrintfValidatorService,
            jp.cafebabe.fritter.validators.impl.nostatic.NoStaticMethodValidatorService,
            jp.cafebabe.fritter.validators.impl.onedot.OneDotPerLineValidatorService,
            NoPrimitivesValidatorService,
            jp.cafebabe.fritter.validators.impl.smallentities.LinesOfMethodValidatorService,
            jp.cafebabe.fritter.validators.impl.smallentities.LinesOfClassValidatorService,
            jp.cafebabe.fritter.validators.impl.variables.VariableCountValidatorService,
            jp.cafebabe.fritter.validators.impl.variables.SingleCharacterNameValidatorService,
            jp.cafebabe.fritter.validators.impl.smallentities.ClassCountInPackageValidatorService;

    uses jp.cafebabe.fritter.validators.spi.ValidatorService;
    uses jp.cafebabe.fritter.cli.printer.PrinterService;

    exports jp.cafebabe.fritter.cli.printer;
    exports jp.cafebabe.fritter.config;
    exports jp.cafebabe.fritter.utils;
    exports jp.cafebabe.fritter.cli.options;
    exports jp.cafebabe.fritter.entities;
    exports jp.cafebabe.fritter.validators;
    exports jp.cafebabe.fritter.validators.spi;

    opens jp.cafebabe.fritter.cli.printer;
    opens jp.cafebabe.fritter.config;
    opens jp.cafebabe.fritter.utils;
    opens jp.cafebabe.fritter.cli.options;
    opens jp.cafebabe.fritter.entities;
    opens jp.cafebabe.fritter.validators;
    opens jp.cafebabe.fritter.validators.spi;
}