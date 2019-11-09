package pl.pw.edu.demo.algorithm;
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

    public void addRate(String currencyName1, String currencyName2, double value, String provisionType, double provision) {
        Vertex vertexFrom;
        Vertex vertexTo;
        boolean added = false;

        for (int i = 0; i < vertexList.size(); i++) {
            vertexFrom = vertexList.get(i);
            if (vertexFrom.name.equals(currencyName1)) {
                for (int j = 0; j < vertexList.size(); j++) {
                    vertexTo = vertexList.get(j);
                    if (vertexTo.name.equals(currencyName2)) {
                        vertexFrom.neighbourList.add(new Rate(vertexTo, value, provisionType, provision));
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

    public List<String> getBestExchenge(String inCurrency, String outCurrency, double value) {
        checkGraphForBestExchenge(inCurrency, value);
        List result = readBestRoad(inCurrency, outCurrency);
        return result;
    }
    private void checkGraphForBestExchenge(String inCurrency, double value) {
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
                vertexFrom.checkNeighbourWithCycleBreak(queue, inCurrency);
                vertexFrom.check = true;
            }
        }
    }

    private List<String> readBestRoad(String inCurrency, String outCurrency) {
        List<String> result = new ArrayList<>();
        Vertex vertexFrom = null;
        if (inCurrency == null) {
            result.add("Nie istnieje arbitraz");
            return result;
        }
        boolean exist = true;
        for (int i = 0; i < vertexList.size(); i++) {
            vertexFrom = vertexList.get(i);
            if (vertexFrom.name.equals(outCurrency)) {
                exist = false;
                break;
            }
        }
        if (exist) {
            result.add("Podana waluta wyjściowa nie istnieje");
            return result;
        }
        if (vertexFrom.parrent == null) {
            result.add("Podana waluty nie sa polaczone");
            return result;
        }
        result.add(vertexFrom.name);
        vertexFrom = vertexFrom.parrent;
        while (!vertexFrom.name.equals(inCurrency)) {
            result.add(vertexFrom.name);
            vertexFrom = vertexFrom.parrent;
        }
        result.add(vertexFrom.name);
        for (int i = 0; i < vertexList.size(); i++) {
            vertexFrom = vertexList.get(i);
            if (vertexFrom.name.equals(outCurrency)) {
                System.out.format("Wynik: %.4f %s%n", vertexFrom.value, vertexFrom.name);
                break;
            }
        }
        return result;
    }

}
