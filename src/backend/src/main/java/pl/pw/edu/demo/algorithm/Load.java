package pl.pw.edu.demo.algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Load {

    private final String filename;

    public Load(String filename) {
        this.filename = filename;
    }

    public Graph load() throws FileNotFoundException {
        Scanner goodFile = new Scanner(new FileReader(filename));
        String buffor;
        Graph graph = new Graph();
        if (!goodFile.nextLine().startsWith("#")) {
            throw new IllegalArgumentException("Nie wykryto lini inicjalizującej");
        }
        while (goodFile.hasNextLine()) {
            buffor = goodFile.nextLine();
            String[] tmp = buffor.split("\\s");
            if ("#".equals(tmp[0])) {
                break;
            } else {
                graph.addVertex(tmp[1]);
            }
        }
        while (goodFile.hasNextLine()) {
            buffor = goodFile.nextLine();
            String[] tmp = buffor.split("\\s");
            graph.addFlight(tmp[1], tmp[2], Double.parseDouble(tmp[3]), Double.parseDouble(tmp[4]));
        }
        return graph;
    }

    public int checkData() throws FileNotFoundException {
        Scanner checker = new Scanner(new File("./dataForTest/" + filename));

        int vertexNumber = 0;
        int rateNumber = 0;

        while (checker.hasNextLine()) {
            String line = checker.nextLine();
            String[] tokens = line.split("\\s");
            if ("#".equals(tokens[0])) {
            } else {
                vertexNumber++;
            }
        }
        return -1;
    }
}
