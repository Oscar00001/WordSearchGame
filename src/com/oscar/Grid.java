package com.oscar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Grid {
    private int gridSize;
    private char[][] contents;
    private List<Coordinate> corrdinates = new ArrayList<>();

    // Inner class to increase encapsulation
    private class Coordinate{
        int x;
        int y;

        private Coordinate(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public Grid(int size){
        this.gridSize = size;
        contents = new char[gridSize][gridSize];

        for (int i = 0; i < gridSize; i++){
            for (int j = 0; j < gridSize; j++) {
                corrdinates.add(new Coordinate(i,j));
                contents[i][j] = '_'; // fill each element
            }
        }
    }

    public void fillGrid(List<String> words){
        for (String word: words){
            int x = ThreadLocalRandom.current().nextInt(0, gridSize); // random from 0 - 10
            int y = ThreadLocalRandom.current().nextInt(0, gridSize); // random from 0 - 10
                if( y + word.length() < gridSize){
                for (char c : word.toCharArray()){
                    contents[x][y++] = c;
                }
            }
        }
    }

    public void displayGrid(){
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                System.out.print(contents[i][j]); // print each element
            }
            System.out.println(""); // at the start of a new column
        }
    }



}
