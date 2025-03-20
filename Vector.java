public class Vector {
    private int x, y, z;

    public Vector(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector(Vector v) {
        this.x = v.getX();
        this.y = v.getY();
        this.z = v.getZ();
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }
    public void setZ(int z) {
        this.z = z;
    }

    public int getZ() {
        return z;
    }

    public void Info() {
        System.out.println("x: " + getX() + " y: " + getY() + " z: " + getZ());
    }
}
