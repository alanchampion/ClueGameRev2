package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Set;

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
	public abstract ArrayList<Card> makeSuggestion(Card currentRoom);
	public abstract BoardCell makeMove(Set<BoardCell> targets);
	
	/**
	 * Default initializer for player. 
	 * 
	 * @param nam Name for player. 
	 * @param col Color for player. 
	 * @param x Location variable
	 * @param y Location variable
	 */
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
	
	/**
	 * Add card to hand and update information accordingly.
	 * This adds the card to the actual hand of the player. Should only be used when dealing. 
	 * 
	 * @param card The card to add to the hand. 
	 */
	public void addCard(Card card) {
		hand.add(card);
		addInformation(card);
	}
	
	/**
	 * Add card to known list and remove from unknowns cards.
	 * This is used to add the information but not the actual card. 
	 * Note that this is useful for primarily the computer player. The human player keeps track of their own cards.  
	 * 
	 * @param card The card to get information from. 
	 */
	public void addInformation(Card card) {
		knownCards.add(card);
		unknownCards.remove(card);
	}
	
	/**
	 * Adds the full deck of cards to the list of unknown cards.
	 * This adds all given cards to unknown cards. This should be used at set up. 
	 * Using this method after set up is dangerous because overlap in unknownCards and knownCards causes errors. 
	 * 
	 * @param deck The cards (usually the whole deck) to add to the unknown cards. 
	 */
	public void addUnknownCards(ArrayList<Card> deck) {
		unknownCards.addAll(deck);
	}
	
	/**
	 * Moves the location of the character
	 * 
	 * @param x New location x
	 * @param y New location y
	 */
	public void moveLocation(int x, int y) {
		row = x;
		column = y;
	}
	
	/**
	 * Returns players current location as a board cell. 
	 * 
	 * @param doorDirection The door direction. 
	 * @param name The name of the board cell with the first letter being the initial.
	 * @return A new board cell that has the players location. 
	 */
	public BoardCell getCell(char doorDirection, String name) {
		return new BoardCell(row, column, name, doorDirection);
	}
}
