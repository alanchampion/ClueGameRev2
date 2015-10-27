package tests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.io.FileReader;
import java.util.Scanner;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.CardType;
import clueGame.ComputerPlayer;
import clueGame.HumanPlayer;

public class GameSetupTests {
	
	// Cards
	// Persons
	static Card scarletCard;
	static Card whiteCard;
	static Card peacockCard;
	static Card mustardCard;
	static Card plumCard;
	static Card greenCard;
	// Weapons
	static Card candlestickCard;
	static Card revolverCard;
	static Card ropeCard;
	static Card wrenchCard;
	static Card pipeCard;
	static Card knifeCard;
	// Rooms
	static Card conservatoryCard;
	static Card kitchenCard;
	static Card ballroomCard;
	static Card billiardCard;
	static Card libraryCard;
	static Card studyCard;
	static Card diningCard;
	static Card loungeCard;
	static Card hallCard;
	
	// Players
	static HumanPlayer human;
	static ComputerPlayer comp1;
	static ComputerPlayer comp2;
	static ComputerPlayer comp3;
	static ComputerPlayer comp4;
	static ComputerPlayer comp5;
	
	private static Board board;
	
	@BeforeClass
	public static void setUp() {
		
		// Persons
		scarletCard = new Card("Miss Scarlet", CardType.PERSON);
		whiteCard = new Card("Mrs. White", CardType.PERSON);
		peacockCard = new Card("Mrs. Peacock", CardType.PERSON);
		mustardCard = new Card("Colonel Mustard", CardType.PERSON);
		plumCard = new Card("Professor Plum", CardType.PERSON);
		greenCard = new Card("Mr. Green", CardType.PERSON);
		
		// Weapons
		candlestickCard = new Card("Candlestick", CardType.WEAPON);
		revolverCard = new Card("Revolver", CardType.WEAPON);
		ropeCard = new Card("Knife", CardType.WEAPON);
		wrenchCard = new Card("Wrench", CardType.WEAPON);
		pipeCard = new Card("Lead Pipe", CardType.WEAPON);
		knifeCard = new Card("Knife", CardType.WEAPON);
		
		// Rooms
		conservatoryCard = new Card("Conservatory", CardType.ROOM);
		kitchenCard = new Card("Kitchen", CardType.ROOM);
		ballroomCard = new Card("Ballroom", CardType.ROOM);
		billiardCard = new Card("Billiard Room", CardType.ROOM);
		libraryCard = new Card("Library", CardType.ROOM);
		studyCard = new Card("Study", CardType.ROOM);
		diningCard = new Card("Dining Room", CardType.ROOM);
		loungeCard = new Card("Lounge", CardType.ROOM);
		hallCard = new Card("Hall", CardType.ROOM);
		
		// Players
		human = new HumanPlayer("Human", Color.RED, 0, 0);
		comp1 = new ComputerPlayer("Computer 1", Color.ORANGE, 0, 1);
		comp2 = new ComputerPlayer("Computer 2", Color.YELLOW, 0, 2);
		comp3 = new ComputerPlayer("Computer 3", Color.GREEN, 0, 3);
		comp4 = new ComputerPlayer("Computer 4", Color.BLUE, 0, 4);
		comp5 = new ComputerPlayer("Computer 5", Color.MAGENTA, 0, 5);
		
		board = new Board("", "", "Characters.txt", "Weapons.txt");
	}
	
	//Makes sure that the people cards are loaded correctly including incorrect texts being imported. 
	@Test
	public void testPeople() {
		
		// Check name
		assertEquals(human.getName(), "Human");
		assertEquals(comp1.getName(), "Computer 1");
		assertEquals(comp5.getName(), "Computer 5");
		
		// Check color
		assertEquals(human.getColor(), Color.RED);
		assertEquals(comp1.getColor(), Color.ORANGE);
		assertEquals(comp5.getColor(), Color.MAGENTA);
		
		// Check starting row
		assertEquals(human.getRow(), 0);
		assertEquals(comp1.getRow(), 0);
		assertEquals(comp5.getRow(), 0);
		
		// Check starting column
		assertEquals(human.getColumn(), 0);
		assertEquals(comp1.getColumn(), 1);
		assertEquals(comp5.getColumn(), 5);
	}
	
	//Makes sure that the entire deck is created correctly
	@Test
	public void testDeck() {
		
	}
	
	//Makes sure that shuffling and dealing works
	@Test
	public void testDealer() {
		
	}
}
