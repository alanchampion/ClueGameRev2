package clueGame;

import java.util.ArrayList;

public class Solution {
	public ArrayList<Card> solutionCards; 
	
	public Solution(ArrayList<Card> cards)
	{
		solutionCards = new ArrayList<Card>();
		addArray(cards);
	}
	
	public Solution(Card card1, Card card2, Card card3)
	{
		solutionCards = new ArrayList<Card>();
		solutionCards.add(card1);
		solutionCards.add(card2);
		solutionCards.add(card3);
	}
	
	private void addArray(ArrayList<Card> cards) {
		if(cards.size() < 3)
			System.out.println("Note that there are not enough cards in solution.");
		if(cards.size() > 3)
			System.out.println("Note that too many cards where given to solution.");
		
		boolean hasPerson = false;
		boolean hasWeapon = false;
		boolean hasRoom   = false;
		for(Card card : cards){
			if(card.getType() == CardType.PERSON && !hasPerson) {
				solutionCards.add(card);
				hasPerson = true;
			}
			if(card.getType() == CardType.WEAPON && !hasWeapon) {
				solutionCards.add(card);
				hasWeapon = true;
			}
			if(card.getType() == CardType.ROOM && !hasRoom) {
				solutionCards.add(card);
				hasRoom = true;
			}
		}
		
		if(solutionCards.size() < 3){
			if(!hasPerson)
				System.out.println("Missing a person card.");
			if(!hasWeapon)
				System.out.println("Missing a weapon card.");
			if(!hasRoom)
				System.out.println("Missing a room card.");
		}
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
