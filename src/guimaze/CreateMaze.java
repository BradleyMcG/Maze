package guimaze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


public class CreateMaze implements ActionListener, Runnable{

    //public MazeGenerator program;


    //////////CHANGES
    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS mazes ("
                    + "idx INTEGER PRIMARY KEY /*!40101 AUTO_INCREMENT */ NOT NULL UNIQUE,"
                    + "title VARCHAR(30),"
                    + "length VARCHAR(30),"
                    + "height VARCHAR(20),"
                    + "author VARCHAR(10),"
                    + "date VARCHAR(30)" + ");";

    private PreparedStatement addMaze;

    private static final String INSERT_MAZE = "INSERT INTO mazes (title, length, height, author, date) VALUES (?, ?, ?, ?, ?);";

    private static final String GET_NAMES = "SELECT name FROM address";

    private static final String GET_PERSON = "SELECT * FROM address WHERE name=?";

    private static final String DELETE_PERSON = "DELETE FROM address WHERE name=?";

    private static final String COUNT_ROWS = "SELECT COUNT(*) FROM address";

    private Connection connection;




    ///////// CHAGNES


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

    ///changes
    public void addMaze() {
        try {
            /* BEGIN MISSING CODE */
            addMaze.setString(1, Title.getText());
            addMaze.setString(2, length.getText());
            addMaze.setString(3, height.getText());
            addMaze.setString(4, authorName.getText());
            addMaze.setString(5, maze.createDate);
            addMaze.execute();
            /* END MISSING CODE */
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    //changes

    CreateMaze(){


        connection = JDBCConnection.getInstance();
        try {
            Statement st = connection.createStatement();
            st.execute(CREATE_TABLE);
            /* BEGIN MISSING CODE */
            addMaze = connection.prepareStatement(INSERT_MAZE);
            /* END MISSING CODE */
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        CreateMazeGUI(); //eventually DisplayGUI (from interface)
        btnAutomatic.addActionListener(this);
        btnManual.addActionListener(this);



    }

    private void CreateMazeGUI(){

        frame.setSize(800, 800);
        frame.setResizable(false);
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


    private void inputHandler(boolean Auto) throws NumberFormatException{
        /**
         * @param Auto - an indicator for which button has been pressed (Auto OR Manual)
         */
        //Catch exceptions related to incorrect input - display appropriate dialog
        String title = Title.getText();
        String author = Author.getText();
        int x = Integer.parseInt(length.getText());
        System.out.println("input length : " + x);
        int y = Integer.parseInt(height.getText());
        System.out.println("input height: " + y);
        String date ="14/12/2000";
        HideGUI();
        if (Auto){
            CreateAutomatic(title,date,author, x, y);
        }else{
            CreateManual(title,date,author, x, y);
        }
    }


    private void InputExceptionHandler(boolean Auto){
        /**
         * @param Auto - an indicator for which button has been pressed (Auto OR Manual)
         */
        try{
          inputHandler(Auto);
        } catch(NumberFormatException c){
            System.out.println(c.getMessage());
            errorDialog(); // "errorDialog" unfinished
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String title;
        String author;
        int x;
        int y;
        if(e.getSource()==btnAutomatic) {
            System.out.println("btn pressed, automatically generate maze");
            InputExceptionHandler(true);
        }else if(e.getSource() == btnManual){
            System.out.println("btn pressed, manually generate maze");
            InputExceptionHandler(false);
        }

    }

    private void errorDialog(){
        JDialog d = new JDialog(frame, "Input Error");
        JPanel p = new JPanel();
        p.setBorder(BorderFactory.createLineBorder(Color.black));
        JTextField inputError = new JTextField("Uh oh. Incorrect data input; make sure your dimensions and whole numbers and " +
                "title/author is a string literal");
        inputError.setSize(100, 100);
        d.setSize(400, 200);
        p.add(inputError);
        d.add(p);
        d.setVisible(true);
    }

    @Override
    public void run() {

    }



    private void CreateAutomatic(String title,String date ,String author, int x, int y){
        maze = new Maze(title,date,author, x, y);
        //MazeGenerator.StoreMaze(maze);
        //requires StoreMaze to be static, but therefore StoreMaze can only be called with one set of params
            // Dont know how to resolve
        MazeGenerator.GetInstance().NewMaze(this.maze);
        AutomaticGeneration createAuto = new AutomaticGeneration(maze);

    }

    private void CreateManual(String title,String date, String author, int x, int y){
        maze = new Maze(title,date,author, x, y);
        //MazeGenerator.StoreMaze(maze);
        //requires StoreMaze to be static, but therefore StoreMaze can only be called with one set of params
            // Dont know how to resolve
        MazeGenerator.GetInstance().NewMaze(this.maze);
        ManualGeneration createManual = new ManualGeneration(maze);

    }

    protected void HideGUI(){
        frame.dispose();
    }
    public void DisplayGUI(){
        frame.setVisible(true);
    }

}
