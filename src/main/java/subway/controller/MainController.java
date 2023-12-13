package subway.controller;


import java.util.Arrays;
import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.MainFunction;
import subway.domain.Path;
import subway.domain.PathFinder;
import subway.domain.ReadFunction;
import subway.domain.Section;
import subway.domain.SectionRepository;
import subway.domain.Station;
import subway.domain.StationRepository;
import subway.exception.ErrorMessage;
import subway.exception.SubwayException;
import subway.util.ExceptionRoofer;
import subway.view.InputView;
import subway.view.OutputView;

public class MainController {

    private final InputView inputView;
    private final OutputView outputView;

    public MainController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        init();

        while (true) {
            outputView.printMainFunction();
            MainFunction mainFunction = getMainFunction();

            if (mainFunction.isRead()) {
                runReadFunction();
                continue;
            }

            // MainFunction is TERMINATION
            break;
        }

        inputView.close();
    }

    private void init() {
        String stationNames = "교대역, 강남역, 역삼역, 남부터미널역, 양재역, 양재시민의숲역, 매봉역";
        String lineNames = "2호선, 3호선, 신분당선";

        Arrays.stream(stationNames.split(", "))
                .map(Station::new)
                .forEach(StationRepository::addStation);

        Arrays.stream(lineNames.split(", "))
                .map(Line::new)
                .forEach(LineRepository::addLine);

        addSection("교대역", "강남역", 2, 3);
        addSection("강남역", "역삼역", 2, 3);
        addSection("교대역", "남부터미널역", 3, 2);
        addSection("남부터미널역", "양재역", 6, 5);
        addSection("양재역", "매봉역", 1, 1);
        addSection("강남역", "양재역", 2, 8);
        addSection("양재역", "양재시민의숲역", 10, 3);

        PathFinder.init();
    }

    private void addSection(
            final String fromStationName,
            final String toStationName,
            final int distance,
            final int time) {

        Station fromStation = StationRepository.findStationByName(fromStationName);
        Station toStation = StationRepository.findStationByName(toStationName);
        Section section = new Section(fromStation, toStation, distance, time);
        SectionRepository.addSection(section);
    }

    private MainFunction getMainFunction() {
        return ExceptionRoofer.supply(() -> {
            String mainFunction = inputView.readFunction();
            return MainFunction.from(mainFunction);
        });
    }

    private void runReadFunction() {
        ExceptionRoofer.run(() -> {
            outputView.printReadFunction();
            ReadFunction readFunction = getReadFunction();

            if (readFunction.isBack()) {
                return;
            }

            String startStationName = inputView.readStartStation();
            Station startStation = StationRepository.findStationByName(startStationName);

            String endStationName = inputView.readEndStation();
            Station endStation = StationRepository.findStationByName(endStationName);

            if (startStation.equals(endStation)) {
                throw new SubwayException(ErrorMessage.DUPLICATE_STATION);
            }

            if (readFunction.isDistance()) {
                runDistanceFunction(startStation, endStation);
                return;
            }

            if (readFunction.isTime()) {
                runTimeFunction(startStation, endStation);
            }

            // ReadFunction is Back
        });
    }

    private ReadFunction getReadFunction() {
        return ExceptionRoofer.supply(() -> {
            String readFunction = inputView.readFunction();
            return ReadFunction.from(readFunction);
        });
    }

    private void runDistanceFunction(final Station startStation, final Station endStation) {
        Path path = PathFinder.findShortestDistancePath(startStation.getName(), endStation.getName());
    }

    private void runTimeFunction(final Station startStation, final Station endStation) {
        Path path = PathFinder.findShortestTimePath(startStation.getName(), endStation.getName());
    }
}
