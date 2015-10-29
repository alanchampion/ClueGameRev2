package clueGame;

import java.awt.Color;
import java.util.ArrayList;

public abstract class Player {
	protected String name;
	protected int row;
	protected int column;
	protected Color color;
	protected ArrayList<Card> unknownCards;
	protected ArrayList<Card> hand;
	protected ArrayList<Card> knownCards;
	
	public abstract Card disproveSuggestion(ArrayList<Card> suggestion);
	public abstract ArrayList<Card> makeAccusation();
	public abstract void makeSuggestion();
	public abstract void makeMove();
	
	public Player(String nam, Color col, int x, int y) {
		unknownCards = new ArrayList<Card>();
		knownCards = new ArrayList<Card>();
		hand  = new ArrayList<Card>();
		name = nam;
		color = col;
		row = x;
		column = y;
	}
	
	// Getters
	public String getName() {
		return name;
	}
	public int getRow() {
		return row;
	}
	public int getColumn() {
		return column;
	}
	public Color getColor() {
		return color;
	}
	public ArrayList<Card> getHand() {
		return hand;
	}
	public int getHandSize() {
		return hand.size();
	}
	
	// Add card to hand and update information accordingly
	public void addCard(Card card) {
		hand.add(card);
		addInformation(card);
	}
	
	// Add card to known list and remove from unknowns cards
	public void addInformation(Card card) {
		knownCards.add(card);
		unknownCards.remove(card);
	}
	
	// Adds the full deck of cards to the list of unknown cards
	public void addUnknownCards(ArrayList<Card> deck) {
		unknownCards.addAll(deck);
	}
}
