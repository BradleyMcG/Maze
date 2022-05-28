package guimaze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManualGenDialog implements ActionListener {

    //logical fields
    private int entry_points;
    private String[] cords;

    //GUI fields
    private JPanel buttonPanel;
    private JPanel background;

    private JButton btnSubmit;
    private JButton btnAdd;

    private JTextField x1input;
    private JTextField x2input;
    private JTextField y1input;
    private JTextField y2input;

    private JLabel header;
    private JFrame frame = new JFrame("Dialog");

    private JDialog ui;

    private JTable table;

    ManualGenDialog(JFrame frames){
        entry_points = 2;
        DisplayGUI(frames);
        btnSubmit.addActionListener(this);
        btnAdd.addActionListener(this);

    }

    private void DisplayGUI(JFrame frames){
       // ui = new JDialog(frame, "Manual Wall Removal");



       // frame.setBounds(200,100,400,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(200,100,400,400);

        Container container = frame.getContentPane();
        container.setLayout(null);

    //    buttonPanel = new JPanel(new GridLayout(1, 3));

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
        x1input.setBounds(65,35,100,15);

        x2input = new JTextField();
        x2input.setBounds(65,55,100,15);

        y1input = new JTextField();
        y1input.setBounds(225,35,100,15);

        y2input = new JTextField();
        y2input.setBounds(225,55,100,15);

        container.add(header);
        container.add(x1);
        container.add(y1);
        container.add(x2);
        container.add(y2);

        container.add(x2input);
        container.add(x1input);
        container.add(y2input);
        container.add(y1input);

        frame.setVisible(true);



        buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.setBackground(Color.RED);
        buttonPanel.setBounds(100, 200, 200, 50);
        btnSubmit = new JButton("Submit");
        buttonPanel.add(btnSubmit);
        btnAdd = new JButton("Add Row");
        buttonPanel.add(btnAdd);

       // frame.add(background);
        frame.add(buttonPanel);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnSubmit){
            System.out.println("pressed 'submit'");
            String textFieldValue = x1input.getText();
            HideGUI();

        }
        if(e.getSource()==btnAdd){
            System.out.println("pressed 'Add'");

        }

    }

    protected void HideGUI(){
        frame.dispose();
    }

}
