package tests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;

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
	
	//Makes sure that the computer is choosing good places to move to on their turn. 
	@Test
	public void testTargetting() {
		
	}
	
	//Checks to see that the disproving of a suggestion works well
	@Test
	public void testDisprovingSuggestion() {
		
	}
	
	//Checks that making a suggestion works as expected
	@Test
	public void testSuggestion() {
		
	}
}
