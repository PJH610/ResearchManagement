package controller;

import java.util.Random;

/**
 * Created by 12413 on 2018/5/23.
 */
public class NumFprmatTest {

    public static void main(String[] args) {
        int randNum = new Random(System.nanoTime()).nextInt(100000);
        String str = String.format("%06d", randNum);
        System.out.println("str: " + str);
    }

}
