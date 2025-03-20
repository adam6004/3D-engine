import java.awt.Graphics;

public class Wall {
    private Vector[] vectors = new Vector[4];

    private Vector[] PerspectiveVectors = new Vector[4];

    public Wall(Vector v1, Vector v2, Vector v3, Vector v4) {
        vectors[0] = new Vector(v1);
        vectors[1] = new Vector(v2);
        vectors[2] = new Vector(v3);
        vectors[3] = new Vector(v4);

        PerspectiveVectors[0] = new Vector(v1);
        PerspectiveVectors[1] = new Vector(v2);
        PerspectiveVectors[2] = new Vector(v3);
        PerspectiveVectors[3] = new Vector(v4);

        setPerspectiveVer(0);
        setPerspectiveVer(1);
        setPerspectiveVer(2);
        setPerspectiveVer(3);

    }

    public void DrawWall(Graphics g) {
        boolean isClockwise;
        int sum = 0;
        
        for (int i = 0; i < PerspectiveVectors.length; i++) {
            //(x2-x1)(y2+y1)
            int j = i+1;
            if(j == PerspectiveVectors.length)
                j = 0;
            sum += (PerspectiveVectors[j].getX() - PerspectiveVectors[i].getX())*(PerspectiveVectors[j].getY() + PerspectiveVectors[i].getY());
        }
        if(sum > 0) {
            isClockwise = true;
        }
        else {
            isClockwise = false;
        }
        if(!isClockwise) {
            for (int i = 0; i < PerspectiveVectors.length; i++) {
                int j = i+1;
                if(j == PerspectiveVectors.length)
                    j = 0;
                g.drawLine(PerspectiveVectors[i].getX(), PerspectiveVectors[i].getY(), PerspectiveVectors[j].getX(), PerspectiveVectors[j].getY());
            }
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

    private void setPerspectiveVer(int index) {
        PerspectiveVectors[index].setX((int)(vectors[index].getX() * (1 - vectors[index].getZ()/Board.FIELD_OF_VIEW) + (Board.WIDTH/2 * (vectors[index].getZ()/Board.FIELD_OF_VIEW))));
        PerspectiveVectors[index].setY((int)(vectors[index].getY() * (1 - vectors[index].getZ()/Board.FIELD_OF_VIEW) + (Board.HEIGHT/2 * (vectors[index].getZ()/Board.FIELD_OF_VIEW))));
    }
}
