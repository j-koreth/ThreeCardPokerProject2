import java.util.ArrayList;
import java.util.Collections;

public class PlayGame {

    /*Returns what the player won or lost from the pair plus wager*/
    public static int returnPpw(Player player) {
        if (player.pairPlusBet == 0) {
            return player.totalWinnings;
        }
        /*If the player's hand doesn't qualify for the ppw, subtract winnings from total*/
        if (ThreeCardLogic.evalHand(player.hand) == 0) {
            player.totalWinnings -= player.pairPlusBet;
        }//end of if
        /*Player's hand qualified for ppw, add winnings to total*/
        else {
            player.totalWinnings += ThreeCardLogic.evalPPWinnings(player.hand, player.pairPlusBet);
        }//end of else

        return player.totalWinnings;
    }//end of returnPpw


    /*Returns what the player won or lost from the wager bet*/
    public static int playerVDealer(Dealer dealer, Player player) {

        /*If the player's hand lost to the dealer, subtract money from total*/
        if (ThreeCardLogic.compareHands(dealer.dealersHand, player.hand) == 1) {
            player.totalWinnings -= (player.anteBet * 2);
            player.totalWinnings = returnPpw(player);
        }//end of if
        /*The player's hand beat the dealer, add winnings to total*/
        else if (ThreeCardLogic.compareHands(dealer.dealersHand, player.hand) == 2) {
            player.totalWinnings += (player.anteBet * 4);
            player.totalWinnings = returnPpw(player);
        }//end of else if
        /*Dealer and player tied*/
        else {
            player.totalWinnings = returnPpw(player);
        }//end of else

        return player.totalWinnings;
    }//end of playerVDealer

    /*Checks to see if the dealer has a queen high or not*/
    public static boolean queenHigh(Dealer qH) {

        ArrayList<Card> queenH = qH.dealersHand;
        Collections.sort(queenH);
        if (queenH.get(2).value < 12) {
            return false;
        }
        return true;
    }

    /*Prints out the different messages for the game*/
    public static String messageString(int compareOne, int compareTwo) {
        if (compareOne == 1 && compareTwo == 1) {
            return "Both players lost! Dealer won! Make a new ante and/or PPW and click Deal to play again!";
        }//end of if
        else if (compareOne == 1 && compareTwo == 2) {
            return "Player one lost! Player two Won! Make a new ante and/or PPW and click Deal to play again!";
        }//end of else if
        else if (compareOne == 1 && compareTwo == 3) {
            return "Player one lost! Player two folded! Make a new ante and/or PPW and click Deal to play again!";
        }//end of else if
        else if (compareOne == 1 && compareTwo == 0) {
            return "Player one lost! Player two and Dealer tied! Make a new ante and/or PPW and click Deal to play again!";
        }//end of else if
        else if (compareOne == 2 && compareTwo == 2) {
            return "Both players won! Dealer lost! Make a new ante and/or PPW and click Deal to play again!";
        }//end of else if
        else if (compareOne == 2 && compareTwo == 1) {
            return "Player one won! Player two lost! Make a new ante and/or PPW and click Deal to play again!";
        }//end of else if
        else if (compareOne == 2 && compareTwo == 0) {
            return "Player one won! Player two and Dealer tied! Make a new ante and/or PPW and click Deal to play again!";
        }//end of else if
        else if (compareOne == 2 && compareTwo == 3) {
            return "Player one won! Player two folded! Make a new ante and/or PPW and click Deal to play again!";
        }//end of else if
        else if (compareOne == 0 && compareTwo == 0) {
            return "Everyone tied! Make a new ante and/or PPW and click Deal to play again!";
        }//end of else if
        else if (compareOne == 0 && compareTwo == 1) {
            return "Player one and Dealer tied! PLayer two lost! Make a new ante and/or PPW and click Deal to play again!";
        }//end of else if
        else if (compareOne == 0 && compareTwo == 2) {
            return "Player one and Dealer tied! Player two won! Make a new ante and/or PPW and click Deal to play again!";
        }//end of else if
        else if (compareOne == 0 && compareTwo == 3) {
            return "Player one and Dealer tied! Player two folded! Make a new ante and/or PPW and click Deal to play again!";
        }//end of else if
        else if (compareOne == 3 && compareTwo == 3) {
            return "Both players folded! Make a new ante and/or PPW and click Deal to play again!";
        }//end of else if
        else if (compareOne == 3 && compareTwo == 0) {
            return "Player one folded! Player two and Dealer tied! Make a new ante and/or PPW and click Deal to play again!";
        }//end of else if
        else if (compareOne == 3 && compareTwo == 1) {
            return "Player one folded! Player two lost! Make a new ante and/or PPW and click Deal to play again!";
        }//end of else if
        else if (compareOne == 3 && compareTwo == 4) {
            return "Player one folded! Dealer does not have queen high! Make a new ante and/or PPW and click Deal to play again!";
        }//end of else if
        else if (compareOne == 4 && compareTwo == 3) {
            return "Player two folded! Dealer does not have queen high! Ante and wager bets will carry over! Make a new PPW if you want! Click Bet or Fold when ready!";
        }//end of else if
        else if (compareOne == 4 && compareTwo == 4) {
            return "Dealer does not have queen high! Ante and wager bets will carry over! Make a new PPW if you want! Click Bet or Fold when ready!";
        }//end of else if
        else {
            return "Player one folded! Player two won! Make a new ante and/or PPW and click Deal to play again!";
        }//end of else
    }//end of messageString
}//end fo PlayGame
