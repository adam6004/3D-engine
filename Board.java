import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Board extends JPanel{
    public static final int WIDTH = 800, HEIGHT = 800;

    public static final double FIELD_OF_VIEW = 1000.0;

    public GameObject object;

    public Board() {
        super();
        setPreferredSize(new Dimension(800, 800));
        setDoubleBuffered(true);
        object = new GameObject(600, 400, 100, 100, 200, 100);
    }


    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        super.paintComponent(g2D);
        object.Draw(g2D);
    }
}
