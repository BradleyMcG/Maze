package guimaze;

import javax.swing.*;
import java.awt.*;



public class CreateMaze {

    JFrame frame = new JFrame();

    JLabel CreateSign = new JLabel("Create Maze");
    JLabel AuthorName = new JLabel("Author's Name");

    JTextField Author = new JTextField("Author's Name");
    CreateMaze(){

        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel pnlInputs = new JPanel(new GridBagLayout());
        frame.setContentPane(pnlInputs);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 10;
        constraints.weighty = 10;

        constraints.gridx = 0;
        constraints.gridwidth = 2;
        constraints.gridy = 0;
        pnlInputs.add(CreateSign, constraints);

        //Authors Text Label
        constraints.gridx = 1;
        constraints.gridwidth = 2;
        constraints.gridy = 1;
        pnlInputs.add(AuthorName, constraints);
        //Authors Text Box
        constraints.gridx = 2;
        constraints.gridwidth = 2;
        constraints.gridy = 1;
        pnlInputs.add(Author, constraints);

        //repaint();
        frame.setVisible(true);

    }

}
