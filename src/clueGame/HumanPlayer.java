package clueGame;

import java.awt.Color;

public class HumanPlayer extends Player {
	
	String name;
	Color color;
	int row, column;

	public HumanPlayer(String nam, Color col, int x, int y) {
		name = nam;
		color = col;
		row = x;
		column = y;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void makeMove() {
		// TODO Auto-generated method stub
		
	}

}
