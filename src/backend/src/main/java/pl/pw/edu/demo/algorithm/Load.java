package pl.pw.edu.demo.algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Load {

    private final String citesFileName;
    private final String flightFileName;

    public Load(String citesFileName, String flightFileName) {
        this.citesFileName = citesFileName;
        this.flightFileName = flightFileName;

    }

    public Graph load() throws FileNotFoundException {
        Scanner citesFile = new Scanner(new FileReader(citesFileName));
        String buffor;
        Graph graph = new Graph();
        if (!citesFile.nextLine().startsWith("#")) {
            throw new IllegalArgumentException("Nie wykryto lini inicjalizującej");
        }
        while (citesFile.hasNextLine()) {
            buffor = citesFile.nextLine();
            String[] tmp = buffor.split("\\s");
            if ("#".equals(tmp[0])) {
                break;
            } else {
                graph.addVertex(tmp[1]);
            }
        }
        Scanner flightFile = new Scanner(new FileReader(flightFileName));
        while (flightFile.hasNextLine()) {
            buffor = flightFile.nextLine();
            String[] tmp = buffor.split("\\s");
            graph.addFlight(tmp[1], tmp[2], Double.parseDouble(tmp[3]), Double.parseDouble(tmp[4]));
        }
        return graph;
    }

}
