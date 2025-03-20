import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import java.util.HashSet;
import java.util.Set;

public class GameObject {

        private final int[][] OFFSETS = {
            {-1, -1, -1}, {1, -1, -1}, {1, 1, -1}, {-1, 1, -1}, // Front wall
            {1, -1, 1}, {-1, -1, 1}, {-1, 1, 1}, {1, 1, 1}      // Back wall
        };
    
        private Wall[] walls = new Wall[6];
    
        private Set<Integer> activeKeys = new HashSet<>();
    
        private int posX, posY, posZ, size;
        private int speed = 2;
    
        private Vector[] vectors = new Vector[8];
    
        public GameObject(int posX, int posY, int posZ, int size) {
    
            this.posX = posX;
            this.posY = posY;
            this.posZ = posZ;
            this.size = size;
    
    
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
    
    
        }
    
        public void Draw(Graphics g) {
            g.setColor(Color.BLACK);
            for (Wall wall : walls) {
                wall.DrawWall(g);
            }
        }
    
        public void KeyPressed(KeyEvent k) {
            activeKeys.add(k.getKeyCode());
        }
    
        public void KeyReleased(KeyEvent k) {
            activeKeys.remove(k.getKeyCode());
        }
    
        public void TranslatePos() {
    
            if (activeKeys.contains(KeyEvent.VK_RIGHT)) posX += speed;
            if (activeKeys.contains(KeyEvent.VK_LEFT)) posX -= speed;
            if (activeKeys.contains(KeyEvent.VK_UP)) posY -= speed;
            if (activeKeys.contains(KeyEvent.VK_DOWN)) posY += speed;
            if (activeKeys.contains(KeyEvent.VK_E)) posZ += speed;
            if (activeKeys.contains(KeyEvent.VK_Q)) posZ -= speed;
        
            UpdateVerticiesPos();
    
        }
    
        public void CreateVerticies() {
    
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
        }
    
        public void UpdateVerticiesPos() {
    
    
            for (int i = 0; i < vectors.length; i++) {
                vectors[i].setX(posX + (OFFSETS[i][0] * size / 2));
                vectors[i].setY(posY + (OFFSETS[i][1] * size / 2));
                vectors[i].setZ(posZ + (OFFSETS[i][2] * size / 2));
            }
    
        // Update all walls with the new vertex positions
        
        // for (int i = 0; i < walls.length; i++) {
        //     walls[i].setPerspectiveVer(i);
        // }


        // //front wall verticies
        // vectors[0].setX(posX-size/2);
        // vectors[0].setY(posY-size/2);
        // vectors[0].setZ(posZ-size/2);

        // vectors[1].setX(posX+size/2);
        // vectors[1].setY(posY-size/2);
        // vectors[1].setZ(posZ-size/2);

        // vectors[2].setX(posX+size/2);
        // vectors[2].setY(posY+size/2);
        // vectors[2].setZ(posZ-size/2);

        // vectors[3].setX(posX-size/2);
        // vectors[3].setY(posY+size/2);
        // vectors[3].setZ(posZ-size/2);

        // //back wall verticies
        // vectors[4].setX(posX+size/2);
        // vectors[4].setY(posY-size/2);
        // vectors[4].setZ(posZ+size/2);

        // vectors[5].setX(posX-size/2);
        // vectors[5].setY(posY-size/2);
        // vectors[5].setZ(posZ+size/2);

        // vectors[6].setX(posX-size/2);
        // vectors[6].setY(posY+size/2);
        // vectors[6].setZ(posZ+size/2);

        // vectors[7].setX(posX+size/2);
        // vectors[7].setY(posY+size/2);
        // vectors[7].setZ(posZ+size/2);


        for (Wall wall : walls) {
            wall.updatePerspective();
        }
    }
}
