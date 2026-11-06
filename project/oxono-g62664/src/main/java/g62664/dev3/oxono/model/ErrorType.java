package g62664.dev3.oxono.model;

public enum ErrorType {
    INVALID_MOVE("Error", "Invalid move", "You can't move the totem there"),
    INVALID_INSERT("Error", "Invalid insert", "You can't place a piece there"),
    NO_ERROR("No error", "There is no error", "No error to signal");

    // attributes
    private final String title;
    private final String header;
    private final String message;

    // constructor
    ErrorType(String title, String header, String message) {
        this.title = title;
        this.header = header;
        this.message = message;
    }

    // getters
    public String getTitle() {
        return title;
    }
    public String getHeader() {
        return header;
    }
    public String getMessage() {
        return message;
    }
}
