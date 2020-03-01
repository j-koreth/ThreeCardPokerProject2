import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

public class DeckDealerTest {
//Deck Testing

    Deck testDeck1 = new Deck();
    Deck testDeck2 = new Deck();
    Deck testDeck3 = new Deck();
    Deck testDeck4 = new Deck();
    Deck testDeck5 = new Deck();

    @Test
    void deckTest1() //Check deck length, and make sure it's saved correct.
    {
        assertEquals(52, testDeck1.size(), "Deck is the wrong size");

        System.out.println("The deck is: " + testDeck1);
    }

    @Test
    void deckTest2() //Check if after removing half the deck if new deck restores deck to correct amount.
    {
        for (int i = 0; i < 26; ++i) {
            testDeck2.remove(0);
        }
        assertEquals(26, testDeck2.size(), "Deck was not cut in half");

        testDeck2.newDeck();

        assertEquals(52, testDeck2.size(), "New deck size is incorrect");
    }

    @Test
    void deckTest3() //Make sure that when new deck is made cards are shuffled.
    {
        ArrayList<Card> testCards1 = new ArrayList<Card>();
        for (int i = 0; i < 52; ++i) {
            testCards1.add(testDeck3.get(i));
        }

        testDeck3.newDeck();

        ArrayList<Card> testCards2 = new ArrayList<Card>();
        for (int i = 0; i < 52; ++i) {
            testCards2.add(testDeck3.get(i));
        }
        assertNotEquals(testCards1, testCards2, "Deck was not reshuffled");
    }

    @Test
    void deckTest4() //Check to make sure no card is repeated in the deck
    {
        for (int i = 0; i < 52; ++i) {
            for (int j = 0; j < 52; ++j) {
                /*If index is the same card should be the same*/
                if (i == j) {
                    continue;
                }//end of if

                /*Check cards with different index*/
                else {
                    if (testDeck4.get(i) == testDeck4.get(j)) {
                        fail("Card was repeated in the deck");
                    }//end of if
                }//end of else

            }//end of for j
        } //end of for i
    }

    @Test
    void deckTest5()//Check that when deck is completely cleared new deck works.
    {
        assertEquals(52, testDeck5.size(), "Deck size is correct");

        testDeck5.clear();
        testDeck5.newDeck();

        assertEquals(52, testDeck5.size(), "New deck size is correct");

        for (int i = 0; i < 52; ++i) {
            System.out.println(testDeck5.get(i));
        }
    }

    /*-------------------------------------------------------------------------------------------------------------------------------*/
//Dealer testing

    Dealer testDealer1 = new Dealer();
    Dealer testDealer2 = new Dealer();
    Dealer testDealer3 = new Dealer();
    Dealer testDealer4 = new Dealer();
    Dealer testDealer5 = new Dealer();

    @Test
    void dealerTest1() //Check to make sure deck and hand are correct size when initialized
    {
        assertEquals(52, testDealer1.theDeck.size(), "The size of the deck is correct");
        assertEquals(0, testDealer1.dealersHand.size(), "Dealer's hand is correct, cards have not been dealt");
    }

    @Test
    void dealerTest2() //check to make sure dealing a hand works correctly
    {
        ArrayList<Card> testHand1 = testDealer2.dealHand();;;
        assertEquals(49, testDealer2.theDeck.size(), "Cards were not dealt");
        assertEquals(0, testDealer2.dealersHand.size(), "Cards have not been dealt");



        ArrayList<Card> testHand2 = testDealer2.dealHand();
        assertEquals(46, testDealer2.theDeck.size(), "Cards have not been dealt");
        assertEquals(0, testDealer2.dealersHand.size(), "Cards have not been dealt");

        assertNotEquals(testHand1, testHand2, "The hands are the same");
    }

    @Test
    void dealerTest3() //Test to make sure dealer's deck doesn't fall below 34 cards
    {
        assertEquals(52, testDealer3.theDeck.size());

        ArrayList<Card> dealtHand = testDealer3.dealHand();

        assertEquals(0, testDealer3.dealersHand.size(), "Dealer's hand size is wrong");

        for (int i = 0; i < 7; ++i) {
            //Make sure dealer is getting new hands
            System.out.println("Dealt hand:" + dealtHand);

            System.out.println("Deck size: " + testDealer3.theDeck.size());
            testDealer3.dealHand();
        }

        System.out.println("Deck size is " + testDealer3.theDeck.size());

        if (testDealer3.theDeck.size() <= 34) {
            fail("Deck did not reset");
        }

    }

    @Test
    void dealerTest4() //Check to make sure after deck is shuffled the top hand are different
    {
        ArrayList<Card> testHand3 = testDealer4.dealHand();
        assertEquals(0, testDealer4.dealersHand.size(), "Cards were not dealt");

        for (int i = 0; i < 6; ++i) {
            testDealer4.dealHand();
        }

        assertEquals(49, testDealer4.theDeck.size(), "The size did not reset to full deck");

        ArrayList<Card> testHand4 = testDealer4.dealHand();

        assertNotEquals(testHand3, testHand4, "Shuffled deck returned the same hands");
    }

    @Test
    void dealerTest5()
    {
        assertEquals(52, testDealer5.theDeck.size());

        //Move through two rounds
        for (int i = 0; i < 5; ++i) {
            testDealer5.dealHand();
        }
        ass
    }
}
