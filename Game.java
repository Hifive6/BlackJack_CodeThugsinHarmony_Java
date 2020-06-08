import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        // Scanner for user input
        System.out.println("Welcome to Blackjack!");
        Scanner userInput = new Scanner(System.in);
        System.out.println("Please enter your Name: ");
        String name = userInput.nextLine();

        // Creating the Deck from the Deck class and shuffling them
        Deck playingDeck = new Deck(1, true);

        // Created two players the real player and the dealer
        Player thePlayer = new Player(name);
        Player dealer = new Player("dealer");
        // playingDeck.createFullDeck();
        // playingDeck.shuffle();

        // playerCards will be the cards the player has in their hand
        // Deck playerCards = new Deck();
        // playerMoney holds players cash - we will be lazy and use doubles instead of
        // bigdecimals
        Player1 betting = new Player1();
        double playerMoney = betting.updateBalanceMoney();
        ;

        // dealerCards will be the cards the dealer has in their hand

        // Just in case it doesnt work may need to tweek this method
        // Deck dealerCards = new Deck();

        // Play the game while the player has money
        // Game loop
        while (playerMoney > 0) {
            // Take Bet
            // System.out.println("You have $" + playerMoney + ", how much would you like to
            // bet?");

            // double playerBet = userInput.nextDouble();
            double playerBet = betting.getBet();

            // betting.getBalanceMoney();
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

            // // Dealer gets two cards
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
                    // add the next card in the deck if the player hits 1
                    thePlayer.addCardHand(playingDeck.dealNextCard());
                    // print the players hand
                    // thePlayer.printPlayersHand(true);
                    // Bust if they go over 21
                    if (thePlayer.getHandSum() > 21) {
                        System.out.println("Bust. Your current cards are valued at: " + thePlayer.getHandSum());
                        betting.userWins = false;
                        betting.updateBalanceMoney();
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
            dealer.printPlayersHand(true);
            // See if dealer has more points than player
            if ((dealer.getHandSum() > thePlayer.getHandSum()) && endRound == false) {
                System.out.println("Dealer beats you " + dealer.getHandSum() + " to " + thePlayer.getHandSum());
                betting.userWins = false;
                betting.updateBalanceMoney();
                endRound = true;
            }
            // Dealer hits at 16 stands at 17
            while ((dealer.getHandSum() < 17) && endRound == false) {
                dealer.addCardHand(playingDeck.dealNextCard());
                dealer.printPlayersHand(true);
            }
            // Display value of dealer
            System.out.println("Dealers hand value: " + dealer.getHandSum());
            // Determine if dealer busted
            if ((dealer.getHandSum() > 21) && endRound == false) {
                System.out.println("Dealer Busts. You win!");
                betting.userWins = true;
                betting.updateBalanceMoney();
                endRound = true;
            }
            // Determine if push
            if ((thePlayer.getHandSum() == dealer.getHandSum()) && endRound == false) {
                System.out.println("Push.");
                endRound = true;
            }
            // Determine if player wins
            if ((thePlayer.getHandSum() > dealer.getHandSum()) && endRound == false) {
                betting.userWins = true;
                betting.updateBalanceMoney();
                System.out.println("You win the hand.");

                endRound = true;
            } else if (endRound == false) // dealer wins
            {
                betting.userWins = false;
                betting.updateBalanceMoney();
                System.out.println("Dealer wins.");

            }

            // End of hand - put cards back in deck
            thePlayer.emptyHand();
            dealer.emptyHand();
            System.out.println("End of Hand.");

        }

        // Close Scanner
        userInput.close();

        // Close Scanner

    }

}