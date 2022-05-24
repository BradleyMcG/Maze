package guimaze;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Random;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AutomaticGeneration extends CreateMaze implements ActionListener, Runnable{
    /**
     * @author sam.fleming
     * @version 2
     *
     */

    //Logical Fields
    private List<int[]> enteredCells = new ArrayList<int[]>();
    private int[] currentCell = new int[2];
    private final List<int[]> directions = new ArrayList<int[]>(
            Arrays.asList(new int[]{0, 1}, new int[]{0,-1}, new int[]{-1,0}, new int[]{1,0} ));
    private List<Integer> nextDirect = Arrays.asList(0, 1, 2, 3);

    //GUI Fields
    private JFrame frame;
    private JPanel displayPanel;
    private final int displayLength = 500;
    private final int displayHeight = 500;

    private JPanel buttonPanel;
    private JButton btnInsertImg;
    private JButton btnSubmit;
    private JButton btnRegen;

    private JPanel labelPanel;
    private JLabel optimalSolve;
    private JLabel optimal;
    private JLabel deadEnds;
    private JLabel dead;




    private void CreateGUI(){ //will eventually be from GUI interface

        super.HideGUI();
        displayPanel = new JPanel();
        displayPanel.setLayout(null);
        displayPanel.setBackground(Color.GREEN);
        displayPanel.setBounds(25,25,displayLength, displayHeight);
        //displayPanel.add(new JLabel("[Area for working Maze]"));

        this.maze.Draw(displayPanel);




        buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.setBackground(Color.RED);
        buttonPanel.setBounds(25, 600, 700, 150);
        btnRegen = new JButton("Regenerate");
        btnInsertImg = new JButton("Insert Image");
        btnSubmit = new JButton("Submit");
        buttonPanel.add(btnRegen);
        buttonPanel.add(btnInsertImg);
        buttonPanel.add(btnSubmit);


        labelPanel = new JPanel(new GridLayout(3,2));
        labelPanel.setBounds(550, 25, 225, 500);
        optimalSolve = new JLabel("Optimal Solve(%):");
        String opt = Float.toString(OptimalPercentage()) + "%";
        optimal = new JLabel(opt);
        deadEnds = new JLabel("Dead End Cells (%)");
        String deadper = Float.toString(DeadEndPercentage()) + "%";
        dead = new JLabel(deadper);
        labelPanel.add(optimalSolve);
        labelPanel.add(optimal);
        labelPanel.add(deadEnds);
        labelPanel.add(dead);

        frame = new JFrame("Automatic Maze Generation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(800, 800);
        //frame.setResizable(false);


        frame.add(labelPanel);
        frame.add(buttonPanel);
        frame.add(displayPanel);
        frame.setVisible(true);


    }



    public AutomaticGeneration(Maze maze) {
        /**
         * @param maze - Reference to Maze instance just created to now be developed
         */
        super();
        this.maze = maze;
        //initializeMazeArray();

        currentCell = this.maze.startCell;
        enteredCells.add(currentCell);

        Generate(); //AutoGenerate
        CreateGUI();
        //this.maze.Draw(displayPanel);
            //for some reason, when uncommented, CreateMaze.errorDialog() is called

        btnRegen.addActionListener(this);
        btnInsertImg.addActionListener(this);
        btnSubmit.addActionListener(this);
    }



    private void initializeMazeArray() {
        this.maze.cells = new Cell[this.maze.length][this.maze.height];
    }



    private void ResetFields(){
        this.enteredCells = new ArrayList<int[]>();
        this.currentCell = new int[2];
    }



    private float OptimalPercentage(){
        Random rand = new Random();
        float result = (float)rand.nextInt(100-1) + 1;
        return result;
        //dummy value - random percentage
    }

    private float DeadEndPercentage(){
        Random rand = new Random();
        float result = (float)rand.nextInt(100-1) + 1;
        return result;
        //dummy value - random percentage
    }

    @Override
    public void run() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnRegen){
            System.out.println("pressed 'Regen'");
        }
        if(e.getSource()==btnInsertImg){
            System.out.println("pressed 'insert img'");
        }
        if(e.getSource()==btnSubmit){
            System.out.println("pressed 'submit'");
            HideGUI();
            MazeGenerator.GetInstance().ShowGUI();
        }

    }

    private void Generate(){
        //Where the Maze generation algorithm with ensue
        System.out.println("[Maze: " + maze.title + " solution generated automatically ]");
    }

    private void AutoGenerate(){


        while(enteredCells.size() < (maze.length * maze.height)){

            AutoMove();

        }

    }

    private void AutoMove(){
        boolean done = false;
        Reset_nextDirect();
        while (!nextDirect.isEmpty()||done){
            int move = GetRandomMove(nextDirect);

            if (MoveIsValid(move)){
                //break_exit_wall()
                //make_next_current()
                //breakEntryWall()
                //addToEntered()
                //done = true;
            }else{
                //ReplaceMove()
            }

        }
        if (!done){
            //change_current_cell()
            Reset_nextDirect();
        }
    }



    private int GetRandomMove(List<Integer> nextDirect){
        Random rand = new Random();
        int randDirection = nextDirect.get(rand.nextInt(nextDirect.size()));
        return randDirection;
    }

    private boolean MoveIsValid(int move){ //int move is the direction
        /**
         * @param move - index correlating to randomly generated direction of next move
         */
        /*
        int[] moveCoords = directions.get(move);
        if (moveInEntered(moveCoords)){
            return false;
        }if (!moveInDomain(moveCoords)){
            return false;
        }else{
            return true;
        }

         */
        return false;

    }

    private void Reset_nextDirect(){
        nextDirect = Arrays.asList(0, 1, 2, 3);
    }

    protected void HideGUI(){
        frame.dispose();
    }
    public void DisplayGUI(){
        frame.setVisible(true);
    }

}