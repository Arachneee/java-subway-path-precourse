package subway.exception;

public enum ErrorMessage {

    INVALID_FUNCTION("존재하지 않는 기능입니다."),
    INVALID_STATION("존재하지 않는 역입니다."),
    INVALID_MENU_VALUE(" 가 존재하지 않습니다."),
    BLANK("입력이 비어있을 수 없습니다."),
    NOT_NUMBER("숫자가 아닙니다."),
    INVALID_RANGE("잘못된 범위입니다."),
    INVALID_SIZE("잘못된 크기입니다."),
    INVALID_FORMAT("입력 형식이 잘못되었습니다.");

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message;
    }
}
