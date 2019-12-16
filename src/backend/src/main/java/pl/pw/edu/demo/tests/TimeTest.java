package pl.pw.edu.demo.tests;

import pl.pw.edu.demo.algorithm.Graph;
import org.junit.jupiter.api.Test;
import pl.pw.edu.demo.dto.CourseResponse;

import static org.junit.jupiter.api.Assertions.*;


public class TimeTest {

    @Test
    void getBestRouteByTimeSimpleTest() {
        Graph instanceGraph = new Graph();
        instanceGraph.addVertex("ciechanow");
        instanceGraph.addVertex("warszawa");
        instanceGraph.addFlight("ciechanow", "warszawa", 20, 30);
        CourseResponse cr = instanceGraph.getBestRoute("ciechanow", "warszawa", 0,true);
        assertEquals("warszawa", cr.getCities().get(0));
        assertEquals("ciechanow", cr.getCities().get(1));
        assertEquals(30, cr.getValue());
    }
}
