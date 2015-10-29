package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class ComputerPlayer extends Player {

	Random rand;
	
	public ComputerPlayer(String nam, Color col, int x, int y) {
		super(nam, col, x, y);
		rand = new Random();
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
	public void makeSuggestion() {
		
	}

	@Override
	public void makeMove() {
		// TODO Auto-generated method stub
		if(unknownCards.size() == 3){
			makeAccusation();
		}
	}

}
