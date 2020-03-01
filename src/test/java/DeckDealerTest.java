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
    void deckTest1() //Check deck length, and make sure it prints correct
    {
        System.out.println("******************* Deck Testing *******************");
        assertEquals(52, testDeck1.size(), "Deck is the wrong size");
        System.out.println("The deck is: " + testDeck1);

    }//end of deckTest1()

    @Test
    void deckTest2() //Check if after removing half the deck if new deck restores deck to correct amount.
    {
        /*Remove half of the deck*/
        for (int i = 0; i < 26; ++i) {
            testDeck2.remove(0);
        }//end of for

        /*Make sure the deck was cut in half, and create a new deck*/
        assertEquals(26, testDeck2.size(), "Deck was not cut in half");
        testDeck2.newDeck();

        /*Check to make sure new deck is correct size*/
        assertEquals(52, testDeck2.size(), "New deck size is incorrect");
    }//end of deckTest2()

    @Test
    void deckTest3() //Make sure that when new deck is made cards are shuffled.
    {
        /*Save original deck, and create a new deck*/
        ArrayList<Card> testCards1 = new ArrayList<Card>();
        for (int i = 0; i < 52; ++i) {
            testCards1.add(testDeck3.get(i));
        }//end of for
        testDeck3.newDeck();

        /*Save the new deck, and check to make sure the original and new decks are different*/
        ArrayList<Card> testCards2 = new ArrayList<Card>();
        for (int i = 0; i < 52; ++i) {
            testCards2.add(testDeck3.get(i));
        }//end of for

        assertNotEquals(testCards1, testCards2, "Deck was not reshuffled");
    }//end of deckTest3()

    @Test
    void deckTest4() //Check to make sure no card is repeated in the deck
    {
        for (int i = 0; i < 52; ++i) {
            for (int j = 0; j < 52; ++j) {
                /*When i == j  the card will be the same, but not repeated in the deck*/
                if (i == j) {
                    continue;
                }//end of if

                /*Check cards with different index*/
                else {
                    /*There has been the same card found at different indexes*/
                    if (testDeck4.get(i) == testDeck4.get(j)) {
                        fail("Card was repeated in the deck");
                    }//end of if

                }//end of else

            }//end of for j
        } //end of for i
    }//end of deckTest4()

    @Test
    void deckTest5()//Check that when deck is completely cleared new deck works.
    {
        /*Make sure deck size is correct*/
        assertEquals(52, testDeck5.size(), "Deck size is not correct");

        /*Clear deck and make sure it's empty*/
        testDeck5.clear();
        assertEquals(0, testDeck5.size());

        /*Create the new deck and check the size*/
        testDeck5.newDeck();
        assertEquals(52, testDeck5.size(), "New deck size is not correct");

        /*Print out the new deck*/
        for (int i = 0; i < 52; ++i) {
            System.out.println(testDeck5.get(i));
        }//end of for

    }//end of deckTest5()

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
        System.out.println("******************* Dealer Testing *******************");
        assertEquals(52, testDealer1.theDeck.size(), "The size of the deck is correct");
        assertEquals(0, testDealer1.dealersHand.size(), "Dealer's hand is correct, cards have not been dealt");
    }//end of dealerTest1()

    @Test
    void dealerTest2() //check to make sure dealing a hand works correctly
    {
        /*Save first dealt hand, make sure deck size changed by three and dealer's hand was unaffected*/
        ArrayList<Card> testHand1 = testDealer2.dealHand();;;
        assertEquals(49, testDealer2.theDeck.size(), "Cards were not dealt");
        assertEquals(0, testDealer2.dealersHand.size(), "Cards have not been dealt");

        /*Save second dealt hand, make sure deck size changed by three and dealer's hand was unaffected*/
        ArrayList<Card> testHand2 = testDealer2.dealHand();
        assertEquals(46, testDealer2.theDeck.size(), "Cards have not been dealt");
        assertEquals(0, testDealer2.dealersHand.size(), "Cards have not been dealt");

        /*Check that the dealt hands are different*/
        assertNotEquals(testHand1, testHand2, "The hands are the same");
    }//end of dealerTest2()

    @Test
    void dealerTest3() //Test to make sure dealer's deck is reset to original number, and see how the cards are printed out
    {
        /*Make sure deck starts with the correct size*/
        assertEquals(52, testDealer3.theDeck.size());

        /*Save the dealt hands to be printed, make sure dealer's hand is unchanged*/
        ArrayList<Card> dealtHand = testDealer3.dealHand();
        assertEquals(0, testDealer3.dealersHand.size(), "Dealer's hand size is wrong");

        /*Move through two rounds so that the deck reshuffles at the end*/
        for (int i = 0; i < 7; ++i) {
            /*Print out the hand*/
            System.out.println("Dealt hand:" + dealtHand);
            /*Print out the deck size*/
            System.out.println("Deck size: " + testDealer3.theDeck.size());
            testDealer3.dealHand();
        }//end of for

        /*Check the deck size has been reset*/
        System.out.println("Deck size is " + testDealer3.theDeck.size());

        /*Check to make sure deck didn't fall below 34 cards*/
        if (testDealer3.theDeck.size() <= 34) {
            fail("Deck did not reset");
        }//end of if

    }//end of dealerTest3()

    @Test
    void dealerTest4() //Check to make sure after deck is shuffled the hands are different
    {
        /*Save first hand, make sure dealer's hand is unchanged*/
        ArrayList<Card> testHand3 = testDealer4.dealHand();
        assertEquals(0, testDealer4.dealersHand.size(), "Cards were not dealt");

        /*Deal cards until the deck is reshuffled*/
        for (int i = 0; i < 6; ++i) {
            testDealer4.dealHand();
        }//end of for

        /*Make sure deck is reshuffled*/
        assertEquals(49, testDealer4.theDeck.size(), "The size did not reset to full deck");

        /*Save second hand, then check to make sure two hands are different*/
        ArrayList<Card> testHand4 = testDealer4.dealHand();
        assertNotEquals(testHand3, testHand4, "Shuffled deck returned the same hands");

    }//end of dealerTest4()

    @Test
    void dealerTest5() // Check to make sure that through multiple rounds that the deck doesn't drop below 34
    {
        /*Make sure deck starts out with correct number*/
        assertEquals(52, testDealer5.theDeck.size());

        /*Deal the cards out for 10 rounds*/
        for (int i = 0; i < 90; ++i) {
            testDealer5.dealHand();

            /*Make sure deck doesn't fall below 34*/
            if (testDealer5.theDeck.size() < 34) {
                fail("The deck fell below 34");
            }//end of if

        }//end of for loop

    }//end of dealerTest5()
}
