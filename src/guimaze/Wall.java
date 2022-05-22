package guimaze;

import javax.swing.*;
import java.awt.*;

public class Wall extends JPanel{

    private boolean enabled;
    private String position;

    public Wall(boolean direction){

        this.enabled = direction;
    }

    public Wall(String position){
        this.position = position;
        enabled = true;
    }

    public void Draw(){

        if (enabled){
            setBackground(Color.BLACK);
        }else{
            setBackground(Color.WHITE);
        }
    }



}
