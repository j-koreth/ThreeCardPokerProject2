import java.util.ArrayList;
import java.util.Collections;

public class ThreeCardLogic {
    public static int evalHand(ArrayList<Card> hand){
        ArrayList ch = new ArrayList();
        for(Card card : hand){
            ch.add(hand.toString());
        }
        Collections.sort(ch);
        System.out.println(hand);
        return 0;
    }

    public static int evalPPWinnings(ArrayList<Card> hand, int bet){
        return 0;
    }

    public static int compareHands(ArrayList<Card> dealer, ArrayList<Card> player){
        return 0;
    }
}
