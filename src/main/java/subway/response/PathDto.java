package subway.response;

import java.util.List;
import subway.domain.Path;

public class PathDto {

    private final int distance;
    private final int time;
    private final List<String> stationNames;

    private PathDto(int distance, int time, List<String> stationNames) {
        this.distance = distance;
        this.time = time;
        this.stationNames = stationNames;
    }

    public static PathDto from(final Path path) {
        return new PathDto(path.getDistance(), path.getTime(), path.getStationNames());
    }

    public int getDistance() {
        return distance;
    }

    public int getTime() {
        return time;
    }

    public List<String> getStationNames() {
        return stationNames;
    }
}
