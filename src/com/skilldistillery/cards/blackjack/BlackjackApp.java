package com.skilldistillery.cards.blackjack;

import java.util.Scanner;

import com.skilldistillery.cards.blackjack.players.Dealer;
import com.skilldistillery.cards.blackjack.players.Player;
import com.skilldistillery.cards.common.Card;
import com.skilldistillery.cards.common.Deck;

public class BlackjackApp {
	public static void main(String[] args) {
		BlackjackApp bja = new BlackjackApp();
		bja.run();
	}

	private void run() {
		int roundCounter = 0;
		int playerWins = 0;
		int dealerWins = 0;
		int draw = 0;
		boolean newGame = true;
		int winner;
		
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to Blackjack!!!");
		System.out.println();
		
		System.out.println("Enter the player's name: ");
		String name = input.nextLine();

		while (newGame) {
			roundCounter++;
			winner = gameRound(input, name);
			switch (winner) {
			case 1:
				playerWins++;
				break;
			case 2:
				dealerWins++;
				break;
			case 0:
				draw++;
				break;
			default:
				System.err.println("Error");
				break;
			}
			newGame = newRoundInput(input);
		}
		
		finalResults(roundCounter, playerWins, dealerWins, draw);
		input.close();
	}

	private int gameRound(Scanner input, String name) {

		// Make a Deck
		Deck deck = new Deck();
		// Shuffle Deck
		System.out.println("Shuffling Deck");
		System.out.println();
		deck.shuffle();
		System.out.println("3...");
		System.out.println("2...");
		System.out.println("1...");

		Player player = new Player(name);
		Dealer dealer = new Dealer();

		//Dealer is removing cards from the deck to deal (Only Dealer touches deck!)
		System.out.println("Dealer is dealing cards...");
		Card cardOne = dealer.removeCardFromDeck(deck);
		Card cardTwo = dealer.removeCardFromDeck(deck);
		Card cardThree = dealer.removeCardFromDeck(deck);
		Card cardFour = dealer.removeCardFromDeck(deck);

		// Deal Hand to Player
		BlackjackHand playerHand = new BlackjackHand(cardOne, cardThree);
		player.setHand(playerHand);
		System.out.println(player.getName() + "'s hand:");
		player.showHand();
		System.out.println();

		// Deal Hand to Dealer

		BlackjackHand dealerHand = new BlackjackHand(cardTwo, cardFour);
		dealer.setDealerHand(dealerHand);
		System.out.println(dealer.getName() + "'s hand: ");
		dealer.showHiddenHand();

		boolean continueGame = true;

		// Player turn:
		while (true) {
			playerMenu();

			System.out.println(player.getName() + "'s turn to make a move.");
			System.out.println("Enter the number of the move you would like to make: ");
			int selection = input.nextInt();
			input.nextLine();
			// Hit
			if (selection == 1) {
				Card beingDealt = dealer.removeCardFromDeck(deck);
				playerHand = player.hit(playerHand, beingDealt);
//				playerHand.addCard(beingDealt);
//				player.setCards(playerHand);
				showCardsDealerHidden(player, dealer);

				if (playerHand.isBust()) {
					System.out.println("Over 21! Bust! Dealer wins.");
					continueGame = false;
					break;
				} else if (playerHand.isBlackJack()) {
					System.out.println("Black Jack! You have won!!!");
					continueGame = false;
					break;
				}
			}
			// Stay
			else if (selection == 2) {
				player.stay();
				break;
			}
			// Invalid input
			else {
				System.out.println("Invalid input.");
			}
		}
		// Dealer turn:
		while (continueGame == true) {
			
			//Check if dealt a bust
			if (dealerHand.isBust()) {
				System.out.println("Dealer over 21! Bust! Player wins!");
				continueGame = false;
				
			} //Check if dealt black jack 
			else if (dealerHand.isBlackJack()) {
				System.out.println("Black Jack! Dealer Wins!");
				continueGame = false;
			} else {
				int handValue = dealerHand.getHandValue();
				//Under 17 must hit
				if (handValue < 17) {
					System.out.println("Dealer's hand value is under 17. Must hit.");
					Card beingDealt = dealer.removeCardFromDeck(deck);
					dealerHand = dealer.hit(dealerHand, beingDealt);
//					dealerHand.addCard(beingDealt);
//					dealer.setDealerHand(dealerHand);
					showAll(player, dealer);

				} 
				//Over 17 must stay 
				else {
					System.out.println("Dealer's hand value is over or equal to 17.");
					dealer.stay();
					continueGame = false;
				}
			}

		}
		// Show round results
		System.out.println();
		System.out.println("____Round Results____");
		System.out.println("Show all cards: ");
		showAll(player, dealer);

		// Determine winner
		int results = winner(playerHand, dealerHand, player.getName());
		System.out.println("Deck Remaining: " + deck.checkDeckSize());
		return results;

	}

