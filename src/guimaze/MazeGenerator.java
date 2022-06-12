package guimaze;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
/**
 * @author bradley.mcgrath
 * @version 4
 */

public class MazeGenerator extends JFrame implements ActionListener, Runnable {

    //Logical Fields
    public static List<Maze> allMazes = new ArrayList<Maze>();


    //GUI Fields
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;

    private List<Integer> refMaze = new ArrayList<>();

    private JButton btnCreate;
    private JButton btnFind;
    private JButton btnDisplay;
    private JButton btnExport;
    private JButton btnExportOptimal;

    private JTable tableMaze; // ADDED
   // private JButton btnDisplay;

    private JFrame frame;
    Object[][] data;

    public static class Connect{
        JDBCConnection connection = new JDBCConnection();
    }


    MazeGenerator(){
        /**
         * Maze Generator Constructor
         */
        new Connect();
        populateDummyMazes();
        data = PopulateObject(allMazes);
        createGUI();
    }


    private static class SingletonHolder{
        /**
         * Creates Singleton Instance
         */
        private final static MazeGenerator Program = new MazeGenerator();
    }

    public static MazeGenerator GetInstance(){
        /**
         * Retrives Singleton instance
         * @return SingletonHolder.Program returns singleton instance
         */

        return SingletonHolder.Program;
    }

    private void createGUI() {
        frame = new JFrame("Maze Generator");
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
        headerLabel.setFont(new Font("Test", Font.PLAIN, 30));
        pane.add(headerLabel, constraints);

        btnCreate = new JButton("Create Maze");
        constraints.gridx = 1;
        constraints.gridwidth = 2;
        constraints.gridy = 3;
        constraints.ipady = 40;
        constraints.ipadx = 240;
        pane.add(btnCreate, constraints);

        btnDisplay = new JButton("Display Maze");
        constraints.gridx = 3;
        constraints.gridwidth = 1;
        constraints.gridy = 1;
        constraints.ipady = 0;
        constraints.ipadx = 0;
        pane.add(btnDisplay, constraints);

        btnExport = new JButton("Normal Export Maze");
        constraints.gridx = 3;
        constraints.gridwidth = 1;
        constraints.gridy = 2;
        constraints.ipady = 0;
        pane.add(btnExport, constraints);

        btnExportOptimal = new JButton("Optimal Export Maze");
        constraints.gridx = 3;
        constraints.gridwidth = 1;
        constraints.gridy = 3;
        constraints.ipady = 0;
        pane.add(btnExportOptimal, constraints);


        btnCreate.addActionListener(this);
        btnDisplay.addActionListener(this);
        btnExport.addActionListener(this);
        btnExportOptimal.addActionListener(this);

        String [] columnNames = {"Maze Number", "Author Name", "Maze Title", "Date Created", "Last Edit","Select"};

        tableMaze = new JTable(data, columnNames){
            public Class getColumnClass(int column) {
                //return Boolean.class
                return getValueAt(0, column).getClass();
            }
        };

        tableMaze.setAutoCreateRowSorter(true);
        tableMaze.setPreferredScrollableViewportSize(new Dimension(500, 300));
        tableMaze.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(tableMaze);
        constraints.gridx = 0;
        constraints.gridwidth = 3;
        constraints.gridheight = 2;
        constraints.gridy = 1;
        constraints.ipady = 0;
        frame.add(scrollPane,constraints);

        repaint();

        frame.setVisible(true); //Show the window
    }



    private void checkTickBox(){
        /**
         * Checks which tick boxes have been selected and adds there referenece to a list
         */
        int rowNumber = tableMaze.getRowCount();
        int columnNumber = tableMaze.getColumnCount();
        int tick_num = 5; //for original configuration = 3

        String[][] obj = new String[rowNumber][columnNumber];

        for(int i = 0; i<rowNumber; i++){

                obj[i][tick_num] = (tableMaze.getValueAt(i,tick_num).toString());

                System.out.println("Values together are " + obj[i][tick_num]);
                //System.out.println(obj[i][j]);
                if(obj[i][tick_num] == "true"){
                    //refMaze.add(i);
                    refMaze.add((Integer) tableMaze.getValueAt(i, 0));
                }


        }
    }

