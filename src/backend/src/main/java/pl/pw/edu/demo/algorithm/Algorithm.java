package pl.pw.edu.demo.algorithm;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import pl.pw.edu.demo.dto.CourseResponse;
import pl.pw.edu.demo.dto.SaveCourseRequest;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Algorithm {
    Graph graph;

    private Algorithm() {
        try {
            graph = new Load("Test.txt").load();
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
        CourseResponse result = graph.getBestRoute(startCity, destCity, 0);
        return result;
    }

    public int addCours(SaveCourseRequest request){
        graph.addVertex(request.getStart());
        graph.addVertex(request.getDestination());
        graph.addFlight(request.getStart(),request.getDestination(),request.getPrice(),request.getTime());
        return 200;
    }

    public List<String> getCities(){
        List<String> cities = new ArrayList<>();
        for(Vertex vertex: graph.getVertexList()){
            cities.add(vertex.getName());
        }
        return cities;
    }
}

