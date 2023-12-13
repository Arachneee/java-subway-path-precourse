package subway.domain;

import subway.exception.ErrorMessage;
import subway.exception.SubwayException;

public class StartEndStation {

    private final Station startStation;
    private final Station endStation;

    private StartEndStation(Station startStation, Station endStation) {
        this.startStation = startStation;
        this.endStation = endStation;
    }

    public static StartEndStation of(final Station startStation, final Station endStation) {
        if (startStation.equals(endStation)) {
            throw new SubwayException(ErrorMessage.DUPLICATE_STATION);
        }

        return new StartEndStation(startStation, endStation);
    }

    public Path getShortestDistancePath() {
        return PathFinder.findShortestDistancePath(startStation.getName(), endStation.getName());
    }

    public Path getShortestTimePath() {
        return PathFinder.findShortestTimePath(startStation.getName(), endStation.getName());
    }
}
