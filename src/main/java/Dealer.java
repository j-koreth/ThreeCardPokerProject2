import java.util.ArrayList;
import java.util.Random;

public class Dealer {
    Deck theDeck;
    ArrayList<Card> dealersHand;

    Dealer(){
        theDeck = new Deck();
        dealersHand = new ArrayList<Card>();
    }

    public ArrayList<Card> dealHand(){
        if(theDeck.size() < 34){
            theDeck.newDeck();
            return dealHand();
        }
        else{
            for(int x = 0; x < 3; x++){
                dealersHand.add(theDeck.remove(0));
            }
        }
        return dealersHand;
    }

}
