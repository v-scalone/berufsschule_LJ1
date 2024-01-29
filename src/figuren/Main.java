package figuren;
import ack.shapes.Leinwand;

import java.util.*;
import java.util.List;

import static java.lang.Math.sqrt;

public class Main {
    static Map<Integer, Form> formenListe =  new HashMap<Integer, Form>();
    static Map<Integer, List<Form>> dreieckListe = new HashMap();
    public static Integer objectIndexer = 0;




    static Leinwand leinwand = new Leinwand("Leinwand", 600, 900);
    static int[] middle = new int[] {(int) (leinwand.getLeinwandBreite() * 0.5), (int) (leinwand.getLeinwandHoehe()* 0.5)};


    public static void main(String[] args) throws InterruptedException {

        //animate(new Kreis(30, 30, 10, "rot"));

        kreisBewegung(new Kreis(0, 450, 60, "gelb"),
                List.of(new Rechteck(263, 550, 75, 350, "braun"),
                        new Kreis(300, 500, 100, "gruen")));


    }

    public static void animate(Kreis kreis) throws InterruptedException {
        int[] direction = new int[] {6, 8};

        while (true) {
            leinwand.zeichne(kreis);
            leinwand.warte(20);

            switch (checkForBoundary(kreis)) {
                case "left" -> direction[0] = Math.abs(direction[0]);
                case "right" -> direction[0] = Math.abs(direction[0]) * -1;
                case "up" -> direction[1] = Math.abs(direction[1]);
                case "down" -> direction[1] = Math.abs(direction[1]) * -1;
                case null, default -> {}
            }

            kreis.move(direction);
        }
    }

    private static void kreisBewegung(Kreis kreis, List<Form> scenery) throws InterruptedException {
        boolean isDay = true;

        final double START_ANGLE = 214;
        double angleInDeg = START_ANGLE;

        int radius = (int) sqrt(Math.pow((kreis.positionX - middle[0]), 2.0) + Math.pow((kreis.positionY - middle[1]), 2));

        Rechteck bgRectangle = new Rechteck(-10, -10, 1000, 1000, "weiß");
        Kreis abgebissen = new Kreis(0, 450, 60, "schwarz");

        while (true) {
            // redraw all shapes
            leinwand.zeichne(bgRectangle);
            leinwand.zeichne(kreis);
            if (!isDay) leinwand.zeichne(abgebissen);
            scenery.forEach(leinwand::zeichne);

            // increment angle and move sun / moon
            angleInDeg += 1;
            moveToPoint(kreis, getPointFromAngleAndRadius(Math.toRadians(angleInDeg), radius));
            moveToPoint(abgebissen, new int[] {kreis.positionX - 40, kreis.positionY});

            // check if circle touches right border and switch between day or night; reset circle to left
            if (Objects.equals(checkForBoundary(kreis), "right") && isDay) {
                bgRectangle.farbe = "schwarz";
                kreis.farbe = "weiss";
                isDay = false;
                angleInDeg = START_ANGLE;
            } else if (Objects.equals(checkForBoundary(kreis), "right") && !isDay) {
                bgRectangle.farbe = "weiss";
                kreis.farbe = "gelb";
                isDay = true;
                angleInDeg = START_ANGLE;
            }

            leinwand.warte(10);
        }

    }

    private static void moveToPoint (Kreis kreis, int[] point) {
        int xMove = point[0] - kreis.positionX;
        int yMove = point[1] - kreis.positionY;

        kreis.move(new int[]{xMove, yMove});
    }

    private static int[] getPointFromAngleAndRadius(double angle, int radius) {
        int[] middle = new int[] {(int) (leinwand.getLeinwandBreite() * 0.5), (int) (leinwand.getLeinwandHoehe()* 0.5)};

        double sin = Math.sin(angle);
        double yCoordinate = sin * radius + middle[1];

        double cos = Math.cos(angle);
        double xCoordinate = cos * radius + middle[0];

        return new int[] {(int) xCoordinate, (int) yCoordinate};
    }


    private static String checkForBoundary(Kreis kreis) {
        int boundaryX = leinwand.getLeinwandBreite();
        int boundaryY = leinwand.getLeinwandHoehe();

        if (kreis.positionX - kreis.radius <= 0) {
            return "left";
        }

        if (kreis.positionX + kreis.radius >= boundaryX) {
            return "right";
        }

        if (kreis.positionY - kreis.radius <= 0) {
            return "up";
        }

        if (kreis.positionY + kreis.radius >= boundaryY) {
            return "down";
        }
        return null;
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
