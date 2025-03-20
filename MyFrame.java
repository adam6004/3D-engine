import javax.swing.JFrame;

public class MyFrame extends JFrame {
    public MyFrame(String name) {
        super(name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        add(new Board());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
