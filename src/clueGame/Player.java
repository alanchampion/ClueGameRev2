package clueGame;

import java.awt.Color;
import java.util.ArrayList;

public abstract class Player {
	protected String name;
	protected int row;
	protected int column;
	protected Color color;
	private ArrayList<Card> unknownCards;
	private ArrayList<Card> hand;
	private ArrayList<Card> knownCards;
	
	public abstract Card disproveSuggestion();
	public abstract void makeAccusation();
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
	
	public void addCard(Card card) {
		hand.add(card);
		addInformation(card);
	}
	
	public void addInformation(Card card) {
		knownCards.add(card);
		unknownCards.remove(card);
	}
	
	public void addUnknownCards(ArrayList<Card> deck) {
		unknownCards.addAll(deck);
	}
}
