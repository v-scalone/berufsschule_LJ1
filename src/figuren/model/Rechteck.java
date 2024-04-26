package figuren.model;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Rechteck extends Form {
    public int positionX;
    public int positionY;
    int breite;
    int hoehe;
    double[] mitte = new double[2];
    public String farbe;

    public Rechteck() {
        this(200, 100, 200, 100, "rot");
    }

    public Rechteck(int positionX, int positionY, int breite, int hoehe, String farbe) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.breite = breite;
        this.hoehe = hoehe;
        this.farbe = farbe;
    }

    public void turn90Degrees() {
        calculateMiddle();
        double[] oldMitte = Arrays.copyOf(mitte, 2);

        int oldBreite = breite;
        breite = hoehe;
        hoehe = oldBreite;

        calculateMiddle();
        double moveX = oldMitte[0] - mitte[0];
        double moveY = oldMitte[1] - mitte[1];

        positionX += moveX;
        positionY += moveY;
    }

    private void calculateMiddle() {
        mitte[0] = positionX + 0.5 * breite;
        mitte[1] = positionY + 0.5 * hoehe;
    }

    public void move(int[] direction) {
        this.positionX += direction[0];
        this.positionY += direction[1];
    }

}
