package pl.pw.edu.demo.algorithm;


import pl.pw.edu.demo.dto.CourseResponse;

import java.io.FileNotFoundException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Load load = new Load("Test.txt");
        Graph graph = load.load();
        CourseResponse result = null;
        result = graph.getBestRoute("Warszawa", "Sydney", 0);
        System.out.print("Ścieżka: ");
        for (int i = result.getCities().size() - 1; i >= 0; i--) {
            System.out.print(result.getCities().get(i) + " ");
        }
        System.out.println("\nKoszt: " + result.getValue() + " zł" );
    }
}
