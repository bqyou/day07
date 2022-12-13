package day07;

import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

public class TerminalOps {

    public static void main(String[] args) {
        Integer max = 200;
        Integer range = 99;
        Random rand = new SecureRandom();

        List<Integer> numList = new LinkedList<Integer>();
        for (Integer i = 0; i < max; i++) {
            numList.add(rand.nextInt(range));
        }

        System.out.println(">>>> numList : " + numList);
        // filter
        joining(numList);
        joiningAsReducing(numList);
        reducing(numList);

    }

    public static void filter(List<Integer> numList) {
        System.out.println("======FILTER======");
        List<Integer> resultList = new LinkedList<Integer>();
        resultList = numList.stream()
                .filter((i) -> 0 == (i % 3))
                .distinct()
                .sorted()
                .limit(5)
                .map(n -> "%d%d".formatted(n, n))
                .map(Integer::parseInt) // method reference
                .toList();

        System.out.println(">>>>streamlist: " + resultList);

    }

    public static void joining(List<Integer> numList) {
        System.out.println("======JOINING======");
        String resultString = numList.stream()
                .map(n -> "%d%d".formatted(n, n + 1))
                .limit(10)
                .collect(Collectors.joining("\n"));

        System.out.println(">>>>streamlist: \n" + resultString);
    }

    public static void reducing(List<Integer> numList) {
        System.out.println("======REDUCING======");

        Optional<Integer> total = numList.stream()
                .map(n -> "%d%d".formatted(n, n + 1))
                .map(Integer::parseInt)
                .limit(10)
                .collect(
                        // Integer apply (integer total, integer i)
                        Collectors.reducing((acc, i) -> {
                            System.out.printf("Total: %d, i: %d\n", acc, i);
                            return acc + i;
                        }));
        if (total.isPresent())
            System.out.println(">>>> total: \n" + total.get());
        else
            System.out.println("reducing produces no result");
    }

    public static void joiningAsReducing(List<Integer> numList) {
        System.out.println("======Joining as REDUCING======");

        Optional<String> total = numList.stream()
                .map(n -> "%d%d".formatted(n, n))
                .limit(10)
                .collect(
                        Collectors.reducing((acc, i) -> {
                            System.out.printf("Total: %s, i: %s\n", acc, i);
                            return acc + i;
                        }));

        if (total.isPresent())
            System.out.println(">>>> total: \n" + total.get());
        else
            System.out.println("joining produces no result");
    }

    public static void joiningAsReducing2(List<Integer> numList) {
        System.out.println("======Joining as REDUCING======");

        String total = numList.stream()
                .map(n -> "%d%d".formatted(n, n))
                .limit(10)
                .collect(
                        Collectors.reducing("", (acc, i) -> {
                            System.out.printf("Total: %s, i: %s\n", acc, i);
                            return acc + i;
                        }));

        System.out.println(">>>> total: \n" + total);
    }
}
