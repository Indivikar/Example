package Streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ArbeitsweiseVonStreams {
    public static void main(String[] args)
    {
        List<String> w�rter = Arrays.asList("Welt", "PC", "Hallo", "Tag");

        // 1. Stream erstellen
        Stream<String> w�rterStream = w�rter.stream();

        // 2. beliebig viele intermediate Operations - Stream bleibt Operation bestehen
        Stream<String> w�rterStreamSortiert = w�rterStream.sorted();
        //w�rterStream.sorted(); => IllegalStateException
        Stream<String> w�rterStreamSortiertGefiltert
                = w�rterStreamSortiert.filter(string -> string.length() > 3);
        Stream<String> w�rterStreamSortiertGefiltertGro�
                = w�rterStreamSortiertGefiltert.map(String::toUpperCase);

        // 3. eine terminal Operation - Stream wird nach der ersten Operation beendet
        w�rterStreamSortiertGefiltertGro�.forEach(System.out::println);

        // Kurzform
        w�rter.stream()
                .sorted()
                .filter(string -> string.length() > 3)
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }
}
