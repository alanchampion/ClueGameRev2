package clueGame;

public class Card {

	private String cardName;
	private CardType cardType;
	
	public Card(String name, CardType type) {
		cardName = name;
		cardType = type;
	}
	public CardType getType() {
		return cardType;
	}
	public boolean equals(Card otherCard) {
		return otherCard.getType() == getType();
	}
	
	@Override
	public String toString() {
		return "Card [cardName=" + cardName + ", cardType=" + cardType + "]";
	}
}
