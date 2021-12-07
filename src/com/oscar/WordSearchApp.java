package com.oscar;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class WordSearchApp {
    public static void main( String[] args){
        List<String> words = Arrays.asList("COVID-19","CVS","WASH","SAFE","THANKYOU");
//        List<String> words = Arrays.asList("One","TWO","THREE","FOUR", "FIVE" ,  "SIX" , "SEVEN");
        Grid grid = new Grid(15);
        grid.fillGrid(words);
        grid.displayGrid();
        System.out.println(words);
    }
}
