package main.modele;

public class Hero {

    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPosition(int x, int y) {
        System.out.println("Setting position to: (" + x + ", " + y + ")"); // Debug statement
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Hero is at (" + x + ", " + y + ")";
    }

}
