package subway.controller;


import subway.domain.MainFunction;
import subway.domain.ReadFunction;
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
                runDistanceFunction();
            }

            if (readFunction.isTime()) {
                runTimeFunction();
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

    private void runDistanceFunction() {

    }

    private void runTimeFunction() {

    }
}
