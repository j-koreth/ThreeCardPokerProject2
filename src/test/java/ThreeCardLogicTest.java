import org.junit.jupiter.api.Test;

public class ThreeCardLogicTest {
    Dealer testDeck1 = new Dealer();
    Player testPlayer = new Player();
    Player testPlayer2 = new Player();

    @Test
    void test1(){
        testPlayer.hand = testDeck1.dealHand();

        ThreeCardLogic.evalHand(testPlayer.hand);
    }
}
