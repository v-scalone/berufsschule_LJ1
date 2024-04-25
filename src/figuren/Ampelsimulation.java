package figuren;

import ack.shapes.Leinwand;

public class Ampelsimulation {

    static Leinwand leinwand = new Leinwand("Leinwand", 600, 900);

    public static void main(String[] args) throws InterruptedException {
        Ampel ampel = new Ampel();
        //ampel.zeichne(leinwand);
        ampel.animate(leinwand);
    }
}
