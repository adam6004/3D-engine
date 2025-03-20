import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Board extends JPanel{
    public static final int WIDTH = 800, HEIGHT = 800;

    public static final double FIELD_OF_VIEW = 1000.0;

    public Board() {
        super();
        setPreferredSize(new Dimension(800, 800));
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        GameObject object = new GameObject(200, 200, 100, 100);
        object.Draw(g);
    }
}
