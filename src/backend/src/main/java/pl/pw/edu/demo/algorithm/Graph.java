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
    private int FlightCounter = 0;

    public Graph() {
        vertexList = new ArrayList<>();
    }

    public String addVertex(String city) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (vertexList.get(i).getName().equals(city)) {
                return "Miasto o nazwie " + city + " już istnieje, nie zostało dodane powtórnie";
            }
        }
        vertexList.add(new Vertex(city));
        return "ok";
    }

    public String addFlight(String startCity, String destCity, double price, double time) {
        if (price < 0) {
            return "Cena " + price + " jest niepoprawna";
        }
        if (time < 0) {
            return "Czas " + time + " jest niepoprawny";
        }
        Vertex vertexFrom;
        Vertex vertexTo;
        for (int i = 0; i < vertexList.size(); i++) {
            vertexFrom = vertexList.get(i);
            if (vertexFrom.getName().equals(startCity)) {
                for (int j = 0; j < vertexList.size(); j++) {
                    vertexTo = vertexList.get(j);
                    if (vertexTo.getName().equals(destCity)) {
                        vertexFrom.getNeighbourList().add(new Flight(vertexTo, price, time));
                        FlightCounter++;
                        return "ok";
                    }
                }
                return "Nie odnaleziono połączenia z miasta " + startCity + " do " + destCity;
            }

        }
        return "Miasto " + startCity + " nie zostało wprowadzone";


    }

    public CourseResponse getBestRoute(String startCity, String destCity, double value, boolean... byTime) {
        checkGraphForBestRoute(startCity, value, byTime);
        return readBestRoute(startCity, destCity);
    }

    private void checkGraphForBestRoute(String startCity, double value, boolean... byTime ) {
        Vertex vertexFrom;
        Queue<Vertex> queue = new ArrayDeque<>();

        for (int i = 0; i < vertexList.size(); i++) {
            vertexFrom = vertexList.get(i);
            if (vertexFrom.getName().equals(startCity)) {
                vertexFrom.setValue(value);
                queue.add(vertexFrom);
                break;
            }
        }
        while (!queue.isEmpty()) {
            vertexFrom = queue.remove();
            if (!vertexFrom.isCheck()) {
                vertexFrom.checkNeighbourWithCycleBreak(queue,byTime);
                vertexFrom.setCheck(true);
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
            if (vertexFrom.getName().equals(destCity)) {
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
        if (vertexFrom.getParent() == null) {
            result.add("Podane miasta nie sa polaczone");
            response.setCities(result);
            response.setValue(0);
            return response;
        }
        result.add(vertexFrom.getName());
        vertexFrom = vertexFrom.getParent();
        while (!vertexFrom.getName().equals(startCity)) {
            result.add(vertexFrom.getName());
            vertexFrom = vertexFrom.getParent();
        }
        result.add(vertexFrom.getName());
        for (int i = 0; i < vertexList.size(); i++) {
            vertexFrom = vertexList.get(i);
            if (vertexFrom.getName().equals(destCity)) {
                response.setValue(vertexFrom.getValue());
                break;
            }
        }
        response.setCities(result);
        return response;
    }

}
