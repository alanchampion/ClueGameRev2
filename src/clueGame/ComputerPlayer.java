package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class ComputerPlayer extends Player {

	Random rand;
	char lastRoom;
	
	public ComputerPlayer(String nam, Color col, int x, int y) {
		super(nam, col, x, y);
		rand = new Random();
		lastRoom = '?';
	}
	
	@Override
	public Card disproveSuggestion(ArrayList<Card> suggestion) {
		ArrayList<Card> tempCards = new ArrayList<Card>();
		for(Card card : suggestion) {
			if(hand.contains(card)){
				tempCards.add(card);
			}
		}
		if(tempCards.isEmpty()){
			return null;
		}
		else{
			return tempCards.get(rand.nextInt(tempCards.size()));
		}
	}
	
	//This method will work because the computer players should only ever make an accusation
	//if there are three cards in unknownCards.
	//The Board will handle the checking of the accusation. 
	@Override
	public ArrayList<Card> makeAccusation() {
		return unknownCards;
	}

	@Override
	public ArrayList<Card> makeSuggestion(Card currentRoom) {
		//TODO finish this method.
		ArrayList<Card> suggestion = new ArrayList<Card>();
		Card tempCard;
		boolean hasPerson = false, hasWeapon = false;
		suggestion.add(currentRoom);
		while(suggestion.size() != 3)
		{
			tempCard = unknownCards.get(rand.nextInt(unknownCards.size()));
			if(!hasPerson && tempCard.getType() == CardType.PERSON)
			{
				suggestion.add(tempCard);
				hasPerson = true;
			}
			if(!hasWeapon && tempCard.getType() == CardType.WEAPON)
			{
				suggestion.add(tempCard);
				hasWeapon = true;
			}
		}
		
		return suggestion;
	}

	@Override
	public void makeMove(Set<BoardCell> targets) {
		
		if(unknownCards.size() == 3){
			makeAccusation();
		}
	}
}
