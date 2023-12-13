package subway.domain;

public class Section {

    private final Station leftStation;
    private final Station rightStation;
    private final int distance;
    private final int time;

    public Section(Station leftStation, Station rightStation, int distance, int time) {
        this.leftStation = leftStation;
        this.rightStation = rightStation;
        this.distance = distance;
        this.time = time;
    }
}
