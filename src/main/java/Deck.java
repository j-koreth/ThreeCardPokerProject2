import java.util.ArrayList;
import java.util.Collections;

public class Deck extends ArrayList<Card> {
    Deck(){
        GenerateDeck();
    }

    void newDeck(){
        GenerateDeck();
    }

    //Generate new deck of cards in a random order
    void GenerateDeck(){
        this.clear();
        for(char c : Card.availableSuits()){
            for(int x: Card.availableValues()){
                this.add(new Card(c, x));
            }
        }
        Collections.shuffle(this);
    }


}
