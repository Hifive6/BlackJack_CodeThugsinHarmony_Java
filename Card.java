public class Card{
    //using Suit enum and assigning a value
    private Suit mySuit;
    //ace = 1, numbers axiomatic, J-K = 11-13
    private int cardNumber;

    //constructor
    public Card(Suit aSuit, int aNumber){
        this.mySuit = aSuit;

        if (aNumber >=1 && aNumber <= 13){
        this.cardNumber = aNumber;
    }   else {
        System.err.println(aNumber + " is not a valid Card number");
        System.exit(1);
    }
}

    //returns the number of the card
    public int getNumber(){
        return cardNumber;
    }

    public String toString(){
        String numString = "Error";
        switch (this.cardNumber) {
            case 1:
                numString = "Ace";
                break;
            case 2:
                numString = "Two";
                break;
            case 3:
                numString = "Three";
                break;
            case 4:
                numString = "Four";
                break;
            case 5:
                numString = "Five";
                break;
            case 6:
                numString = "Six";
                break;
            case 7:
                numString = "Seven";
                break;
            case 8:
                numString = "Eight";
                break;
            case 9:
                numString = "Nine";
                break;
            case 10:
                numString = "Ten";
                break;
            case 11:
                numString = "Jack";
                break;
            case 12:
                numString = "Queen";
                break;
            case 13:
                numString = "King";
                break;
            default:
                break;
        }
        return numString + " of " + mySuit.toString();
    }
}