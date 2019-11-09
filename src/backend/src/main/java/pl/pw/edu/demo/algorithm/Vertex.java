package pl.pw.edu.demo.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Vertex {

    String name;
    List<Rate> neighbourList;
    double value = 0;
    boolean visit = false;
    boolean check = false;
    Vertex parrent = null;

    Vertex(String name) {
        this.name = name;
        this.neighbourList = new ArrayList<>();
    }

    void checkNeighbourWithCycleBreak(Queue<Vertex> queue, String inCurrency) {
        Vertex visiting;
        boolean cyclebreak;

        for (int i = 0; i < neighbourList.size(); i++) {
            cyclebreak = true;
            visiting = neighbourList.get(i).vertexOut;
            double newValue = neighbourList.get(i).calculateRateValue(this.value);
            if (newValue > visiting.value) {
                Vertex grandparrent = this.parrent;
                while (grandparrent != null) {
                    if (grandparrent == visiting) {
                        cyclebreak = false;
                        break;
                    }
                    grandparrent = grandparrent.parrent;
                }
                if (cyclebreak) {
                    visiting.value = newValue;
                    visiting.parrent = this;
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
