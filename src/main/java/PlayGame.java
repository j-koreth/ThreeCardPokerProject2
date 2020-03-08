public class PlayGame {

    //Returns what the player won or lost from the pair plus wager
    public static int returnPpw(Player player) {
        if (ThreeCardLogic.evalHand(player.hand) == 0) {
            player.totalWinnings -= player.pairPlusBet;
        }
        else {
            player.totalWinnings += ThreeCardLogic.evalPPWinnings(player.hand, player.pairPlusBet);
        }
        return player.totalWinnings;
    }

    //Returns what the player won or lost from the wager bet
    public static int playerVDealer(Dealer dealer, Player player) {
        if (ThreeCardLogic.compareHands(dealer.dealersHand, player.hand) == 1) {
            player.totalWinnings -= (player.anteBet * 2);
            player.totalWinnings = returnPpw(player);
        }
        else if (ThreeCardLogic.compareHands(dealer.dealersHand, player.hand) == 2) {
            player.totalWinnings += (player.anteBet * 4);
            player.totalWinnings = returnPpw(player);
        }
        else {
            player.totalWinnings = returnPpw(player);
        }
        return player.totalWinnings;
    }

    public static String messageString(int compareOne, int compareTwo) {
        if (compareOne == 1 && compareTwo == 1) {
            return "Both players lost! Dealer won! Make a new ante and/or PPW and click Deal to play again!";
        }
        else if (compareOne == 1 && compareTwo == 2) {
            return "Player one lost! Player two Won! Make a new ante and/or PPW and click Deal to play again!";
        }
        else if (compareOne == 1 && compareTwo == 3) {
            return "Player one lost! Player two folded! Make a new ante and/or PPW and click Deal to play again!";
        }
        else if (compareOne == 1 && compareTwo == 0) {
            return "Player one lost! Player two and Dealer tied! Make a new ante and/or PPW and click Deal to play again!";
        }
        else if (compareOne == 2 && compareTwo == 2) {
            return "Both players won! Dealer lost! Make a new ante and/or PPW and click Deal to play again!";
        }
        else if (compareOne == 2 && compareTwo == 1) {
            return "Player one won! Player two lost! Make a new ante and/or PPW and click Deal to play again!";
        }
        else if (compareOne == 2 && compareTwo == 0) {
            return "Player one won! Player two and Dealer tied! Make a new ante and/or PPW and click Deal to play again!";
        }
        else if (compareOne == 2 && compareTwo == 3) {
            return "Player one won! Player two folded! Make a new ante and/or PPW and click Deal to play again!";
        }
        else if (compareOne == 0 && compareTwo == 0) {
            return "Everyone tied! Make a new ante and/or PPW and click Deal to play again!";
        }
        else if (compareOne == 0 && compareTwo == 1) {
            return "Player one and Dealer tied! PLayer two lost! Make a new ante and/or PPW and click Deal to play again!";
        }
        else if (compareOne == 0 && compareTwo == 2) {
            return "Player one and Dealer tied! Player two won! Make a new ante and/or PPW and click Deal to play again!";
        }
        else if (compareOne == 0 && compareTwo == 3) {
            return "Player one and Dealer tied! Player two folded! Make a new ante and/or PPW and click Deal to play again!";
        }
        else if (compareOne == 3 && compareTwo == 3) {
            return "Both players folded! Make a new ante and/or PPW and click Deal to play again!";
        }
        else if (compareOne == 3 && compareTwo == 0) {
            return "Player one folded! Player two and Dealer tied! Make a new ante and/or PPW and click Deal to play again!";
        }
        else if (compareOne == 3 && compareTwo == 1) {
            return "Player one folded! Player two lost! Make a new ante and/or PPW and click Deal to play again!";
        }
        else {
            return "Player one folded! Player two won! Make a new ante and/or PPW and click Deal to play again!";
        }
    }
}
