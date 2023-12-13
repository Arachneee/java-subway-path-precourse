package subway.controller;


import subway.domain.MainFunction;
import subway.domain.ReadFunction;
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
        outputView.printReadFunction();
        ReadFunction readFunction = getReadFunction();
    }

    private ReadFunction getReadFunction() {
        return ExceptionRoofer.supply(() -> {
            String readFunction = inputView.readFunction();
            return ReadFunction.from(readFunction);
        });
    }
}
