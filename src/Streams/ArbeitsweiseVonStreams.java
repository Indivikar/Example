package Streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ArbeitsweiseVonStreams {
    public static void main(String[] args)
    {
        List<String> wörter = Arrays.asList("Welt", "PC", "Hallo", "Tag");

        // 1. Stream erstellen
        Stream<String> wörterStream = wörter.stream();

        // 2. beliebig viele intermediate Operations - Stream bleibt Operation bestehen
        Stream<String> wörterStreamSortiert = wörterStream.sorted();
        //wörterStream.sorted(); => IllegalStateException
        Stream<String> wörterStreamSortiertGefiltert
                = wörterStreamSortiert.filter(string -> string.length() > 3);
        Stream<String> wörterStreamSortiertGefiltertGroß
                = wörterStreamSortiertGefiltert.map(String::toUpperCase);

        // 3. eine terminal Operation - Stream wird nach der ersten Operation beendet
        wörterStreamSortiertGefiltertGroß.forEach(System.out::println);

        // Kurzform
        wörter.stream()
                .sorted()
                .filter(string -> string.length() > 3)
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }
}
