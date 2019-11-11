package pl.pw.edu.demo.tests;

import pl.pw.edu.demo.algorithm.Graph;
import org.junit.jupiter.api.Test;
import pl.pw.edu.demo.algorithm.Vertex;
import pl.pw.edu.demo.dto.CourseResponse;

import javax.validation.constraints.Null;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    @Test
    void addOneVertexTest() {
        Graph instanceGraph = new Graph();
        String message = instanceGraph.addVertex("ciechanow");
        assertEquals(1, instanceGraph.getVertexList().size());
        assertEquals("ciechanow", instanceGraph.getVertexList().get(0).getName());
        assertEquals("ok", message);
    }

    @Test
    void addDuplicateVertexTest() {
        Graph instanceGraph = new Graph();
        instanceGraph.addVertex("ciechanow");
        String message = instanceGraph.addVertex("ciechanow");
        assertEquals(1, instanceGraph.getVertexList().size());
        assertEquals("Miasto o nazwie ciechanow już istnieje, nie zostało dodane powtórnie", message);
    }

    @Test
    void addMultipleVertexTest() {
        Graph instanceGraph = new Graph();
        for (int i = 1; i <= 100; i++) {
            instanceGraph.addVertex("ciechanow" + i);
        }
        assertEquals(100, instanceGraph.getVertexList().size());
        assertEquals("ciechanow51", instanceGraph.getVertexList().get(50).getName());
    }

    @Test
    void addOneFlightTest() {
        Graph instanceGraph = new Graph();
        instanceGraph.addVertex("ciechanow");
        instanceGraph.addVertex("warszawa");
        instanceGraph.addFlight("ciechanow", "warszawa", 20, 30);

        assertEquals(1, instanceGraph.getFlightCounter());
        assertEquals(20, instanceGraph.getVertexList().get(0).getNeighbourList().get(0).getPrice());
        assertEquals(30, instanceGraph.getVertexList().get(0).getNeighbourList().get(0).getTime());
    }

    @Test
    void addOneFlightWithBadPriceTest() {
        Graph instanceGraph = new Graph();
        instanceGraph.addVertex("ciechanow");
        instanceGraph.addVertex("warszawa");
        String message = instanceGraph.addFlight("ciechanow", "warszawa", -20, 30);

        assertEquals(0, instanceGraph.getFlightCounter());
        assertEquals("Cena -20.0 jest niepoprawna", message);
    }

    @Test
    void addOneFlightWithBadTimeTest() {
        Graph instanceGraph = new Graph();
        instanceGraph.addVertex("ciechanow");
        instanceGraph.addVertex("warszawa");
        String message = instanceGraph.addFlight("ciechanow", "warszawa", 20, -30);

        assertEquals(0, instanceGraph.getFlightCounter());
        assertEquals("Czas -30.0 jest niepoprawny", message);
    }

    @Test
    void addOneFlightWithBadDestinationTest() {
        Graph instanceGraph = new Graph();
        instanceGraph.addVertex("ciechanow");
        instanceGraph.addVertex("warszawa");
        String message = instanceGraph.addFlight("ciechanow", "krakow", 20, 30);

        assertEquals(0, instanceGraph.getFlightCounter());
        assertEquals("Nie odnaleziono połączenia z miasta ciechanow do krakow", message);
    }

    @Test
    void addOneFlightWithBadStartTest() {
        Graph instanceGraph = new Graph();
        instanceGraph.addVertex("ciechanow");
        instanceGraph.addVertex("warszawa");
        String message = instanceGraph.addFlight("krakow", "warszawa", 20, 30);

        assertEquals(0, instanceGraph.getFlightCounter());
        assertEquals("Miasto krakow nie zostało wprowadzone", message);
    }

    @Test
    void getBestRouteSimpleTest() {
        Graph instanceGraph = new Graph();
        instanceGraph.addVertex("ciechanow");
        instanceGraph.addVertex("warszawa");
        instanceGraph.addFlight("ciechanow", "warszawa", 20, 30);
        CourseResponse cr = instanceGraph.getBestRoute("ciechanow", "warszawa", 0);
        assertEquals("warszawa", cr.getCities().get(0));
        assertEquals("ciechanow", cr.getCities().get(1));
        assertEquals(20, cr.getValue());
    }

    @Test
    void getBestRouteInterestingTest() {
        Graph instanceGraph = new Graph();
        instanceGraph.addVertex("ciechanow");
        instanceGraph.addVertex("warszawa");
        instanceGraph.addVertex("krakow");
        instanceGraph.addFlight("ciechanow", "warszawa", 20, 30);
        instanceGraph.addFlight("ciechanow", "krakow", 50, 50);
        instanceGraph.addFlight("warszawa", "krakow", 20, 20);
        CourseResponse cr = instanceGraph.getBestRoute("ciechanow", "krakow", 0);
        assertEquals("krakow", cr.getCities().get(0));
        assertEquals("warszawa", cr.getCities().get(1));
        assertEquals("ciechanow", cr.getCities().get(2));
        assertEquals(40, cr.getValue());
    }

    @Test
    void getBestRouteWhenPriceDoubleMaxTest() {
        Graph instanceGraph = new Graph();
        instanceGraph.addVertex("Char");
        instanceGraph.addVertex("Aiur");
        instanceGraph.addFlight("Char", "Aiur", Double.MAX_VALUE, 100);
        CourseResponse cr = instanceGraph.getBestRoute("Char", "Aiur", 0);
        assertEquals("Aiur", cr.getCities().get(0));
        assertEquals("Char", cr.getCities().get(1));
        assertEquals(Double.MAX_VALUE, cr.getValue());
    }


    @Test
    void getBestRouteWhenOneRoutePriceHigherThanDoubleMaxValueTest() {
        Graph instanceGraph = new Graph();
        instanceGraph.addVertex("Char");
        instanceGraph.addVertex("Aiur");
        instanceGraph.addVertex("Korhal");
        instanceGraph.addFlight("Char", "Aiur", Double.MAX_VALUE, 100);
        instanceGraph.addFlight("Aiur", "Korhal", 1000, 100);
        instanceGraph.addFlight("Char", "Korhal", 2000, 50);
        CourseResponse cr = instanceGraph.getBestRoute("Char", "Korhal", 0);
        assertEquals("Korhal", cr.getCities().get(0));
        assertEquals("Char", cr.getCities().get(1));
        assertEquals(2000, cr.getValue());
    }

    @Test
    void getBestRouteWhenThereIsNoConnectionTest() {
        Graph instanceGraph = new Graph();
        instanceGraph.addVertex("Char");
        instanceGraph.addVertex("Aiur");
        instanceGraph.addVertex("Korhal");
        instanceGraph.addFlight("Char", "Aiur", 3000, 100);
        instanceGraph.addFlight("Aiur", "Char", 3000, 100);
        CourseResponse cr = instanceGraph.getBestRoute("Char", "Korhal", 0);
        assertEquals(1, cr.getCities().size());
        assertEquals(0, cr.getValue());
    }

    @Test
    void getBestRouteWhenThereIsNoConnectionMoreComplicatedTest() {
        Graph instanceGraph = new Graph();
        instanceGraph.addVertex("Char");
        instanceGraph.addVertex("Aiur");
        instanceGraph.addVertex("Korhal");
        instanceGraph.addVertex("Earth");
        instanceGraph.addVertex("Mar Sara");
        instanceGraph.addFlight("Char", "Aiur", 3000, 100);
        instanceGraph.addFlight("Aiur", "Char", 3000, 100);
        instanceGraph.addFlight("Aiur", "Korhal", 5000, 100);
        instanceGraph.addFlight("Korhal", "Earth", 5000, 100);
        instanceGraph.addFlight("Mar Sara", "Earth", 6000, 100);
        CourseResponse cr = instanceGraph.getBestRoute("Char", "Mar Sara", 0);
        assertEquals(1, cr.getCities().size());
        assertEquals(0, cr.getValue());
    }

    @Test
    void getBestRouteWhenThereIsNoDestinationTest() {
        Graph instanceGraph = new Graph();
        instanceGraph.addVertex("Char");
        instanceGraph.addVertex("Aiur");
        instanceGraph.addFlight("Char", "Aiur", 3000, 100);
        CourseResponse cr = instanceGraph.getBestRoute("Char", "Korhal", 0);
        assertEquals(1, cr.getCities().size());
        assertEquals(0, cr.getValue());
    }
}