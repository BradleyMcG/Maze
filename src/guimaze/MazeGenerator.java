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
 * @version 1
 */

public class MazeGenerator extends JFrame implements ActionListener, Runnable {

    //Logical Fields
    public static List<Maze> allMazes = new ArrayList<Maze>();


    //GUI Fields
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;



    private JButton btnCreate;
    private JButton btnFind;
    private JButton btnDisplay;
    private JButton btnExport;
   // private JButton btnDisplay;

    private JFrame frame;
    Object[][] data;

    MazeGenerator(){

        populateDummyMazes();
        data = PopulateObject(allMazes);
        createGUI();
    }


    private static class SingletonHolder{
        private final static MazeGenerator Program = new MazeGenerator();
    }

    public static MazeGenerator GetInstance(){

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

        btnExport = new JButton("Export Maze");
        constraints.gridx = 3;
        constraints.gridwidth = 1;
        constraints.gridy = 2;
        constraints.ipady = 0;
        pane.add(btnExport, constraints);

        /*btnFind = new JButton("Find Previous Maze");
        constraints.gridx = 3;
        constraints.gridwidth = 1;
        constraints.gridy = 3;
        constraints.ipady = 40;
        pane.add(btnFind, constraints);*/

        btnCreate.addActionListener(this);
      //  btnFind.addActionListener(this);
        btnDisplay.addActionListener(this);
        btnExport.addActionListener(this);

        //JPanel pane = new JPanel();
       // frame.setContentPane(pane);

        String [] columnNames = {"Author Name","Date","Maze Title","Export"};

        JTable tableMaze = new JTable(data, columnNames){
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
        //frame.pack();

        frame.setVisible(true); //Show the window
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





    private Object[][] PopulateObject(List<Maze> mazes){
        Object[][] obj = new Object[mazes.size()][4];
        for(int i = 0; i < mazes.size(); i++){
            Maze current = mazes.get(i);
            obj[i][0] = current.author;
            obj[i][1] = current.date;
            obj[i][2] = current.title;
            obj[i][3] = false;
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
        /*if(e.getSource()==btnFind){
            System.out.println("pressed");
        }*/
        if(e.getSource()==btnDisplay){
            System.out.println("pressed");
            //allMazes.add(new Maze("Maze 1", "Jim Jameson", 10, 7));
            System.out.println(allMazes.get(0));
            HideGUI();
            Display dis = new Display(allMazes.get(1));
        }
        if(e.getSource() == btnExport){
            System.out.println("EXPORT");
            HideGUI();
            Export exp = new Export();
        }
        
    }

    public void StoreMaze(Maze maze){
        allMazes.add(maze);
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
