import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class MyFrame extends JFrame implements ActionListener {
    private Board board;

    public MyFrame(String name) {
        super(name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        board = new Board();
        addKeyListener(board);
        add(board);
        Timer timer = new Timer(16, this);
        timer.start();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        board.object.TranslatePos();
        board.repaint();
    }
}
