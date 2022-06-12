package guimaze;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManualGenerationTest {

    Maze maze;

    @BeforeEach
    public void ConstructMaze(){
        String title = "TestMaze";
        String date = "01/01/2000";
        String author = "Programmer";
        int length = 5;
        int height = 5;
        maze = new Maze(title, date, author,length, height);
        maze.populateMazeArray();
    }

    @Test
    void RemoveSingleWallNorth(){
        ManualGeneration test = new ManualGeneration(maze);
        int data[][] = new int[4][4];
        data[0][0] = 0; //x1
        data[0][1] = 0; //x2
        data[0][2] = 1;
        data[0][3] = 0;

        test.RemoveWalls(data);
    }

    @Test
    void RemoveSingleWallSouth(){
        ManualGeneration test = new ManualGeneration(maze);
        int data[][] = new int[4][4];
        data[0][0] = 0; //x1
        data[0][1] = 0; //x2
        data[0][2] = 0;
        data[0][3] = 1;

        test.RemoveWalls(data);
    }

    @Test
    void RemoveSingleWallEast(){
        ManualGeneration test = new ManualGeneration(maze);
        int data[][] = new int[4][4];
        data[0][0] = 1; //x1
        data[0][1] = 0; //x2
        data[0][2] = 0;
        data[0][3] = 0;

        test.RemoveWalls(data);
    }

    @Test
    void RemoveSingleWallWest(){
        ManualGeneration test = new ManualGeneration(maze);
        int data[][] = new int[4][4];
        data[0][0] = 0; //x1
        data[0][1] = 1; //x2
        data[0][2] = 0;
        data[0][3] = 0;

        test.RemoveWalls(data);
    }

    @Test
    void RemoveMultipleWallsNorth(){
        ManualGeneration test = new ManualGeneration(maze);
        int data[][] = new int[4][4];
        data[0][0] = 0; //x1
        data[0][1] = 0; //x2
        data[0][2] = 2;
        data[0][3] = 0;

        int[] temp = new int[5];
        int[] temp2 = new int[5];

        test.RemoveAlotOfWalls(temp,temp2,0,1);
    }

    @Test
    void RemoveMultipleWallsSouth(){
        ManualGeneration test = new ManualGeneration(maze);
        int data[][] = new int[4][4];
        data[0][0] = 0; //x1
        data[0][1] = 0; //x2
        data[0][2] = 0;
        data[0][3] = 2;
        int[] temp = new int[5];
        int[] temp2 = new int[5];

        test.RemoveAlotOfWalls(temp,temp2,1,0);
    }

    @Test
    void RemoveMultipleWallsEast(){
        ManualGeneration test = new ManualGeneration(maze);
        int data[][] = new int[4][4];
        data[0][0] = 0; //x1
        data[0][1] = 2; //x2
        data[0][2] = 0;
        data[0][3] = 2;
        int[] temp = new int[5];
        int[] temp2 = new int[5];

        test.RemoveAlotOfWalls(temp,temp2,2,3);
    }

    @Test
    void RemoveMultipleWallsWest(){
        ManualGeneration test = new ManualGeneration(maze);
        int data[][] = new int[4][4];
        data[0][0] = 2; //x1
        data[0][1] = 0; //x2
        data[0][2] = 0;
        data[0][3] = 0;
        int[] temp = new int[5];
        int[] temp2 = new int[5];

        test.RemoveAlotOfWalls(temp,temp2,3,2);
    }

}