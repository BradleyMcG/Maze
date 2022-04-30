package guimaze;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Random;

public class AutomaticGeneration extends CreateMaze{

    private List<int[]> enteredCells = new ArrayList<int[]>();
    private int[] currentCell = new int[2];
    private final List<int[]> directions = new ArrayList<int[]>(
            Arrays.asList(new int[]{0, 1}, new int[]{0,-1}, new int[]{-1,0}, new int[]{1,0} ));



    public AutomaticGeneration() {
        super();
        initializeMazeArray();
        currentCell = this.maze.startCell;
        enteredCells.add(currentCell);
    }



    private void initializeMazeArray() {
        this.maze.cells = new Cell[this.maze.length][this.maze.height];

    }
}