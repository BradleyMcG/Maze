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
    private ArrayList<int[]> enteredCells = new ArrayList<>();
    private int[] currentCell = new int[2];
    private final ArrayList<int[]> directions = new ArrayList<int[]>(
            Arrays.asList(new int[]{0, -1}, new int[]{0,1}, new int[]{1,0}, new int[]{-1,0} ));
    private final String[] directions_str = {"NORTH", "SOUTH", "EAST", "WEST"};

    private List<Integer> nextDirect = new ArrayList<>();

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

        //currentCell = this.maze.startCell; - doesn't work - reference type logical errors
        currentCell[0] = this.maze.startCell[0];
        currentCell[1] = this.maze.startCell[1];

        //initialize enteredCells
        int[] cell_add = new int[2];
        enteredCells.add(cell_add);
        cell_add[0] = currentCell[0];
        cell_add[1] = currentCell[1];
        Reset_nextDirect();

        //Generate();
        AutoGenerate();
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

    public void updateFrame(){
        //updates the window for after logical changes
    }

    private void Generate(){
        //Where the Maze generation algorithm with ensue
        System.out.println("[Maze: " + maze.title + " solution generated automatically ]");
        int move = GetRandomMove(nextDirect);
        System.out.println("The Move is: " + move);
    }

    private void AutoGenerate(){

        //implement this when img/block cells are implemented
        System.out.println("Invalid Cells: " + this.maze.invalidCells.size());
        System.out.println("Entered Cells: " + enteredCells.get(0)[0] + "," + enteredCells.get(0)[1]);
        while (enteredCells.size() <= validCells_size()){
            //System.out.println("Num Entered Cells: " + enteredCells.size());
            AutoMove();
        }

    }

    private int validCells_size(){
        int num;
        num = (maze.length * maze.height) - this.maze.invalidCells.size();
        return num;
    }


    private void AutoMove(){
        System.out.println("New AutoMove");
        boolean done = false;
        Reset_nextDirect();
        while (!nextDirect.isEmpty()&&!done){
            int move = GetRandomMove(nextDirect);
            System.out.println("Move is (" + move + ") " + directions_str[move] + " " + directions.get(move)[0] + "," + directions.get(move)[1]);
            if (MoveIsValid(move)){
                break_exit_wall(move);
                break_entry_wall(move);
                //check_enteredCells();
                make_next_current(move);
                //check_enteredCells();
                int[] new_cell = new int[2];
                enteredCells.add(new_cell);
                new_cell[0] = currentCell[0];
                new_cell[1] = currentCell[1];
                //check_enteredCells();
                System.out.println(currentCell[0] + "," + currentCell[1] + " added to 'enteredCells'");
                //System.out.println("Entered: " + enteredCells);
                done = true;
            }else{
                Replace_nextDirect(move);
            }

        }
        if (!done){
            change_current_cell();
            Reset_nextDirect();
        }
    }

    private void AddTo_enteredCells(){
        int[] new_cell = new int[2];
        enteredCells.add(new_cell);
        new_cell[0] = currentCell[0];
        new_cell[1] = currentCell[1];
    }

    private void change_current_cell(){
        Random rand = new Random();
        int rand_cell_index = rand.nextInt(enteredCells.size());
        currentCell = enteredCells.get(rand_cell_index);
    }

    private void check_enteredCells(){
        for (int i = 0; i < enteredCells.size(); i++){
            System.out.print("Index: " + i + ",   ");
            System.out.print(" (" + enteredCells.get(i)[0] + "," + enteredCells.get(i)[1] + "), ");
        }
    }

    private void make_next_current(int move){
        //check_enteredCells();
        print_entered();
        int x = directions.get(move)[0] + currentCell[0];
        int y = directions.get(move)[1] + currentCell[1];
        currentCell[0] = x;
        currentCell[1] = y;
        System.out.println("");
        print_entered();
        //check_enteredCells();

        //All elements of enteredCells are references/storages of the same instance 'currentCell'
            //- therefore whenever currentCell is changes, all elements in enteredCells are changed
    }

    private void print_entered(){
        for (int i = 0; i < enteredCells.size(); i++){
            System.out.print(" (" + enteredCells.get(i)[0] + "," + enteredCells.get(i)[1] + "), ");
        }
        System.out.println("Start Cell: (" + this.maze.startCell[0] + "," + this.maze.startCell[1] + ")");
    }
    
    private void break_exit_wall(int move){
        this.maze.cells[currentCell[0]][currentCell[1]].break_Wall(move);
        System.out.println("Break wall " + move + " at cell: " + currentCell[0] + "," + currentCell[1]);
    }
    
    private void break_entry_wall(int move){
        int wall = 0;
        switch (move){
            case 0:
                wall = 1;
                break;
            case 1:
                wall = 0;
                break;
            case 2:
                wall = 3;
                break;
            case 3:
                wall = 2;
                break;
        }
        int next_x = currentCell[0]+ directions.get(move)[0];
        int next_y = currentCell[1]+directions.get(move)[1];
        this.maze.cells[next_x][next_y].break_Wall(wall);
        System.out.println("Break wall " + wall + " at cell: " + next_x + "," + next_y);
    }

    private int GetRandomMove(List<Integer> nextDirect){
        /**
         * @param nextDirect - the arrayList of components for each directional move
         * returns random move direction as integer
         */
        Random rand = new Random();
        int randDirection = nextDirect.get(rand.nextInt(nextDirect.size()));
        return randDirection;
    }

    private boolean MoveIsValid(int move){ //int move is the direction
        /**
         * @param move - index correlating to randomly generated direction of next move
         *
         */
        /*
        move is invalid when
            - move points outside of domain
            - move points to an invalid cell (img or blacked out)
            - move points to an already entered cell
         ie, move is onlt valid when
            - move points to an unentered valid cell within domain
         */
        int[] moveCoords = directions.get(move);
        int next_x = currentCell[0] + moveCoords[0];
        int next_y = currentCell[1] + moveCoords[1];


        if (moveInEntered(moveCoords, next_x, next_y)){
            System.out.println("Invalid, Move is Already Entered");
            return false;
        }else if (!moveInDomain(next_x, next_y)){
            System.out.println("Invalid, Move is outside domain");
            return false;
        }else if(!moveIsAvail(moveCoords, next_x, next_y)){
            System.out.println("Invalid, Move is not available");
            return false;
        }else{
            System.out.println("Move is Valid");
            return true;
        }
    }

    private boolean moveInEntered(int[] Coords, int next_x, int next_y){
        /**
         * @param Coords - index of next enetered cells coordinates from 'directions'
         */

        //int[] xy = {next_x, next_y};
        boolean already_entered = CoordsExistsIn(Coords);

        if(already_entered){
            return true;
        }else{
            System.out.println("Cell " + next_x +","+ next_y + " isn't in entered");
            return false;
        }

    }

    private boolean moveInDomain(int next_x, int next_y){
        /**
         * @param Coords - index of next enetered cells coordinates from 'directions'
         */
        boolean x = 0<=next_x && next_x <= this.maze.length-1;
        boolean y = 0<=next_y && next_y <= this.maze.height-1;
        if(x && y){
            return true;
        }else{
            return false;
        }

    }

    private boolean moveIsAvail(Object Coords, int next_x, int next_y){
        /**
         * @param Coords - index of next enetered cells coordinates from 'directions'
         */
        return true;
    }

    private void Reset_nextDirect(){
        for (int j = nextDirect.size(); j < 4; j++){
            nextDirect.add(0);
        }
        for (int i = 0; i < 4; i++){
            nextDirect.set(i, i);
        }

    }

    private void Replace_nextDirect(int move){
        for (int i = 0; i < nextDirect.size(); i++){
            if (nextDirect.get(i) == move){
                nextDirect.remove(i);
            }
        }
    }

    private boolean CoordsExistsIn(int[] Coords){
        int[] next = {currentCell[0] + Coords[0], currentCell[1] + Coords[1]};
        for (int i = 0; i < enteredCells.size(); i++){
            //System.out.print("enteredCells[" + i + "]: (" + enteredCells.get(i)[0] + "," + enteredCells.get(i)[1] + ")");
            //System.out.println("  vs  " + "Checked Cell: (" + next[0] + "," + next[1] + ")");
            if((next[0] == enteredCells.get(i)[0])&&(next[1] == enteredCells.get(i)[1])){
                return true;
            }
        }
        return false;
    }



    protected void HideGUI(){
        frame.dispose();
    }
    public void DisplayGUI(){
        frame.setVisible(true);
    }

}