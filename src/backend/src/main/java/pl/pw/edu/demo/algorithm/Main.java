//package walutaż;
//
//import Graph.Graph;
//import Load.Load;
//import java.io.FileNotFoundException;
//import java.util.List;
//
//public class Main {
//
//    public static void main(String[] args) throws FileNotFoundException {
//        if (args.length == 3 && args[0].equals("arbitaz")) {
//            System.out.println("Rozpoczynam liczenie arbirazu");
//        } else if (args.length == 5 && args[0].equals("wymiana")) {
//            System.out.println("Rozpoczynam liczenie wymiany");
//        } else {
//            System.out.println("Podano nieprawidlowa liczbe argumentow lub podano zla nazwe operacji");
//            System.out.println("Podaj jako pierwszy argument arbitraz , nazwe pliku i ilosc waluty - dla arbitraz");
//            System.out.println("Podaj jako pierwszy argument wymiana , nazwe pliku i walute wejsciowa jej ilosci i walute wyjsciowa - dla wymiany");
//            return;
//        }
//        Load load = new Load(args[1]);
//        Graph graph = load.load();
//        List<String> result = null;
//        if (args.length == 5) {
//            result = graph.getBestExchenge(args[3], args[4], Double.parseDouble(args[2]));
//        } else {
//            result = graph.getArbitrag(Double.parseDouble(args[2]));
//        }
//        System.out.print("Ścieżka: ");
//        for (int i = result.size() - 1; i >= 0; i--) {
//            System.out.print(result.get(i) + " ");
//        }
//    }
//}
