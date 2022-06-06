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
     * @version 4
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
    private JButton btnOptimal;

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


    public void updateFrame(){
        /**
         * updates content(state) of GUI elements contained in JFrame
         */
        String opt = Float.toString(OptimalPercentage()) + "%";
        optimal = new JLabel(opt);

        String deadper = Float.toString(DeadEndPercentage()) + "%";
        dead = new JLabel(deadper);

        this.maze.Draw(displayPanel);
    }


    public AutomaticGeneration(Maze maze) {
        /**
         * @param maze - Reference to Maze instance just created to now be developed
         */
        super();
        this.maze = maze;
        //initializeMazeArray();


        AutoGenInitialize();


        AutoGenerate();
        CreateGUI();
        //this.maze.Draw(displayPanel);
            //for some reason, when uncommented, CreateMaze.errorDialog() is called

        btnRegen.addActionListener(this);
        btnInsertImg.addActionListener(this);
        btnSubmit.addActionListener(this);
    }

    private void AutoGenInitialize(){
        //currentCell = this.maze.startCell; - doesn't work - reference type logical errors
        currentCell[0] = this.maze.startCell[0];
        currentCell[1] = this.maze.startCell[1];

        //initialize enteredCells
        int[] cell_add = new int[2];
        enteredCells.add(cell_add);
        cell_add[0] = currentCell[0];
        cell_add[1] = currentCell[1];
        Reset_nextDirect();
    }



    private void initializeMazeArray() {
        this.maze.cells = new Cell[this.maze.length][this.maze.height];
    }



    private void ResetFields(){
        this.enteredCells = new ArrayList<int[]>();
        this.currentCell = new int[2];
    }



    private float OptimalPercentage(){
        /**
         * @return - percentage of cells in the optimal (shortest) solution to maze
         */
        Random rand = new Random();
        float result = (float)rand.nextInt(100-1) + 1;
        return result;
        //dummy value - random percentage
    }

    private float DeadEndPercentage(){
        /**
         * @return - percentage of cells that are a dead end (have 3 walls present)
         */
        /*
        Random rand = new Random();
        double result = (double)rand.nextInt(100-1) + 1;
        return result;
        //dummy value - random percentage
         */
        return this.maze.DeadEnd_Percentage();
    }

    @Override
    public void run() {

    }


    private void Regen(){
        System.out.println("pressed 'Regen'");
        //maze.populateMazeArray();
        //AutoGenInitialize();
        //AutoGenerate();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnRegen){
            Regen();
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


    private void AutoGenerate(){
        /**
         * Condition controlled loop excecutes 'AutoMoves' which populate collection enteredCells to automatically
         * generate the maze. Will stop performing moves once maze is fully generated.
         */
        //implement this when img/block cells are implemented
        System.out.println("Invalid Cells: " + this.maze.invalidCells.size());
        System.out.println("Entered Cells: " + enteredCells.get(0)[0] + "," + enteredCells.get(0)[1]);
        while (enteredCells.size() <= maze.NumValidCells()-1){

            AutoMove();

        }
        System.out.println("Num Entered Cells: " + enteredCells.size());
        Check_enteredCells_dupes();


    }

    //originally where NumValidCells() was called
    private int validCells_size(){
        /**
         * @return - the number of cells in maze that aren't disabled
         */
        int num;
        num = (maze.length * maze.height) - this.maze.invalidCells.size();
        return num;
    }


    private void AutoMove(){
        /**
         * From its starting state, currentCell, randomly generates directions to move into, break the respective
         * walls, and add new cell to eneteredCells . If no directions are available, will randomly change
         * currentCell to try again
         */
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

                AddTo_enteredCells();

                //check_enteredCells();
                //System.out.println(currentCell[0] + "," + currentCell[1] + " added to 'enteredCells'");
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
        /**
         * adds the currentCell to enteredCells avoiding reference type mishaps
         */
        int[] new_cell = new int[2];
        enteredCells.add(new_cell);
        new_cell[0] = currentCell[0];
        new_cell[1] = currentCell[1];
    }

    private void change_current_cell(){
        /**
         * After no available directions, the currentCell changed to a radnom
         * one that already has been entered - so next move can ensue
         */
        Random rand = new Random();
        int rand_cell_index = rand.nextInt(enteredCells.size());
        int[] cell = enteredCells.get(rand_cell_index);
        currentCell[0] = cell[0];
        currentCell[1] = cell[1];
        //currentCell = enteredCells.get(rand_cell_index);
        //currentCell[0] = enteredCells.get(rand_cell_index)[0];
        //currentCell[1] = enteredCells.get(rand_cell_index)[1];
        System.out.println("New Random Current: " + currentCell[0] + currentCell[1]);
    }

    private void check_enteredCells(){
        /**
         * used in development
         */
        for (int i = 0; i < enteredCells.size(); i++){
            System.out.print("Index: " + i + ",   ");
            System.out.print(" (" + enteredCells.get(i)[0] + "," + enteredCells.get(i)[1] + "), ");
        }
    }

    private void make_next_current(int move){
        /**
         * Makes the next cell (the checking cell) the now current cell after completing a move
         *
         * @param move - the integer index for a move either N,S,E or W (ref directions_str)
         */
        //check_enteredCells();
        //print_entered();
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
        /**
         * - used in development - to print entire collection 'enteredCells'
         */
        for (int i = 0; i < enteredCells.size(); i++){
            System.out.print(" (" + enteredCells.get(i)[0] + "," + enteredCells.get(i)[1] + "), ");
        }
        System.out.println("Start Cell: (" + this.maze.startCell[0] + "," + this.maze.startCell[1] + ")");
    }
    
    private void break_exit_wall(int move){
        /**
         * Disables the wall fom the exiting cell
         *
         * @param move - the integer index for a move either N,S,E or W (ref directions_str)
         */
        this.maze.cells[currentCell[0]][currentCell[1]].break_Wall(move);
        System.out.println("Break wall " + move + " at cell: " + currentCell[0] + "," + currentCell[1]);
    }
    
    private void break_entry_wall(int move){
        /**
         * Disables the wall in the entering cell
         *
         * @param move - the integer index for a move either N,S,E or W (ref directions_str)
         */
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
         * @return - the integer index for a move either N,S,E or W (ref directions_str)
         */
        Random rand = new Random();
        int randDirection = nextDirect.get(rand.nextInt(nextDirect.size()));
        return randDirection;
    }

    private boolean MoveIsValid(int move){ //int move is the direction
        /**
         * @param move - the integer index for a move either N,S,E or W (ref directions_str)
         * @return - indicator whether checked cell can be entered
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
         * @param Coords - index of new x,y components for checked cell from 'directions'
         * @param next_x - the length coordinate for the cell being checked
         * @param next_y - the height coordinate for the cell being checked
         * @return - indicator whether next checked cell has already been entered
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
         * @param next_x - the length coordinate for the cell being checked
         * @param next_y - the height coordinate for the cell being checked
         * @return - indicator whether checked cell is within maze length&width domain
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
         * @param Coords
         * @param next_x - the length coordinate for the cell being checked
         * @param next_y - the height coordinate for the cell being checked
         * @return - indicator whether checked cell is not disabled
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
        /**
         * @param move - the integer index for a move either N,S,E or W (ref directions_str)
         */
        for (int i = 0; i < nextDirect.size(); i++){
            if (nextDirect.get(i) == move){
                nextDirect.remove(i);
            }
        }
    }

    private boolean CoordsExistsIn(int[] Coords){
        /**
         * @param Coords - index of new x,y components for checked cell from 'directions'
         * @return - Indicator whether checked cell is already entered
         */
        int[] next = {currentCell[0] + Coords[0], currentCell[1] + Coords[1]};
        for (int i = 0; i < enteredCells.size(); i++){
            System.out.print("enteredCells[" + i + "]: (" + enteredCells.get(i)[0] + "," + enteredCells.get(i)[1] + ")");
            System.out.println("  vs  " + "Checked Cell: (" + next[0] + "," + next[1] + ")");
            if((next[0] == enteredCells.get(i)[0])&&(next[1] == enteredCells.get(i)[1])){
                return true;
            }
        }
        return false;
    }

    private void Check_enteredCells_dupes(){
        ArrayList<int[]> dupes = new ArrayList<>();
        int[] cell_tally = new int[enteredCells.size()];
        String str = "";
        for (int i = 0; i < enteredCells.size(); i++){
            int[] tester = enteredCells.get(i);
            int tally = 0;
            for (int j = 0; j < enteredCells.size(); j++){
                int[] checker = enteredCells.get(j);
                if(checker[0] == tester[0] && checker[1] == tester[1]){
                    tally += 1;
                }
            }
            cell_tally[i] = tally;
        }
        for (int i = 0; i < cell_tally.length; i++){
            if (cell_tally[i] > 1){
                int[] next = new int[2];
                dupes.add(next);
                next[0] = enteredCells.get(i)[0];
                next[1] = enteredCells.get(i)[1];
            }
        }
        for (int k = 0; k < dupes.size(); k++){
            str = str.concat(", (" + dupes.get(k)[0] + "," + dupes.get(k)[1] + ")");
        }
        double dupl = (dupes.size()/2);
        System.out.print(dupl + " duplicates in entered Cells: ");
        System.out.println(str);
    }


    protected void HideGUI(){
        frame.dispose();
    }
    public void DisplayGUI(){
        frame.setVisible(true);
    }

}