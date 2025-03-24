each branch has different features for testing how the cube behaves with movement, rotation or just plain rendering.

HOW THE RENDERING WORKS:
1. Rendering works by creating simple verticies around a point. 
2. These verticies are next replaced on other positions by either being moved or rotated. 
3. There are some basic calculations with linear interpolation for depth effect to the center of the screen based on the FIELD_OF_VIEW variable and Z position of the vertex.
4. Shapes are drawn by going through each wall of an object and checking if those verticies are going clockwise or counterclockwise in relation to the camera.
If those verticies are in the counterclockwise order then the wall is facing the camera and can be drawn without any issues


WHAT EACH FILE DOES:

-Main.java - main executable file which creates a window and runs the whole application

-MyFrame.java - creates a frame(window) of the application in which there is a timer for redrawing. to which we add the "Board" script.

-Board.java - this script is a whole scenery of the application where we add the GameObject which are being drawn on the screen.

-GameObject.java - it's a script which defines a whole object that is being drawn on the screen

-Wall.java - it's a wall that makes up the sides of an object

-Vector.java - It's a vector in space from which walls are made


To run the script you need to go to the directory of the project in the command line/terminal and assuming you have java installed on your computer type 'java Main.java'.
It's going to turn on the application window and you will be able to see the rendered cube.


If you want to change the size, position or rotation of the cube, you need to change the properties of 'GameObject' in 'Board.java' script. 
First three values are for the position of the cube, while the next three go for the size. If you're on the 'cube-rotation' branch 'GameObject' has three additional properties for the rotation.
