package figuren;

import ack.shapes.Leinwand;
import figuren.model.Ampel;

public class Ampelsimulation {

    static Leinwand leinwand = new Leinwand();

    public static void main(String[] args) throws InterruptedException {
        Ampel ampel = new Ampel();
        //ampel.zeichne(leinwand);
        animateTrafficLight(ampel, leinwand);
    }

    public static void animateTrafficLight(Ampel trafficLight, Leinwand leinwand) throws InterruptedException {
        final long SLEEP_TIME = 1000;

        do {
            trafficLight.umschalten(leinwand);
            Thread.sleep(SLEEP_TIME);
        } while (true);
    }
}
