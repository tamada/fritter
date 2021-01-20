package jp.cafebabe.fritter.entities;

public class Message {
    private String message;

    private Message(String formatter, Object... args) {
        this.message = String.format(formatter, args);
    }

    @Override
    public String toString() {
        return message;
    }

    public static final Message format(String formatter, Object... args) {
        return new Message(formatter, args);
    }
}
