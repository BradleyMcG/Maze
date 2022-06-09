package guimaze;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Random;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManualGeneration extends CreateMaze implements ActionListener, Runnable{
    /**
     * @author bradley mcgrath
     * @version 7
     */

    //Logical Fields
    private List<int[]> enteredCells = new ArrayList<int[]>();
    private int[] currentCell = new int[2];
    private int[] nextCell = new int[2];


    //GUI Fields
    private JFrame frame;
    private JPanel displayPanel;

    private final int displayLength = 500;
    private final int displayHeight = 500;

    private JPanel buttonPanel;
    private JButton btnInsertImg;
    private JButton btnSubmit;
    private JButton btnUpdate;
    private JButton btnRemoveWalls;

    private JPanel labelPanel;
    private JLabel isSolvable;
    private JLabel solveable;
    private JLabel optimalSolve;
    private JLabel optimal;
    private JLabel deadEnds;
    private JLabel dead;

    private String author;
    private String title;
    private int length;
    private int height;

    private ManualGenDialog GenDialog;
    // private String ;


    private JDialog wall_remove_dialog;

    private final List<int[]> directions = new ArrayList<int[]>(
            Arrays.asList(new int[]{0, 1}, new int[]{0,-1}, new int[]{-1,0}, new int[]{1,0} ));



    public ManualGeneration(Maze maze) {
        /**
         * @param maze - Reference to Maze instance just created to now be developed
         */
        super();
        this.maze = maze;
        this.maze.editDate = GetDate();

        currentCell = this.maze.startCell;
        enteredCells.add(currentCell);

        author = this.maze.author;
        title = this.maze.title;
        length = this.maze.length;
        height = this.maze.height;

        //maze.Wall

        CreateGUI();

    }



    public void updateFrame(){
        /**
         * updates content(state) of GUI elements contained in JFrame by rebuilding each panel individually
         * in the JFrame
         */
        buttonPanel.removeAll();
        labelPanel.removeAll();
        frame.setVisible(false);

        System.out.println("frame has been updated");

        this.maze.Draw(displayPanel);
        createButtons();
        createLabels();

        frame.setVisible(true);

    }

    private void createButtons(){
        btnUpdate = new JButton("Reset");
        btnInsertImg = new JButton("Insert Image");
        btnSubmit = new JButton("Submit Maze");
        btnRemoveWalls = new JButton("Remove Walls");

        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnInsertImg);
        buttonPanel.add(btnSubmit);
        buttonPanel.add(btnRemoveWalls);

        btnUpdate.addActionListener(this);
        btnInsertImg.addActionListener(this);
        btnSubmit.addActionListener(this);
        btnRemoveWalls.addActionListener(this);
    }

    private void createLabels(){

        isSolvable = new JLabel("Currently Solvable: ");
        solveable = new JLabel("Yes");
        optimalSolve = new JLabel("Optimal Solve(%):");
        String opt = Float.toString(OptimalPercentage()) + "%";
        optimal = new JLabel(opt);
        deadEnds = new JLabel("Dead End Cells (%)");
        String deadper = Float.toString(DeadEndPercentage()) + "%";
        dead = new JLabel(deadper);

        labelPanel.add(isSolvable);
        labelPanel.add(solveable);
        labelPanel.add(optimalSolve);
        labelPanel.add(optimal);
        labelPanel.add(deadEnds);
        labelPanel.add(dead);
    }

    private void CreateGUI(){ //will eventually be from GUI interface

        super.HideGUI();
        displayPanel = new JPanel();
        displayPanel.setLayout(null);
        displayPanel.setBackground(Color.RED);
        displayPanel.setBounds(25,25,displayLength, displayHeight);


        this.maze.Draw(displayPanel);


        buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.setBackground(Color.RED);
        buttonPanel.setBounds(25, 600, 700, 150);

        createButtons();

        labelPanel = new JPanel(new GridLayout(3,2));
        labelPanel.setBounds(550, 25, 225, 500);


        createLabels();

        frame = new JFrame("Manual Maze Generation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(800, 800);
        //frame.setResizable(false);


        frame.add(labelPanel);
        frame.add(buttonPanel);
        frame.add(displayPanel);
        frame.setVisible(true);


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
        /**
         * @return - percentage of cells that are a dead end (have 3 walls present)
         */

        return this.maze.DeadEnd_Percentage();
    }

    private List<int[]> wallDialog(){

        return new ArrayList<int[]>();
    }

    public void RemoveWalls(int[][] data){
        frame.setVisible(false);

        System.out.println("Remove walls");


        //maze.cells[2][1].break_Wall(1);
        // maze.cells[1][1].break_Wall(2);


        System.out.println("Walls should be broken");
        System.out.println("Length of array" + data.length);
        int walls = 0;
        int nextWalls = 0;



        if((data[0][0]-data[0][1]) > 1 || (data[0][1]-data[0][0]) > 1 || (data[0][2]-data[0][3]) > 1 || (data[0][3]-data[0][2]) > 1){
            System.out.println("Bigger than 1 cell apart");
            for (int i = 0; i < data.length; i++) {
                currentCell[0] = data[i][0];
                nextCell[0] = data[i][1];
                currentCell[1] = data[i][2];
                nextCell[1] = data[i][3];
                if(currentCell[0] < nextCell[0]){
                    walls = 2;
                    nextWalls = 3;
                    System.out.println("East");
                } else if (currentCell[0] > nextCell[0]){
                    walls = 3;
                    nextWalls=2;
                    System.out.println("West");
                } else if(currentCell[1] < nextCell[1]){
                    walls = 1;
                    nextWalls=0;
                    System.out.println("South");
                } else if (currentCell[1] > nextCell[1]){
                    walls = 0;
                    nextWalls=1;
                    System.out.println("North");
                }
                RemoveAlotOfWalls(currentCell,nextCell,walls,nextWalls);
            }

        } else{
            for (int i = 0; i < data.length; i++) {

                currentCell[0] = data[i][0];
                nextCell[0] = data[i][1];
                currentCell[1] = data[i][2];
                nextCell[1] = data[i][3];
                System.out.println("Current cell{0} is x1"+ currentCell[0]);
                System.out.println("Current cell{1} is y1 "+ currentCell[1]);
                System.out.println("next cell{0} is x2"+ nextCell[0]);
                System.out.println("next cell{1} is y2"+ nextCell[1]);

                if(currentCell[0] < nextCell[0]){
                    walls = 2;
                    nextWalls = 3;
                    System.out.println("East");
                } else if (currentCell[0] > nextCell[0]){
                    walls = 3;
                    nextWalls=2;
                    System.out.println("West");
                } else if(currentCell[1] < nextCell[1]){
                    walls = 1;
                    nextWalls=0;
                    System.out.println("South");
                } else if (currentCell[1] > nextCell[1]){
                    walls = 0;
                    nextWalls=1;
                    System.out.println("North");
                }


                maze.cells[currentCell[0]][currentCell[1]].break_Wall(walls);
                maze.cells[nextCell[0]][nextCell[1]].break_Wall(nextWalls);

            }
        }

        this.maze.Draw(displayPanel);
        frame.setVisible(true);
    }

    public void RemoveAlotOfWalls(int[] current, int[] next , int move, int nextmove){
        int temp = 0;
        System.out.println("Remove alot of walls");

        if(move == 0 ||move == 1 ){

            for(int i = 0; i <= next[1];i++){
                maze.cells[current[0]][i].break_Wall(move);

                maze.cells[next[0]][i].break_Wall(nextmove);

                //System.out.println("Current Cells are:" + maze.cells[current[0]][i] + "Next Cells are" + maze.cells[next[0]][next[1]+i]);
            }
            maze.cells[current[0]][current[1]].add_Wall(nextmove);
            maze.cells[next[0]][next[1]].add_Wall(move);

        } else if (move == 2 || move == 3){
            for(int i = 0; i <= next[0];i++){
                maze.cells[i][current[1]].break_Wall(move);
                maze.cells[i][next[1]].break_Wall(nextmove);
            }

            maze.cells[next[0]][next[1]].add_Wall(move);
            maze.cells[current[0]][current[1]].add_Wall(nextmove);
            //maze.cells[next[0]][next[1]].add_Wall(move);

        }

        this.maze.Draw(displayPanel);
        frame.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnUpdate){
            System.out.println("pressed 'Reset'");
            maze.populateMazeArray();
            frame.setVisible(false);
            this.maze.Draw(displayPanel);
            frame.setVisible(true);
        }
        if(e.getSource()==btnInsertImg){
            System.out.println("pressed 'insert img'");
        }
        if(e.getSource()==btnSubmit){
            System.out.println("pressed 'submit'");

            HideGUI();
            MazeGenerator.GetInstance().ShowGUI();
        }
        if(e.getSource()==btnRemoveWalls){
            System.out.println("pressed 'Remove walls'");
            //   HideGUI();
            //  MazeGenerator.GetInstance().ShowGUI();
            GenDialog = new ManualGenDialog(maze,this);

        }

    }

    private String GetDate(){
        String str = "";
        int day = LocalDate.now().getDayOfMonth();
        int month = LocalDate.now().getMonthValue();
        int year = LocalDate.now().getYear();
        str = str.concat(day + "/"+ month +"/"+ year);
        return str;
    }


    protected void HideGUI(){
        frame.dispose();
    }
    public void DisplayGUI(){
        frame.setVisible(true);
    }


}
