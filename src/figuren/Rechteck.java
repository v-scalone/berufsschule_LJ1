package figuren;

public class Rechteck extends Form{
    int positionX;
    int positionY;
    int breite;
    int hoehe;
    String farbe;

    public Rechteck(){
        this(200,150,300,100,"rot");
    }

    public Rechteck(int positionX, int positionY, int breite, int hoehe, String farbe) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.breite = breite;
        this.hoehe = hoehe;
        this.farbe = farbe;
    }
}
