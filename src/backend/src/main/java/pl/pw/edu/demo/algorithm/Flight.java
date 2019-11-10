package pl.pw.edu.demo.algorithm;

import lombok.Getter;

@Getter
public class Flight {
    private Vertex vertexOut;
    private double price;
    private double time;

    public Flight(Vertex vertexOut, double price, double time) {
        this.vertexOut = vertexOut;
        this.price = price;
        this.time = time;
    }

    public double calculatePrice(double baseValue) {
        if(baseValue < 0 || price < 0) {
            return Double.NaN;
        }
        return baseValue + price;
    }

    public double calculateTime(double baseValue){
        if(baseValue < 0 || time < 0) {
            return Double.NaN;
        }
        return baseValue + time;
    }

}
