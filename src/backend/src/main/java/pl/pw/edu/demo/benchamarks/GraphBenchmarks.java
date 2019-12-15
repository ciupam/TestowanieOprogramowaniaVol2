package pl.pw.edu.demo.benchamarks;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import pl.pw.edu.demo.algorithm.Graph;
import pl.pw.edu.demo.algorithm.Vertex;

public class GraphBenchmarks {
    @State(Scope.Thread)
    public static class GraphStateTwoCities {
        public Graph testGraph;

        @Setup(Level.Invocation)
        public void init() {
            testGraph = new Graph();
            testGraph.addVertex("Warszawa");
            testGraph.addVertex("Kielce");
        }
    }

    @State(Scope.Thread)
    public static class GraphStateManyCities {
        public Graph testGraph;
        public int citiesNumber = 10000;

        @Setup(Level.Invocation)
        public void init() {
            testGraph = new Graph();
            for (int i =0; i<citiesNumber; i++){
                testGraph.addVertex("Warszawa" + i);
                testGraph.addVertex("Kielce" + i);
            }
        }
    }

    @State(Scope.Thread)
    public static class GraphStateManyCitiesWithFlights {
        public Graph testGraph;
        public int citiesNumber = 101;

        @Setup(Level.Invocation)
        public void init() {
            testGraph = new Graph();
            for (int i=0; i<citiesNumber; i++){
                testGraph.addVertex("Warszawa" + i);
                testGraph.addVertex("Kielce" + i);
                testGraph.addFlight("Warszawa" + i, "Kielce" + i, 10, 10);
            }
            for (int i=0; i<citiesNumber; i++){
                testGraph.addVertex("Bialystok" + i);
                testGraph.addVertex("Gdansk" + i);
                testGraph.addVertex("Szczecin" + i);
                testGraph.addFlight("Bialystok" + i, "Gdansk" + i, 10, 10);
                testGraph.addFlight("Gdansk" + i, "Szczecin" + i, 10, 10);
                testGraph.addFlight("Bialystok" + i, "Szczecin" + i, 30, 30);
            }
        }
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 2)
    @BenchmarkMode(Mode.AverageTime)
    @Measurement(iterations = 5)
    public void addVertex(Blackhole blackhole) { // (create Graph, add Vertex, consume Graph, consume message) * N
        for (int i = 0; i <= 1000000; i++) {
            Graph instanceGraph = new Graph();
            String message = instanceGraph.addVertex("ciechanow");
            blackhole.consume(message);
            blackhole.consume(instanceGraph);
        }
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 2)
    @BenchmarkMode(Mode.AverageTime)
    @Measurement(iterations = 5)
    public void addVertex2(Blackhole blackhole) { // create Graph, (add Vertex , consume message) * N, consume Graph
        Graph instanceGraph = new Graph();
        for (int i = 0; i <= 1000000; i++) {
            String message = instanceGraph.addVertex("ciechanow");
            blackhole.consume(message);
        }
        blackhole.consume(instanceGraph);
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 2)
    @BenchmarkMode(Mode.AverageTime)
    @Measurement(iterations = 5)
    public void addVertex3(Blackhole blackhole) { // (create Graph, (add unique Vertex, consume message) * 10, consume Graph) * N
        for (int i = 0; i <= 1000000; i++) {
            Graph instanceGraph = new Graph();
            for (int j = 0; j < 10; j++) {
                String message = instanceGraph.addVertex(String.valueOf(j));
                blackhole.consume(message);
            }
            blackhole.consume(instanceGraph);
        }
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 2)
    @BenchmarkMode(Mode.AverageTime)
    @Measurement(iterations = 5)
    public void addFlight1(GraphStateTwoCities graphState, Blackhole blackhole) {
        Graph graph = graphState.testGraph;
        String string = graph.addFlight("Warszawa", "Kielce", 10, 10);
        blackhole.consume(string);
        blackhole.consume(graph);
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 2)
    @BenchmarkMode(Mode.AverageTime)
    @Measurement(iterations = 5)
    public void addFlight2(GraphStateTwoCities graphState, Blackhole blackhole) {
        Graph graph = graphState.testGraph;
        for(int i =0; i<1000000; i++){
            String string = graph.addFlight("Warszawa", "Kielce", i, i);
            blackhole.consume(string);
        }
        blackhole.consume(graph);
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 2)
    @BenchmarkMode(Mode.AverageTime)
    @Measurement(iterations = 5)
    public void addFlight3(GraphStateManyCities graphState, Blackhole blackhole) {
        Graph graph = graphState.testGraph;
        for(int i =0; i<graphState.citiesNumber; i++){
            String string = graph.addFlight("Warszawa" + i, "Kielce" + i, 10, 10);
            blackhole.consume(string);
        }
        blackhole.consume(graph);
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 2)
    @BenchmarkMode(Mode.AverageTime)
    @Measurement(iterations = 5)
    public void addFlight4(GraphStateManyCities graphState, Blackhole blackhole) {
        Graph graph = graphState.testGraph;
        String string = graph.addFlight("Warszawa" + (graphState.citiesNumber -1), "Warszawa" + (graphState.citiesNumber -1), 10, 10);
        blackhole.consume(string);
        blackhole.consume(graph);
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 2)
    @BenchmarkMode(Mode.AverageTime)
    @Measurement(iterations = 5)
    public void getBestRoute1(GraphStateManyCitiesWithFlights graphState, Blackhole blackhole) {
        Graph graph = graphState.testGraph;
        graph.getBestRoute("Warszawa" + 0, "Kielce" + 0, 0);
        blackhole.consume(graph);
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 2)
    @BenchmarkMode(Mode.AverageTime)
    @Measurement(iterations = 5)
    public void getBestRoute2(GraphStateManyCitiesWithFlights graphState, Blackhole blackhole) {
        Graph graph = graphState.testGraph;
        for (int i =0; i<100; i++){
            graph.getBestRoute("Warszawa", "Kielce", 0);
        }
        blackhole.consume(graph);
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 2)
    @BenchmarkMode(Mode.AverageTime)
    @Measurement(iterations = 5)
    public void getBestRoute3(GraphStateManyCitiesWithFlights graphState, Blackhole blackhole) {
        Graph graph = graphState.testGraph;
        graph.getBestRoute("Bialystok" + 0, "Szczecin" + 0, 0);
        blackhole.consume(graph);
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 2)
    @BenchmarkMode(Mode.AverageTime)
    @Measurement(iterations = 5)
    public void getBestRoute4(GraphStateManyCitiesWithFlights graphState, Blackhole blackhole) {
        Graph graph = graphState.testGraph;
        for (int i =0; i<100; i++){
            graph.getBestRoute("Bialystok" + i, "Szczecin" + i, 0);
        }
        blackhole.consume(graph);
    }
}
