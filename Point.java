public class Point { // Point class from Donald Simon to store coordinates

    public int x; // x coordinate
    public int y; // y coordinate

    public Point(int x, int y) { // Point object stores x and y values
        this.x = x;
        this.y = y;
    }

    // Getters and setters

    public int getx() {
        return x;
    }

    public int gety() {
        return y;
    }

    public void setx(int x) {
        this.x = x;
    }

    public void sety(int y) {
        this.y = y;
    }

}