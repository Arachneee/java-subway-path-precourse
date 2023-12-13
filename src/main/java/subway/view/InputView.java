package subway.view;


import java.util.Scanner;

public class InputView {

    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void close() {
        scanner.close();
    }

    public String readFunction() {
        System.out.println(System.lineSeparator() + Request.FUNCTION.value);
        return scanner.nextLine();
    }

    private enum Request {
        FUNCTION("## 원하는 기능을 선택하세요.");
        private final String value;

        Request(final String value) {
            this.value = value;
        }
    }
}
