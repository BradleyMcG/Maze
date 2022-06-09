package guimaze;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ManualGenDialog implements ActionListener {
    /**
     * @author bradley mcgrath
     * @version 4
     *
     */

    public ManualGeneration ManualGen;

    //logical fields
    private int entry_points;
    private String[] cords;
    private int ID = 0;

    //GUI fields
    private JPanel buttonPanel;
    private JPanel background;

    private JButton btnSubmit;
    private JButton btnAdd;
    private JButton btnDelete;
    private JButton btnUpdate;

    private JTable deleteTable;
    private  JScrollPane pane;
    private DefaultTableModel model;

    private JTextField x1input;
    private JTextField x2input;
    private JTextField y1input;
    private JTextField y2input;

    private JLabel header;
    private JFrame frame = new JFrame("Dialog");

    private JDialog ui;

    private JTable table;

    private Object[] columns = {"X1","X2","Y1","Y2"};

    ManualGenDialog(Maze maze, ManualGeneration ManualGens){
        this.ManualGen = ManualGens;
        entry_points = 2;
        DisplayGUI();
        btnSubmit.addActionListener(this);
        btnAdd.addActionListener(this);
        btnDelete.addActionListener(this);
        btnUpdate.addActionListener(this);


    }

    public void DisplayGUI(){
        // ui = new JDialog(frame, "Manual Wall Removal");

        frame.setLayout(null);
        deleteTable = new JTable();

        //Object[] columns = {"X1","X2","Y1","Y2"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        deleteTable.setModel(model);

        deleteTable.setBackground(Color.darkGray);
        deleteTable.setForeground(Color.white);
        deleteTable.setRowHeight(25);



        pane = new JScrollPane(deleteTable);
        pane.setBounds(20,80,350,200);


        // frame.setBounds(200,100,400,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(400,400,400,600);


        Container container = frame.getContentPane();
        container.setLayout(null);

        //  buttonPanel = new JPanel(new GridLayout(1, 3));

        header = new JLabel("Please type in which walls you would like to remove");
        header.setBounds(30,10,350,30);

        JLabel x1 = new JLabel("X1: ");
        x1.setBounds(20,30,100,30);

        JLabel y1 = new JLabel("Y1: ");
        y1.setBounds(180,30,100,30);

        JLabel x2 = new JLabel("X2: ");
        x2.setBounds(20,50,100,30);

        JLabel y2 = new JLabel("Y2: ");
        y2.setBounds(180,50,100,30);

        x1input = new JTextField();
        x1input.setBounds(65,35,100,20);

        x2input = new JTextField();
        x2input.setBounds(65,55,100,20);

        y1input = new JTextField();
        y1input.setBounds(225,35,100,20);

        y2input = new JTextField();
        y2input.setBounds(225,55,100,20);

        container.add(header);
        container.add(x1);
        container.add(y1);
        container.add(x2);
        container.add(y2);

        container.add(x2input);
        container.add(x1input);
        container.add(y2input);
        container.add(y1input);

        buttonPanel = new JPanel(new GridLayout(1, 4));
        buttonPanel.setBackground(Color.RED);
        buttonPanel.setBounds(20, 300, 350, 50);
        btnSubmit = new JButton("Submit");
        buttonPanel.add(btnSubmit);
        btnAdd = new JButton("Add");
        buttonPanel.add(btnAdd);
        btnDelete = new JButton("Delete");
        buttonPanel.add(btnDelete);

        btnUpdate = new JButton("Update");
        buttonPanel.add(btnUpdate);

        // frame.add(background);
        deleteTable.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e){
                int i = deleteTable.getSelectedRow();
                x1input.setText(model.getValueAt(i,1).toString());
                x2input.setText(model.getValueAt(i,2).toString());
                y1input.setText(model.getValueAt(i,3).toString());
                y2input.setText(model.getValueAt(i,4).toString());

            }
        });

        frame.add(buttonPanel);
        frame.add(pane);




        frame.setVisible(true);
    }

    private Object[][] populateObject(int entryPoints){

        Object[][] obj = new Object[entryPoints][5];
        for (int i = 0; i < entryPoints; i++){
            obj[i][0] = i+1;
            obj[i][1] = "x: ";
            obj[i][2] = "";
            obj[i][0] = "y: ";
            obj[i][0] = "";
        }
        return obj;
    }

    private String[] removeWallArray(){
        cords[0] = x1input.getText();
        cords[1] = x2input.getText();
        cords[2] = y1input.getText();
        cords[3] = y2input.getText();

        return cords;
    }

    public int[][] transferData (JTable table){
        System.out.println("Transfer Data is called");
        int rowNumber = table.getRowCount();
        int columnNumber = table.getColumnCount();
        int[][] obj = new int[rowNumber][columnNumber];

        for(int i = 0; i<rowNumber; i++){
            for(int j = 0; j<columnNumber; j++){
                obj[i][j] = Integer.parseInt(table.getValueAt(i,j).toString());

                System.out.println("Values together are " + table.getValueAt(i,j));
                System.out.println(obj[i][j]);

            }
        }
        return obj;
    }


    /*private static int[] linearSearch(int[][] data, int[][] target)
    {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] == target) {
                    return new int[] { i, j };
                }
            }
        }
        return new int[] { -1, -1 };
    }*/

    public int[][] SendData(){
        int[][] obj ;
        obj = transferData(deleteTable);

        return obj;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        Object[] obj = new Object[4];
        obj[0] =x1input.getText();
        obj[1] =x2input.getText();
        obj[2] =y1input.getText();
        obj[3] =y2input.getText();

        if(e.getSource()==btnSubmit){
            System.out.println("pressed 'submit'");

            HideGUI();
            ManualGen.RemoveWalls(transferData(deleteTable));
            // super.UpdateDisplay();
            //SendData();
            ManualGen.updateFrame();


        }
        if(e.getSource()==btnAdd){

            // ADD X1 == X2 || Y1 == Y2
            System.out.println("pressed 'Add'");
            ID++;
            model.addRow(obj);

        }
        if(e.getSource()== btnDelete){
            System.out.println("pressed 'Delete' ");
            int i = deleteTable.getSelectedRow();
            if(i>=0){
                model.removeRow(i);
                ID--;
            } else {
                System.out.println("Nothing Selected");
            }
        }
        if(e.getSource() == btnUpdate){
            int i = deleteTable.getSelectedRow();

            if(i>=0){
                model.setValueAt(x1input.getText(),i,1);
                model.setValueAt(x2input.getText(),i,2);
                model.setValueAt(y1input.getText(),i,3);
                model.setValueAt(y2input.getText(),i,4);

            } else {
                System.out.println("Update Error");
            }
        }

    }

    protected void HideGUI(){
        frame.dispose();
    }

}
