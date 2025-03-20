import java.awt.Graphics;

public class Wall {
    private Vector[] vectors = new Vector[4];

    private Vector[] PerspectiveVectors = new Vector[4];

    public Wall(Vector v1, Vector v2, Vector v3, Vector v4) {
        vectors[0] = v1;
        vectors[1] = v2;
        vectors[2] = v3;
        vectors[3] = v4;

        PerspectiveVectors[0] = new Vector(v1);
        PerspectiveVectors[1] = new Vector(v2);
        PerspectiveVectors[2] = new Vector(v3);
        PerspectiveVectors[3] = new Vector(v4);

        updatePerspective();

    }

    public void DrawWall(Graphics g) {
        int sum = 0;
        
        for (int i = 0; i < PerspectiveVectors.length; i++) {
            //(x2-x1)(y2+y1)
            int j = (i+1)%PerspectiveVectors.length;
            
            sum += (PerspectiveVectors[j].getX() - PerspectiveVectors[i].getX()) *
                    (PerspectiveVectors[j].getY() + PerspectiveVectors[i].getY());
        }

        if(sum>0)
            return;

        for (int i = 0; i < PerspectiveVectors.length; i++) {
            int j = (i+1) % PerspectiveVectors.length;
            
            g.drawLine(
                PerspectiveVectors[i].getX(), PerspectiveVectors[i].getY(), 
                PerspectiveVectors[j].getX(), PerspectiveVectors[j].getY()
            );
        }
    }


    public void PerInfo() {
        for (Vector vector : PerspectiveVectors) {
            vector.Info();
        }
        int sum = 0;
        for (int i = 0; i < PerspectiveVectors.length; i++) {
            int j=i+1;
            //(x2-x1)(y2+y1)
            if(j == PerspectiveVectors.length)
                j = 0;
            sum += (PerspectiveVectors[j].getX() - PerspectiveVectors[i].getX())*(PerspectiveVectors[j].getY() + PerspectiveVectors[i].getY());
        }
        System.out.println(sum);
    }

    public void updatePerspective() {
        for (int i = 0; i < PerspectiveVectors.length; i++) {
            double depth = vectors[i].getZ() / Board.FIELD_OF_VIEW;
            double depthFactor = 1 - depth;
            PerspectiveVectors[i].setX((int)(vectors[i].getX() * depthFactor + (Board.WIDTH / 2 * depth)));
            PerspectiveVectors[i].setY((int)(vectors[i].getY() * depthFactor + (Board.HEIGHT / 2 * depth)));
        }    
    }
}
