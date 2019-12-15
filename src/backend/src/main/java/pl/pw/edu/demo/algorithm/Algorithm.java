package pl.pw.edu.demo.algorithm;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import pl.pw.edu.demo.dto.CourseResponse;
import pl.pw.edu.demo.dto.SaveCourseRequest;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Algorithm {
    Load load;
    Graph graph;
    private Algorithm() {
        this.load = new Load("citiesx.txt", "flightx.txt");
        try {
           this.graph = load.load();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Bean
    @Scope("singleton")
    public static Algorithm algorithmSingleton() {
        return new Algorithm();
    }

    public CourseResponse findBestConnection(String startCity, String destCity) {
        Graph graph = null;
        try {
            graph = load.load();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        CourseResponse result = graph.getBestRoute(startCity, destCity, 0);
        return result;
    }

    public int addCours(SaveCourseRequest request) {
        BufferedWriter writer = null;
        if(!(getCities().contains(request.getDestination()))){
            try {
                writer = new BufferedWriter(new FileWriter("cities.txt", true));
                writer.append("\n10 " + request.getDestination());
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
                return 500;
            }
        }
        if(!(getCities().contains(request.getStart()))){
            try {
                writer = new BufferedWriter(new FileWriter("cities.txt", true));
                writer.append("\n10 " + request.getStart());
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
                return 500;
            }
        }
        String str = "\n13 " + request.getStart() + " " + request.getDestination() + " " + request.getPrice() + " " + request.getTime();
        BufferedWriter writer2 = null;
        try {
            writer2 = new BufferedWriter(new FileWriter("flight.txt", true));
            writer2.append(' ');
            writer2.append(str);
            writer2.close();
        } catch (IOException e) {
            e.printStackTrace();
            return 500;
        }
        return 200;
    }

    public List<String> getCities() {
        try {
            graph = load.load();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<String> cities = new ArrayList<>();
        for (Vertex vertex : graph.getVertexList()) {
            cities.add(vertex.getName());
        }
        return cities;
    }
}

