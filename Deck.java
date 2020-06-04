import java.util.Random;

//Create several decks of cards.... maybe 8 full decks since that's considered standard @ casinos
public class Deck {

    //array where top card is in first index
    private Card[] myCards;
    //also could use an ArrayList or LinkedList instead of an array

    //number of cards remaining in deck
    private int numCards;

    public Deck(){
        this(1, false);
    }

    /*constructor for card Deck
    numDeck = number of decks used (probably 8)
    shuffle = self-explanatory*/
    public Deck(int numDeck, boolean shuffle){

        

        this.numCards = numDeck * 52;
        this.myCards = new Card[this.numCards];
        int c = 0; //reps the index location of the cards in the array

        for (int d = 0; d < numDeck; d++){
            //d = deck, s == suit, n = number
            for (int s = 0; s < 4; s++){

                for (int n = 1; n <= 13; n++){
                    //add a new card to deck
                    this.myCards[c] = new Card(Suit.values()[s], n);
                    c++;
                }
            }
        }
        if (shuffle){
            this.shuffle();
        }

    }
    
    public void shuffle() {

        Random generator = new Random();

        Card temp; //placeholder to use when swapping
        int j;
        for (int i= 0; i < this.numCards; i++){
            j = generator.nextInt(this.numCards);

            temp = this.myCards[i];
            this.myCards[i] = this.myCards[j];
            this.myCards[j] = temp;
        }
    }
    
    /*deals the top card from the deck (index 0) & returns that card*/
    public Card dealNextCard(){
        //takes top card 
        Card top = this.myCards[0];
        //shifts cards to move the top card off
        for (int c = 1; c < this.numCards; c++){
            this.myCards[c-1] = this.myCards[c];
        }
        this.myCards[this.numCards-1] = null;
        //decrement the number of cards in deck
        this.numCards--;
        return top;
    }

    //prints the top cards in the deck (DOES NOT REMOVE THEM)
    public void printDeck(int numToPrint){
        for (int c = 0; c < numToPrint; c++){
            System.out.printf("% 3d/%d %s\n", c+1, this.numCards, this.myCards[c].toString());
        }
        System.out.printf("\t[%d other]\n", this.numCards-numToPrint);
    }
    
}