import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ThreeCardLogic {
    public static int evalHand(ArrayList<Card> hand){

        Collections.sort(hand, new SortCard());

        //Checking if it's a flush
        boolean flush = hand.get(0).suit == hand.get(1).suit && hand.get(1).suit == hand.get(2).suit;

        boolean straight = hand.get(0).value+1 ==  hand.get(1).value &&  hand.get(1).value+1 ==  hand.get(2).value;

        boolean threeOfAKind = hand.get(0).value ==  hand.get(1).value &&  hand.get(1).value ==  hand.get(2).value;

        boolean pair = hand.get(0).value ==  hand.get(1).value ||  hand.get(1).value ==  hand.get(2).value || hand.get(0).value ==  hand.get(2).value;

        if(flush && straight){
            return 1;
        }

        if(threeOfAKind){
            return 2;
        }

        if(straight){
            return 3;
        }

        if(flush){
            return 4;
        }

        if(pair){
            return 5;
        }

        return 0;
    }

    public static int evalPPWinnings(ArrayList<Card> hand, int bet){
        int winnings = evalHand(hand);

        if (winnings == 1) {
            bet *= 40;
        }
        else if (winnings == 2) {
            bet *= 30;
        }
        else if (winnings == 3) {
            bet *= 6;
        }
        else if (winnings == 4) {
            bet *= 3;
        }
        else if (winnings == 5) {
            bet *= 1;
        }
        else {
            bet = 0;
        }
        return bet;
    }

    public static int compareHands(ArrayList<Card> dealer, ArrayList<Card> player) {
        return 0;
    }

    static class SortCard implements Comparator<Card> {
        @Override
        public int compare(Card a, Card b) {
            return a.value - b.value;
        }
    }
}
