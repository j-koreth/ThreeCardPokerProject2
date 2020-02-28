import java.util.ArrayList;
import java.util.Collections;

public class Deck extends ArrayList<Card> {
    ArrayList<Card> cards;

    Deck(){
        GenerateDeck();
    }

    void newDeck(){
        GenerateDeck();
    }

    //Generate new deck of cards in a random order
    void GenerateDeck(){
        cards = new ArrayList<Card>();

        for(char c : Card.availableSuits()){
            for(int x: Card.availableValues()){
                cards.add(new Card(c, x));
            }
        }
        Collections.shuffle(cards);
    }
}
