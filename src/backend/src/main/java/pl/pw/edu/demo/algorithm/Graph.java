package pl.pw.edu.demo.algorithm;
import lombok.Getter;
import pl.pw.edu.demo.dto.CourseResponse;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@Getter
public class Graph {

    private final List<Vertex> vertexList;
    private int rateCounter = 0;

    public Graph() {
        vertexList = new ArrayList<>();
    }

    public void addVertex(String code) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (vertexList.get(i).name.equals(code)) {
                System.out.println("Waluta o kodzie " + code + " już istnieje, nie zostala dodana powtornie");
                System.out.println("Waluta ta znajduje się w wierszu " + vertexList.size());
                return;
            }
        }
        vertexList.add(new Vertex(code));
    }

    public void addFlight(String startCity, String destCity, double price, double time) {
        Vertex vertexFrom;
        Vertex vertexTo;
        for (int i = 0; i < vertexList.size(); i++) {
            vertexFrom = vertexList.get(i);
            if (vertexFrom.name.equals(startCity)) {
                for (int j = 0; j < vertexList.size(); j++) {
                    vertexTo = vertexList.get(j);
                    if (vertexTo.name.equals(destCity)) {
                        vertexFrom.neighbourList.add(new Flight(vertexTo, price, time));
                        break;
                    }
                }
            }
        }
        rateCounter++;
    }

    public CourseResponse getBestRoute(String startCity, String destCity, double value) {
        checkGraphForBestRoute(startCity, value);
        return readBestRoute(startCity, destCity);
    }

    private void checkGraphForBestRoute(String startCity, double value) {
        Vertex vertexFrom;
        Queue<Vertex> queue = new ArrayDeque<>();

        for (int i = 0; i < vertexList.size(); i++) {
            vertexFrom = vertexList.get(i);
            if (vertexFrom.name.equals(startCity)) {
                vertexFrom.value = value;
                queue.add(vertexFrom);
                break;
            }
        }
        while (!queue.isEmpty()) {
            vertexFrom = queue.remove();
            if (!vertexFrom.check) {
                vertexFrom.checkNeighbourWithCycleBreak(queue);
                vertexFrom.check = true;
            }
        }
    }

    private CourseResponse readBestRoute(String startCity, String destCity) {
        List<String> result = new ArrayList<>();
        CourseResponse response = new CourseResponse();
        Vertex vertexFrom = null;
        boolean exist = true;
        for (int i = 0; i < vertexList.size(); i++) {
            vertexFrom = vertexList.get(i);
            if (vertexFrom.name.equals(destCity)) {
                exist = false;
                break;
            }
        }
        if (exist) {
            result.add("Podane miasto docelowe nie istnieje");
            response.setCities(result);
            response.setValue(0);
            return response;
        }
        if (vertexFrom.parent == null) {
            result.add("Podane miasta nie sa polaczone");
            response.setCities(result);
            response.setValue(0);
            return response;
        }
        result.add(vertexFrom.name);
        vertexFrom = vertexFrom.parent;
        while (!vertexFrom.name.equals(startCity)) {
            result.add(vertexFrom.name);
            vertexFrom = vertexFrom.parent;
        }
        result.add(vertexFrom.name);
        for (int i = 0; i < vertexList.size(); i++) {
            vertexFrom = vertexList.get(i);
            if (vertexFrom.name.equals(destCity)) {
                response.setValue(vertexFrom.value);
                break;
            }
        }
        response.setCities(result);
        return response;
    }

}
