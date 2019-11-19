package sn.modelsis.signart.exception;

@SuppressWarnings("serial")
public class SignArtException extends Exception {

    String message;

    public SignArtException(final String message) {
        super(message);
        this.message = message;
    }

    public SignArtException(final String message, final Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
