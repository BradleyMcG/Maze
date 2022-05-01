package guimaze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * @author bradley.mcgrath
 * @version 1
 */

public class Export implements ActionListener, Runnable {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;

    JLabel headerLabel = new JLabel("Export");
    JButton btnBack = new JButton("Submit");

    Export(){
        ExportGUI();
    }

    private void ExportGUI(){
        JFrame frame = new JFrame("Export GUI");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

      //  JPanel bottomPanel = new JPanel(new GridBagLayout());
      //  bottomPanel.add(btnBack, BorderLayout.LINE_END);


        JPanel pane = new JPanel(new GridBagLayout());


        frame.setContentPane(pane);


        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 10;
        constraints.weighty = 10;

        constraints.gridx = 1;
        constraints.gridwidth = 2;
        constraints.gridy = 0;
        headerLabel.setFont(new Font("Test", Font.PLAIN, 50));
        pane.add(headerLabel, constraints);

        //BUTTONs
        constraints.anchor = GridBagConstraints.PAGE_END;
        constraints.gridwidth = 1;
        constraints.gridx = 3;
        constraints.gridy = 2;
        constraints.ipady = 40;
        btnBack.setFont(new Font("Test",Font.PLAIN,20));
        pane.add(btnBack, constraints);


        //BUTTON ACTION LISTENERS

        btnBack.addActionListener(this);

        //VIEW FRAME

        frame.setVisible(true);

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnBack){
            System.out.println("RETURN TO HOME SCREEN");
            MazeGenerator rnd = new MazeGenerator();
        }
    }

    @Override
    public void run() {
        //ExportGUI();
    }
}
