package guimaze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author bradley.mcgrath
 * @version 1
 */

public class Export implements ActionListener, Runnable {

    protected Maze maze;

    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;

    JLabel headerLabel = new JLabel("Export");
    JButton btnBack = new JButton("Submit");
    JFrame frame;


    //changes

    private PreparedStatement addMaze;

    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS mazes ("
                    + "idx INTEGER PRIMARY KEY /*!40101 AUTO_INCREMENT */ NOT NULL UNIQUE,"
                    + "title VARCHAR(30),"
                    + "length VARCHAR(30),"
                    + "height VARCHAR(20),"
                    + "author VARCHAR(10),"
                    + "date VARCHAR(30)" + ");";





    private static final String INSERT_MAZE = "INSERT INTO mazes (title, length, height, author, date) VALUES (?, ?, ?, ?, ?);";


    private static final String INSERT_MAZES = "INSERT INTO mazes (title, length, height, author, cells, date) VALUES (?, ?, ?, ?, ?, ?);";




    private Connection connection;
    ///changes
    public void addMaze() {



        try {
            /* BEGIN MISSING CODE */
            addMaze.setString(1, maze.title);
            addMaze.setString(2, String.valueOf(maze.length));
            addMaze.setString(3, String.valueOf(maze.height));
            addMaze.setString(4, maze.author);
            addMaze.setString(5, maze.createDate);
            addMaze.execute();
            /* END MISSING CODE */
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    //changes

    public Export(Maze maze){
        super();
        this.maze = maze;

        connection = JDBCConnection.getInstance();


        try {
            Statement st = connection.createStatement();
            st.execute(CREATE_TABLE);

            /* BEGIN MISSING CODE */
            addMaze = connection.prepareStatement(INSERT_MAZE);
            addMaze();
            /* END MISSING CODE */
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        ExportGUI();
    }

    public Export(Maze maze, Boolean check){

        //OPTIMAL
        super();
        this.maze = maze;

        connection = JDBCConnection.getInstance();


        try {
            Statement st = connection.createStatement();
            st.execute(CREATE_TABLE);

            /* BEGIN MISSING CODE */
            addMaze = connection.prepareStatement(INSERT_MAZE);
            addMaze();
            /* END MISSING CODE */
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        ExportGUI();
    }


    private void ExportGUI(){


        frame = new JFrame("Export GUI");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

      //  JPanel bottomPanel = new JPanel(new GridBagLayout());
      //  bottomPanel.add(btnBack, BorderLayout.LINE_END);


        JPanel pane = new JPanel(new GridBagLayout());


        frame.setContentPane(pane);


        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 10;
        constraints.weighty = 10;

        constraints.gridx = 1;
        constraints.gridwidth = 2;
        constraints.gridy = 0;
        headerLabel.setFont(new Font("Test", Font.PLAIN, 50));
        pane.add(headerLabel, constraints);

        //BUTTONs
        constraints.anchor = GridBagConstraints.PAGE_END;
        constraints.gridwidth = 1;
        constraints.gridx = 3;
        constraints.gridy = 2;
        constraints.ipady = 40;
        btnBack.setFont(new Font("Test",Font.PLAIN,20));
        pane.add(btnBack, constraints);


        //BUTTON ACTION LISTENERS

        btnBack.addActionListener(this);

        //VIEW FRAME

        frame.setVisible(true);

    }





    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnBack){
            System.out.println("RETURN TO HOME SCREEN");
            HideGUI();
            MazeGenerator.GetInstance().ShowGUI();
        }
    }

    @Override
    public void run() {
        //ExportGUI();
    }

    private void HideGUI(){
        frame.dispose();
    }
    public void DisplayGUI(){
        frame.setVisible(true);
    }
}
