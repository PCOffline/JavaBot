package Commands.Parameters;

class InvalidTypeException extends Exception {

    private String message;

    InvalidTypeException() {
        this.message = "Invalid Type";
    }

    InvalidTypeException(String message) {
        this.message = message;
    }
}
