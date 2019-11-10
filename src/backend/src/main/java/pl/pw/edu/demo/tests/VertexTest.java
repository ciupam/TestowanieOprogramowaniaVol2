package pl.pw.edu.demo.tests;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import pl.pw.edu.demo.algorithm.Flight;
import pl.pw.edu.demo.algorithm.Vertex;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class VertexTest {

    private List<Vertex> vertexList = new ArrayList<>();


    private void setSimpleInstanceOfGraph(List<Vertex> vertexList) {
        vertexList.add(new Vertex("ciechanow"));
        vertexList.add(new Vertex("warszawa"));
        vertexList.add(new Vertex("krakow"));
        List<Flight> zCiechanowa = new ArrayList<>();
        zCiechanowa.add(new Flight(vertexList.get(1), 20, 20));
        vertexList.get(0).setNeighbourList(zCiechanowa);
        List<Flight> zWarszawy = new ArrayList<>();
        zWarszawy.add(new Flight(vertexList.get(2), 20, 20));
        vertexList.get(1).setNeighbourList(zWarszawy);
        List<Flight> zKrakowa = new ArrayList<>();
        zKrakowa.add(new Flight(vertexList.get(0), 20, 20));
        vertexList.get(2).setNeighbourList(zKrakowa);
    }

    private void setMoreInterestingInstance(List<Vertex> vertexList) {
        vertexList.add(new Vertex("ciechanow"));
        vertexList.add(new Vertex("warszawa"));
        vertexList.add(new Vertex("krakow"));
        List<Flight> zCiechanowa = new ArrayList<>();
        zCiechanowa.add(new Flight(vertexList.get(1), 20, 20));
        zCiechanowa.add(new Flight(vertexList.get(2), 50, 50));
        vertexList.get(0).setNeighbourList(zCiechanowa);
        List<Flight> zWarszawy = new ArrayList<>();
        zWarszawy.add(new Flight(vertexList.get(2), 20, 20));
        vertexList.get(1).setNeighbourList(zWarszawy);
        List<Flight> zKrakowa = new ArrayList<>();
        zKrakowa.add(new Flight(vertexList.get(0), 20, 20));
        vertexList.get(2).setNeighbourList(zKrakowa);
    }

    @Test
    void checkNeighbourWithCycleBreakTestA() { //Sprawdza czy jedno miasto zostaje poprawnie dodane do kolejki
        setSimpleInstanceOfGraph(vertexList);
        Queue<Vertex> queue = new ArrayDeque<>();
        queue.add(vertexList.get(0));
        assertEquals(1, queue.size());

        vertexList.get(0).checkNeighbourWithCycleBreak(queue);
        assertEquals(2, queue.size());

        double result = vertexList.get(1).getValue();
        assertEquals(20, result);
    }

    @Test
    void checkNeighbourWithCycleBreakTestB() { //Sprawdza sąsiadów dla pełnej kolejki
        setSimpleInstanceOfGraph(vertexList);
        Queue<Vertex> queue = new ArrayDeque<>();
        queue.add(vertexList.get(0));
        queue.add(vertexList.get(1));
        queue.add(vertexList.get(2));
        assertEquals(3, queue.size());

        vertexList.get(2).checkNeighbourWithCycleBreak(queue);
        assertEquals(3, queue.size());
    }

    @Test
    void checkNeighbourWithCycleBreakTestC() { //Sprawdza czy zakolejkuje dwa miasta na raz
        setMoreInterestingInstance(vertexList);
        Queue<Vertex> queue = new ArrayDeque<>();
        queue.add(vertexList.get(0));
        assertEquals(1, queue.size());

        vertexList.get(0).checkNeighbourWithCycleBreak(queue);
        assertEquals(3, queue.size());

        vertexList.get(2).checkNeighbourWithCycleBreak(queue);
        assertEquals(3, queue.size());
    }

    @Test
    void checkNeighbourWithCycleBreakTestD() { //Sprawdza czy zmieni wartość po odnalezieniu lepszej drogi
        setMoreInterestingInstance(vertexList);
        Queue<Vertex> queue = new ArrayDeque<>();
        queue.add(vertexList.get(0));
        assertEquals(1, queue.size());

        vertexList.get(0).checkNeighbourWithCycleBreak(queue);
        double valueA = vertexList.get(2).getValue();
        assertEquals(50, valueA);
        vertexList.get(1).checkNeighbourWithCycleBreak(queue);
        double valueB = vertexList.get(2).getValue();
        assertEquals(40, valueB);
    }

    @Test
    void checkNeighbourWithCycleBreakTestE() { //Sprawdza czy zadziała poprawnie dla pustej listy sąsiedztwa
        Vertex vertex = new Vertex("ciechanow");
        Queue<Vertex> queue = new ArrayDeque<>();
        vertex.checkNeighbourWithCycleBreak(queue);
        assertEquals(0, queue.size());
    }

    @Test
    void testToString() {
        Vertex vertex = new Vertex("name");
        String result = vertex.toString();
        assertEquals("Vertex{name=name, value=0.0}", result);
    }
}