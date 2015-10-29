package clueGame;

import java.awt.Color;
import java.util.Random;

public class ComputerPlayer extends Player {

	Random rand;
	
	public ComputerPlayer(String nam, Color col, int x, int y) {
		super(nam, col, x, y);
		rand = new Random();
	}
	
	@Override
	public Card disproveSuggestion(Card suggestion) {
		if (hand.contains(suggestion)) {
			return suggestion;
		}
		
		return null;
	}

	@Override
	public void makeAccusation() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void makeSuggestion() {
		
	}

	@Override
	public void makeMove() {
		// TODO Auto-generated method stub
		
	}

}
