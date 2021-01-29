package jp.cafebabe.fritter.cli.printer.xml;

public class XmlUtils {
    public static final String escape(Object object) {
        return escape(object.toString());
    }

    public static final String escape(String string) {
        string = string.replaceAll("&", "&amp;");
        string = string.replaceAll("\"", "&quot;");
        string = string.replaceAll("<", "&lt;");
        return string.replaceAll(">", "&gt;");
    }
}
