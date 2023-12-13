package subway.exception;

public enum ErrorMessage {

    INVALID_FUNCTION("존재하지 않는 기능입니다."),
    INVALID_STATION("존재하지 않는 역입니다."),
    DUPLICATE_STATION("출발역과 도착역이 동일합니다."),
    NONE_LINE("출발역과 도착역이 연결되어 있지 않습니다."),
    INVALID_SECTION("구간을 찾을 수 없습니다.");

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message;
    }
}
