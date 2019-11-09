package pl.pw.edu.demo.algorithm;

public class Rate {
    Vertex vertexOut;
    double price;
    double time;

    Rate(Vertex vartexOut, double price, double time) {
        this.vertexOut = vartexOut;
        this.price = price;
        this.time = time;
    }

    double calculatePrice(double baseValue) {
        return baseValue + price;
    }

    double calculateTime(double baseValue){
        return baseValue + time;
    }

}
