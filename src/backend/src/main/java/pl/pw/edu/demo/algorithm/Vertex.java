package pl.pw.edu.demo.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Vertex {

    String name;
    List<Flight> neighbourList;
    double value = 0;
    boolean check = false;
    Vertex parent = null;

    public Vertex(String name) {
        this.name = name;
        this.neighbourList = new ArrayList<>();
    }

    public void checkNeighbourWithCycleBreak(Queue<Vertex> queue) {
        Vertex visiting;
        boolean cycleBreak;

        for (int i = 0; i < neighbourList.size(); i++) {
            cycleBreak = true;
            visiting = neighbourList.get(i).vertexOut;
            double newValue = neighbourList.get(i).calculatePrice(this.value);
            if (newValue < visiting.value || visiting.value ==0 ) {
                Vertex grandparent = this.parent;
                while (grandparent != null) {
                    if (grandparent == visiting) {
                        cycleBreak = false;
                        break;
                    }
                    grandparent = grandparent.parent;
                }
                if (cycleBreak) {
                    visiting.value = newValue;
                    visiting.parent = this;
                    visiting.check = false;
                    if (!queue.contains(visiting)) {
                        queue.add(visiting);
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Vertex{" + "name=" + name + ", value=" + value + '}';
    }

}
