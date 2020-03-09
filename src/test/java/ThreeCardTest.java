import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ThreeCardTest {

	Dealer testDeck1 = new Dealer();
	Player testPlayer = new Player();

	Dealer testDealer8 = new Dealer();
	Player testPlayer8 = new Player();
	Dealer testDealer9 = new Dealer();
	Player testPlayer9 = new Player();
	Dealer testDealer10 = new Dealer();
	Player testPlayer10 = new Player();
	Dealer testDealer11 = new Dealer();
	Player testPlayer11 = new Player();
	Dealer testDealer12 = new Dealer();
	Player testPlayer12 = new Player();
	Dealer testDealer13 = new Dealer();
	Player testPlayer13 = new Player();
	Dealer testDealer14 = new Dealer();
	Player testPlayer14 = new Player();
	Dealer testDealer15 = new Dealer();
	Player testPlayer15 = new Player();
	Dealer testDealer16 = new Dealer();
	Player testPlayer16 = new Player();
	Dealer testDealer17 = new Dealer();
	Player testPlayer17 = new Player();
	Dealer testDealer18 = new Dealer();
	Player testPlayer18 = new Player();
	Dealer testDealer19 = new Dealer();
	Player testPlayer19 = new Player();


	@Test
	void logicTest1(){
		System.out.println("******************* Three Card Logic Testing *******************");
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

	@Test
	void logicTest2() //This test is used to test if evalHand and EvalPPWinnings for a bad hand
	{
		ArrayList<Card> test2 = new ArrayList<Card>();
		test2.add(new Card('C', 3));
		test2.add(new Card('H', 5));
		test2.add(new Card('D', 2));

		assertEquals(0, ThreeCardLogic.evalHand(test2), "Hand was not evaluated correctly");

		int result = ThreeCardLogic.evalPPWinnings(test2, 10);

		assertEquals(0, result, "Winnings was returned wrong");
	}

	@Test
	void logicTest3() //This test is used to test if evalHand and EvalPPWinnings for a straight flush
	{
		ArrayList<Card> test3 = new ArrayList<Card>();
		test3.add(new Card('C', 9));
		test3.add(new Card('C', 7));
		test3.add(new Card('C', 8));

		assertEquals(1, ThreeCardLogic.evalHand(test3), "Hand was not evaluated correctly");
		assertNotEquals(3, ThreeCardLogic.evalHand(test3), "Hand is also considered a straight");
		assertNotEquals(4, ThreeCardLogic.evalHand(test3), "Hand is also considered a flush");

		int result = ThreeCardLogic.evalPPWinnings(test3, 10);

		assertEquals(400, result, "Winnings was returned wrong");
	}

	@Test
	void logicTest4() //This test is used to test if evalHand and EvalPPWinnings for a three of a kind
	{
		ArrayList<Card> test4 = new ArrayList<Card>();
		test4.add(new Card('C', 3));
		test4.add(new Card('S', 3));
		test4.add(new Card('D', 3));

		assertEquals(2, ThreeCardLogic.evalHand(test4), "Hand was not evaluated correctly");
		assertNotEquals(5, ThreeCardLogic.evalHand(test4), "Hand is also considered pair");

		int result = ThreeCardLogic.evalPPWinnings(test4, 10);

		assertEquals(300, result, "Winnings was returned wrong");
	}

	@Test
	void logicTest5() //This test is used to test if evalHand and EvalPPWinnings for a straight
	{
		ArrayList<Card> test5 = new ArrayList<Card>();
		test5.add(new Card('D', 9));
		test5.add(new Card('S', 7));
		test5.add(new Card('H', 8));

		assertEquals(3, ThreeCardLogic.evalHand(test5), "Hand was not evaluated correctly");
		assertNotEquals(1, ThreeCardLogic.evalHand(test5), "Hand is also a straight flush");

		int result = ThreeCardLogic.evalPPWinnings(test5, 10);

		assertEquals(60, result, "Winnings was returned wrong");
	}

	@Test
	void logicTest6() //This test is used to test if evalHand and EvalPPWinnings for a flush
	{
		ArrayList<Card> test6 = new ArrayList<Card>();
		test6.add(new Card('C', 9));
		test6.add(new Card('C', 14));
		test6.add(new Card('C', 11));

		assertEquals(4, ThreeCardLogic.evalHand(test6), "Hand was not evaluated correctly");
		assertNotEquals(1, ThreeCardLogic.evalHand(test6), "Hand is also a straight flush");

		int result = ThreeCardLogic.evalPPWinnings(test6, 10);

		assertEquals(30, result, "Winnings was returned wrong");
	}

	@Test
	void logicTest7() //This test is used to test if evalHand and EvalPPWinnings for a pair
	{
		ArrayList<Card> test7 = new ArrayList<Card>();
		test7.add(new Card('C', 9));
		test7.add(new Card('D', 8));
		test7.add(new Card('S', 8));

		assertEquals(5, ThreeCardLogic.evalHand(test7), "Hand was not evaluated correctly");
		assertNotEquals(2, ThreeCardLogic.evalHand(test7), "Hand was not considered three of a kind");

		int result = ThreeCardLogic.evalPPWinnings(test7, 10);

		assertEquals(10, result, "Winnings was returned wrong");

		test7.clear();
		test7.add(new Card('C', 9));
		test7.add(new Card('C', 13));
		test7.add(new Card('S', 8));
		assertNotEquals(5, ThreeCardLogic.evalHand(test7), "hand was considered a pair");

	}

	@Test
	void logicTest8() //Checking winning hands vs garbage hands
	{
		/*Test for pair which would compares 5 to 0*/
		testDealer8.dealersHand.add(new Card('C', 9));
		testDealer8.dealersHand.add(new Card('H', 9));
		testDealer8.dealersHand.add(new Card('D', 12));

		testPlayer8.hand.add(new Card('S', 7));
		testPlayer8.hand.add(new Card('H', 3));
		testPlayer8.hand.add(new Card('D', 9));

		assertEquals(1, ThreeCardLogic.compareHands(testDealer8.dealersHand, testPlayer8.hand), "The dealer's hand was not better");

		testPlayer8.hand.clear();
		testDealer8.dealersHand.clear();

		/*Test for straight flush which would compare 1 to 0*/
		testDealer8.dealersHand.add(new Card('H', 10));
		testDealer8.dealersHand.add(new Card('C', 3));
		testDealer8.dealersHand.add(new Card('S', 8));

		testPlayer8.hand.add(new Card('D', 10));
		testPlayer8.hand.add(new Card('D', 8));
		testPlayer8.hand.add(new Card('D', 9));

		assertEquals(2, ThreeCardLogic.compareHands(testDealer8.dealersHand, testPlayer8.hand), "The player's hand was not better");
	}

	@Test
	void logicTest9() // Check for winning dealer's hands against loosing player's hands
	{
		/*Compare three of a kind to a pair, 2 vs 5*/
		testDealer9.dealersHand.add(new Card('D', 3));
		testDealer9.dealersHand.add(new Card('S', 3));
		testDealer9.dealersHand.add(new Card('H', 3));

		testPlayer9.hand.add(new Card('C', 12));
		testPlayer9.hand.add(new Card('H', 12));
		testPlayer9.hand.add(new Card('S', 9));

		assertEquals(1, ThreeCardLogic.compareHands(testDealer9.dealersHand, testPlayer9.hand), "The dealer's hand was not better");

		testPlayer9.hand.clear();
		testDealer9.dealersHand.clear();

		/*Compare straight flush to a three of a kind, 1 vs 2*/
		testDealer9.dealersHand.add(new Card('S', 13));
		testDealer9.dealersHand.add(new Card('S', 14));
		testDealer9.dealersHand.add(new Card('S', 12));

		testPlayer9.hand.add(new Card('C', 3));
		testPlayer9.hand.add(new Card('H', 3));
		testPlayer9.hand.add(new Card('D', 3));

		assertEquals(1, ThreeCardLogic.compareHands(testDealer9.dealersHand, testPlayer9.hand), "The dealer's hand was not better");
	}

	@Test
	void logicTest10()
	{
		/*Compare straight to a straight flush, 3 vs 1*/
		testDealer10.dealersHand.add(new Card('D', 10));
		testDealer10.dealersHand.add(new Card('S', 9));
		testDealer10.dealersHand.add(new Card('C', 11));

		testPlayer10.hand.add(new Card('H', 3));
		testPlayer10.hand.add(new Card('H', 4));
		testPlayer10.hand.add(new Card('H', 2));

		assertEquals(2, ThreeCardLogic.compareHands(testDealer10.dealersHand, testPlayer10.hand), "The player's hand was not better");

		testPlayer9.hand.clear();
		testDealer9.dealersHand.clear();

		/*Compare flush to a straight, 4 vs 3*/
		testDealer10.dealersHand.add(new Card('D', 7));
		testDealer10.dealersHand.add(new Card('D', 3));
		testDealer10.dealersHand.add(new Card('D', 14));

		testPlayer10.hand.add(new Card('D', 6));
		testPlayer10.hand.add(new Card('C', 5));
		testPlayer10.hand.add(new Card('H', 4));

		assertEquals(2, ThreeCardLogic.compareHands(testDealer10.dealersHand, testPlayer10.hand), "The player's hand was not better");
	}

	@Test
	void logicTest11() // Check possibilities for garbage hand (evalHand = 0)
	{
		/*Check for when the dealer and player have exactly equal hands*/
		testDealer11.dealersHand.add(new Card('S', 7));
		testDealer11.dealersHand.add(new Card('H', 5));
		testDealer11.dealersHand.add(new Card('C', 11));

		testPlayer11.hand.add(new Card('H', 7));
		testPlayer11.hand.add(new Card('D', 11));
		testPlayer11.hand.add(new Card('C', 5));

		assertEquals(0, ThreeCardLogic.compareHands(testDealer11.dealersHand, testPlayer11.hand), "The hands are not equal");

		testDealer11.dealersHand.clear();
		testPlayer11.hand.clear();

		/*Check for when the dealer has a better hand than the player*/
		testDealer11.dealersHand.add(new Card('D', 12));
		testDealer11.dealersHand.add(new Card('C', 4));
		testDealer11.dealersHand.add(new Card('S', 8));

		testPlayer11.hand.add(new Card('D', 5));
		testPlayer11.hand.add(new Card('S', 9));
		testPlayer11.hand.add(new Card('H', 2));

		assertEquals(1, ThreeCardLogic.compareHands(testDealer11.dealersHand, testPlayer11.hand), "The dealer's hand is not better");

		testDealer11.dealersHand.clear();
		testPlayer11.hand.clear();

		/*Check for when the player has a better hand than the dealer*/
		testDealer11.dealersHand.add(new Card('D', 13));
		testDealer11.dealersHand.add(new Card('H', 4));
		testDealer11.dealersHand.add(new Card('S', 7));

		testPlayer11.hand.add(new Card('C', 2));
		testPlayer11.hand.add(new Card('S', 9));
		testPlayer11.hand.add(new Card('D', 13));

		assertEquals(2, ThreeCardLogic.compareHands(testDealer11.dealersHand, testPlayer11.hand), "The player's hand is not better");

	}

	@Test
	void logicTest12() // Check possibilities for straight flush (evalHand = 1)
	{
		/*Check for when the dealer and player have exactly equal hands*/
		testDealer12.dealersHand.add(new Card('H', 13));
		testDealer12.dealersHand.add(new Card('H', 11));
		testDealer12.dealersHand.add(new Card('H', 12));

		testPlayer12.hand.add(new Card('D', 11));
		testPlayer12.hand.add(new Card('D', 13));
		testPlayer12.hand.add(new Card('D', 12));

		assertEquals(0, ThreeCardLogic.compareHands(testDealer12.dealersHand, testPlayer12.hand), "The hands are not equal");

		testDealer12.dealersHand.clear();
		testPlayer12.hand.clear();

		/*Check for when the dealer has a better hand than the player*/
		testDealer12.dealersHand.add(new Card('S', 10));
		testDealer12.dealersHand.add(new Card('S', 8));
		testDealer12.dealersHand.add(new Card('S', 9));

		testPlayer12.hand.add(new Card('H', 6));
		testPlayer12.hand.add(new Card('H', 8));
		testPlayer12.hand.add(new Card('H', 7));

		assertEquals(1, ThreeCardLogic.compareHands(testDealer12.dealersHand, testPlayer12.hand), "The dealer's hand is not better");

		testDealer12.dealersHand.clear();
		testPlayer12.hand.clear();

		/*Check for when the player has a better hand than the dealer*/
		testDealer12.dealersHand.add(new Card('C', 6));
		testDealer12.dealersHand.add(new Card('C', 5));
		testDealer12.dealersHand.add(new Card('C', 4));

		testPlayer12.hand.add(new Card('D', 5));
		testPlayer12.hand.add(new Card('D', 6));
		testPlayer12.hand.add(new Card('D', 7));

		assertEquals(2, ThreeCardLogic.compareHands(testDealer12.dealersHand, testPlayer12.hand), "The player's hand is not better");
	}

	@Test
	void logicTest13() // Check possibilities for three of a kind (evalHand = 2)
	{
		/*There is not a situation where the player and the dealer can have the same hand*/
		/*Check for when the dealer has a better hand than the player*/
		testDealer13.dealersHand.add(new Card('D', 14));
		testDealer13.dealersHand.add(new Card('H', 14));
		testDealer13.dealersHand.add(new Card('S', 14));

		testPlayer13.hand.add(new Card('H', 11));
		testPlayer13.hand.add(new Card('C', 11));
		testPlayer13.hand.add(new Card('D', 11));

		assertEquals(1, ThreeCardLogic.compareHands(testDealer13.dealersHand, testPlayer13.hand), "The dealer's hand is not better");

		testDealer13.dealersHand.clear();
		testPlayer13.hand.clear();

		/*Check for when the player has a better hand than the dealer*/
		testDealer13.dealersHand.add(new Card('D', 5));
		testDealer13.dealersHand.add(new Card('C', 5));
		testDealer13.dealersHand.add(new Card('H', 5));

		testPlayer13.hand.add(new Card('S', 7));
		testPlayer13.hand.add(new Card('H', 7));
		testPlayer13.hand.add(new Card('D', 7));

		assertEquals(2, ThreeCardLogic.compareHands(testDealer13.dealersHand, testPlayer13.hand), "The player's hand is not better");
	}

	@Test
	void logicTest14() // Check possibilities for straight (evalHand = 3)
	{
		/*Check for when the dealer and player have exactly equal hands*/
		testDealer14.dealersHand.add(new Card('S', 12));
		testDealer14.dealersHand.add(new Card('C', 10));
		testDealer14.dealersHand.add(new Card('D', 11));

		testPlayer14.hand.add(new Card('H', 12));
		testPlayer14.hand.add(new Card('D', 11));
		testPlayer14.hand.add(new Card('C', 10));

		assertEquals(0, ThreeCardLogic.compareHands(testDealer14.dealersHand, testPlayer14.hand), "The hands are not equal");

		testDealer14.dealersHand.clear();
		testPlayer14.hand.clear();

		/*Check for when the dealer has a better hand than the player*/
		testDealer14.dealersHand.add(new Card('C', 10));
		testDealer14.dealersHand.add(new Card('D', 8));
		testDealer14.dealersHand.add(new Card('H', 9));

		testPlayer14.hand.add(new Card('C', 5));
		testPlayer14.hand.add(new Card('S', 6));
		testPlayer14.hand.add(new Card('D', 4));

		assertEquals(1, ThreeCardLogic.compareHands(testDealer14.dealersHand, testPlayer14.hand), "The dealer's hand is not better");

		testDealer14.dealersHand.clear();
		testPlayer14.hand.clear();

		/*Check for when the player has a better hand than the dealer*/
		testDealer14.dealersHand.add(new Card('D', 4));
		testDealer14.dealersHand.add(new Card('H', 2));
		testDealer14.dealersHand.add(new Card('S', 3));

		testPlayer14.hand.add(new Card('C', 6));
		testPlayer14.hand.add(new Card('D', 4));
		testPlayer14.hand.add(new Card('S', 5));

		assertEquals(2, ThreeCardLogic.compareHands(testDealer14.dealersHand, testPlayer14.hand), "The player's hand is not better");
	}

	@Test
	void logicTest15() // Check possibilities for flush (evalHand = 4)
	{
		/*Check for when the dealer and player have exactly equal hands*/
		testDealer15.dealersHand.add(new Card('D', 13));
		testDealer15.dealersHand.add(new Card('D', 4));
		testDealer15.dealersHand.add(new Card('D', 7));

		testPlayer15.hand.add(new Card('C', 4));
		testPlayer15.hand.add(new Card('C', 13));
		testPlayer15.hand.add(new Card('C', 7));

		assertEquals(0, ThreeCardLogic.compareHands(testDealer15.dealersHand, testPlayer15.hand), "The hands are not equal");

		testDealer15.dealersHand.clear();
		testPlayer15.hand.clear();

		/*Check for when the dealer has a better hand than the player*/
		testDealer15.dealersHand.add(new Card('C', 2));
		testDealer15.dealersHand.add(new Card('C', 12));
		testDealer15.dealersHand.add(new Card('C', 9));

		testPlayer15.hand.add(new Card('H', 10));
		testPlayer15.hand.add(new Card('H', 3));
		testPlayer15.hand.add(new Card('H', 9));

		assertEquals(1, ThreeCardLogic.compareHands(testDealer15.dealersHand, testPlayer15.hand), "The dealer's hand is not better");

		testDealer15.dealersHand.clear();
		testPlayer15.hand.clear();

		/*Check for when the player has a better hand than the dealer*/
		testDealer15.dealersHand.add(new Card('C', 6));
		testDealer15.dealersHand.add(new Card('C', 4));
		testDealer15.dealersHand.add(new Card('C', 8));

		testPlayer15.hand.add(new Card('S', 9));
		testPlayer15.hand.add(new Card('S', 7));
		testPlayer15.hand.add(new Card('S', 3));

		assertEquals(2, ThreeCardLogic.compareHands(testDealer15.dealersHand, testPlayer15.hand), "The player's hand is not better");
	}

	@Test
	void logicTest16() // Check possibilities for pair (evalHand = 5)
	{
		/*Check for when the dealer and player have exactly equal hands*/
		testDealer16.dealersHand.add(new Card('H', 8));
		testDealer16.dealersHand.add(new Card('C', 6));
		testDealer16.dealersHand.add(new Card('S', 8));

		testPlayer16.hand.add(new Card('C', 8));
		testPlayer16.hand.add(new Card('D', 8));
		testPlayer16.hand.add(new Card('H', 6));

		assertEquals(0, ThreeCardLogic.compareHands(testDealer16.dealersHand, testPlayer16.hand), "The hands are not equal");

		testDealer16.dealersHand.clear();
		testPlayer16.hand.clear();

		/*Check for when the dealer has a better hand than the player*/
		testDealer16.dealersHand.add(new Card('H', 11));
		testDealer16.dealersHand.add(new Card('S', 7));
		testDealer16.dealersHand.add(new Card('D', 11));

		testPlayer16.hand.add(new Card('S', 7));
		testPlayer16.hand.add(new Card('D', 3));
		testPlayer16.hand.add(new Card('C', 7));

		assertEquals(1, ThreeCardLogic.compareHands(testDealer16.dealersHand, testPlayer16.hand), "The dealer's hand is not better");

		testDealer16.dealersHand.clear();
		testPlayer16.hand.clear();

		/*Check for when the player has a better hand than the dealer*/
		testDealer16.dealersHand.add(new Card('D', 6));
		testDealer16.dealersHand.add(new Card('C', 14));
		testDealer16.dealersHand.add(new Card('H', 6));

		testPlayer16.hand.add(new Card('S', 9));
		testPlayer16.hand.add(new Card('H', 9));
		testPlayer16.hand.add(new Card('C', 10));

		assertEquals(2, ThreeCardLogic.compareHands(testDealer16.dealersHand, testPlayer16.hand), "The player's hand is not better");
	}

	@Test
	void logicTest17() //Check for certain odd cases with pairs
	{
		/*When there's card from the same suit, make sure they're not considered flush*/
		testDealer17.dealersHand.add(new Card('S', 6));
		testDealer17.dealersHand.add(new Card('S', 12));
		testDealer17.dealersHand.add(new Card('H', 6));

		assertNotEquals(4, ThreeCardLogic.evalHand(testDealer17.dealersHand), "Make sure dealer's hand isn't flush");

		testPlayer17.hand.add(new Card('C', 8));
		testPlayer17.hand.add(new Card('D', 3));
		testPlayer17.hand.add(new Card('C', 3));

		assertNotEquals(4, ThreeCardLogic.evalHand(testPlayer17.hand), "Make sure dealer's hand isn't flush");
		assertEquals(1, ThreeCardLogic.compareHands(testDealer17.dealersHand, testPlayer17.hand), "Dealer has the better hand");

		testDealer17.dealersHand.clear();
		testPlayer17.hand.clear();

		/*When the high card for both is equal*/
		testDealer17.dealersHand.add(new Card('H', 4));
		testDealer17.dealersHand.add(new Card('S', 4));
		testDealer17.dealersHand.add(new Card('D', 14));

		testPlayer17.hand.add(new Card('C', 8));
		testPlayer17.hand.add(new Card('D', 14));
		testPlayer17.hand.add(new Card('S', 8));

		assertEquals(2, ThreeCardLogic.compareHands(testDealer17.dealersHand, testPlayer17.hand), "Player has the better hand");
	}

	@Test
	void logicTest18() // Other possible hands test
	{
		/*Case where a card number in every suit are dealt*/
		testDealer18.dealersHand.add(new Card('S', 9));
		testDealer18.dealersHand.add(new Card('C', 9));
		testDealer18.dealersHand.add(new Card('H', 9));

		testPlayer18.hand.add(new Card('D', 9));
		testPlayer18.hand.add(new Card('H', 13));
		testPlayer18.hand.add(new Card('D', 13));

		/*Dealer = 2, player = 5*/
		assertEquals(1, ThreeCardLogic.compareHands(testDealer18.dealersHand, testPlayer18.hand), "Dealer didn't win");

		testDealer18.dealersHand.clear();
		testPlayer18.hand.clear();

	}

	@Test
	void logicTest19() //Check to make sure basic game play works
	{
		testPlayer19.hand = testDealer19.dealHand();
		testDealer19.dealersHand = testDealer19.dealHand();

		assertNotEquals(testPlayer19.hand,testDealer19.dealersHand );
	}

	@Test
	void logicTest20() //Check to make sure basic game play works
	{
		for(int x = 0; x < 21; x++){
			testPlayer19.hand = testDealer19.dealHand();
			testDealer19.dealersHand = testDealer19.dealHand();

			assertNotEquals(testPlayer19.hand,testDealer19.dealersHand );
		}
	}
}
