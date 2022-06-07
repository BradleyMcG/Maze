package guimaze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Date;


public class CreateMaze implements ActionListener, Runnable{

    //Logical Fields
    protected Maze maze;
    private final int WIDTH = 800;
    private final int HEIGHT = 800;
    private boolean auto = true;





    //GUI Fields
    private JFrame frame = new JFrame();
    private final Font labels = new Font("Arial", Font.PLAIN, 20);

    private JPanel pnlFields;
    private JLabel mazeLength = new JLabel("x:");
    private JTextField length = new JTextField("100<x<0");
    private JLabel mazeHeight = new JLabel("y:");
    private JTextField height = new JTextField("100<y<0");
    private JLabel authorName = new JLabel("Author:");
    private JTextField Author = new JTextField("");
    private JLabel mazeTitle = new JLabel("Title:");
    private JTextField Title = new JTextField("");
    private JButton btnAutomatic = new JButton("Generate Automatically");
    private JButton btnManual = new JButton("Generate Manually");

    private JPanel pnlEnds;
    private JButton btnSubmit;
    private JLabel lblStart;
    private JTextField txt_xs;
    private JTextField txt_ys;
    private JLabel lblEnd;
    private JTextField txt_xe;
    private JTextField txt_ye;


    private JLabel CreateSign = new JLabel("Create Maze");
    private JLabel dimensions = new JLabel("Dimensions");








    CreateMaze(){

/*
        CreateMazeGUI(); //eventually DisplayGUI (from interface)
        btnAutomatic.addActionListener(this);
        btnManual.addActionListener(this);

 */
        CreateGUI();



    }

    private void CreateFields(){


        mazeTitle = new JLabel("Title: ");
        mazeTitle.setBounds(0,0, 100, 75);
        mazeTitle.setFont(labels);
        pnlFields.add(mazeTitle);
        Title = new JTextField();
        Title.setBounds(150, 0, 550, 75);
        pnlFields.add(Title);

        authorName = new JLabel("Author: ");
        authorName.setBounds(0, 100, 100, 75);
        authorName.setFont(labels);
        pnlFields.add(authorName);
        Author = new JTextField();
        Author.setBounds(150, 100, 550, 75);
        pnlFields.add(Author);

        mazeLength = new JLabel("Maze Length: ");
        mazeLength.setBounds(0, 200, 100, 75);
        pnlFields.add(mazeLength);
        length = new JTextField();
        length.setBounds(100, 215, 75, 45);
        pnlFields.add(length);

        mazeHeight = new JLabel("Maze Height: ");
        mazeHeight.setBounds(375, 200, 100, 75);
        pnlFields.add(mazeHeight);
        height = new JTextField();
        height.setBounds(475, 215, 75, 45);
        pnlFields.add(height);

        btnAutomatic = new JButton("Generate Automatically");
        btnAutomatic.setBounds(50, 300, 275, 125);
        pnlFields.add(btnAutomatic);
        btnAutomatic.addActionListener(this);
        btnManual = new JButton("Generate Manually");
        btnManual.setBounds(425, 300, 275, 125);
        pnlFields.add(btnManual);
        btnManual.addActionListener(this);

    }

    private void CreateEnds(){

        frame.setVisible(false);
        //pnlFields.removeAll();
        //CreateFields();
        DisableFields();

        lblStart = new JLabel("Start Cell (x, y): ");
        lblStart.setBounds(0, 0, 150, 75);
        lblStart.setFont(labels);
        pnlEnds.add(lblStart);
        txt_xs = new JTextField();
        txt_xs.setBounds(200, 15, 75, 45);
        pnlEnds.add(txt_xs);
        txt_ys = new JTextField();
        txt_ys.setBounds(300, 15, 75, 45);
        pnlEnds.add(txt_ys);

        lblEnd = new JLabel("End Cell (x, y): ");
        lblEnd.setBounds(0, 100, 150, 75);
        lblEnd.setFont(labels);
        pnlEnds.add(lblEnd);
        txt_xe = new JTextField();
        txt_xe.setBounds(200, 115, 75, 45);
        pnlEnds.add(txt_xe);
        txt_ye = new JTextField();
        txt_ye.setBounds(300, 115, 75, 45);
        pnlEnds.add(txt_ye);

        frame.add(pnlEnds);
        frame.setVisible(true);

    }

    private void DisableFields(){
        btnAutomatic.setEnabled(false);
        btnManual.setEnabled(false);
        length.setEditable(false);
        height.setEditable(false);
        Author.setEditable(false);
        Title.setEditable(false);
    }

    private void CreateGUI(){
        frame = new JFrame();
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);

        pnlFields = new JPanel();
        pnlFields.setLayout(null);
        pnlFields.setBounds(25, 25, 750, 450);//750  x 450 pixels
        CreateFields();

        pnlEnds = new JPanel();
        pnlEnds.setLayout(null);
        pnlEnds.setBounds(25, 525, 750, 200); //750 x 200 pixels
        //CreateEnds();

        frame.add(pnlFields);
        //frame.add(pnlEnds);
        frame.setVisible(true);

        System.out.println("Date is: " + GetDate());

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




    private void inputHandler() throws NumberFormatException{
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

        String date = GetDate();
        //HideGUI();
        if (auto){
            //CreateAutomatic(title,date,author, x, y);
            CreateEnds();
        }else{
            //CreateManual(title,date,author, x, y);
            CreateEnds();
        }
    }

    private void Cell_inputHandler() throws NumberFormatException{

    }

    private String GetDate(){
        String str = "";
        int day = LocalDate.now().getDayOfMonth();
        int month = LocalDate.now().getMonthValue();
        int year = LocalDate.now().getYear();
        str = str.concat(day + "/"+ month +"/"+ year);
        return str;
    }

    private void InputExceptionHandler(){
        /**
         * @param Auto - an indicator for which button has been pressed (Auto OR Manual)
         */
        try{
          inputHandler();
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
            auto = true;
            InputExceptionHandler();
        }else if(e.getSource() == btnManual){
            System.out.println("btn pressed, manually generate maze");
            auto = false;
            InputExceptionHandler();
        }

    }

    private void errorDialog(){
        JDialog d = new JDialog(frame, "Input Error");
        d.setSize(400, 200);
        JPanel p = new JPanel();
        p.setBorder(BorderFactory.createLineBorder(Color.black));
        JTextField inputError = new JTextField("Uh oh. Incorrect data input; make sure your dimensions and whole numbers and " +
                "title/author is a string literal"); //format to wrap text
        inputError.setSize(100, 100);

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
