package pl.pw.edu.demo.algorithm;


import java.io.FileNotFoundException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Load load = new Load("Test.txt");
        Graph graph = load.load();
        List<String> result = null;
        result = graph.getBestExchenge("EUR", "GBP", 0);
        System.out.print("Ścieżka: ");
        for (int i = result.size() - 1; i >= 0; i--) {
            System.out.print(result.get(i) + " ");
        }
    }
}
