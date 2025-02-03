import java.util.ArrayList;
import java.util.Scanner;


public class Deck {
    EigenerScanner scanny = new EigenerScanner();
    private ArrayList<Integer> karten = new ArrayList<>();

    public Integer getKarte(Integer stelle){
        return karten.get(stelle-1);
    }

    public void kartenEinsortieren(){
        for (int i = 0; i < 10; i++){
            int kartenwert = scanny.int_abfragen("Welchen Wert soll die "+(i+1)+". Karte haben?");
            if (!karten.contains(kartenwert)){
                karten.add(kartenwert);
            }
            else if (karten.contains(kartenwert)) {
                System.out.println("Ungültige Eingabe! Karte wurde bereits einsortiert");
                i -= 1;
            }
            else if (kartenwert < 1){
                System.out.println("Ungültige Eingabe! Wähle eine Karte zwischen 1 und 10");
                i -= 1;
            }
        }
        System.out.println("Deine Kartenfolge: "+karten);
    }

    public void kartenEinsortieren_Bot(){
        for (int i = 0; i < 10; i++){
            int kartenwert = ((int)(Math.random() * 10)+1);
            if (!karten.contains(kartenwert)){
                karten.add(kartenwert);
            }
            else{
                i -= 1;
            }
        }
        System.out.println("Der Bot hat seine Karten sortiert..."+karten);
    }
}
