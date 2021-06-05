package com.skilldistillery.cards.blackjack.players;



import com.skilldistillery.cards.blackjack.BlackjackHand;
import com.skilldistillery.cards.common.Card;


public class Player {
	
	private BlackjackHand hand;
	protected String name;
	
	
	public Player(String name) {
		
		this.name = name;
		
		
		
	}
	public Player() {}

	

	
	public BlackjackHand hit(BlackjackHand playerHand, Card beingDealt) {
		System.out.println(name+" says 'Hit me!");
		playerHand.addCard(beingDealt);
		setHand(playerHand);
		return playerHand;
		
	}
	
	public void stay() {
		System.out.println(name+ " will stay with: ");
		showHand();
		
	}
	
	public void showHand() {
		
		for(Card c: this.hand.getCards()) {
			
			System.out.println(c);
		}
	}
	
	
	
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setHand(BlackjackHand hand) {
		this.hand = hand;
	}
	
}
