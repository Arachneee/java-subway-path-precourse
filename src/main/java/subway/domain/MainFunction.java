package subway.domain;

import java.util.Arrays;

public enum MainFunction {

    READ("1"),
    TERMINATION("Q");

    private final String value;

    MainFunction(String value) {
        this.value = value;
    }

    public static MainFunction from(final String value) {
        return Arrays.stream(values())
                .filter(mainFunction -> mainFunction.value.equals(value))
                .findFirst()
                .orElseThrow(() -> )
    }
}
