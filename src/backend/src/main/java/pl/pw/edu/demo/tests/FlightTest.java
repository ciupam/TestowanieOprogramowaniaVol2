package pl.pw.edu.demo.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import pl.pw.edu.demo.algorithm.Flight;
import pl.pw.edu.demo.algorithm.Vertex;

class FlightTest {

    @Test
    void calculatePriceNormal() {
        Flight flight = new Flight(new Vertex("ciechanow"), 500, 2.5);
        double result = flight.calculatePrice(200);
        assertEquals(700, result);
    }

    @Test
    void calculatePriceNew() {
        Flight flight = new Flight(new Vertex("ciechanow"), 500, 2.5);
        double result = flight.calculatePrice(0);
        assertEquals(500, result);
    }

    @Test
    void calculatePriceNegative() {
        Flight flight = new Flight(new Vertex("ciechanow"), -200, 2.5);
        double result = flight.calculatePrice(500);
        assertEquals(Double.NaN, result);
    }

    @Test
    void calculateTimeNormal() {
        Flight flight = new Flight(new Vertex("ciechanow"), 500, 30);
        double result = flight.calculateTime(150);
        assertEquals(180, result);
    }

    @Test
    void calculateTimeNew() {
        Flight flight = new Flight(new Vertex("ciechanow"), 500, 30);
        double result = flight.calculateTime(0);
        assertEquals(30, result);
    }

    @Test
    void calculateTimeNegative() {
        Flight flight = new Flight(new Vertex("ciechanow"), 200, -30);
        double result = flight.calculateTime(500);
        assertEquals(Double.NaN, result);
    }
}