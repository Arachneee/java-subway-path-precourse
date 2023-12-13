package subway.view;

public class OutputView {

    public void printError(final String message) {
        System.out.println(message);
    }

    public void printMainFunction() {
        System.out.println(Response.MAIN_FUNCTION.value);
    }

    private enum Response {
        MAIN_FUNCTION("## 메인 화면\n"
                + "1. 경로 조회\n"
                + "Q. 종료");

        private final String value;

        Response(final String value) {
            this.value = value;
        }

        public String getWithEnter() {
            return value + System.lineSeparator();
        }
    }
}
