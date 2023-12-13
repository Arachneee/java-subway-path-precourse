package subway.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        return new Path(shortestPath, calculateDistance(shortestPath), calculateTime(shortestPath));
    }

    private static int calculateDistance(final List<Station> shortestPath) {
        final int pathSize = shortestPath.size();

        return IntStream.range(0, pathSize - 1)
                .map(i -> SectionRepository.findSection(shortestPath.get(i), shortestPath.get(i + 1)).getDistance())
                .sum();
    }

    private static int calculateTime(final List<Station> shortestPath) {
        final int pathSize = shortestPath.size();

        return IntStream.range(0, pathSize - 1)
                .map(i -> SectionRepository.findSection(shortestPath.get(i), shortestPath.get(i + 1)).getTime())
                .sum();
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
