package com.oscar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Grid {
    private int gridSize;
    private char[][] contents;
    private List<Coordinate> coordinates = new ArrayList<>();

    private enum Direction {
        HORIZONTAL,
        VERTICAL,
        DIAGONAL
    }
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
                coordinates.add(new Coordinate(i,j)); // add to the arraylist of Coordinates objects which will have one specif x and y
                contents[i][j] = '_'; // fill each element
            }
        }
    }

    public void fillGrid(List<String> words){
        for (String word: words){
            Collections.shuffle(coordinates);
            for (Coordinate coordinate: coordinates){
                int x = coordinate.x;//get shuffled coordinate from coordinate list and put it in this int
                int y = coordinate.y;//get shuffles coordinate from coodinate list and put in in this int
                Direction selectedDirection = getDirection(word, coordinate);
                if(doesWordFit(word,coordinate)) {

                    for (char c: word.toCharArray()){
                        contents[x][y++] = c;
                    }
                    break;//need this break to finsh for loop
                }
//                if(y + word.length() < gridSize){
//                    for (char c: word.toCharArray()){
//                        contents[x][y++] = c;
//                    }
//                    break;
//                }
            }
        }
    }

    public void displayGrid(){
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                System.out.print(contents[i][j] + " "); // print each element
            }
            System.out.println(""); // at the start of a new column
        }
    }

    private boolean existArea(String word, Coordinate coordinate){
        if (coordinate.y +word.length() < gridSize){
            for (int i = 0; i < word.length(); i++) {
                if(contents[coordinate.x][coordinate.y + i] == '_'){
                    System.out.print(word + "This is the inner if loop");
                    System.out.println(contents[coordinate.x][coordinate.y +i]);
                    return true;
                }
            }
            return true;
        }
        return false;
    }

    private boolean doesWordFit(String word, Coordinate cord){
        //check if the words length will fit there
        if(cord.y + word.length() < gridSize){
            // lets check if for that area so lets say coordinate x = 1 y = [1,2,3}
            for (int i = 0; i < word.length(); i++) {
                System.out.println(contents[cord.x][cord.y+1]);
                System.out.println(word + " This is the inner for  loop");
                if(contents[cord.x][cord.y + i] != '_' ){
                    // instead of checking for one we will go through each untill we find a notchar
                    System.out.println(word + " This is the inner if loop");
//                    System.out.println(contents[cord.x][cord.y+1]);
                    return false;
                }
            }
            System.out.println("This is true");
            return true;
        }
        System.out.println("This is false");
        return false; //this is alwaays going to return also
    }

    private Direction getDirection( String word, Coordinate coordinate){
        List<Direction> directions = Arrays.asList(Direction.values());
        Collections.shuffle(directions);
        for (Direction direction: directions){
            if(doesFit(word, coordinate,direction)){
                return direction;
            }
        }
        return null;
    }
    private boolean doesFit(String word, Coordinate cord, Direction direction){
        int wordLength = word.length();

        switch (direction){
            case DIAGONAL:
                if(cord.y + wordLength > gridSize || cord.x > gridSize)
                    return false;
                for (int i = 0; i < wordLength; i++) {
                    if(contents[cord.x + i][cord.y + i] !='_')
                        return false;
                }
                break;

            case VERTICAL:
                if(cord.y + wordLength > gridSize)
                    return false;
                for (int i = 0; i < wordLength; i++) {
                    if( contents[cord.x] [cord.y + 1] != '_')
                        return true;
                }
                break;

            case HORIZONTAL:
                if(cord.x + wordLength > gridSize)
                    return false;
                for (int i = 0; i < wordLength; i++) {
                    if(contents[cord.x +1] [cord.y] != '_')
                        return false;
                }
                break;
        }

        return true;
    }


}
