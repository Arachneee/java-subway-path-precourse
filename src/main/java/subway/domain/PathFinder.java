package subway.domain;

import java.util.List;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;

public class PathFinder {

    private static DijkstraShortestPath dijkstraShortestDistancePath;
    private static DijkstraShortestPath dijkstraShortestTimePath;

    public static void init() {
        List<Section> sections = SectionRepository.sections();
        List<Station> stations = StationRepository.stations();

        WeightedMultigraph<String, DefaultWeightedEdge> distanceGraph = new WeightedMultigraph(DefaultWeightedEdge.class);
        WeightedMultigraph<String, DefaultWeightedEdge> timeGraph = new WeightedMultigraph(DefaultWeightedEdge.class);

        stations.forEach(station -> {
            distanceGraph.addVertex(station.getName());
            timeGraph.addVertex(station.getName());
        });

        sections.forEach(section -> {
            distanceGraph.setEdgeWeight(
                    distanceGraph.addEdge(section.getLeftStationName(), section.getRightStationName()),
                    section.getDistance());
            timeGraph.setEdgeWeight(
                    distanceGraph.addEdge(section.getLeftStationName(), section.getRightStationName()),
                    section.getTime());
        });

        dijkstraShortestDistancePath = new DijkstraShortestPath(distanceGraph);
        dijkstraShortestTimePath = new DijkstraShortestPath(timeGraph);
    }

    public static Path findShortestDistancePath(final String startStationName, final String endStationName) {
        List<String> shortestPath = dijkstraShortestDistancePath.getPath(startStationName, endStationName).getVertexList();
        List<Station> shortestStations = StationRepository.findAllStationByName(shortestPath);

        return Path.create(shortestPath);
    }

    public static Path findShortestTimePath(final String startStationName, final String endStationName) {
        List<String> shortestPath = dijkstraShortestTimePath.getPath(startStationName, endStationName).getVertexList();
        List<Station> shortestStations = StationRepository.findAllStationByName(shortestPath);

        return Path.create(shortestPath);
    }
}
