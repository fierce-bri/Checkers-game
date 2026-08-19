package checkers;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class GamePanel extends JPanel {

    public GamePanel() {
        setLayout(new BorderLayout());

        JTextField statusField = new JTextField();
        Board board = new Board(statusField);

        add(board, BorderLayout.CENTER);
    }
}
