package tests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Set;

//import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.*;

public class GameActionsTests {
	static ComputerPlayer com1;
	static Board board;
	static Solution solution;
	
	@BeforeClass
	public static void setup() {
		com1 = new ComputerPlayer("Com1", Color.RED, 0, 1);
		board = new Board("Map.csv", "MapKey.txt", "Characters.txt", "Weapons.txt");
		solution = new Solution(board.getSolution());
	}
	
	//Checks when an accusation is correct or incorrect
	@Test
	public void testAccusation() {
		ArrayList<Card> solution = new ArrayList<Card>();
		solution.addAll(board.getSolution());
		//Checks that accusation works when all correct
		com1.addUnknownCards(solution);
		assertTrue(board.checkAccusation(com1.makeAccusation()));
		
		//Clears com1's unknown cards.
		for(Card card : solution)
			com1.addInformation(card);
		//Adds a fake person card
		for(Card card : solution)
			if(card.getType() == CardType.PERSON)
			{
				solution.remove(card);
				break;
			}
		solution.add(new Card("Not a Person",CardType.PERSON));
		//Checks for false person
		com1.addUnknownCards(solution);
		assertTrue(!board.checkAccusation(com1.makeAccusation()));
		
		//Clears com1's unknown cards.
		for(Card card : solution)
			com1.addInformation(card);
		//Clears solution then re-stocks it with the correct values
		solution.removeAll(solution);
		solution.addAll(board.getSolution());
		//Adds a fake weapon card
		for(Card card : solution)
			if(card.getType() == CardType.WEAPON)
			{
				solution.remove(card);
				break;
			}
		solution.add(new Card("Peashooter",CardType.WEAPON));
		//Checks for false weapon
		com1.addUnknownCards(solution);
		assertTrue(!board.checkAccusation(com1.makeAccusation()));
		
		//Clears com1's unknown cards.
		for(Card card : solution)
			com1.addInformation(card);
		//Clears solution then re-stocks it with the correct values
		solution.removeAll(solution);
		solution.addAll(board.getSolution());
		//Adds a fake room card
		for(Card card : solution)
			if(card.getType() == CardType.ROOM)
			{
				solution.remove(card);
				break;
			}
		solution.add(new Card("Peashooter",CardType.ROOM));
		//Checks for false room
		com1.addUnknownCards(solution);
		assertTrue(!board.checkAccusation(com1.makeAccusation()));
	}
	
	//Checks to see that the disproving of a suggestion works well
	@Test
	public void testDisprovingSuggestion() {
		//ArrayList<Card> solutionCards = new ArrayList<Card>(board.getSolution());
		ArrayList<Card> wrongCards = new ArrayList<Card>();
		Card tempCard;
		
		//assertTrue(board.checkSuggestion(board.players[1],solutionCards) == null);
		
		//Checks given the wrong cards.
		tempCard = board.players[1].getHand().get(0);
		wrongCards.add(tempCard);
		switch(tempCard.getType()){
		case PERSON: 
			wrongCards.add(solution.getWeapon());
			wrongCards.add(solution.getRoom());
			break;
		case WEAPON:
			wrongCards.add(solution.getPerson());
			wrongCards.add(solution.getRoom());
			break;
		case ROOM:
			wrongCards.add(solution.getWeapon());
			wrongCards.add(solution.getPerson());
			break;
		default:
			assertTrue(false);
			break;
		}
		
		//Wrong card is in suggesting players hand
		assertTrue(board.checkSuggestion(board.players[1],wrongCards) == null);
		//Wrong card is in first players hand
		assertTrue(board.checkSuggestion(board.players[0],wrongCards) == tempCard);
		//Wrong card is in last players hand
		assertTrue(board.checkSuggestion(board.players[5],wrongCards) == tempCard);
	}
	
	//Checks that making a suggestion works as expected
	@Test
	public void testSuggestion() {
		ArrayList<Card> suggestion;
		com1.moveLocation(10,3);
		suggestion = com1.makeSuggestion(board.getRoomFromInitial(board.getCellAt(10, 3).getInitial()));
		System.out.println(suggestion.toString());
		
		assertTrue(suggestion.size() == 3);
		assertTrue(suggestion.contains(board.getRoomFromInitial(board.getCellAt(10, 3).getInitial())));
	}
	
	//Makes sure that the computer is choosing good places to move to on their turn. 
	@Test
	public void testTargetting() {
		board.calcTargets(7, 0, 6);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCellAt(6, 1)));
		assertTrue(targets.contains(board.getCellAt(6, 3)));	
		assertTrue(targets.contains(board.getCellAt(6, 5)));	
		assertTrue(targets.contains(board.getCellAt(7, 2)));	
		assertTrue(targets.contains(board.getCellAt(7, 4)));	
		assertTrue(targets.contains(board.getCellAt(7, 6)));
	}
}
