package com.skilldistillery.cards.blackjack.players;

import com.skilldistillery.cards.blackjack.BlackjackHand;
import com.skilldistillery.cards.common.Card;
import com.skilldistillery.cards.common.Deck;

public class Dealer extends Player{
	BlackjackHand dealerHand; 
	
	public Dealer() {
		super.name = "Dealer";
				
		
	}
	
	
	public void takeTurn(){
		//TODO
	}
	
	
	
	public String getName() {
		return name;
	}
	
	public Card removeCardFromDeck(Deck deck) {
		return deck.dealCard();
	}
	
	public void showHiddenHand() {
		System.out.println(dealerHand.getCards().get(0));
		System.out.println("Card two is hidden.");
	}
	public void showHand() {
		for(Card c: dealerHand.getCards()) {
			
			System.out.println(c);
		}
	}
	
	public void setDealerHand(BlackjackHand hand) {
		this.dealerHand = hand;
	}
	
	public BlackjackHand hit(BlackjackHand dealerHand, Card beingDealt) {
		System.out.println(name+" says 'Hit me!");
		dealerHand.addCard(beingDealt);
		setDealerHand(dealerHand);
		return dealerHand;
		
	}
	
}
