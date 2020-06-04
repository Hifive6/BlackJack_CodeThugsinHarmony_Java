/**
 * Player
 */
    public class PlayerClass {
        private String name;
    
        private Card[] hand = new Card[10];
    
        private int numberOfCards;
    
        //Constructor for Player class
        public Player(String name) {
            this.name = name;
    
            this.emptyHand();
        }
    
        //Reset the players's hand to have no Cards
        public void emptyHand() {
            for (int c = 0; c < 10; c++) {
                this.hand[c] = name;
    
                this.emptyHand();
            }
        }
    
        //Add a card to the player's hand
        public boolean addCardHand(Card card) {
    
            // print error if we have the max allowed card in hand
            if (this.numberOfCards == 10) {
                System.err.println("");
                System.exit(1);
            }
    
            // Adds a Card to players hand
            this.hand[this.numberOfCards] = card;
            this.numberOfCards++;
    
            return (this.getHandSum() <= 21);
    
        }
    
        public int getHandSum() {
            int sumOfHand = 0;
            int cardNumber;
            int numberOfAces = 0;
    
            // User for handling the value of the Aces
            for (int i = 0; i < this.numberOfCards; i++) {
                cardNumber = this.hand[i].getNumber();
    
                if (cardNumber == 1) {
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
            System.out.printf("%s's cards:\n", this.name);
    
            for (int i = 0; i < this.numberOfCards; i++) {
    
                if (i == 0 && !showFirstCard) {
                    System.outprintln(" [hidden] ");
                } else {
                    System.outprintf(" %s\n", this.hand[c].toString());
                }
            }
        }
    }
