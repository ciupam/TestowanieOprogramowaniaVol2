package pl.pw.edu.demo.algorithm;
import pl.pw.edu.demo.dto.CourseResponse;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

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

    public void addFlight(String currencyName1, String currencyName2, double price, double time) {
        Vertex vertexFrom;
        Vertex vertexTo;
        boolean added = false;

        for (int i = 0; i < vertexList.size(); i++) {
            vertexFrom = vertexList.get(i);
            if (vertexFrom.name.equals(currencyName1)) {
                for (int j = 0; j < vertexList.size(); j++) {
                    vertexTo = vertexList.get(j);
                    if (vertexTo.name.equals(currencyName2)) {
                        vertexFrom.neighbourList.add(new Flight(vertexTo, price, time));
                        added = true;
                        break;
                    }
                }
            }
        }
        if (!added) {
            System.out.println("Nie dodano kursu pomiedzy " + currencyName1 + " " + currencyName2 + " jedna z walut nie istnieje");
            System.out.println("Kurs ten znajduje sie w w wierszu " + rateCounter);
        }
        rateCounter++;
    }

    public CourseResponse getBestRoute(String inCurrency, String outCurrency, double value) {
        checkGraphForBestRoute(inCurrency, value);
        return readBestRoute(inCurrency, outCurrency);
    }

    private void checkGraphForBestRoute(String inCurrency, double value) {
        Vertex vertexFrom;
        Queue<Vertex> queue = new ArrayDeque<>();

        for (int i = 0; i < vertexList.size(); i++) {
            vertexFrom = vertexList.get(i);
            if (vertexFrom.name.equals(inCurrency)) {
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

    private CourseResponse readBestRoute(String inCurrency, String outCurrency) {
        List<String> result = new ArrayList<>();
        CourseResponse response = new CourseResponse();
        Vertex vertexFrom = null;
        boolean exist = true;
        for (int i = 0; i < vertexList.size(); i++) {
            vertexFrom = vertexList.get(i);
            if (vertexFrom.name.equals(outCurrency)) {
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
        while (!vertexFrom.name.equals(inCurrency)) {
            result.add(vertexFrom.name);
            vertexFrom = vertexFrom.parent;
        }
        result.add(vertexFrom.name);
        for (int i = 0; i < vertexList.size(); i++) {
            vertexFrom = vertexList.get(i);
            if (vertexFrom.name.equals(outCurrency)) {
                response.setValue(vertexFrom.value);
                break;
            }
        }
        response.setCities(result);
        return response;
    }

}
