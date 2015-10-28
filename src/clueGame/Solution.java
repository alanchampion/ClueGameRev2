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
}
