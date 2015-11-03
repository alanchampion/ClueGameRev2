package clueGame;

import java.util.ArrayList;

public class Solution {
	public ArrayList<Card> solutionCards; 
	
	public Solution(ArrayList<Card> cards)
	{
		solutionCards = new ArrayList<Card>();
		solutionCards.addAll(cards);
	}
	
	public boolean testAccusation(Card card1, Card card2, Card card3) {
		return solutionCards.contains(card1) && solutionCards.contains(card2) && solutionCards.contains(card3);
	}
	
	public Card getPerson() {
		for(Card card : solutionCards)
			if(card.getType() == CardType.PERSON)
				return card;
		return null;
	}
	
	public Card getRoom() {
		for(Card card : solutionCards)
			if(card.getType() == CardType.ROOM)
				return card;
		return null;
	}
	
	public Card getWeapon() {
		for(Card card : solutionCards)
			if(card.getType() == CardType.WEAPON)
				return card;
		return null;
	}
}
