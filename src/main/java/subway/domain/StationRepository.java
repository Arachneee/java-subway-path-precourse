package subway.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import subway.exception.ErrorMessage;
import subway.exception.SubwayException;

public class StationRepository {
    private static final List<Station> stations = new ArrayList<>();

    public static List<Station> stations() {
        return Collections.unmodifiableList(stations);
    }

    public static void addStation(final Station station) {
        stations.add(station);
    }

    public static boolean deleteStation(final String name) {
        return stations.removeIf(station -> Objects.equals(station.getName(), name));
    }

    public static void deleteAll() {
        stations.clear();
    }

    public static Station findStationByName(final String name) {
        return stations.stream()
                .filter(station -> Objects.equals(station.getName(), name))
                .findAny()
                .orElseThrow(() -> new SubwayException(ErrorMessage.INVALID_STATION));
    }

    public static List<Station> findAllStationByName(final List<String> shortestPath) {
        return Collections.unmodifiableList(shortestPath.stream()
                .map(StationRepository::findStationByName)
                .collect(Collectors.toList()));
    }
}
