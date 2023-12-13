package subway.domain;

import java.util.Arrays;
import subway.exception.ErrorMessage;
import subway.exception.SubwayException;

public enum ReadFunction {

    DISTANCE("1"),
    TIME("2"),
    BACK("B");

    private final String value;

    ReadFunction(String value) {
        this.value = value;
    }

    public static ReadFunction from(final String value) {
        return Arrays.stream(values())
                .filter(mainFunction -> mainFunction.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new SubwayException(ErrorMessage.INVALID_FUNCTION));
    }

    public boolean isDistance() {
        return this.equals(DISTANCE);
    }

    public boolean isTime() {
        return this.equals(TIME);
    }

    public boolean isBack() {
        return this.equals(BACK);
    }
}
