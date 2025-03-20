import java.awt.Color;
import java.awt.Graphics;

public class GameObject {
    private Wall[] walls = new Wall[6];

    public GameObject(int posX, int posY, int posZ, int size) {

        //object verticies
        Vector[] vectors = new Vector[8];

        //front wall
        vectors[0] = new Vector(posX-size/2, posY-size/2, posZ-size/2);
        vectors[1] = new Vector(posX+size/2, posY-size/2, posZ-size/2);
        vectors[2] = new Vector(posX+size/2, posY+size/2, posZ-size/2);
        vectors[3] = new Vector(posX-size/2, posY+size/2, posZ-size/2);
        

        //back wall
        vectors[4] = new Vector(posX+size/2, posY-size/2, posZ+size/2);
        vectors[5] = new Vector(posX-size/2, posY-size/2, posZ+size/2);
        vectors[6] = new Vector(posX-size/2, posY+size/2, posZ+size/2);
        vectors[7] = new Vector(posX+size/2, posY+size/2, posZ+size/2);

        //applying verticies to walls of an object

        //front wall
        walls[0] = new Wall(vectors[0], vectors[1], vectors[2], vectors[3]);

        //up wall
        walls[1] = new Wall(vectors[0], vectors[5], vectors[4], vectors[1]);

        //right wall
        walls[2] = new Wall(vectors[1], vectors[4], vectors[7], vectors[2]);

        //left wall
        walls[3] = new Wall(vectors[5], vectors[0], vectors[3], vectors[6]);

        //down wall
        walls[4] = new Wall(vectors[3], vectors[2], vectors[7], vectors[6]);

        //back wall
        walls[5] = new Wall(vectors[4], vectors[5], vectors[6], vectors[7]);


    }

    public void Draw(Graphics g) {
        g.setColor(Color.BLACK);
        for (Wall wall : walls) {
            wall.DrawWall(g);
            wall.PerInfo();
            System.out.println();
        }
    }
}
