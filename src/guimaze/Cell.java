package guimaze;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.BorderLayout;




public class Cell {

    /**
     * @author sam.fleming
     * @version 5
     *
     */

    public boolean live;
    public String[] wallPos = {"NORTH", "SOUTH", "EAST", "WEST"};
    private Wall[] walls;
    public int[] coords;


    public Cell(int[] xy){
        coords = xy;
        walls = new Wall[4];
        live = true;
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
        //System.out.println("Display dimensions: " + pane.getWidth() + " " + pane.getHeight());
        cell.setBounds(lengthPos, heightPos, cellPixels, cellPixels);
        //System.out.println("in display pane, x=" + lengthPos + " y=" + heightPos + " and is " + cellPixels + " square");
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
                case 3:
                    cell.add(walls[i], BorderLayout.WEST);
                    break;

            }




            //cell.add(walls[i], BorderLayout.wallPos[i]);
                //this functionality is more robust, just don't know how to implement
        }



        pane.add(cell);


    }

    public int NumWallsEnabled(){
        int tally = 0;
        for (int i = 0; i < 4; i ++){
            if(walls[i].enabled){
                tally += 1;
            }
        }
        return tally;
    }

    public void break_Wall(int wall){
        walls[wall].Disable();
    }

    public void add_Wall(int wall){
        walls[wall].Enable();
    }

}
