package figuren;

public class Kreis extends Form{
    int positionX;
    int positionY;
    int radius;
    String farbe;

    public Kreis() {
        this(100,100,50,"gruen");
    }

    public Kreis(int positionX, int positionY, int radius, String farbe) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.radius = radius;
        this.farbe = farbe;
    }
}
