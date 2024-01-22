package figuren;
import ack.shapes.Leinwand;

import java.util.*;

public class Test {
    static Map<Integer, Form> formenListe =  new HashMap<Integer, Form>();
    static Map<Integer, List<Form>> dreieckListe = new HashMap();

    public static Integer objectIndexer = 0;

    static Leinwand leinwand = new Leinwand("Leinwand", 100, 300);

    public static void main(String[] args) {

        while (true){
            System.out.println("Was willst du zeichen? rechteck[1]/kreis[2]");
            int decision = new Scanner(System.in).nextInt();
            switch (decision){
                case 1 -> drawRechteck();
                case 2 -> drawKreis();
                case 3 -> deleteFromCanvas();
                case 4 -> drawDreieck();
            }
        }
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
