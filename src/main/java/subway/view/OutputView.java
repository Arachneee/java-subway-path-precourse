package subway.view;

public class OutputView {

    public void printError(final String message) {
        System.out.println(message);
    }

    public void printMainFunction() {
        System.out.println(Response.MAIN_FUNCTION.value);
    }

    public void printReadFunction() {
        System.out.println(Response.READ_FUNCTION.value);
    }

    private enum Response {
        MAIN_FUNCTION("## 메인 화면\n"
                + "1. 경로 조회\n"
                + "Q. 종료"),
        READ_FUNCTION("## 경로 기준\n"
                + "1. 최단 거리\n"
                + "2. 최소 시간 \n"
                + "B. 돌아가기");

        private final String value;

        Response(final String value) {
            this.value = value;
        }

        public String getWithEnter() {
            return value + System.lineSeparator();
        }
    }
}
