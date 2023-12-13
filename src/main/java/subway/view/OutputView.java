package subway.view;

import java.util.List;
import subway.response.PathDto;

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

    public void printResult(final PathDto pathDto) {
        System.out.printf(Response.RESULT_FORMAT.getWithEnter(), pathDto.getDistance(), pathDto.getTime());
        printStations(pathDto.getStationNames());
    }

    private void printStations(List<String> stationNames) {
        stationNames.forEach(stationName -> System.out.println(Response.PREFIX + stationName));
        System.out.println();
    }

    private enum Response {
        MAIN_FUNCTION("## 메인 화면\n"
                + "1. 경로 조회\n"
                + "Q. 종료"),
        READ_FUNCTION("## 경로 기준\n"
                + "1. 최단 거리\n"
                + "2. 최소 시간 \n"
                + "B. 돌아가기"),
        RESULT_FORMAT("## 조회 결과\n"
                + "[INFO] ---\n"
                + "[INFO] 총 거리: %dkm\n"
                + "[INFO] 총 소요 시간: %d분\n"
                + "[INFO] ---");

        private static final String PREFIX = "[INFO] ";
        private final String value;

        Response(final String value) {
            this.value = value;
        }

        public String getWithEnter() {
            return value + System.lineSeparator();
        }
    }
}
