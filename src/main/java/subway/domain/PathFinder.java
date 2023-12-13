package subway.domain;

import java.util.List;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;

public class PathFinder {

    public static Path findShortestPath(final String startStationName, final String endStationName) {
        List<Section> sections = SectionRepository.sections();
        List<Station> stations = StationRepository.stations();

        WeightedMultigraph<String, DefaultWeightedEdge> graph = new WeightedMultigraph(DefaultWeightedEdge.class);

        stations.forEach(station -> graph.addVertex(station.getName()));
        sections.forEach(section -> graph.setEdgeWeight(
                graph.addEdge(section.getLeftStationName(), section.getRightStationName()), section.getDistance()));

        DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(graph);
        List<String> shortestPath = dijkstraShortestPath.getPath(startStationName, endStationName).getVertexList();
        List<Station> shortestStations = StationRepository.findAllStationByName(shortestPath);

        return Path.create(shortestPath);
    }
}
