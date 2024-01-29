package figuren;

public class Kreis extends Form{
    int positionX;
    int positionY;
    String farbe;
    int radius;
    public Kreis() {
        this(100,100,50,"gruen");
    }

    public Kreis(int positionX, int positionY, int radius, String farbe) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.radius = radius;
        this.farbe = farbe;
    }

    public void move(int[] direction) {
        this.positionX += direction[0];
        this.positionY += direction[1];
    }

}
