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

    public boolean hasStation(final Station leftStation, final Station rightStation) {
        return (this.leftStation.equals(leftStation) && this.rightStation.equals(rightStation))
                || (this.leftStation.equals(rightStation) && this.rightStation.equals(leftStation));
    }

    public String  getLeftStationName() {
        return leftStation.getName();
    }

    public String  getRightStationName() {
        return rightStation.getName();
    }

    public int getDistance() {
        return distance;
    }

    public int getTime() {
        return time;
    }
}
