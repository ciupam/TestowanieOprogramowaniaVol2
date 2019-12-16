package pl.pw.edu.demo.BDD.time;


import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import pl.pw.edu.demo.algorithm.Graph;
import pl.pw.edu.demo.algorithm.Vertex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TimeScenarioSteps {

    private Graph graph;
    private String startCity;
    private String destCity;

    @Given("the connections is <connections>")
    public void createGraphWithConnections(@Named("connections") String connections) {
        String[] connections_list = connections.split(",");
        String[] parametrs;
        graph = new Graph();
        for(String conn: connections_list){
            parametrs = conn.split("-");
            graph.addVertex(parametrs[0]);
            graph.addVertex(parametrs[1]);
            graph.addFlight(parametrs[0],parametrs[1],Double.parseDouble(parametrs[2]),Double.parseDouble(parametrs[3]));
        }
    }

    @When("startCity is <startCity>")
    public void setStartCity(@Named("startCity") String start) {
        startCity = start;
    }

    @When("destCity is <destCity>")
    public void setDestCity(@Named("destCity") String dest) {
        destCity = dest;
    }

    @Then("the Algoritm should return <result>")
    public void checkCities(@Named("result") String result) {
        String[] resultParameters = result.split(",");
        List<String> citiesResponse = new ArrayList<>();
        Collections.addAll(citiesResponse,resultParameters[0].split("-"));
        assertEquals(citiesResponse,graph.getBestRoute(startCity,destCity,0,true).getCities());
        assertEquals(Double.parseDouble(resultParameters[1]),graph.getBestRoute(startCity,destCity,0,true).getValue(),0);
    }
}
