package guimaze;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class Display extends JFrame implements ActionListener, Runnable {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;

    JLabel headerLabel = new JLabel("Display");
    JLabel AuthorLabel = new JLabel("Author: Bradley McGrath");
    JLabel TitleLabel = new JLabel("Maze 1");
    JLabel EditDateLabel = new JLabel("Last Edit: 29/03/21");
    JLabel DateLabel = new JLabel("Creation Date: 25/03/21");

    JButton btnBack = new JButton("Back");
    JButton btnExport = new JButton("Export");
    JButton btnRoute = new JButton("Optimal Route");

    JFrame frame;
    JPanel pane;
    GridBagConstraints constraints;

    Display(){
        DisplayGUI();
    }

    private void DisplayGUI(){
        frame = new JFrame("Display GUI");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        pane = new JPanel(new GridBagLayout());
        frame.setContentPane(pane);


        constraints = new GridBagConstraints();
        constraints.weightx = 10;
        constraints.weighty = 10;

        constraints.gridx = 0;
        constraints.gridwidth = 2;
        constraints.gridy = 0;
        headerLabel.setFont(new Font("Test", Font.PLAIN, 50));
        pane.add(headerLabel, constraints);

        //BUTTONs
        /*constraints.anchor = GridBagConstraints.PAGE_END;
        constraints.gridwidth = 1;
        constraints.gridx = 3;
        constraints.gridy = 2;
        constraints.ipady = 40;
        btnBack.setFont(new Font("Test",Font.PLAIN,20));
        pane.add(btnBack, constraints);*/

        constraints.gridx = 3;
        constraints.gridwidth = 1;
        constraints.gridy = 2;
        constraints.ipady = 0;
        pane.add(btnExport, constraints);

        constraints.gridx = 3;
        constraints.gridwidth = 1;
        constraints.gridy = 3;
        constraints.ipady = 0;
        pane.add(btnRoute, constraints);


        //BUTTON ACTION LISTENERS

        btnBack.addActionListener(this);
        btnRoute.addActionListener(this);

        //IMAGE MAZE
        ImageIcon icon = new ImageIcon("noroute.png");
        constraints.gridx = 3;
        constraints.gridwidth = 1;
        constraints.gridy = 0;
        constraints.ipady = 0;
        pane.add(new JLabel(icon),constraints);

        //VIEW FRAME

        frame.setVisible(true);

    }

    private void OptimalRoute(){
        // Calculate best route on the maze and displays it

        //IMAGE MAZE
        ImageIcon icon = new ImageIcon("route.png");
        constraints.gridx = 3;
        constraints.gridwidth = 1;
        constraints.gridy = 1;
        constraints.ipady = 0;
        pane.add(new JLabel(icon),constraints);
        //System.out.println("Running");

        //VIEW FRAME

        frame.setVisible(true);

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnBack){
            System.out.println("RETURN TO HOME SCREEN");

        }
        if(e.getSource() == btnRoute){
            OptimalRoute();
        }
    }

    @Override
    public void run() {
        //ExportGUI();
    }

}
