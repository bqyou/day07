package day07;

import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class intList {

    public static void main(String[] args) {
        Integer max = 200;
        Integer range = 99;
        Random rand = new SecureRandom();

        List<Integer> numList = new LinkedList<Integer>();
        for (Integer i = 0; i < max; i++) {
            numList.add(rand.nextInt(range));
        }

        System.out.println(">>>> numList : " + numList);

    }
}