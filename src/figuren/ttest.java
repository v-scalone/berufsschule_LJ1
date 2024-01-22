package figuren;
import ack.shapes.Leinwand;

import java.util.*;

public class Test {
    static Map<Integer, Form> formenListe =  new HashMap<Integer, Form>();

    public static Integer objectIndexer = 1;

    static Leinwand leinwand = new Leinwand("Leinwand", 100, 300);

    public static void main(String[] args) {

        while (true){
            System.out.println("Was willst du zeichen? rechteck[1]/kreis[2]");
            int decision = new Scanner(System.in).nextInt();
            switch (decision){
                case 1 -> drawRechteck(leinwand);
                case 2 -> drawKreis(leinwand);
                case 3 -> delete;
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

        leinwand.zeichne(new Rechteck(posX, posY, breite, hoehe, colour));
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

        drawCanvas(formenListe);
    }

    public static void drawCanvas(List<Form> formen){
        for (Form form : formen){
            leinwand.zeichne(form);
        }
    }

    public static void addToMap(Form form){
        objectIndexer++;
        formenListe.put(objectIndexer, form);
    }

    public static void deleteFromCanvas(){
        System.out.println("Diese Formen existieren gerade: ");
        System.out.println(formenListe);
        int index = new Scanner(System.in).nextInt();
        formenListe.pop(index);


    }
}