	public void playerMenu() {

		System.out.println();
		System.out.println("Moves Menu");
		System.out.println("1. Hit");
		System.out.println("2. Stay");
		System.out.println();

	}

	public void showCardsDealerHidden(Player player, Dealer dealer) {
		System.out.println(player.getName() + "'s Cards: ");
		player.showHand();
		System.out.println();
		System.out.println("Dealer's Cards: ");
		dealer.showHiddenHand();
	}

	public void showAll(Player player, Dealer dealer) {
		System.out.println(player.getName() + "'s Cards: ");
		player.showHand();
		System.out.println();
		System.out.println("Dealer's Cards: ");
		dealer.showHand();
		System.out.println();
	}

	public int winner(BlackjackHand playerHand, BlackjackHand dealerHand, String player) {
		int results = 0;
		int playerScore = playerHand.getHandValue();
		int dealerScore = dealerHand.getHandValue();

		System.out.println("Player hand value: " + playerScore);
		System.out.println("Dealer hand value: " + dealerScore);
		if (playerScore > 21) {
			System.out.println(player + " has bust. Dealer wins!!!");
			results = 2;
			return results;
		} else if (playerScore == 21) {
			System.out.println(player + " has won with Black Jack!!!");
			results = 1;
			return results;
		} else if (dealerScore > 21) {
			System.out.println("Dealer has bust. " + player + " wins!!!");
			results = 1;
			return results;
		} else if (dealerScore == 21) {
			System.out.println("Dealer has won with Black Jack!!!");
			results = 2;
			return results;
		} else if (playerScore > dealerScore) {
			System.out.println(player + " wins!!!");
			results = 1;
			return results;
		} else if (playerScore < dealerScore) {
			System.out.println("Dealer Wins!!!");
			results = 2;
			return results;
		} else {
			System.out.println("Its a draw.");
			results = 0;
			return results;
		}
	}
	
	public void finalResults(int roundCounter, int playerWins, int dealerWins, int draw) {
		System.out.println("___Final Results____");
		System.out.println("Number of Rounds: " + roundCounter);
		System.out.println("Player Wins: " + playerWins);
		System.out.println("Dealer Wins: " + dealerWins);
		System.out.println("Draws: " + draw);
		System.out.println();
		if(playerWins>dealerWins) {
			System.out.println("Final Winner: Player");
		}
		else if(dealerWins>playerWins) {
			System.out.println("Final Winner: Dealer");
		}else {
			System.out.println("Final Winner: It's a tie");
		}
		System.out.println("Thanks for playing Black Jack!");
		System.out.println("Goodbye...");
	}
	
	public boolean newRoundInput(Scanner input) {
		boolean newGame = true;
		System.out.println("Play another round? Enter Yes or No: ");
		String newRoundInput = input.nextLine();
		if(newRoundInput.equalsIgnoreCase("no")) {
			newGame = false;
			return newGame;
		}
		else if(newRoundInput.equalsIgnoreCase("yes")) {
			newGame = true;
			return newGame;
		}
		else {
			return newRoundInput(input);
		}
	}
}
