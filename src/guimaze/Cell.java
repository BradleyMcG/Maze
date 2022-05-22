package guimaze;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.BorderLayout;

import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;
import static java.awt.BorderLayout.EAST;
import static java.awt.BorderLayout.WEST;

public class Cell {

    private boolean live;
    public String[] wallPos = {NORTH, SOUTH, EAST, WEST};
    private Wall[] walls = new Wall[4];

    public Cell(){
        initializeWalls();
    }

    private void initializeWalls(){
        for (int i = 0; i < 4; i++){
            /* - Old Initialise Implementation
            if (i == 0 || i == 2 ){
                walls[i] = new Wall(true);
            }else{
                walls[i] = new Wall(false);
            }

             */

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
        cell.setBounds(x, y, cellPixels, cellPixels);
        cell.setLayout(new BorderLayout());

        for (int i = 0; i < 4; i ++){
            walls[i].Draw();

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

    private Wall createWall(Color c, String position){
        Wall wall = new Wall(position);//Create a JPanel Object and store locally
        wall.setBackground(c); //set background to c
        return wall; // return local variable
    }

}
