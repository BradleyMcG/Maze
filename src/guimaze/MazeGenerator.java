package guimaze;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;



public class MazeGenerator extends JFrame implements ActionListener, Runnable {

    //Logical Fields
    public static List<Maze> allMazes = new ArrayList<Maze>();


    //GUI Fields
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;

    private JPanel pnlOne;
    private JPanel pnlTwo;
    private JPanel pnlBtn;
    private JPanel pnlFour;
    private JPanel pnlFive;
    private JPanel centrePnl;

    private JButton btnCreate;
    private JButton btnFind;
    private JButton btnDisplay;

    private JFrame frame;



    private void createGUI() {
        JFrame frame = new JFrame("Maze Generator");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel pane = new JPanel(new GridBagLayout());
        frame.setContentPane(pane);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 10;
        constraints.weighty = 10;
        //constraints.fill = GridBagConstraints.HORIZONTAL;

        JLabel headerLabel = new JLabel("Maze Generator");
        constraints.gridx = 1;
        constraints.gridwidth = 2;
        constraints.gridy = 0;
        pane.add(headerLabel, constraints);

        btnCreate = new JButton("Create Maze");
        constraints.gridx = 1;
        constraints.gridwidth = 1;
        constraints.gridy = 1;
        pane.add(btnCreate, constraints);

        btnDisplay = new JButton("Display Maze");
        constraints.gridx = 1;
        constraints.gridwidth = 1;
        constraints.gridy = 2;
        pane.add(btnDisplay, constraints);

        btnFind = new JButton("Find Previous Maze");
        constraints.gridx = 1;
        constraints.gridwidth = 1;
        constraints.gridy = 3;
        pane.add(btnFind, constraints);

        btnCreate.addActionListener(this);
        btnFind.addActionListener(this);
        btnDisplay.addActionListener(this);

        pnlTwo = createPanel(Color.BLACK);
        getContentPane().add(pnlTwo,BorderLayout.WEST);

        pnlBtn = createPanel(Color.BLACK);
        getContentPane().add(pnlBtn, constraints.gridx = 0);
        pnlFour = createPanel(Color.BLACK);
        getContentPane().add(pnlFour,BorderLayout.NORTH);
        pnlFive = createPanel(Color.BLACK);
        getContentPane().add(pnlFive,BorderLayout.EAST);
        repaint();

        frame.setVisible(true); //Show the window
    }



    private void createCreationGui(){
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        //Panel related code will go here
        // pnlOne = createPanel(Color.WHITE);
        getContentPane().add(pnlOne,BorderLayout.CENTER);
        pnlOne = new JPanel(new GridLayout(5, 2, 5, 5));

        pnlTwo = createPanel(Color.BLACK);
        getContentPane().add(pnlTwo,BorderLayout.WEST);
        pnlBtn = createPanel(Color.BLACK);
        getContentPane().add(pnlBtn,BorderLayout.SOUTH);

        pnlFour = createPanel(Color.BLACK);
        getContentPane().add(pnlFour,BorderLayout.NORTH);

        pnlFive = createPanel(Color.BLACK);
        getContentPane().add(pnlFive,BorderLayout.EAST);
        repaint();
        setVisible(true);
    }

    private JPanel createPanel(Color c) {
        //Create a JPanel object and store it in a local var
        JPanel var = new JPanel();
        //set the background colour to that passed in c
        var.setBackground(c);
        //Return the JPanel object
        return var;
    }

    private JButton createButton(String str) {
        //Create a JButton object and store it in a local var
        JButton var = new JButton(str);
        //Set the button text to that passed in str

        //Add the frame as an actionListener
        var.addActionListener(this);

        //Return the JButton object
        return var;
    }

    private void addToPanel(JPanel jp,Component c, GridBagConstraints
            constraints,int x, int y, int w, int h) {
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = w;
        constraints.gridheight = h;
        jp.add(c, constraints);
    }

   /* private void layoutButtonPanel() {
        GridBagLayout layout = new GridBagLayout();
        pnlOne.setLayout(layout);
        //Lots of layout code here
        //pnlOne.add(btnCreate, new GridBagConstraints());
        //pnlOne.add(btnFind, new GridBagConstraints());
      //  pnlOne.add(btnDisplay, new GridBagConstraints());
        //add components to grid
        GridBagConstraints constraints = new GridBagConstraints();
        //Defaults
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        //constraints.weightx = 10;
       // constraints.weighty = 10;



        addToPanel(pnlOne,btnCreate,constraints,400,200,2,1);
        //addToPanel(pnlBtn, btnUnload,constraints,3,0,2,1);
        addToPanel(pnlOne, btnFind,constraints,10,0,2,1);

        addToPanel(pnlOne, btnDisplay,constraints,3,10,2,1);
    } */

    private void layoutButtonPanel(){
       // JPanel pane = new JPanel(new GridBagLayout()); //Create a pane to house all content, and give it a GridBagLayout
     //   frame.setContentPane(pane);
    }



    @Override
    public void run() {
        // SwingUtilities.invokeLater(new FramesAndPanels("BorderLayout"));

        createGUI();



    }

    public static void main(String[] args){

        SwingUtilities.invokeLater(new MazeGenerator());
        /*
        //eventually need to utilise a singleton pattern to maintain MazeGenerator (Program)
        //is only instantiated once without being static - needs to be instantiated to store mazes
        // - THIS WILL ALLOW MAIN TO BE STATIC ONCE AGAIN
         */
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnCreate){
            CreateMaze Create = new CreateMaze();
            System.out.println("pressed");
        }
        if(e.getSource()==btnFind){
            System.out.println("pressed");
        }
        if(e.getSource()==btnDisplay){
            System.out.println("pressed");
        }
        
    }

    public void StoreMaze(Maze maze){
        allMazes.add(maze);
    }

    private void populateDummyMazes(){
        allMazes.add(new Maze("Maze 1", "Jim Jameson", 10, 7));
        allMazes.add(new Maze("Maze 2", "Zinedine Zidane", 5, 10));
        allMazes.add(new Maze("Maze for the free world", "Nelson Mandela", 96, 96));
        allMazes.add(new Maze("But its honest work", "Brad", 2, 2));
    }
}
