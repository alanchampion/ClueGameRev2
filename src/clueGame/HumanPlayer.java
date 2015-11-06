package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class HumanPlayer extends Player {

	/**
	 * Initializer for the human player
	 * 
	 * @param nam The name of the human player
	 * @param col The Color of the human player
	 * @param x Location variable
	 * @param y Location variable
	 */
	public HumanPlayer(String nam, Color col, int x, int y) {
		super(nam, col, x, y);
	}
	
	public Card disproveSuggestion(Card suggestion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Card> makeAccusation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Card> makeSuggestion(Card currentRoom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoardCell makeMove(Set<BoardCell> targets) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Card disproveSuggestion(ArrayList<Card> suggestion) {
		// TODO Auto-generated method stub
		return null;
	}

}
