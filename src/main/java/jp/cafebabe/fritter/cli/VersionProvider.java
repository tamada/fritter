package jp.cafebabe.fritter.cli;

import picocli.CommandLine.IVersionProvider;

public class VersionProvider implements IVersionProvider {
    @Override
    public String[] getVersion() throws Exception {
        return new String[] {
                "fritter 1.0.0",
        };
    }
}
