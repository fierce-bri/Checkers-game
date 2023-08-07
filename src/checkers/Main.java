package checkers;

import java.awt.BorderLayout;
import javax.swing.*;
 
public class Main {

    public static void main(String[] args) {
        JFrame checkersFrame = new JFrame();
        checkersFrame.setLayout(new BorderLayout());
        checkersFrame.setLocationRelativeTo(null);
        checkersFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        checkersFrame.setTitle("CheckerBoard");
        checkersFrame.add(new GamePanel());
        checkersFrame.pack();
        checkersFrame.setLocationRelativeTo(null);
        checkersFrame.setVisible(true);
    }
}
