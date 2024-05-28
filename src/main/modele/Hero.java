package main.modele;

public class Hero {

    private int x;
    private int y;

//    public Hero(int startX, int startY){
//        this.x = startX;
//        this.y = startY;
//    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

//    public void setNewPosition(int newX, int newY) {
//        this.x = newX;
//        this.y = newY;
//    }

    @Override
    public String toString() {
        return "Hero is at (" + x + ", " + y + ")";
    }

}
