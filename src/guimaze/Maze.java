package guimaze;

import java.util.*;
import javax.swing.JPanel;

public class Maze{

    //constructor fields
    public String title;
    public String author;
    public String createDate;
    public String editDate;
    public int length;
    public int height;

    public Cell[][] cells;
    public List<int[]> invalidCells = new ArrayList<int[]>();


    public int[] startCell = new int[2];
    public int[] finishCell = new int[2];


    public Maze(String title,String date,String author, int length, int height){
        this.title = title;
        this.author = author;
        this.length = length;
        this.height = height;
        this.createDate = date;
        this.editDate = date;
        cells = new Cell[length][height];
        startCell[0] = 0;
        startCell[1] = 0;
        finishCell[0] = length - 1;
        finishCell[1] = height -1;

        populateMazeArray();

    }

    public Maze(String title,String date,String author, int length, int height, int[]start, int[]end){
        this.title = title;
        this.author = author;
        this.length = length;
        this.height = height;
        this.createDate = date;
        this.editDate = date;
        cells = new Cell[length][height];
        startCell[0] = start[0];
        startCell[1] = start[1];
        finishCell[0] = end[0];
        finishCell[1] = end[1];

        populateMazeArray();

    }

    public void populateMazeArray(){

        for (int len = 0; len < length; len ++){
            //System.out.println("len =" + len);
            for (int hei = 0; hei < height; hei++ ){
                //System.out.println("hei =" + hei);
                int[] coords = {len, hei};
                //System.out.println("Cell at pos; " + len + ", " + hei + " created");
                this.cells[len][hei] = new Cell(coords);

            }
        }
    }

    public float DeadEnd_Percentage(){
        float percentage = 0;
        int total = Total_DeadEnd();

        System.out.println("Dead End Cells Total:  " + total);
        float totalValid = (float)NumValidCells();
        float f_total = (float)total;
        percentage = (f_total/totalValid)*100;
        System.out.println("Dead end Percentage: " + percentage);
        return percentage;
    }

    public int Total_DeadEnd(){
        int tally = 0;
        for (int i = 0; i < length; i++){
            for (int j = 0; j < height; j++){
                if (cells[i][j].NumWallsEnabled() == 3 && cells[i][j].live){
                    tally += 1;
                }
            }

        }
        return tally;
    }

    public String TotalCellOptimal(){
        return "[not yeat implemented]";
    }

    public int NumValidCells(){
        int num;
        num = (length * height) - invalidCells.size();
        return num;
    }

    public void Draw(JPanel pane){

        pane.removeAll();
        /**
         * @param pane - Reference to the JPanel to be drawn on
         */

        int pane_x = pane.getWidth();
        int pane_y = pane.getHeight();
        int longest_side;

        //checks that displaypanel is square
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


        for (int l = 0; l < this.length; l++){
            //System.out.println("l =" + l);
            for (int h = 0; h < this.height; h++ ){
                //System.out.println("h =" + h);
                this.cells[l][h].Draw(pane, cellPixels, l, h);
                    //evaluates as null for some reason
            }
        }
    }





}
