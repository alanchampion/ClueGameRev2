package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class ComputerPlayer extends Player {

	Random rand;
	char lastRoom;
	
	/**
	 * Initializer for the computer player
	 * 
	 * @param nam The name of the computer player
	 * @param col The Color of the computer player
	 * @param x Location variable
	 * @param y Location variable
	 */
	public ComputerPlayer(String nam, Color col, int x, int y) {
		super(nam, col, x, y);
		rand = new Random();
		lastRoom = '?';
	}
	
	/**
	 * Disprove the suggestion of another player
	 * 
	 * @param suggestion The three card suggestion of the player making the suggestion.
	 */
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
	
	/**
	 * Make an accusation. This will only happen if a computer player has three cards that will guarantee a correct answer.
	 * The board handles the checking of accusations.  
	 */
	@Override
	public ArrayList<Card> makeAccusation() {
		return unknownCards;
	}
	
	/**
	 * Makes a suggestion. 
	 * This will happen when the computer player enters a room.
	 * 
	 *  @param currentRoom The card of the current room the player is in.
	 *  	This is needed because the rules require a suggestion be from the room they are in. 
	 */
	@Override
	public ArrayList<Card> makeSuggestion(Card currentRoom) {
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
	/**
	 * Makes the move for the computer players. 
	 * 
	 * @param targets The possible targets for the computer player. 
	 */
	@Override
	public BoardCell makeMove(Set<BoardCell> targets) {
		boolean hasTarget = false;
		BoardCell newLoc = null;
		
		while(!hasTarget) {
			int value = rand.nextInt(targets.size()); 
			int i = 0;
			for(BoardCell targ: targets)
			{
			    if (i == value)
			    	if(targ.getInitial() != lastRoom) {
			    		newLoc = targ;
			    		hasTarget = true;
			    		break;
			    	}
			    i = i + 1;
			}
		}
		if(newLoc.getInitial() != 'W')
			lastRoom = newLoc.getInitial();
		
		row = newLoc.getRow();
		column = newLoc.getColumn();
		
		return newLoc;
	}
}
