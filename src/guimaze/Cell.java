package guimaze;

import javax.swing.JPanel;
import java.awt.BorderLayout;

public class Cell {

    private boolean live;
    private Wall[] walls = new Wall[4];

    public Cell(){
        initializeWalls();
    }

    private void initializeWalls(){
        for (int i = 0; i < 4; i++){

            if (i == 0 || i == 2 ){
                walls[i] = new Wall(true);
            }else{
                walls[i] = new Wall(false);
            }


        }
    }

    public void Draw(JPanel pane, int cellPixels, int x, int y){
        JPanel cell = new JPanel(new BorderLayout());

                /*
        for (int i = 0; i < 4; i ++){
            walls[i].Draw();
        }

                 */
    }

}
