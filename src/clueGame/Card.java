package clueGame;

public class Card {

	private String cardName;
	private CardType cardType;
	private char roomName;
	
	public Card(String name, CardType type) {
		cardName = name;
		cardType = type;
		roomName = '?';
	}
	public Card(String name, CardType type, Character room) {
		cardName = name;
		cardType = type;
		roomName = room;
	}
	public CardType getType() {
		return cardType;
	}
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
