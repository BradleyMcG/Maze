package guimaze;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Random;
import javax.swing.*;


public class AutomaticGeneration extends CreateMaze{
    /**
     * @author sam.fleming
     * @version 1
     *
     */

    //Logical Fields
    private List<int[]> enteredCells = new ArrayList<int[]>();
    private int[] currentCell = new int[2];
    private final List<int[]> directions = new ArrayList<int[]>(
            Arrays.asList(new int[]{0, 1}, new int[]{0,-1}, new int[]{-1,0}, new int[]{1,0} ));

    //GUI Fields
    JFrame frame;


    private void DisplayGUI(){ //will eventually be from GUI interface

    }



    public AutomaticGeneration(Maze maze) {
        /**
         * @param maze - Reference to Maze instance just created to now be developed
         */
        super();
        this.maze = maze;
        initializeMazeArray();
        currentCell = this.maze.startCell;
        enteredCells.add(currentCell);
    }



    private void initializeMazeArray() {
        this.maze.cells = new Cell[this.maze.length][this.maze.height];

    }



    private void ResetFields(){
        this.enteredCells = new ArrayList<int[]>();
        this.currentCell = new int[2];
    }

    private void Generate(){
        //Where the Maze generation algorithm with ensue
        System.out.println("[Maze: " + maze.title + " solution generated automatically ]");
    }

}