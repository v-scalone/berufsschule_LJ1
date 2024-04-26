package figuren.model;

import ack.shapes.Leinwand;

import java.util.List;

public class Ampel {

    private final Rechteck gehaeuse;
    private final Kreis gruenerKreis;
    private final Kreis gelberKreis;
    private final Kreis roterKreis;
    private Ampelzustand ampelzustand;

    public Ampel(int posX, int posY, int breite, Ampelzustand ampelzustand) {
        this.ampelzustand = ampelzustand;
        gehaeuse = new Rechteck(posX, posY, breite, 3 * breite, "schwarz");
        roterKreis = new Kreis((int) (posX + 0.5 * breite), (int) (posY + 0.5 * breite), (int) (0.45 * breite), "rot");
        gelberKreis = new Kreis((int) (posX + 0.5 * breite), (int) (posY + 1.5 * breite), (int) (0.45 * breite), "gelb");
        gruenerKreis = new Kreis((int) (posX + 0.5 * breite), (int) (posY + 2.5 * breite), (int) (0.45 * breite), "gruen");
    }

    public Ampel() {
        this(50, 50, 100, Ampelzustand.ROT);
    }

    public void zeichne(Leinwand leinwand) {
        leinwand.zeichne(gehaeuse);
        leinwand.zeichne(roterKreis);
        leinwand.zeichne(gelberKreis);
        leinwand.zeichne(gruenerKreis);
    }

    public void umschalten(Leinwand leinwand) throws InterruptedException {
        switch (ampelzustand) {
            case ROT -> {
                drawCircles(List.of(roterKreis), leinwand);
                ampelzustand = Ampelzustand.ROT_GELB;
            }
            case ROT_GELB -> {
                drawCircles(List.of(roterKreis, gelberKreis), leinwand);
                ampelzustand = Ampelzustand.GRUEN;
            }
            case GRUEN -> {
                drawCircles(List.of(gruenerKreis), leinwand);
                ampelzustand = Ampelzustand.GELB;
            }
            case GELB -> {
                drawCircles(List.of(gelberKreis), leinwand);
                ampelzustand = Ampelzustand.ROT;
            }
        }
    }

    private void drawCircles(List<Kreis> circles, Leinwand leinwand) {
        leinwand.zeichne(gehaeuse);
        for (Kreis circle : circles) {
            leinwand.zeichne(circle);
        }
    }
}
