import java.util.ArrayList;
import java.util.List;

/**
 * Player
 */
    public class Player {
        private String name;
    
        private int numberOfCards;

        private List<Card> hand;
    
        //Constructor for Player class
        public Player(String name) {
            this.name = name;
            this.hand = new ArrayList<Card>();
    
            this.emptyHand();
        }
    
        //Reset the players's hand to have no Cards
        public void emptyHand() {
            for (int c = 0; c < 10; c++) {
                hand.clear();
                this.numberOfCards = 0;
            }
        }
    

    // Add a card to the player's hand
    public void addCardHand(Card aCard) {

        // print error if we have the max allowed card in hand
        if (this.numberOfCards == 10) {
            System.err.println("");
            System.exit(1);
        }

        // Adds a Card to players hand
        hand.add(aCard);
        // this.hand = aCard;
        this.numberOfCards++;

        // return (this.getHandSum() <= 21);

    }

    public int getHandSum() {
        int sumOfHand = 0;
        int cardNumber;
        int numberOfAces = 0;

        // calculate each card's contribution to the sum of the hand
        for (int i = 0; i < this.numberOfCards; i++) {
            cardNumber = hand.get(i).getNumber();

            if (cardNumber == 1) {// Ace
                numberOfAces++;
                sumOfHand += 11;
            } else if (cardNumber > 10) { // Value of face Cards
                sumOfHand += 10;
            } else {
                sumOfHand += cardNumber;
            }
        }

        // if aces and hand > 21 set all aces to value 1
        while (sumOfHand > 21 && numberOfAces > 0) {
            sumOfHand -= 10;
            numberOfAces--;
        }
        return sumOfHand;
    }

    public void printPlayersHand(boolean showFirstCard) {
        System.out.printf("%s's cards: \n", this.name);
        for (int i = 0; i < numberOfCards; i++) {
            if (i == 0 && !showFirstCard) {
                System.out.println(" [hidden] ");
            } else {
                System.out.printf("  %s\n", hand.get(i).toString());
            }
        }
    }

}
