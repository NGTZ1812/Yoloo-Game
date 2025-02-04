public class Person {

    protected Deck eigenesDeck = new Deck();

    private String name;
    private int punkte = 0;
    private boolean istSieger = false;

    public Person(String spielername) {
        name = spielername;
        deckBauen();
    }

    public Integer get_Punkte(){
        return punkte;
    }

    public String get_Name(){
        return name;
    }

    public void deckBauen(){
        eigenesDeck.kartenEinsortieren();
    }

    public Integer karteSpielen(Integer aktuelleRunde){
        return eigenesDeck.getKarte(aktuelleRunde);
    }

    public void punkteHinzufuegen(Integer wert){
        punkte += wert;
    }

    public void zumSiegerErklaeren(){
        istSieger = true;
    }
}
