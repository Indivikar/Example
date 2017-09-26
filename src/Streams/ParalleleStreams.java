package Streams;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// https://www.youtube.com/watch?v=BMGqmP0VHS8&t=959

public class ParalleleStreams
{
    public static void main(String[] args)
    {
        int anzahl = 10_000_000;

        List<Integer> zahlenVon1Bis800 = Stream.iterate(1, x -> x + 1)
                                .limit(anzahl)
                                .collect(Collectors.toList());

        BiFunction<Integer, Integer, Integer> accumulator = Integer::sum;

        BinaryOperator<Integer> combiner = Integer::sum;

        int summe = zahlenVon1Bis800.parallelStream()
                        .reduce(0, accumulator, combiner);

        System.out.println(summe);
    }
}
