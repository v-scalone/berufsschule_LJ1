package figuren;
import ack.shapes.Leinwand;

import java.sql.Time;
import java.util.*;

public class Test {
    static Map<Integer, Form> formenListe =  new HashMap<Integer, Form>();
    static Map<Integer, List<Form>> dreieckListe = new HashMap();

    public static Integer objectIndexer = 0;

    static Leinwand leinwand = new Leinwand("Leinwand", 600, 500);

    public static void main(String[] args) throws InterruptedException {

        boolean loop = false;
        while (loop){
            System.out.println("Was willst du zeichen? rechteck[1]/kreis[2]");
            int decision = new Scanner(System.in).nextInt();
            switch (decision){
                case 1 -> drawRechteck();
                case 2 -> drawKreis();
                case 3 -> deleteFromCanvas();
                case 4 -> drawDreieck();
            }
        }

        Kreis kreis = new Kreis(30, 30, 10, "rot");
        animate(kreis);

    }

    public static void animate(Kreis kreis) throws InterruptedException {
        int boundaryX = leinwand.getLeinwandBreite();
        int boundaryY = leinwand.getLeinwandHoehe();
        int[] direction = randomDirection();

        while (true) {
            leinwand.zeichne(kreis);
            Thread.sleep(20);


            if (isTouchingBoundary(kreis)) {
                direction = randomDirection();
                if (kreis.positionX - kreis.radius <= 0) {
                    direction[0] = ensurePositive(direction[0]);
                }

                if (kreis.positionX + kreis.radius >= boundaryX) {
                    direction[0] = ensureNegative(direction[0]);
                }

                if (kreis.positionY - kreis.radius <= 0) {
                    direction[1] = ensurePositive(direction[1]);
                }

                if (kreis.positionY - kreis.radius >= boundaryY) {
                    direction[1] = ensureNegative(direction[1]);
                }
            }
            kreis.move(direction);
        }
    }

    private static boolean isTouchingBoundary (Kreis kreis) {
        int boundaryX = leinwand.getLeinwandBreite();
        int boundaryY = leinwand.getLeinwandHoehe();

        if (kreis.positionX - kreis.radius <= 0) {
            return true;
        }

        if (kreis.positionX + kreis.radius >= boundaryX) {
            return true;
        }

        if (kreis.positionY - kreis.radius <= 0) {
            return true;
        }

        if (kreis.positionY - kreis.radius >= boundaryY) {
            return true;
        }
        return false;
    }

    private static int ensurePositive(int input) {
        if (input >= 0) return input;
        else return input * -1;
    }
    private static int ensureNegative(int input) {
        if (input <= 0) return input;
        else return input * -1;
    }

    private static int[] randomDirection() {
        Random random = new Random();
        int[] direction = new int[2];
        direction[0] = random.nextInt(9) - 4;
        direction[1] = random.nextInt(9) - 4;
        return direction;
    }

    public static void drawRechteck(){
        System.out.println("What should your rechteck be?");
        System.out.print("[posx]: ");
        int posX = new Scanner(System.in).nextInt();
        System.out.println();

        System.out.print("[posy]: ");
        int posY = new Scanner(System.in).nextInt();
        System.out.println();

        System.out.print("[breite]: ");
        int breite = new Scanner(System.in).nextInt();
        System.out.println();

        System.out.print("[höhe]: ");
        int hoehe = new Scanner(System.in).nextInt();
        System.out.println();

        System.out.print("[farbe]: ");
        String colour = new Scanner(System.in).nextLine();
        System.out.println();

        Rechteck rechteck = new Rechteck(posX, posY, breite, hoehe, colour);

        addToMap(rechteck);

        leinwand.zeichne(rechteck);
    }

    public static void drawKreis (){
        System.out.println("What should your Kreis be?");
        System.out.print("[posx]: ");
        int posX = new Scanner(System.in).nextInt();
        System.out.println();

        System.out.print("[posy]: ");
        int posY = new Scanner(System.in).nextInt();
        System.out.println();

        System.out.print("[radius]: ");
        int breite = new Scanner(System.in).nextInt();
        System.out.println();

        System.out.print("[farbe]: ");
        String colour = new Scanner(System.in).nextLine();
        System.out.println();

        Kreis kreis = new Kreis(posX, posY, breite, colour);

        System.out.println("Zeichne Kreis...");

        addToMap(kreis);

        leinwand.zeichne(kreis);
    }

    public static void drawDreieck(){
        List<Form> dreieckLListe = new ArrayList<>();
        for (int i = 0; i < 100; i++){
            Form dreieckPart = new Rechteck(150 - i, 150 + i, 1+ i*2, 1, "rot");
            leinwand.zeichne(dreieckPart);
            dreieckLListe.add(dreieckPart);
        }
        objectIndexer ++;
        dreieckListe.put(objectIndexer, dreieckLListe);
    }

    public static void addToMap(Form form){
        objectIndexer++;
        formenListe.put(objectIndexer, form);
    }

    public static void deleteFromCanvas(){
        System.out.println("Diese Formen existieren gerade: ");
        System.out.println(formenListe);
        int index = new Scanner(System.in).nextInt();
        if (index != 0) {
            Form formToDelete = formenListe.remove(index);
            leinwand.entferne(formToDelete);
        }

        System.out.println("Diese Dreiecke existieren gerade: ");
        System.out.println(dreieckListe);
        int indexDreieck = new Scanner(System.in).nextInt();
        if (indexDreieck != 0) {
            List<Form> formToDelete = dreieckListe.remove(indexDreieck);
            for (Form form : formToDelete) {
                leinwand.entferne(form);
            }
        }
    }
}
