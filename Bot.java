public class Bot extends Person{
    public Bot(String spielername) {
        super(spielername);
    }

    public void deckBauen(){
        eigenesDeck.kartenEinsortieren_Bot();
    }
}
