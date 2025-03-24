import java.awt.Graphics;

public class Wall {
    private Vector[] vectors = new Vector[4];

    private Vector[] perspectiveVectors = new Vector[4];

    private Vector[] rotatedVectors = new Vector[4];

    private int rotationX, rotationY, rotationZ;

    private int posX, posY, posZ;

    public Wall(Vector v1, Vector v2, Vector v3, Vector v4) {
        vectors[0] = v1;
        vectors[1] = v2;
        vectors[2] = v3;
        vectors[3] = v4;

        perspectiveVectors[0] = new Vector(v1);
        perspectiveVectors[1] = new Vector(v2);
        perspectiveVectors[2] = new Vector(v3);
        perspectiveVectors[3] = new Vector(v4);

        rotatedVectors[0] = new Vector(v1);
        rotatedVectors[1] = new Vector(v2);
        rotatedVectors[2] = new Vector(v3);
        rotatedVectors[3] = new Vector(v4);

        updatePerspective();

    }

    public void DrawWall(Graphics g) {
        int sum = 0;
        
        for (int i = 0; i < perspectiveVectors.length; i++) {
            //(x2-x1)(y2+y1)
            int j = (i+1)%perspectiveVectors.length;
            
            sum += (perspectiveVectors[j].getX() - perspectiveVectors[i].getX()) *
                    (perspectiveVectors[j].getY() + perspectiveVectors[i].getY());
        }

        if(sum>0)
            return;

        for (int i = 0; i < perspectiveVectors.length; i++) {
            int j = (i+1) % perspectiveVectors.length;
            
            g.drawLine(
                perspectiveVectors[i].getX(), perspectiveVectors[i].getY(), 
                perspectiveVectors[j].getX(), perspectiveVectors[j].getY()
            );
        }
    }


    public void PerInfo() {
        for (Vector vector : perspectiveVectors) {
            vector.Info();
        }
        int sum = 0;
        for (int i = 0; i < perspectiveVectors.length; i++) {
            int j=i+1;
            //(x2-x1)(y2+y1)
            if(j == perspectiveVectors.length)
                j = 0;
            sum += (perspectiveVectors[j].getX() - perspectiveVectors[i].getX())*(perspectiveVectors[j].getY() + perspectiveVectors[i].getY());
        }
        System.out.println(sum);
    }

    public void updatePerspective() {
        rotate();
        for (int i = 0; i < perspectiveVectors.length; i++) {
            double depth = vectors[i].getZ() / Board.FIELD_OF_VIEW;
            double depthFactor = 1 - depth;
            perspectiveVectors[i].setX((int)(rotatedVectors[i].getX() * depthFactor + (Board.WIDTH / 2 * depth)));
            perspectiveVectors[i].setY((int)(rotatedVectors[i].getY() * depthFactor + (Board.HEIGHT / 2 * depth)));
        }    
    }

    public void updateRotationVar(int rotationX, int rotationY, int rotationZ, int posX, int posY, int posZ) {
        this.rotationX = rotationX;
        this.rotationY = rotationY;
        this.rotationZ = rotationZ;

        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;

        updatePerspective();
    }

    public void rotate() {
        // Convert degrees to radians
        double radX = Math.toRadians(rotationX);
        double radY = Math.toRadians(rotationY);
        double radZ = Math.toRadians(rotationZ);

        for (int i = 0; i < vectors.length; i++) {
            double tempX = vectors[i].getX() - posX;
            double tempY = vectors[i].getY() - posY;
            double tempZ = vectors[i].getZ() - posZ;

            // Rotation around the X-axis
            double newY = tempY * Math.cos(radX) - tempZ * Math.sin(radX);
            double newZ = tempY * Math.sin(radX) + tempZ * Math.cos(radX);
            tempY = newY;
            tempZ = newZ;

            // Rotation around the Y-axis
            double newX = tempX * Math.cos(radY) + tempZ * Math.sin(radY);
            newZ = -tempX * Math.sin(radY) + tempZ * Math.cos(radY);
            tempX = newX;
            tempZ = newZ;

            // Rotation around the Z-axis
            newX = tempX * Math.cos(radZ) - tempY * Math.sin(radZ);
            newY = tempX * Math.sin(radZ) + tempY * Math.cos(radZ);
            tempX = newX;
            tempY = newY;

            // Store the rotated values back into rotatedVectors
            rotatedVectors[i].setX((int) tempX + posX);
            rotatedVectors[i].setY((int) tempY + posY);
            rotatedVectors[i].setZ((int) tempZ + posZ);
        }
    }
}
