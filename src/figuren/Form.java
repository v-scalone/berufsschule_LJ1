package figuren;

public abstract class Form {
    int positionX;
    int positionY;
    String farbe;

    public abstract void move(int[] direction);
}
