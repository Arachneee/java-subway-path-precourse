package subway.domain;

import java.util.List;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import subway.exception.ErrorMessage;
import subway.exception.SubwayException;

public class PathFinder {

    private static DijkstraShortestPath dijkstraShortestDistancePath;
    private static DijkstraShortestPath dijkstraShortestTimePath;

    public static void init() {
        List<Section> sections = SectionRepository.sections();
        List<Station> stations = StationRepository.stations();

        WeightedMultigraph<String, DefaultWeightedEdge> distanceGraph = new WeightedMultigraph(DefaultWeightedEdge.class);
        WeightedMultigraph<String, DefaultWeightedEdge> timeGraph = new WeightedMultigraph(DefaultWeightedEdge.class);

        addStation(stations, distanceGraph, timeGraph);
        addSection(sections, distanceGraph, timeGraph);

        dijkstraShortestDistancePath = new DijkstraShortestPath(distanceGraph);
        dijkstraShortestTimePath = new DijkstraShortestPath(timeGraph);
    }

    private static void addStation(List<Station> stations, WeightedMultigraph<String, DefaultWeightedEdge> distanceGraph,
            WeightedMultigraph<String, DefaultWeightedEdge> timeGraph) {
        stations.forEach(station -> {
            distanceGraph.addVertex(station.getName());
            timeGraph.addVertex(station.getName());
        });
    }

    private static void addSection(List<Section> sections, WeightedMultigraph<String, DefaultWeightedEdge> distanceGraph,
            WeightedMultigraph<String, DefaultWeightedEdge> timeGraph) {
        sections.forEach(section -> {
            distanceGraph.setEdgeWeight(
                    distanceGraph.addEdge(section.getLeftStationName(), section.getRightStationName()),
                    section.getDistance());
            timeGraph.setEdgeWeight(
                    timeGraph.addEdge(section.getLeftStationName(), section.getRightStationName()),
                    section.getTime());
        });
    }

    public static Path findShortestDistancePath(final String startStationName, final String endStationName) {
        GraphPath path = dijkstraShortestDistancePath.getPath(startStationName, endStationName);

        if (path == null) {
            throw new SubwayException(ErrorMessage.NONE_LINE);
        }

        List<String> shortestPath = path.getVertexList();
        List<Station> shortestStations = StationRepository.findAllStationByName(shortestPath);

        return Path.create(shortestStations);
    }

    public static Path findShortestTimePath(final String startStationName, final String endStationName) {
        GraphPath path = dijkstraShortestTimePath.getPath(startStationName, endStationName);

        if (path == null) {
            throw new SubwayException(ErrorMessage.NONE_LINE);
        }

        List<String> shortestPath = path.getVertexList();
        List<Station> shortestStations = StationRepository.findAllStationByName(shortestPath);

        return Path.create(shortestStations);
    }
}
