package pl.pw.edu.demo.algorithm;

public class Flight {
    Vertex vertexOut;
    double price;
    double time;

    public Flight(Vertex vertexOut, double price, double time) {
        this.vertexOut = vertexOut;
        this.price = price;
        this.time = time;
    }

    public double calculatePrice(double baseValue) {
        return baseValue + price;
    }

    public double calculateTime(double baseValue){
        return baseValue + time;
    }

}
