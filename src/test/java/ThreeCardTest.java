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
	void logicTest8() //Checking when the dealer has the highest card they should win
	{
		testDealer8.dealersHand.add(new Card('C', 9));
		testDealer8.dealersHand.add(new Card('H', 9));
		testDealer8.dealersHand.add(new Card('D', 12));

		testPlayer8.hand.add(new Card('S', 7));
		testPlayer8.hand.add(new Card('H', 3));
		testPlayer8.hand.add(new Card('D', 9));

		assertEquals(5, ThreeCardLogic.evalHand(testDealer8.dealersHand), "Dealer's hand is not a pair");

		assertNotEquals(testDealer8.dealersHand, testPlayer8.hand, "Hands are considered equal");

		assertEquals(1, ThreeCardLogic.compareHands(testDealer8.dealersHand, testPlayer8.hand), "The dealer's hand was not considered better");
	}
}
