import java.util.ArrayList;
import java.util.Collections;

public class ThreeCardLogic {
    public static int evalHand(ArrayList<Card> hand){
        ArrayList<Card> handDup = hand;
        Collections.sort(handDup);

        //Checking if it's a flush
        boolean flush = handDup.get(0).suit == handDup.get(1).suit && handDup.get(1).suit == handDup.get(2).suit;

        //Checking if it's a straight
        boolean straight = handDup.get(0).value+1 ==  handDup.get(1).value &&  handDup.get(1).value+1 ==  handDup.get(2).value;

        //Checking if it's a three of a Kind
        boolean threeOfAKind = handDup.get(0).value ==  handDup.get(1).value &&  handDup.get(1).value ==  handDup.get(2).value;

        //Checking if it's a pair
        boolean pair = handDup.get(0).value ==  handDup.get(1).value ||  handDup.get(1).value ==  handDup.get(2).value || handDup.get(0).value ==  handDup.get(2).value;

        //1: Straight Flush
        if(flush && straight){
            return 1;
        }

        //2: Three of a Kind
        if(threeOfAKind){
            return 2;
        }

        //3: Straight
        if(straight){
            return 3;
        }

        //4: Flush
        if(flush){
            return 4;
        }

        //5: Pair
        if(pair){
            return 5;
        }

        //Return the highest card
        return 0;
    }

    public static int evalPPWinnings(ArrayList<Card> hand, int bet){
        int winnings = evalHand(hand);
        //Straight flush
        if (winnings == 1) {
            bet *= 40;
        }//end of if
        //Three of a kind
        else if (winnings == 2) {
            bet *= 30;
        }//end of else if
        //Straight
        else if (winnings == 3) {
            bet *= 6;
        }//end of else if
        //Flush
        else if (winnings == 4) {
            bet *= 3;
        }//end of else if
        /*Pair*/
        else if (winnings == 5) {
            bet *= 1;
        }//end of else if
        /*Garbage hand*/
        else {
            bet = 0;
        }//end of else
        return bet;
    }//end of evalPPWinnings

    public static int compareHands(ArrayList<Card> dealer, ArrayList<Card> player) {
        ArrayList<Card> dealerDup = dealer;
        ArrayList<Card> playerDup = player;
        Collections.sort(dealerDup);
        Collections.sort(playerDup);

        if((evalHand(dealerDup) < evalHand(playerDup)) && (evalHand(dealerDup)  != 0)){
            return 1;
        }
        else if((evalHand(dealerDup) > evalHand(playerDup)) && (evalHand(playerDup) != 0)){
            return 2;
        }
        else if ((evalHand(dealerDup)  != 0) && (evalHand(playerDup) == 0)) {
            return 1;
        }
        else if ((evalHand(dealerDup)  == 0) && (evalHand(playerDup) != 0)) {
            return 2;
        }
        else{
            //Highest Card
            if(evalHand(dealerDup) == 0){
                for(int x = 2; x >= 0; x--){
                    if(dealerDup.get(x).compareTo(playerDup.get(x)) < 0){
                        return 2;
                    }
                    else if(dealerDup.get(x).compareTo(playerDup.get(x)) > 0){
                        return 1;
                    }
                }
                return 0;
            }
            //Straight Flush
            else if(evalHand(dealerDup) == 1){
                if(dealerDup.get(2).value < playerDup.get(2).value){
                    return 2;
                }
                else if(dealerDup.get(2).value > playerDup.get(2).value){
                    return 1;
                }
                else{
                    return 0;
                }
            }
            //Three of a Kind
            /*There is no situation where this should return 0 since there are only 4 suits*/
            else if(evalHand(dealerDup) == 2){
                if(dealerDup.get(2).value < playerDup.get(2).value){
                    return 2;
                }
                else {
                    return 1;
                }
            }
            //Straight
            else if(evalHand(dealerDup) == 3){
                if(dealerDup.get(2).value < playerDup.get(2).value){
                    return 2;
                }
                else if(dealerDup.get(2).value > playerDup.get(2).value){
                    return 1;
                }
                else{
                    return 0;
                }
            }
            //Flush
            else if(evalHand(dealerDup) == 4){
                for(int x = 2; x >= 0; x--){
                    if(dealerDup.get(x).compareTo(playerDup.get(x)) < 0){
                        return 2;
                    }
                    else if(dealerDup.get(x).compareTo(playerDup.get(x)) > 0){
                        return 1;
                    }
                }
                return 0;
            }
            //Pair
            else if(evalHand(dealerDup) == 5){
                //Get Dealer Pair
                ArrayList<Card> dealerPair = new ArrayList<>();
                getPair(dealerPair, dealerDup);

                ArrayList<Card> playerPair = new ArrayList<>();
                getPair(playerPair, playerDup);

                if(dealerPair.get(0).compareTo(playerPair.get(0)) < 0){
                    return 2;
                }
                else if(dealerPair.get(0).compareTo(playerPair.get(0)) > 0){
                    return 1;
                }
                else{
                    return 0;
                }
            }
        }
        return 0;
    }

    public static void getPair(ArrayList<Card> pair, ArrayList<Card> hand){
        if(hand.get(0).value ==  hand.get(1).value){
            pair.add(hand.get(0));
            pair.add(hand.get(1));
        }
        else if(hand.get(1).value ==  hand.get(2).value){
            pair.add(hand.get(1));
            pair.add(hand.get(2));
        }
        else{
            pair.add(hand.get(0));
            pair.add(hand.get(2));
        }
    }
}
