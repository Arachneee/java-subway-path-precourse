package subway.domain;

import java.util.Arrays;

public final class Initializer {

    public static void initStation() {
        String stationNames = "교대역, 강남역, 역삼역, 남부터미널역, 양재역, 양재시민의숲역, 매봉역";
        Arrays.stream(stationNames.split(", "))
                .map(Station::new)
                .forEach(StationRepository::addStation);
    }

    public static void initLine() {
        String lineNames = "2호선, 3호선, 신분당선";
        Arrays.stream(lineNames.split(", "))
                .map(Line::new)
                .forEach(LineRepository::addLine);
    }

    public static void initSection() {
        addSection("교대역", "강남역", 2, 3);
        addSection("강남역", "역삼역", 2, 3);
        addSection("교대역", "남부터미널역", 3, 2);
        addSection("남부터미널역", "양재역", 6, 5);
        addSection("양재역", "매봉역", 1, 1);
        addSection("강남역", "양재역", 2, 8);
        addSection("양재역", "양재시민의숲역", 10, 3);
    }

    private static void addSection(
            final String fromStationName,
            final String toStationName,
            final int distance,
            final int time) {

        Station fromStation = StationRepository.findStationByName(fromStationName);
        Station toStation = StationRepository.findStationByName(toStationName);
        Section section = new Section(fromStation, toStation, distance, time);
        SectionRepository.addSection(section);
    }

}
