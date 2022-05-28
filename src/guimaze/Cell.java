package guimaze;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.BorderLayout;

/*
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;
import static java.awt.BorderLayout.EAST;
import static java.awt.BorderLayout.WEST;

 */

public class Cell {

    private boolean live;
    public String[] wallPos = {"NORTH", "SOUTH", "EAST", "WEST"};
    private Wall[] walls;
    public int[] coords;

    public Cell(int[] xy){
        coords = xy;
        walls = new Wall[4];
        live = false;
        initializeWalls();
    }

    private void initializeWalls(){
        for (int i = 0; i < 4; i++){
            walls[i] = new Wall(wallPos[i]);
        }

    }

    public void Draw(JPanel pane, int cellPixels, int x, int y){
        /**
         * @param pane - maze display panel
         * @param cellPixels - dimension of single square cell
         * @param x - length position of cell on 'pane'
         * @param y - height position of cell on 'pane'
         */
        JPanel cell = new JPanel();
        int lengthPos = (x*cellPixels);
        int heightPos = (y*cellPixels);
        System.out.println("Display dimensions: " + pane.getWidth() + " " + pane.getHeight());
        cell.setBounds(lengthPos, heightPos, cellPixels, cellPixels);
        System.out.println("in display pane, x=" + lengthPos + " y=" + heightPos + " and is " + cellPixels + " square");
        cell.setBackground(Color.WHITE);


        cell.setLayout(new BorderLayout());

        for (int i = 0; i < 4; i ++){
            walls[i].Draw(cellPixels);

            switch(i){
                case 0:
                    cell.add(walls[i], BorderLayout.NORTH);
                    break;
                case 1:
                    cell.add(walls[i], BorderLayout.SOUTH);
                    break;
                case 2:
                    cell.add(walls[i], BorderLayout.EAST);
                    break;
                default:
                    cell.add(walls[i], BorderLayout.WEST);

            }




            //cell.add(walls[i], BorderLayout.wallPos[i]);
                //this functionality is more robust, just don't know how to implement
        }



        pane.add(cell);


    }


}
