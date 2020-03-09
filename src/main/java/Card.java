import java.util.Arrays;

public class Card implements Comparable<Card>{
    //Clubs: C, Diamonds: D, Spades: S, Hearts: H
    char suit;

    //2-14, Ace: 14, King: 13, Queen: 12, Jack: 11, Ten: 10....
    int value;

    public Card(char suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    char getSuit(){
        return suit;
    }

    String getValue(){
        if(value <= 10){
            return "" + value;
        }
        else if(value == 11){
            return "J";
        }
        else if(value == 12){
            return "Q";
        }
        else if(value == 13){
            return "K";
        }
        else{
            return "A";
        }
    }

    //Returns an array of suits used in game
    static char[] availableSuits(){
        char[] suits = {'C', 'D', 'S', 'H'};
        return suits;
    }

    //Returns an array of values used in game
    static int[] availableValues(){
        int[] values = {2,3,4,5,6,7,8,9,10,11,12,13,14};
        return values;
    }

    //Provides a String Formatted in "Suit + Value" format for Printing
    public String toString(){
        return "" + suit + value;
    }

    //Lets the use of java sort algorithms by ranking cards by their value
    @Override
    public int compareTo(Card o) {
        return this.value - o.value;
    }
}
