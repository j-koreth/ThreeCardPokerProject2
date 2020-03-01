import java.util.ArrayList;

public class Dealer {
    Deck theDeck;
    ArrayList<Card> dealersHand;

    Dealer(){
        theDeck = new Deck();
        dealersHand = new ArrayList<Card>();
    }

    //Generate dealers hand
    public ArrayList<Card> dealHand(){
        ArrayList<Card> cards = new ArrayList<Card>();

        //If the Deck Size is less than 34, make new Deck and generate new dealer's hand
        if(theDeck.size() <= 34){
            theDeck.newDeck();
            return dealHand();
        }
        else{
            //Add three cards from top of the deck to return
            for(int x = 0; x < 3; x++){
                cards.add(theDeck.remove(0));
            }
        }
        return cards;
    }

}
