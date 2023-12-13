package subway.exception;

public class SubwayException extends IllegalArgumentException {

    public SubwayException(final ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
