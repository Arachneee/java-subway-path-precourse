package subway.controller;


import subway.domain.Initializer;
import subway.domain.MainFunction;
import subway.domain.Path;
import subway.domain.PathFinder;
import subway.domain.ReadFunction;
import subway.domain.StartEndStation;
import subway.domain.Station;
import subway.domain.StationRepository;
import subway.response.PathDto;
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

    public void start() {
        init();
        run();
        terminate();
    }

    private void init() {
        Initializer.initStation();
        Initializer.initLine();
        Initializer.initSection();

        PathFinder.init();
    }

    private void run() {
        while (true) {
            outputView.printMainFunction();
            final MainFunction mainFunction = getMainFunction();

            if (mainFunction.isRead()) {
                runReadFunction();
                continue;
            }
            // MainFunction is TERMINATION
            break;
        }
    }

    private MainFunction getMainFunction() {
        return ExceptionRoofer.supply(() -> {
            final String mainFunction = inputView.readFunction();
            return MainFunction.from(mainFunction);
        });
    }

    private void runReadFunction() {
        ExceptionRoofer.run(() -> {
            outputView.printReadFunction();
            final ReadFunction readFunction = getReadFunction();

            if (readFunction.isBack()) {
                return;
            }

            final StartEndStation startEndStation = getStartEndStation();

            final Path path = getPath(readFunction, startEndStation);
            final PathDto pathDto = PathDto.from(path);
            outputView.printResult(pathDto);
        });
    }

    private ReadFunction getReadFunction() {
        return ExceptionRoofer.supply(() -> {
            final String readFunction = inputView.readFunction();
            return ReadFunction.from(readFunction);
        });
    }

    private StartEndStation getStartEndStation() {
        final Station startStation = getStartStation();
        final Station endStation = getEndStation();
        return StartEndStation.of(startStation, endStation);
    }

    private Station getStartStation() {
        String startStationName = inputView.readStartStation();
        return StationRepository.findStationByName(startStationName);
    }

    private Station getEndStation() {
        String endStationName = inputView.readEndStation();
        return StationRepository.findStationByName(endStationName);
    }

    private Path getPath(final ReadFunction readFunction, final StartEndStation startEndStation) {
        if (readFunction.isDistance()) {
            return startEndStation.getShortestDistancePath();
        }
        // ReadFunction is TIME
        return startEndStation.getShortestTimePath();
    }

    private void terminate() {
        inputView.close();
    }
}
