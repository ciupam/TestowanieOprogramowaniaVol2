package pl.pw.edu.demo.benchamarks;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import pl.pw.edu.demo.algorithm.Vertex;

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
}
