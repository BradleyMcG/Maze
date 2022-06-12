package guimaze;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.xml.crypto.dsig.spec.HMACParameterSpec;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class ExportTest {

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

       /* JPanel pnlDisplay = new JPanel();
        pnlDisplay.setLayout(null);
        pnlDisplay.setBounds(25, 25, 500, 500);
        maze.Draw(pnlDisplay);*/

    }

    @Test
    public void DoesBufferedImageWorks(){
        Export test = new Export(maze);
        //test.tempDraw(maze);
    }

}