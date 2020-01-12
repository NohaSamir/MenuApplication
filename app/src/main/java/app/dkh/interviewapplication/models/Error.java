package app.dkh.interviewapplication.models;

public class Error {

    private int code;
    private String message;

    public Error(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Error getConnectionError() {
        return new Error(0, "Check your internet connection");
    }

    public static Error getError() {
        return new Error(1, "Something went wrong. Please try again.");
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
