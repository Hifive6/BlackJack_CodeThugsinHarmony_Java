import java.util.Scanner;

public class Game {

    public static void main(String[] args) {

        System.out.println("Welcome to Blackjack!");
        //Creating the Deck from the Deck class and shuffling them
        Deck playingDeck = new Deck(1, true);

        // Created two players the real player and the dealer
        Player thePlayer = new Player("Joe");
        Player dealer = new Player("dealer");
        // playingDeck.createFullDeck();
        // playingDeck.shuffle();
        

        // playerCards will be the cards the player has in their hand
        // Deck playerCards = new Deck();
        // playerMoney holds players cash - we will be lazy and use doubles instead of
        // bigdecimals
        double playerMoney = 200.0;
        // dealerCards will be the cards the dealer has in their hand

        // Just in case it doesnt work may need to tweek this method
        // Deck dealerCards = new Deck();

        // Scanner for user input
        Scanner userInput = new Scanner(System.in);

        // Play the game while the player has money
        // Game loop
        while (playerMoney > 0) {
            // Take Bet
            System.out.println("You have $" + playerMoney + ", how much would you like to bet?");
            double playerBet = userInput.nextDouble();
            boolean endRound = false;
            if (playerBet > playerMoney) {
                // Break if they bet too much
                System.out.println("You cannot bet more than you have.");
                break;
            }

            System.out.println("Dealing...");
            // Player gets two cards
            thePlayer.addCardHand(playingDeck.dealNextCard());
            thePlayer.addCardHand(playingDeck.dealNextCard());

            // Dealer gets two cards
            dealer.addCardHand(playingDeck.dealNextCard());
            dealer.addCardHand(playingDeck.dealNextCard());

            // While loop for drawing new cards
            while (true) {
                // Display player cards
                thePlayer.printPlayersHand(true);

                // Display Value
                System.out.println("Your hand is currently valued at: " + thePlayer.getHandSum());

                // Display dealer cards
                dealer.printPlayersHand(false);

                // What do they want to do
                System.out.println("Would you like to (1)Hit or (2)Stand");
                int response = userInput.nextInt();
                // They hit
                if (response == 1) {
                    //add the next card in the deck if the player hits 1
                    thePlayer.addCardHand(playingDeck.dealNextCard());
                    //print the players hand
                    thePlayer.printPlayersHand(true);
                    // Bust if they go over 21
                    if (thePlayer.getHandSum() > 21) {
                        System.out.println("Bust. Currently valued at: " + thePlayer.getHandSum());
                        playerMoney -= playerBet;
                        endRound = true;
                        break;
                    }
                }

                // Stand
                if (response == 2) {
                    break;
                }

            }

            // Reveal Dealer Cards
            dealer.printPlayersHand(false);
            // See if dealer has more points than player
            if ((thePlayer.getHandSum() > thePlayer.getHandSum()) && endRound == false) {
                System.out.println("Dealer beats you " + thePlayer.getHandSum() + " to " + dealer.getHandSum());
                playerMoney -= playerBet;
                endRound = true;
            }
            // Dealer hits at 16 stands at 17
            while ((thePlayer.getHandSum() < 17) && endRound == false) {
                dealer.addCardHand(playingDeck.dealNextCard());
                dealer.printPlayersHand(false);
            }
            // Display value of dealer
            System.out.println("Dealers hand value: " + dealer.getHandSum());
            // Determine if dealer busted
            if ((dealer.getHandSum() > 21) && endRound == false) {
                System.out.println("Dealer Busts. You win!");
                playerMoney += playerBet;
                endRound = true;
            }
            // Determine if push
            if ((dealer.getHandSum() == dealer.getHandSum()) && endRound == false) {
                System.out.println("Push.");
                endRound = true;
            }
            // Determine if player wins
            if ((thePlayer.getHandSum() > dealer.getHandSum()) && endRound == false) {
                System.out.println("You win the hand.");
                playerMoney += playerBet;
                endRound = true;
            } else if (endRound == false) // dealer wins
            {
                System.out.println("Dealer wins.");
                playerMoney -= playerBet;
            }

            // End of hand - put cards back in deck
            thePlayer.emptyHand();
            dealer.emptyHand();
            // playingDeck.moveAllToDeck(playingDeck);
            // dealerCards.moveAllToDeck(playingDeck);
            System.out.println("End of Hand.");

        }
        // Game is over
        System.out.println("Game over! You lost all your money. :(");

        // Close Scanner
        userInput.close();

    }

}