package figuren.model;

public abstract class Form {
    public int positionX;
    public int positionY;
    public String farbe;

    public abstract void move(int[] direction);
}
