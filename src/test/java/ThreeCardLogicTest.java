import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

public class ThreeCardLogicTest {
    Dealer testDeck1 = new Dealer();
    Player testPlayer = new Player();
    Player testPlayer2 = new Player();

    @Test
    void test1(){
        testPlayer.hand = testDeck1.dealHand();
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card('C',5));
        cards.add(new Card('S',5));
        cards.add(new Card('D',5));
        cards.add(new Card('H',5));
        System.out.println(cards.get(0).compareTo(cards.get(1)));
        System.out.println(Collections.frequency(cards, cards.get(0)));
        ThreeCardLogic.evalHand(testPlayer.hand);
    }
}
