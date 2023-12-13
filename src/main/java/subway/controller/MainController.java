package subway.controller;


import subway.domain.MainFunction;
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
        outputView.printMainFunction();
        MainFunction mainFunction = getMainFunction();


        inputView.close();
    }

    private MainFunction getMainFunction() {
        return ExceptionRoofer.supply(() -> {
            String mainFunction = inputView.readMainFunction();
            return MainFunction.from(mainFunction);
        });
    }
}
