package guimaze;

import java.util.*;
import javax.swing.JPanel;



public class Maze{

    /**
     * @author sam.fleming
     * @version 5
     *
     */

    //constructor fields
    public String title;
    public String author;
    public String createDate;
    public String editDate;
    public int length;
    public int height;

    public Cell[][] cells;
    public List<int[]> invalidCells;
    public List<Relation> rels;


    public int[] startCell = new int[2];
    public int[] finishCell = new int[2];

    class Solution{

        private Maze maze;

        public List<int[]> path;
        public boolean solvable;
        private List<int[]> cellCoords;

        private Deque q;

        public Solution(Maze maze){
            this.maze = maze;
            cellCoords = popCellCoords();
            path = reconstructPath(this.maze.startCell, this.maze.finishCell, Solve());
        }

        private int[][][] Solve(){
            q.add(this.maze.startCell);
            Boolean[][] visited = initialiseVisited();

            int[][][] prev = initialisePrev();
            while(!q.isEmpty()){
                int[] node = (int[]) q.getFirst();
                q.remove(node);//get next in queue and remove it from queue

                //find it's list of integers
                List<int[]> neighbours = getNeighbours(node);

                for(int i = 0; i < neighbours.size(); i++){
                    int[] next = neighbours.get(i);
                    int x = next[0];
                    int y = next[1];
                    if(!visited[x][y]){
                        q.add(next);
                        visited[x][y] = true;
                        prev[x][y] = node;
                    }
                }
            }
            return prev;

        }


        private List<int[]> reconstructPath(int[] start, int[] end, int[][][] prev){
            int[] current;
            List<int[]> path_ = new ArrayList<>();

            for(current = end; current != null; current = prev[current[0]][current[1]]){
                path_.add(current);
            }
            Collections.reverse(path_);
            if(path_.get(0) == start){
                //path = path_;
                solvable = true;
            }else{
                //path = null;
                solvable = false;
            }
            return path_;
        }



        private List<int[]> getNeighbours(int[] node){
            List<int[]> neighbours = new ArrayList<int[]>();
            for (int i = 0; i < this.maze.rels.size(); i++){
                Relation the_rel = this.maze.rels.get(i);
                if(the_rel.rel.get(0) == node){
                    neighbours.add(the_rel.rel.get(1));
                }else if(the_rel.rel.get(1) == node){
                    neighbours.add(the_rel.rel.get(0));
                }
            }
            return neighbours;
        }

        private List<int[]> popCellCoords(){
            List<int[]> cellCoords = new ArrayList<int[]>();
            for (int i = 0; i < this.maze.length; i++){
                for( int j = 0; j < this.maze.height; j++){
                    int[] cell = {i, j};
                    cellCoords.add(cell);
                }
            }
            return cellCoords;
        }

        private Boolean[][] initialiseVisited(){
            Boolean[][] visited = new Boolean[this.maze.length][this.maze.height];
            for (int i = 0; i < this.maze.length; i++){
                for( int j = 0; j < this.maze.height; j++){
                    visited[i][j] = false;
                }
            }
            return visited;
        }

        private int[][][] initialisePrev(){
            int[][][] prev = new int[this.maze.length][this.maze.height][2];
            for (int i = 0; i < this.maze.length; i++){
                for( int j = 0; j < this.maze.height; j++){
                    prev[i][j] = null;
                }
            }
            return prev;
        }

    }


    public Maze(String title,String date,String author, int length, int height){
        this.title = title;
        this.author = author;
        this.length = length;
        this.height = height;
        this.createDate = date;
        this.editDate = date;
        cells = new Cell[length][height];
        startCell[0] = 0;
        startCell[1] = 0;
        finishCell[0] = length - 1;
        finishCell[1] = height -1;

        populateMazeArray();

    }

    public Maze(String title,String date,String author, int length, int height, int[]start, int[]end){
        this.title = title;
        this.author = author;
        this.length = length;
        this.height = height;
        this.createDate = date;
        this.editDate = date;
        cells = new Cell[length][height];
        startCell[0] = start[0];
        startCell[1] = start[1];
        finishCell[0] = end[0];
        finishCell[1] = end[1];

        populateMazeArray();

    }

    private List<int[]> GetCellCoords(){
        List<int[]> cellCoords = new ArrayList<int[]>();
        for (int i = 0; i < length; i++){
            for (int j = 0; i < height; i++){
                int[] cell = {i, j};
                if (cells[i][j].live){
                    cellCoords.add(cell);
                }
            }
        }
        return cellCoords;
    }

    public void showRelations(){
        System.out.println("Relations: ");
        for (int i = 0; i < rels.size(); i++){
            String str = "";
            Relation rela = rels.get(i);
            int[] first = rela.rel.get(0);
            int[] second = rela.rel.get(1);
            str = str.concat("("+ first[0] + "," + first[1] + "), (" + second[0] + "," + second[1] + ")");
            System.out.println(str);

        }
    }

    public void populateMazeArray(){
        invalidCells = new ArrayList<int[]>();
        rels = new ArrayList<Relation>();

        for (int len = 0; len < length; len ++){
            //System.out.println("len =" + len);
            for (int hei = 0; hei < height; hei++ ){
                //System.out.println("hei =" + hei);
                int[] coords = {len, hei};
                //System.out.println("Cell at pos; " + len + ", " + hei + " created");
                this.cells[len][hei] = new Cell(coords);

            }
        }
    }

    public float DeadEnd_Percentage(){
        float percentage = 0;
        int total = Total_DeadEnd();

        System.out.println("Dead End Cells Total:  " + total);
        float totalValid = (float)NumValidCells();
        float f_total = (float)total;
        percentage = (f_total/totalValid)*100;
        System.out.println("Dead end Percentage: " + percentage);
        return percentage;
    }

    public int Total_DeadEnd(){
        int tally = 0;
        for (int i = 0; i < length; i++){
            for (int j = 0; j < height; j++){
                if (cells[i][j].NumWallsEnabled() == 3 && cells[i][j].live){
                    tally += 1;
                }
            }

        }
        return tally;
    }

    public String TotalCellOptimal(){
        return "[not yeat implemented]";
    }

    public int NumValidCells(){
        int num;
        num = (length * height) - invalidCells.size();
        return num;
    }

    public void Draw(JPanel pane){

        pane.removeAll();
        /**
         * @param pane - Reference to the JPanel to be drawn on
         */

        int pane_x = pane.getWidth();
        int pane_y = pane.getHeight();
        int longest_side;

        //checks that displaypanel is square
        if (pane_x != pane_y){
            System.out.println("Invalid Display Panel");
        }

        if (length >= height){
            longest_side = length;
        }else{
            longest_side = height;
        }

        int cellPixels = (int)((double)pane_x/(double)longest_side);
            //pixel size of each cell (rounded down)


        for (int l = 0; l < this.length; l++){
            //System.out.println("l =" + l);
            for (int h = 0; h < this.height; h++ ){
                //System.out.println("h =" + h);
                this.cells[l][h].Draw(pane, cellPixels, l, h);
                    //evaluates as null for some reason
            }
        }
    }





}
