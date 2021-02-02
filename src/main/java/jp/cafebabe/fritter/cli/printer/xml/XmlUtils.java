package jp.cafebabe.fritter.cli.printer.xml;

public class XmlUtils {
    public static final String escape(Object object) {
        return escape(object.toString());
    }

    public static final String escape(String string) {
        return string.replaceAll("&", "&amp;")
                .replaceAll("\"", "&quot;")
                .replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;");
    }
}
