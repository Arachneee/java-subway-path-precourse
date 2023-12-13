package subway.controller;


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
        getMainFunction();

        String mainFunction = inputView.readMainFunction();

        inputView.close();
    }
}
