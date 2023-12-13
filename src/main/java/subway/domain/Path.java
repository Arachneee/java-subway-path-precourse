package subway.domain;

import java.util.List;

public class Path {

    private final List<Station> stations;
    private final int distance;
    private final int time;

    private Path(List<Station> stations, int distance, int time) {
        this.stations = stations;
        this.distance = distance;
        this.time = time;
    }

    public static Path create(List<String> shortestPath) {

    }
}
