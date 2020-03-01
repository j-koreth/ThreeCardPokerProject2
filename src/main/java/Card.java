public class Card {
    //Clubs: C, Diamonds: D, Spades: S, Hearts: H
    char suit;

    //2-14, Ace: 14, King: 13, Queen: 12, Jack: 11, Ten: 10....
    int value;

    public Card(char suit, int value) {
        this.suit = suit;
        this.value = value;
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

    public String toString(){
        return "" + suit + value;
    }
}
