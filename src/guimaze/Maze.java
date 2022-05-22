package guimaze;

import java.util.*;
import javax.swing.JPanel;

public class Maze{

    //constructor fields
    protected String title;
    protected String author;
    protected String date;
    protected int length;
    protected int height;

    protected Cell[][] cells;

    protected int[] startCell = new int[2];
    protected int[] finishCell = new int[2];


    public Maze(String title,String date,String author, int length, int height){
        this.title = title;
        this.author = author;
        this.length = length;
        this.height = height;
        this.date = date;



    }





    private void populateMazeArray(){
        for (int l = 0; l < length; l ++){
            for (int h = 0; h < length; h++ ){
                this.cells[l][h] = new Cell();
            }
        }
    }

    public void Draw(JPanel pane){

        /**
         * @param pane - Reference to the JPanel to be drawn on
         */

        int pane_x = pane.getWidth();
        int pane_y = pane.getHeight();
        int longest_side;

        if (pane_x != pane_y){
            System.out.println("Invalid Display Panel");
        }
        if (length >= height){
            longest_side = length;
        }else{
            longest_side = height;
        }

        int cellPixels = (int)((double)pane_x/(double)longest_side);
            //pixel size of each cell (rounded down)


        for (int l = 0; l < length; l ++){
            for (int h = 0; h < height; h++ ){
                cells[l][h].Draw(pane, cellPixels, l, h);
            }
        }
    }





}
