package clueGame;

import java.awt.Color;
import java.util.ArrayList;

public abstract class Player {
	private String playerName;
	private int row;
	private int column;
	private Color color;
	private ArrayList<Card> hand;
	private ArrayList<Card> knownCards;
	
	public Card disproveSuggestion() {
		return null;
	}
	
	public void addCard(Card card) {
		hand.add(card);
	}
	
	public void addInformation(Card card) {
		knownCards.add(card);
	}
}
