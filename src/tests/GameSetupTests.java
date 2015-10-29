package tests;

import static org.junit.Assert.*;

import java.awt.Color;
//import java.io.FileReader;
import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Scanner;

//import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.Card;
import clueGame.CardType;
import clueGame.ComputerPlayer;
import clueGame.HumanPlayer;
import clueGame.Player;

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
	private static Player[] players;
	private static ArrayList<Card> deck;
	
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
		players = new Player[6];
		players[0] = new HumanPlayer("Human", Color.RED, 0, 0);
		players[1] = new ComputerPlayer("Computer 1", Color.ORANGE, 0, 1);
		players[2] = new ComputerPlayer("Computer 2", Color.YELLOW, 0, 2);
		players[3] = new ComputerPlayer("Computer 3", Color.GREEN, 0, 3);
		players[4] = new ComputerPlayer("Computer 4", Color.BLUE, 0, 4);
		players[5] = new ComputerPlayer("Computer 5", Color.MAGENTA, 0, 5);
		
		board = new Board("Map.csv", "MapKey.txt", "Characters.txt", "Weapons.txt");
		deck = new ArrayList<Card>();
	}
	
	//Makes sure that the people cards are loaded correctly including incorrect texts being imported. 
	@Test
	public void testPeople() {
		
		// Check name
		assertEquals(players[0].getName(), "Human");
		assertEquals(players[1].getName(), "Computer 1");
		assertEquals(players[5].getName(), "Computer 5");
		
		// Check color
		assertEquals(players[0].getColor(), Color.RED);
		assertEquals(players[1].getColor(), Color.ORANGE);
		assertEquals(players[5].getColor(), Color.MAGENTA);
		
		// Check starting row
		assertEquals(players[0].getRow(), 0);
		assertEquals(players[1].getRow(), 0);
		assertEquals(players[5].getRow(), 0);
		
		// Check starting column
		assertEquals(players[0].getColumn(), 0);
		assertEquals(players[1].getColumn(), 1);
		assertEquals(players[5].getColumn(), 5);
	}
	
	//Makes sure that the entire deck is created correctly
	@Test
	public void testDeck() {
		int numRooms = 0;
		int numPersons = 0;
		int numWeapons = 0;
		
		// Gets the full list of cards
		deck = board.getFullDeck();
		
		// Check to see if deck is of size 21
		assertEquals(deck.size(), 21);
		
		// Count each card's type in deck
		for (Card card: deck) {
			if (card.getType() == CardType.ROOM) {
				numRooms++;
			}
			if (card.getType() == CardType.PERSON) {
				numPersons++;
			}
			if (card.getType() == CardType.WEAPON) {
				numWeapons++;
			}
		}
		// Check to see that there is 9 rooms, 6 characters, and 6 weapons
		assertEquals(numRooms, 9);
		assertEquals(numPersons, 6);
		assertEquals(numWeapons, 6);
	}
	
	//Makes sure that shuffling and dealing works
	@Test
	public void testDealer() {
		ArrayList<Card> hand1 = new ArrayList<Card>(3);
		ArrayList<Card> hand2 = new ArrayList<Card>(3);
		ArrayList<Card> hand3 = new ArrayList<Card>(3);
		hand1 = players[0].getHand();
		hand2 = players[1].getHand();
		hand3 = players[5].getHand();
		
		
		// Check to see that deck size is empty after dealing
		assertEquals(board.getDeckSize(), 0);
		
		// Check to see that each player has 3 cards after dealing
		assertEquals(board.players[0].getHandSize(), 3);
		assertEquals(board.players[1].getHandSize(), 3);
		assertEquals(board.players[2].getHandSize(), 3);
		assertEquals(board.players[3].getHandSize(), 3);
		assertEquals(board.players[4].getHandSize(), 3);
		assertEquals(board.players[5].getHandSize(), 3);
		
		// Check to ensure no one has an identical hand
		assertTrue(!(hand1.contains(hand2) || hand2.contains(hand3) || hand3.contains(hand1)));
	}
}
