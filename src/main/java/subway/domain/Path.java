package subway.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Path {

    private final List<Station> stations;
    private final int distance;
    private final int time;

    private Path(List<Station> stations, int distance, int time) {
        this.stations = stations;
        this.distance = distance;
        this.time = time;
    }

    public static Path create(final List<Station> shortestPath) {
        final int pathSize = shortestPath.size();
        int distance = 0;
        int time = 0;

        for (int i = 0; i < pathSize - 1; i++) {
            Section section = SectionRepository.findSection(shortestPath.get(i), shortestPath.get(i + 1));
            distance += section.getDistance();
            time += section.getTime();
        }

        return new Path(shortestPath, distance, time);
    }

    public List<String> getStationNames() {
        return stations.stream()
                .map(Station::getName)
                .collect(Collectors.toList());
    }

    public int getDistance() {
        return distance;
    }

    public int getTime() {
        return time;
    }
}
