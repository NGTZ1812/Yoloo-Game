import java.util.ArrayList;

public class Game {
    EigenerScanner scanny = new EigenerScanner();
    private ArrayList<Person> spielergruppe = new ArrayList<Person>();
    private ArrayList<Integer> gespielteKarten = new ArrayList<>();
    private ArrayList<Integer> gespielteKarten_sorted = new ArrayList<>();

    public Game(){
        preGame();
        whileGame();
        postGame();
    }

    public void preGame(){
        boolean spielbereit = false;

        while (!spielbereit){
            String antwort_JN = scanny.string_abfragen("Wollen sie einen neuen Spieler hinzufügen? (J/N)");
            if (antwort_JN.equals("J")){
                String antwort_SpielerBot = scanny.string_abfragen("Spieler oder Bot? (S/B)");
                if (antwort_SpielerBot.equals("S")){
                    String antwort_Spielername = scanny.string_abfragen("Wie soll der Spieler heißen?");
                    spielerHinzufuegen(antwort_Spielername);
                }
                else if (antwort_SpielerBot.equals("B")) {
                    String antwort_Botname = scanny.string_abfragen("Wie soll der Bot heißen?");
                    botHinzufuegen(antwort_Botname);
                }
            }
            else if (antwort_JN.equals("N")){
                spielbereit = true;
            }
        }
    }

    public void whileGame(){
        boolean spielBeendet = false;
        while (!spielBeendet){
            for (int i = 1; i <= 10; i++){
                String antwort_Starten = scanny.string_abfragen("Den "+i+". Durchlauf starten? (J/N)");
                if (antwort_Starten.equals("J")){
                    duchlaufStarten(i);
                    zwischenergebnisAusgeben();
                }
                else {
                    System.out.println("Na gut dann halt nicht... Frag ich halt nochmal...");
                    i -= 1;
                }
            }
            spielBeendet = true;
        }
    }

    public void postGame(){
        gewinnerBestimmen();
    }

    public void spielerHinzufuegen(String name){
        Person newPerson = new Person(name);
        spielergruppe.add(newPerson);
    }

    public void botHinzufuegen(String name){
        Bot newBot = new Bot(name);
        spielergruppe.add(newBot);
    }

    public void duchlaufStarten(Integer durchlaufNr){
        System.out.println("Durchlauf Nr."+ durchlaufNr + " wird gestartet...");
        for (int i = 0; i < spielergruppe.size(); i++){
            int kartenwert = spielergruppe.get(i).karteSpielen(durchlaufNr);
            System.out.println(spielergruppe.get(i).get_Name()+" spielt folgende Karte: "+kartenwert);
            gespielteKarten.add(kartenwert);
        }
        //gespielteKarten_sorted = gespielteKarten;
        for (int i = 0; i < gespielteKarten.size(); i++){
            int var = gespielteKarten.get(i);
            gespielteKarten_sorted.add(var);
        }
        gespielteKarten_sorted.sort(null);
        punkteVergeben(durchlaufNr);
        System.out.println();
    }

    public void punkteVergeben(Integer punkte){
        int siegwert = 0;
        for (int i = gespielteKarten_sorted.size()-1; i >= 0; i--){
            int vergleichswert = gespielteKarten_sorted.get(i);
            gespielteKarten_sorted.remove(i);
            if (!gespielteKarten_sorted.contains(vergleichswert)){
                siegwert = vergleichswert;
                break;
            }
            gespielteKarten_sorted.add(vergleichswert);
        }
        if (siegwert != 0){
            for (int i= 0; i < gespielteKarten.size(); i++){
                if (siegwert == gespielteKarten.get(i)){
                    System.out.println(spielergruppe.get(i).get_Name()+" hat den Durchlauf gewonnen und bekommt "+punkte+" Punkte!");
                    spielergruppe.get(i).punkteHinzufuegen(punkte);
                }
            }
        }
        gespielteKarten.clear();
        gespielteKarten_sorted.clear();
    }

    public void zwischenergebnisAusgeben(){
        System.out.println("Hier das aktuelle Zwischenergebnis:");
        System.out.println("-----------");
        for (int i = 0; i < spielergruppe.size(); i++){
            Person spieler = spielergruppe.get(i);
            System.out.println(spieler.get_Name()+": "+spieler.get_Punkte());
        }
        System.out.println("-----------");
        System.out.println();
    }

    public void gewinnerBestimmen(){
        Person sieger = spielergruppe.getFirst();
        for (int i = 1; i < spielergruppe.size(); i++){
            int vergkleichspunkte = spielergruppe.get(i).get_Punkte();
            if (sieger.get_Punkte() < vergkleichspunkte){
                sieger = spielergruppe.get(i);
            }
        }
        System.out.println(sieger.get_Name()+" hat das Spiel mit "+sieger.get_Punkte()+" Punkten gewonnen!!!");
    }
}
