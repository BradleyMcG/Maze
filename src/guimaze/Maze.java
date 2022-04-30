package guimaze;

import java.util.*;

public class Maze{

    //constructor fields
    protected String title;
    protected String author;
    protected int length;
    protected int height;

    protected Cell[][] cells;

    protected int[] startCell = new int[2];
    protected int[] finishCell = new int[2];


    public Maze(String title, String author, int length, int height){
        this.title = title;
        this.author = author;
        this.length = length;
        this.height = height;


    }





    private void populateMazeArray(){
        for (int l = 0; l < length; l ++){
            for (int h = 0; h < length; h++ ){
                this.cells[l][h] = new Cell();
            }
        }
    }

    public void Draw(){
        for (int l = 0; l < length; l ++){
            for (int h = 0; h < length; h++ ){
                cells[l][h].Draw();
            }
        }
    }





}
