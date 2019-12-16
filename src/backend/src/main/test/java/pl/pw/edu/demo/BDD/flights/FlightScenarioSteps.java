package pl.pw.edu.demo.BDD.flights;

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

public class FlightScenarioSteps {
    private Graph graph;
    private String startCity;
    private String destCity;
    private double price;
    private double time;

    @Given("the cities is <cities>")
    public void createGraphWithCities(@Named("cities") String cities) {
        String[] cityList = cities.split(",");
        graph = new Graph();
        for(String cit: cityList){
            graph.addVertex(cit);
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

    @When("price is <price>")
    public void setPrice(@Named("price") double pr) {
        price = pr;
    }

    @When("time is <time>")
    public void setTime(@Named("test") double tim) {
        time = tim;
    }

    @Then("the Algoritm should return <result>")
    public void checkCities(@Named("result") String res) {
        String result = res;
        String response = graph.addFlight(startCity, destCity, price, time);
        assertEquals(result, response);
    }
}
