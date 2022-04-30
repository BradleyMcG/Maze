package guimaze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class CreateMaze implements ActionListener, Runnable{

    //public MazeGenerator program;
    protected Maze maze;

    JFrame frame = new JFrame();



    JLabel CreateSign = new JLabel("Create Maze");
    JLabel dimensions = new JLabel("Dimensions");
    JLabel mazeLength = new JLabel("x:");
    JTextField length = new JTextField("100<x<0");
    JLabel mazeHeight = new JLabel("y:");
    JTextField height = new JTextField("100<y<0");
    JLabel authorName = new JLabel("Author:");
    JTextField Author = new JTextField("");
    JLabel mazeTitle = new JLabel("Title:");
    JTextField Title = new JTextField("");

    JButton btnAutomatic = new JButton("Generate Automatically");
    JButton btnManual = new JButton("Generate Manually");




    CreateMaze(){


        CreateMazeGUI(); //eventually DisplayGUI (from interface)
        btnAutomatic.addActionListener(this);
        btnManual.addActionListener(this);



    }

    private void CreateMazeGUI(){

        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel pnlInputs = new JPanel(new GridBagLayout());
        frame.setContentPane(pnlInputs);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 1;
        constraints.weighty = 1;

/*
        //CreateMaze Sign
        constraints.gridx = 1;
        constraints.gridwidth = 5;
        constraints.gridy = 1;
        constraints.gridheight = 3;
        pnlInputs.add(CreateSign, constraints);

 */

        //"Dimensions" Text Label
        constraints.gridx = 1;
        constraints.gridwidth = 1;
        constraints.gridy = 5;
        pnlInputs.add(dimensions, constraints);
        //x dimension Label
        constraints.gridx = 2;
        constraints.gridwidth = 1;
        constraints.gridy = 5;
        pnlInputs.add(mazeLength, constraints);
        //x dimension Input
        constraints.gridx = 3;
        constraints.gridwidth = 1;
        constraints.gridy = 5;
        pnlInputs.add(length, constraints);
        //y dimension Label
        constraints.gridx = 4;
        constraints.gridwidth = 1;
        constraints.gridy = 5;
        pnlInputs.add(mazeHeight, constraints);
        //y dimension Input
        constraints.gridx = 5;
        constraints.gridwidth = 1;
        constraints.gridy = 5;
        pnlInputs.add(height, constraints);

        //MazeTitle Text Label
        constraints.gridx = 1;
        constraints.gridwidth = 1;
        constraints.gridy = 6;
        pnlInputs.add(mazeTitle, constraints);
        //MazeTitle text input
        constraints.gridx = 2;
        constraints.gridwidth = 1;
        constraints.gridy = 6;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        pnlInputs.add(Title, constraints);
        //Author Text Label
        constraints.gridx = 1;
        constraints.gridwidth = 1;
        constraints.gridy = 7;
        pnlInputs.add(authorName, constraints);
        //Author name input
        constraints.gridx = 2;
        constraints.gridwidth = 1;
        constraints.gridy = 7;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        pnlInputs.add(Author, constraints);



        //Generate manually button
        constraints.gridx = 2;
        constraints.gridwidth = 1;
        constraints.gridy = 8;
        constraints.gridheight = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        pnlInputs.add(btnAutomatic, constraints);
        //Generate automatic button
        constraints.gridx = 4;
        constraints.gridwidth = 1;
        constraints.gridy = 8;
        constraints.gridheight = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        pnlInputs.add(btnManual, constraints);

        //repaint();
        frame.setVisible(true);


    }



    @Override
    public void actionPerformed(ActionEvent e) {
        String title;
        String author;
        int x;
        int y;
        if(e.getSource()==btnAutomatic) {
            System.out.println("btn pressed, automatically generate maze");
            //Catch exceptions related to incorrect input - display appropriate dialog
            try {
                title = Title.getText();
                author = Author.getText();
                x = Integer.parseInt(length.getText());
                y = Integer.parseInt(height.getText());
                CreateAutomatic(title, author, x, y);
            } catch (Exception c) {
                System.out.println(c.getMessage());
                errorDialog(); // "errorDialog" unfinished
            }


        }else if(e.getSource() == btnManual){
            System.out.println("btn pressed, manually generate maze");
            //Catch exceptions related to incorrect input - display appropriate dialog
            try {
                title = Title.getText();
                author = Author.getText();
                x = Integer.parseInt(length.getText());
                y = Integer.parseInt(height.getText());
                CreateManual(title, author, x, y);
            } catch (Exception c) {
                System.out.println(c.getMessage());
                errorDialog(); // "errorDialog" unfinished

            }


        }

    }

    private void errorDialog(){
        JDialog d = new JDialog(frame, "Input Error");
        JTextField inputError = new JTextField("Uh oh. Incorrect data input; make sure your dimensions and whole numbers and " +
                "title/author is a string literal");
        d.setSize(200, 200);
        d.add(inputError);
        d.setVisible(true);
    }

    @Override
    public void run() {

    }



    private void CreateAutomatic(String title, String author, int x, int y){
        maze = new Maze(title, author, x, y);
        //MazeGenerator.StoreMaze(maze);
        //requires StoreMaze to be static, but therefore StoreMaze can only be called with one set of params
            // Dont know how to resolve
        AutomaticGeneration createAuto = new AutomaticGeneration(maze);

    }

    private void CreateManual(String title, String author, int x, int y){
        maze = new Maze(title, author, x, y);
        //MazeGenerator.StoreMaze(maze);
        //requires StoreMaze to be static, but therefore StoreMaze can only be called with one set of params
            // Dont know how to resolve

    }
}
