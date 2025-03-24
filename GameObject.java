import java.awt.Color;
import java.awt.Graphics;

public class GameObject {

        private final int[][] OFFSETS = {
            {-1, -1, -1}, {1, -1, -1}, {1, 1, -1}, {-1, 1, -1}, // Front wall
            {1, -1, 1}, {-1, -1, 1}, {-1, 1, 1}, {1, 1, 1}      // Back wall
        };
    
        private Wall[] walls = new Wall[6];
    
        private int posX, posY, posZ;
        private int width, height, depth;
        private int rotationX, rotationY, rotationZ;
    
        private Vector[] vectors = new Vector[8];
    
        public GameObject(int posX, int posY, int posZ, int width, int height, int depth, int rotationX, int rotationY, int rotationZ) {
    
            this.posX = posX;
            this.posY = posY;
            this.posZ = posZ;

            this.width = width;
            this.height = height;
            this.depth = depth;

            this.rotationX = rotationX;
            this.rotationY = rotationY;
            this.rotationZ = rotationZ;
    
    
            CreateVerticies();
    
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
    
            for (Wall wall : walls) {
                wall.updateRotationVar(rotationX, rotationY, rotationZ, posX, posY, posZ);
            }
    
        }
    
        public void Draw(Graphics g) {
            g.setColor(Color.BLACK);
            for (Wall wall : walls) {
                wall.updateRotationVar(rotationX, rotationY, rotationZ, posX, posY, posZ);
                wall.DrawWall(g);
            }
        }
    
        public void CreateVerticies() {
    
            //front wall
            vectors[0] = new Vector(posX-width/2, posY-height/2, posZ-depth/2);
            vectors[1] = new Vector(posX+width/2, posY-height/2, posZ-depth/2);
            vectors[2] = new Vector(posX+width/2, posY+height/2, posZ-depth/2);
            vectors[3] = new Vector(posX-width/2, posY+height/2, posZ-depth/2);
            
    
            //back wall
            vectors[4] = new Vector(posX+width/2, posY-height/2, posZ+depth/2);
            vectors[5] = new Vector(posX-width/2, posY-height/2, posZ+depth/2);
            vectors[6] = new Vector(posX-width/2, posY+height/2, posZ+depth/2);
            vectors[7] = new Vector(posX+width/2, posY+height/2, posZ+depth/2);
        }
    
        public void UpdateVerticiesPos() {
            for (int i = 0; i < vectors.length; i++) {
                vectors[i].setX(posX + (OFFSETS[i][0] * width / 2));
                vectors[i].setY(posY + (OFFSETS[i][1] * height / 2));
                vectors[i].setZ(posZ + (OFFSETS[i][2] * depth / 2));
            }
    
        // Update all walls with the new vertex positions

        for (Wall wall : walls) {
            wall.updatePerspective();
        }
    }
}
