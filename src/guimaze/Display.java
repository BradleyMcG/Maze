package guimaze;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
/**
 * @author bradley.mcgrath
 * @version 1
 */

public class Display extends JFrame implements ActionListener, Runnable {

    protected Maze maze;

    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;

    JLabel headerLabel = new JLabel("Display");
    JLabel AuthorLabel;
    JLabel TitleLabel;

    JLabel EditDateLabel = new JLabel("Last Edit: 29/03/21");
    JLabel DateLabel = new JLabel("Creation Date: 25/03/21");

    JButton btnBack = new JButton("Submit");
    JButton btnExport = new JButton("Export");
    JButton btnRoute = new JButton("Optimal Route");
    JButton btnEdit = new JButton("Edit");

    JFrame frame;
    JPanel pane;
    GridBagConstraints constraints;

    Display(Maze maze){
        super();
        this.maze = maze;
        AuthorLabel = new JLabel("Author:" + maze.author);
        TitleLabel = new JLabel(maze.title);
        CreateGUI();
        //this.maze = maze;
    }

    private void CreateGUI(){
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

        constraints.gridx = 3;
        constraints.gridwidth = 2;
        constraints.gridy = 0;
        headerLabel.setFont(new Font("Test", Font.PLAIN, 20));
        pane.add(TitleLabel, constraints);

        constraints.gridx = 0;
        constraints.gridwidth = 2;
        constraints.gridy = 1;
        headerLabel.setFont(new Font("Test", Font.PLAIN, 20));
        pane.add(EditDateLabel, constraints);

        constraints.gridx = 0;
        constraints.gridwidth = 2;
        constraints.gridy = 2;
        headerLabel.setFont(new Font("Test", Font.PLAIN, 20));
        pane.add(DateLabel, constraints);

        constraints.gridx = 0;
        constraints.gridwidth = 2;
        constraints.gridy = 3;
        headerLabel.setFont(new Font("Test", Font.PLAIN, 20));
        pane.add(AuthorLabel, constraints);
        //BUTTONs
        //constraints.anchor = GridBagConstraints.PAGE_END;
        constraints.gridwidth = 1;
        constraints.gridx = 3;
        constraints.gridy = 6;
        constraints.ipady = 0;
        btnBack.setFont(new Font("Test",Font.PLAIN,20));
        pane.add(btnBack, constraints);

        constraints.gridx = 3;
        constraints.gridwidth = 1;
        constraints.gridy = 3;
        constraints.ipady = 0;
        pane.add(btnExport, constraints);

        constraints.gridx = 3;
        constraints.gridwidth = 1;
        constraints.gridy = 4;
        constraints.ipady = 0;
        pane.add(btnRoute, constraints);

        constraints.gridx = 3;
        constraints.gridwidth = 1;
        constraints.gridy = 5;
        constraints.ipady = 0;
        pane.add(btnEdit, constraints);


        //BUTTON ACTION LISTENERS

        btnBack.addActionListener(this);
        btnRoute.addActionListener(this);
        btnEdit.addActionListener(this);

        //IMAGE MAZE
        ImageIcon icon = new ImageIcon("noroute.png");
        constraints.gridx = 3;
        constraints.gridwidth = 1;
        constraints.gridy = 1;
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
        constraints.gridy = 2;
        constraints.ipady = 0;
        pane.add(new JLabel(icon),constraints);
        //System.out.println("Running");

        //VIEW FRAME

        frame.setVisible(true);

    }
    private void EditMaze(){
        maze = new Maze("Title","Date" ,"Author", 20, 20);
        ManualGeneration edit = new ManualGeneration(this.maze);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnBack){
            System.out.println("RETURN TO HOME SCREEN");

            MazeGenerator temp = new MazeGenerator();

        }
        if(e.getSource() == btnRoute){
            OptimalRoute();
        }
        if(e.getSource() == btnEdit){
            EditMaze();
        }
        if(e.getSource() == btnExport){
            Export temp = new Export();
        }


    }

    @Override
    public void run() {
        //ExportGUI();
    }

    private void HideGUI(){
        frame.setVisible(false);
    }
    public void DisplayGUI(){
        frame.setVisible(true);
    }

}