    private Object[][] PopulateObject(List<Maze> mazes){
        /**
         * @param mazes a list of all mazes
         * @return obj a two dimensional object array will all relevant maze information
         */
        Object[][] obj = new Object[mazes.size()][6];
        for(int i = 0; i < mazes.size(); i++){
            Maze current = mazes.get(i);
            obj[i][0] = i;
            obj[i][1] = current.author;
            obj[i][2] = current.title;
            obj[i][3] = current.createDate;
            obj[i][4] = current.editDate;
            obj[i][5] = false;
        }
        return obj;
    }




    
    @Override
    public void run() {
        // SwingUtilities.invokeLater(new FramesAndPanels("BorderLayout"));

    }

    public static void main(String[] args){

        SwingUtilities.invokeLater(GetInstance());
        /*
        //eventually need to utilise a singleton pattern to maintain MazeGenerator (Program)
        //is only instantiated once without being static - needs to be instantiated to store mazes
        // - THIS WILL ALLOW MAIN TO BE STATIC ONCE AGAIN
         */
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnCreate){
            HideGUI();
            CreateMaze Create = new CreateMaze();
            System.out.println("pressed");
        }
        if(e.getSource()==btnDisplay){
            System.out.println("pressed");


            checkTickBox();


            if(refMaze.size() == 1){
                HideGUI();
                Display dis = new Display(allMazes.get(refMaze.get(0)));
                refMaze.clear();
            } else {
                errorDialog();
                refMaze.clear();
            }


        }
        if(e.getSource() == btnExport){
            System.out.println("EXPORT");
            checkTickBox();


            if(refMaze.size() == 1){

                Export exp = new Export(allMazes.get(refMaze.get(0)));
                refMaze.clear();
            } else {

                for(int i = 0; i < refMaze.size(); i++){

                    Export expmultiple = new Export(allMazes.get(refMaze.get(i)), false);
                }
                refMaze.clear();

            }

            exportDialog();



        }
        if(e.getSource() == btnExportOptimal){

            PerformExport(true);
            exportDialog();
        }
        
    }

    private void PerformExport(boolean check){
        /**
         * Creates new instance of export depending if multiple mazes were selected or not
         * @param check checks whether optimal button has been pressed or not
         */
        System.out.println("EXPORT");
        checkTickBox();

        if(refMaze.size() == 1){
            //HideGUI();
            Export exp = new Export(allMazes.get(refMaze.get(0)), check);
            refMaze.clear();
        } else {

            for(int i = 0; i < refMaze.size(); i++){

                Export expmultiple = new Export(allMazes.get(refMaze.get(i)), check);
            }
            refMaze.clear();
        }
    }

    public void StoreMaze(Maze maze){
        /**
         * @param maze Reference to Maze instance
         */
        allMazes.add(maze);
    }
    private void errorDialog(){
        JDialog d = new JDialog(frame, "Input Error");
        JPanel p = new JPanel();
        p.setBorder(BorderFactory.createLineBorder(Color.black));
        JLabel inputError = new JLabel("You have selected too many tick boxes, " +
                "please make sure only one is selected to Display the maze");
        inputError.setSize(200, 200);
        d.setSize(600, 200);
        p.add(inputError);
        d.add(p);
        d.setVisible(true);
    }

    private void exportDialog(){
        JDialog d = new JDialog(frame, "Input Error");
        JPanel p = new JPanel();
        p.setBorder(BorderFactory.createLineBorder(Color.black));
        JLabel inputError = new JLabel("Export has completed please check local project folder or database");
        inputError.setSize(200, 200);
        d.setSize(600, 200);
        p.add(inputError);
        d.add(p);
        d.setVisible(true);
    }



    private void populateDummyMazes(){
        allMazes.add(new Maze("Maze 1", "14/12/2000","Jim Jameson" ,5, 5));
        allMazes.add(new Maze("Maze 2", "13/12/2000","Zinedine Zidane" ,5, 10));
        allMazes.add(new Maze("Maze for the free world","12/12/2000" ,"Nelson Mandela", 96, 96));
        allMazes.add(new Maze("Amazing Work","14/2/2022", "Brad", 2, 2));
        allMazes.add(new Maze("Even more amazing work","14/2/2022", "Sam", 2, 2));
    }

    private void HideGUI() {
        frame.dispose();
    }
    public void ShowGUI(){
        //frame.setVisible(true);
        System.out.println("Show GUI");
        createGUI();
    }

    public void NewMaze(Maze maze){
        allMazes.add(maze);
        data = PopulateObject(allMazes);

    }
}
