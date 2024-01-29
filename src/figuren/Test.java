package figuren;
import ack.shapes.Leinwand;

import java.awt.*;
import java.util.*;
import java.util.List;

import static java.lang.Math.sqrt;

public class Test {
    static Map<Integer, Form> formenListe =  new HashMap<Integer, Form>();
    static Map<Integer, List<Form>> dreieckListe = new HashMap();
    public static Integer objectIndexer = 0;




    static Leinwand leinwand = new Leinwand("Leinwand", 600, 900);

    static Rechteck trunk = new Rechteck(263, 450, 75, 450, "braun");
    static Kreis leaves = new Kreis(300, 400, 100, "gruen");


    public static void main(String[] args) throws InterruptedException {

        //Kreis kreis = new Kreis(30, 30, 10, "rot");
        //animate(kreis);



        //leinwand.zeichne(new Kreis(middle[0], middle[1], 5, "schwarz" ));

        Kreis sonne = new Kreis(0, 450, 60, "gelb");
        kreisBewegung(sonne);


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

    private static void kreisBewegung(Kreis kreis) throws InterruptedException {
        boolean isNight = false;
        int[] middle = new int[] {(int) (leinwand.getLeinwandBreite() * 0.5), (int) (leinwand.getLeinwandHoehe()* 0.5)};

        double angleInDeg = 180;
        int radius = (int) sqrt(Math.pow((kreis.positionX - middle[0]), 2.0) + Math.pow((kreis.positionY - middle[1]), 2));

        Rechteck bgRectangle = new Rechteck(-10, -10, 1000, 1000, "weiß");

        Kreis abgebissen = new Kreis(0, 450, 60, "schwarz");

        while (true) {
            leinwand.zeichne(trunk);
            leinwand.zeichne(leaves);
            leinwand.zeichne(kreis);
            if (isNight) leinwand.zeichne(abgebissen);

            angleInDeg += 1;
            int[] cords = getNewCordsFromAngle(Math.toRadians(angleInDeg), radius);
            int xMove = cords[0] - kreis.positionX;
            int yMove = cords[1] - kreis.positionY;

            int[] direction = new int[]{xMove, yMove};
            kreis.move(direction);

            int xMoveAbgebissen = kreis.positionX - abgebissen.positionX - 30;
            int yMoveAbgebissen = kreis.positionY - abgebissen.positionY;
            int[] directionAbgebissen = new int[]{xMoveAbgebissen, yMoveAbgebissen};
            abgebissen.move(directionAbgebissen);

            if (Objects.equals(checkForBoundary(kreis), "right") && !isNight) {
                bgRectangle.farbe = "schwarz";
                kreis.farbe = "weiss";
                isNight = true;
                angleInDeg = 180;
                leinwand.zeichne(bgRectangle);
            } else if (Objects.equals(checkForBoundary(kreis), "right") && isNight) {
                bgRectangle.farbe = "weiss";
                kreis.farbe = "gelb";
                isNight = false;
                angleInDeg = 180;
                leinwand.zeichne(bgRectangle);
            }
            leinwand.warte(10);
        }

    }

    private static int[] getNewCordsFromAngle(double angle, int radius) {
        int[] middle = new int[] {(int) (leinwand.getLeinwandBreite() * 0.5), (int) (leinwand.getLeinwandHoehe()* 0.5)};

        double sin = Math.sin(angle);
        double yCoord = sin * radius + middle[1];

        double cos = Math.cos(angle);
        double xCoord = cos * radius + middle[0];

        return new int[] {(int) xCoord, (int) yCoord};
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
