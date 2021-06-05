package com.skilldistillery.cards.blackjack;

import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.cards.common.Card;
import com.skilldistillery.cards.common.Hand;

public class BlackjackHand extends Hand{
	
	private List<Card> cards;
	
	
	public BlackjackHand(Card one, Card two) {
		cards = new ArrayList<>();
		cards.add(one);
		cards.add(two);
		
	}

	@Override
	public int getHandValue() {
		int handVal = 0;
		for(Card c : cards) {
			handVal += c.getValue();
		}
		return handVal;
	}

	public boolean isBlackJack() {
		if(getHandValue() == 21){
		return true;
		}
		else {
			return false;
		}
	}
	public boolean isBust() {
		if(getHandValue() >=21) {
			return true;
		}
		else {
		return false;
		}
	}


	

	public List<Card> getCards() {
		return cards;
		
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	
	public void addCard(Card card) {
		cards.add(card);
	}
	
	
}
