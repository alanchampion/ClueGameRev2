package clueGame;

import java.awt.Color;
import java.util.Random;

public class ComputerPlayer extends Player {

	Random rand;
	
	public ComputerPlayer(String nam, Color col, int x, int y) {
		super(nam, col, x, y);
		rand = new Random();
	}
	
	// Getters
	public String getName() {
		return name;
	}

	public Color getColor() {
		return color;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	
	@Override
	public Card disproveSuggestion() {
		// TODO Auto-generated method stub
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
