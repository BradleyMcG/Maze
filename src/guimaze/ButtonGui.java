package guimaze;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonGui extends JFrame implements ActionListener, Runnable {

    private JButton createButton(String str) {

        JButton var = new JButton(str);

        var.addActionListener(this);

        return var;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void run() {

    }
}
