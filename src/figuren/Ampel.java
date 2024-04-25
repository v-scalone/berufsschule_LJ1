package figuren;

import ack.shapes.Leinwand;

public class Ampel {

    private final Rechteck gehaeuse;
    private final Kreis gruenerKreis;
    private final Kreis gelberKreis;
    private final Kreis roterKreis;

    public Ampel(int posX, int posY, int breite) {
        gehaeuse = new Rechteck(posX, posY, breite, 3 * breite, "schwarz");
        roterKreis = new Kreis((int) (posX + 0.5 * breite), (int) (posY + 0.5 * breite), (int) (0.45 * breite), "rot");
        gelberKreis = new Kreis((int) (posX + 0.5 * breite), (int) (posY + 1.5 * breite), (int) (0.45 * breite), "gelb");
        gruenerKreis = new Kreis((int) (posX + 0.5 * breite), (int) (posY + 2.5 * breite), (int) (0.45 * breite), "gruen");
    }

    public Ampel() {
        this(50, 50, 100);
    }

    public void zeichne(Leinwand leinwand) {
        leinwand.zeichne(gehaeuse);
        leinwand.zeichne(roterKreis);
        leinwand.zeichne(gelberKreis);
        leinwand.zeichne(gruenerKreis);
    }

    public void animate(Leinwand leinwand) throws InterruptedException {
        final long SLEEP_TIME = 500;
        while (true) {
            leinwand.zeichne(gehaeuse);
            leinwand.zeichne(roterKreis);
            Thread.sleep(SLEEP_TIME * 2);
            leinwand.zeichne(gelberKreis);
            Thread.sleep(SLEEP_TIME);
            leinwand.zeichne(gehaeuse);
            leinwand.zeichne(gruenerKreis);
            Thread.sleep(SLEEP_TIME * 2);
            leinwand.zeichne(gehaeuse);
            leinwand.zeichne(gelberKreis);
            Thread.sleep(SLEEP_TIME);
        }
    }
}
