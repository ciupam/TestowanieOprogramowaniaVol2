package pl.pw.edu.demo.benchamarks;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import pl.pw.edu.demo.algorithm.Flight;
import pl.pw.edu.demo.algorithm.Vertex;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class VertexBenchamarks {



    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 2)
    @BenchmarkMode(Mode.AverageTime)
    @Measurement(iterations = 5)
    public void createVertex(Blackhole blackhole) {
        for(int i = 0 ; i <= 1000000 ; i ++) {
            Vertex vertex = new Vertex("Example_name");
            blackhole.consume(vertex);
        }
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 2)
    @BenchmarkMode(Mode.AverageTime)
    @Measurement(iterations = 5)
    public void addOneToTheQueue(Blackhole blackhole) {
        for(int i = 0 ; i <= 1000000 ; i ++) {
            List<Vertex> vertexList = new ArrayList<>();
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
            Queue<Vertex> queue = new ArrayDeque<>();
            queue.add(vertexList.get(0));
            vertexList.get(0).checkNeighbourWithCycleBreak(queue);
            blackhole.consume(vertexList);
        }
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 2)
    @BenchmarkMode(Mode.AverageTime)
    @Measurement(iterations = 5)
    public void addOneToTheQueueHugeADDONLY() {
        List<Vertex> vertexList = new ArrayList<>();
        for(int i = 0 ; i <= 1000000 ; i ++) {
            vertexList.add(new Vertex("ciechanow" + i));
        }
        for(int i = 0 ; i <= 1000000 ; i ++) {
            List<Flight> zCiechanowa = new ArrayList<>();
            zCiechanowa.add(new Flight(vertexList.get(i + 1 <= 1000000 ? i + 1 : 0), 20, 20));
            vertexList.get(i).setNeighbourList(zCiechanowa);
        }
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 2)
    @BenchmarkMode(Mode.AverageTime)
    @Measurement(iterations = 5)
    public void addOneToTheQueueHuge(Blackhole blackhole) {
        List<Vertex> vertexList = new ArrayList<>();
        for(int i = 0 ; i <= 1000000 ; i ++) {
            vertexList.add(new Vertex("ciechanow" + i));
        }
        for(int i = 0 ; i <= 1000000 ; i ++) {
            List<Flight> zCiechanowa = new ArrayList<>();
            zCiechanowa.add(new Flight(vertexList.get(i + 1 <= 1000000 ? i + 1 : 0), 20, 20));
            vertexList.get(i).setNeighbourList(zCiechanowa);
        }
        Queue<Vertex> queue = new ArrayDeque<>();
        queue.add(vertexList.get(0));
        vertexList.get(0).checkNeighbourWithCycleBreak(queue);
        blackhole.consume(vertexList);
    }


    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 2)
    @BenchmarkMode(Mode.AverageTime)
    @Measurement(iterations = 10)
    public void addOneToTheQueueHugeWorstADDONLY() {
        List<Vertex> vertexList = new ArrayList<>();
        for(int i = 0 ; i <= 100000 ; i ++) {
            vertexList.add(new Vertex("ciechanow" + i));
        }
        for(int i = 0 ; i <= 100000 ; i ++) {
            List<Flight> zCiechanowa = new ArrayList<>();
            zCiechanowa.add(new Flight(vertexList.get(i + 1 <= 100000 ? i + 1 : 0), 20, 20));
            vertexList.get(i).setNeighbourList(zCiechanowa);
            vertexList.get(0).getNeighbourList().add(new Flight(vertexList.get(i + 1 <= 100000 ? i + 1 : 0), 20, 20));
        }
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 2)
    @BenchmarkMode(Mode.AverageTime)
    @Measurement(iterations = 10)
    public void addOneToTheQueueHugeWorst(Blackhole blackhole) {
        List<Vertex> vertexList = new ArrayList<>();
        for(int i = 0 ; i <= 100000 ; i ++) {
            vertexList.add(new Vertex("ciechanow" + i));
        }
        for(int i = 0 ; i <= 100000 ; i ++) {
            List<Flight> zCiechanowa = new ArrayList<>();
            zCiechanowa.add(new Flight(vertexList.get(i + 1 <= 100000 ? i + 1 : 0), 20, 20));
            vertexList.get(i).setNeighbourList(zCiechanowa);
            vertexList.get(0).getNeighbourList().add(new Flight(vertexList.get(i + 1 <= 100000 ? i + 1 : 0), 20, 20));
        }
        Queue<Vertex> queue = new ArrayDeque<>();
        queue.add(vertexList.get(0));
        vertexList.get(0).checkNeighbourWithCycleBreak(queue);
        blackhole.consume(vertexList);
    }
}
