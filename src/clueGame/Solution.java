package clueGame;

import java.util.ArrayList;

public class Solution {
	public ArrayList<Card> solutionCards; 
	
	/**
	 * Initializer for solution
	 * 
	 * @param cards The array of three cards to add to solution. 
	 */
	public Solution(ArrayList<Card> cards)
	{
		solutionCards = new ArrayList<Card>();
		addArray(cards);
	}
	/**
	 * A different initializer for solution
	 * 
	 * @param card1 Card to add to solution. 
	 * @param card2 Card to add to solution. 
	 * @param card3 Card to add to solution. 
	 */
	public Solution(Card card1, Card card2, Card card3)
	{
		solutionCards = new ArrayList<Card>();
		solutionCards.add(card1);
		solutionCards.add(card2);
		solutionCards.add(card3);
	}
	
	/**
	 * This is the check for adding an array list of cards to solution. 
	 * It will print out any errors (Not enough cards. No weapon card. Etc.) to the console. 
	 * If given too many cards it will just use the first card of a type and ignore all other cards of that type. 
	 * If given too few cards it will add all applicable cards but not be completely full. This will cause errors!  
	 * 
	 * @param cards The array list of cards to add.
	 */
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
	
	/**
	 * This will test an accusation given the three accusing cards.
	 * 
	 * @param card1 Accusing card
	 * @param card2 Accusing card
	 * @param card3 Accusing card
	 * @return
	 */
	public boolean testAccusation(Card card1, Card card2, Card card3) {
		return solutionCards.contains(card1) && solutionCards.contains(card2) && solutionCards.contains(card3);
	}
	/**
	 * Gets the person card. 
	 * 
	 * @return Person card. 
	 */
	public Card getPerson() {
		for(Card card : solutionCards)
			if(card.getType() == CardType.PERSON)
				return card;
		return null;
	}
	
	/**
	 * Gets the room card. 
	 * 
	 * @return Room card. 
	 */
	public Card getRoom() {
		for(Card card : solutionCards)
			if(card.getType() == CardType.ROOM)
				return card;
		return null;
	}
	
	/**
	 * Gets the weapon card. 
	 * 
	 * @return Weapon card. 
	 */
	public Card getWeapon() {
		for(Card card : solutionCards)
			if(card.getType() == CardType.WEAPON)
				return card;
		return null;
	}
}
