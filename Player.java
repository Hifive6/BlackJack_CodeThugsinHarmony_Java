/**
 * Player
 */
    public class Player {
        private String name;
    
        private int numberOfCards;

        private Card hand = new Card(null, numberOfCards);;
    
        //Constructor for Player class
        public Player(String name) {
            this.name = name;
    
            this.emptyHand();
        }
    
        //Reset the players's hand to have no Cards
        public void emptyHand() {
            for (int c = 0; c < 10; c++) {
                this.hand = null;
    
                this.numberOfCards = 0;
            }
        }
    
        //Add a card to the player's hand
        public boolean addCardHand(Card aCard) {
    
            // print error if we have the max allowed card in hand
            if (this.numberOfCards == 10) {
                System.err.println("");
                System.exit(1);
            }
    
            // Adds a Card to players hand
            this.hand = aCard;
            this.numberOfCards++;
    
            return (this.getHandSum() <= 21);
    
        }
    
        public int getHandSum() {
            int sumOfHand = 0;
            int cardNumber;
            int numberOfAces = 0;
    
            // calculate each card's contribution to the sum of the hand
            for (int i = 0; i < this.numberOfCards; i++) {
                cardNumber = this.hand.getNumber();
    
                if (cardNumber == 1) {//Ace
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
    
        // prints the cards in the player's hand.
        public void printPlayersHand(boolean showFirstCard) {
            System.out.printf("%s's cards: \n", this.name);
    
            for (int i = 0; i < this.numberOfCards; i++) {
    
                if (i == 0 && !showFirstCard) {
                    System.out.println(" [hidden] ");
                } else {
                    System.out.printf("  %s\n", this.hand.toString());
                }
            }
        }
    }
