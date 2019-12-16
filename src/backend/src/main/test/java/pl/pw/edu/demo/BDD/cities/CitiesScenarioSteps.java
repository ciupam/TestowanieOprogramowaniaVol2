package pl.pw.edu.demo.BDD.cities;


import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import pl.pw.edu.demo.algorithm.Algorithm;
import pl.pw.edu.demo.algorithm.Graph;
import pl.pw.edu.demo.algorithm.Vertex;
import pl.pw.edu.demo.dto.SaveCourseRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.*;

public class CitiesScenarioSteps {

    private Graph graph;
    private List<String> givenCities;
    private List<String> cities;

    @Given("the Alghorithm flightList is <flightList>")
    public void createAlgorithm(@Named("flightList") String filghtList) {
        givenCities = new ArrayList<>();
        cities = new ArrayList<>();
        graph = new Graph();
        String[] cities_to_add = filghtList.split(",");
        for(String city: cities_to_add){
            givenCities.add(city);
            graph.addVertex(city);
        }
    }

    @When("requests is getCities")
    public void requestCities() {
        for (Vertex vertex : graph.getVertexList()) {
            cities.add(vertex.getName());
        }
    }

    @Then("the Algoritm should return result")
    public void checkCities(@Named("result") String result) {
        List<String> resultList = new ArrayList<>();
        Collections.addAll(resultList, result.split(","));
        assertEquals(cities,resultList);
    }

    @Given("the Alghorithm with CW flightList is <flightList>")
    public void createAlgorithmCW(@Named("flightList") String filghtList) {
        givenCities = new ArrayList<>();
        cities = new ArrayList<>();
        graph = new Graph();
        graph.addVertex("Ciechanów");
        graph.addVertex("Wrocław");
        String[] cities_to_add = filghtList.split(",");
        for(String city: cities_to_add){
            givenCities.add(city);
            graph.addVertex(city);
        }
    }
}
