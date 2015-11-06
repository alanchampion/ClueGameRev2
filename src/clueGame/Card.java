package clueGame;

public class Card {

	private String cardName;
	private CardType cardType;
	private char roomName;
	
	/**
	 * Initializer for card. 
	 * If the card is a room, use the other initializer. 
	 * 
	 * @param name The name of the card. Could be a weapon or person. 
	 * @param type The type of the card. Either PERSON or WEAPON.
	 */
	public Card(String name, CardType type) {
		cardName = name;
		cardType = type;
		roomName = '?';
	}
	/**
	 * Another initializer for card. 
	 * Use for room cards.
	 * 
	 * @param name The name of the card. 
	 * @param type The type of the card.
	 * @param room The initial of the room. Is only a char. 
	 */
	public Card(String name, CardType type, Character room) {
		cardName = name;
		cardType = type;
		roomName = room;
	}
	
	public CardType getType() {
		return cardType;
	}
	
	/**
	 * Checks if a card is equal in type. 
	 * Does not check if card is the same card! Only checks against type!
	 * 
	 * @param otherCard The card to check against
	 * @return True if same type. False otherwise.
	 */
	public boolean equals(Card otherCard) {
		return otherCard.getType() == getType();
	}
	
	public char getRoomInitial() {
		return roomName;
	}
	
	@Override
	public String toString() {
		return "Card [cardName=" + cardName + ", cardType=" + cardType + "]";
	}
}
