package tests;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Set;

//Doing a static import allows me to write assertEquals rather than
//assertEquals
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.BadConfigFormatException;

public class OurAdjTests {
	// We make the Board static because we can load it one time and 
	// then do all the tests. 
	private static Board board;
	@BeforeClass
	public static void setUp() throws FileNotFoundException, BadConfigFormatException {
		try {
			board = new Board("Map.csv", "MapKey.txt", "", "");
			board.initialize();
		} catch (Exception e) {
			throw new BadConfigFormatException();
			}
	}

	// Ensure that player does not move around within room
	// These cells are ORANGE on the planning spreadsheet
	@Test
	public void testAdjacenciesInsideRooms()
	{
		// Test a corner
		LinkedList<BoardCell> testList = board.getAdjList(1, 1);
		assertEquals(0, testList.size());
		// Test one that has walkway underneath
		testList = board.getAdjList(5, 0);
		assertEquals(0, testList.size());
		// Test one that has walkway above
		testList = board.getAdjList(21, 25);
		assertEquals(0, testList.size());
		// Test one that is in middle of room
		testList = board.getAdjList(18, 22);
		assertEquals(0, testList.size());
		// Test one beside a door
		testList = board.getAdjList(5, 12);
		assertEquals(0, testList.size());
		// Test one in a corner of room
		testList = board.getAdjList(12, 19);
		assertEquals(0, testList.size());
	}

	// Ensure that the adjacency list from a doorway is only the
	// walkway. NOTE: This test could be merged with door 
	// direction test. 
	// These tests are PURPLE on the planning spreadsheet
	@Test
	public void testAdjacencyRoomExit()
	{
		// TEST DOORWAY RIGHT 
		LinkedList<BoardCell> testList = board.getAdjList(8, 7);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(8, 8)));
		// TEST DOORWAY LEFT 
		testList = board.getAdjList(23, 20);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(23, 19)));
		//TEST DOORWAY DOWN
		testList = board.getAdjList(3, 16);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(4, 16)));
		//TEST DOORWAY UP
		testList = board.getAdjList(2, 6);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(1, 6)));
		
	}
	
	// Test adjacency at entrance to rooms
	// These tests are GREEN in planning spreadsheet
	@Test
	public void testAdjacencyDoorways()
	{
		// Test beside a door direction RIGHT
		LinkedList<BoardCell> testList = board.getAdjList(8, 8);
		assertTrue(testList.contains(board.getCellAt(8, 7)));
		assertTrue(testList.contains(board.getCellAt(8, 9)));
		assertTrue(testList.contains(board.getCellAt(9, 8)));
		assertTrue(testList.contains(board.getCellAt(7, 8)));
		assertEquals(4, testList.size());
		// Test beside a door direction DOWN
		testList = board.getAdjList(4, 16);
		assertTrue(testList.contains(board.getCellAt(3, 16)));
		assertTrue(testList.contains(board.getCellAt(5, 16)));
		assertTrue(testList.contains(board.getCellAt(4, 15)));
		assertTrue(testList.contains(board.getCellAt(4, 17)));
		assertEquals(4, testList.size());
		// Test beside a door direction LEFT
		testList = board.getAdjList(23, 19);
		assertTrue(testList.contains(board.getCellAt(22, 19)));
		assertTrue(testList.contains(board.getCellAt(24, 19)));
		assertTrue(testList.contains(board.getCellAt(23, 18)));
		assertTrue(testList.contains(board.getCellAt(23, 20)));
		assertEquals(4, testList.size());
		// Test beside a door direction UP
		testList = board.getAdjList(1, 6);
		assertTrue(testList.contains(board.getCellAt(0, 6)));
		assertTrue(testList.contains(board.getCellAt(2, 6)));
		assertTrue(testList.contains(board.getCellAt(1, 5)));
		assertTrue(testList.contains(board.getCellAt(1, 7)));
		assertEquals(4, testList.size());
	}

	// Test a variety of walkway scenarios
	// These tests are LIGHT PURPLE on the planning spreadsheet
	@Test
	public void testAdjacencyWalkways()
	{
		// Test on top edge of board, just two walkway pieces
		LinkedList<BoardCell> testList = board.getAdjList(0, 14);
		assertTrue(testList.contains(board.getCellAt(0, 15)));
		assertTrue(testList.contains(board.getCellAt(1, 14)));
		assertEquals(2, testList.size());
		
		// Test on right edge of board, just one walkway piece
		testList = board.getAdjList(3, 25);
		assertTrue(testList.contains(board.getCellAt(4, 25)));
		assertEquals(1, testList.size());

		// Test between two rooms, walkways right and left
		testList = board.getAdjList(6, 21);
		assertTrue(testList.contains(board.getCellAt(6, 20)));
		assertTrue(testList.contains(board.getCellAt(6, 22)));
		assertEquals(2, testList.size());

		// Test surrounded by 4 walkways
		testList = board.getAdjList(22,17);
		assertTrue(testList.contains(board.getCellAt(22, 18)));
		assertTrue(testList.contains(board.getCellAt(22, 16)));
		assertTrue(testList.contains(board.getCellAt(21, 17)));
		assertTrue(testList.contains(board.getCellAt(23, 17)));
		assertEquals(4, testList.size());
		
		// Test on bottom edge of board, next to 1 room piece
		testList = board.getAdjList(25, 16);
		assertTrue(testList.contains(board.getCellAt(25, 17)));
		assertTrue(testList.contains(board.getCellAt(24, 16)));
		assertEquals(2, testList.size());
		
		// Test on left edge of board, next to 1 room piece
		testList = board.getAdjList(7, 0);
		assertTrue(testList.contains(board.getCellAt(6, 0)));
		assertTrue(testList.contains(board.getCellAt(7, 1)));
		assertEquals(2, testList.size());

		// Test on walkway next to  door that is not in the needed
		// direction to enter
		testList = board.getAdjList(24, 20);
		assertTrue(testList.contains(board.getCellAt(24, 19)));
		assertTrue(testList.contains(board.getCellAt(25, 20)));

		//is reading in door and putting it in adj. I can't figure out 6+why...
		assertEquals(2, testList.size());
	}	
	
	// Tests of just walkways, 1 step, includes on edge of board
	// and beside room
	// Have already tested adjacency lists on all four edges, will
	// only test two edges here
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsOneStep() {
		board.calcTargets(21, 8, 1);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCellAt(20, 8)));
		assertTrue(targets.contains(board.getCellAt(22, 8)));
		assertTrue(targets.contains(board.getCellAt(21, 9)));	
		
		board.calcTargets(6, 22, 1);
		targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(6, 21)));
		assertTrue(targets.contains(board.getCellAt(6, 23)));	
	}
	
	// Tests of just walkways, 2 steps
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsTwoSteps() {
		board.calcTargets(21, 8, 2);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCellAt(19, 8)));
		assertTrue(targets.contains(board.getCellAt(23, 8)));
		assertTrue(targets.contains(board.getCellAt(20, 9)));
		assertTrue(targets.contains(board.getCellAt(22, 9)));
		
		board.calcTargets(6, 22, 2);
		targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(6, 24)));
		assertTrue(targets.contains(board.getCellAt(6, 20)));	
	}
	
	// Tests of just walkways, 4 steps
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsFourSteps() {
		board.calcTargets(21, 8, 4);
		Set<BoardCell> targets= board.getTargets();		
		assertEquals(10, targets.size());
		assertTrue(targets.contains(board.getCellAt(17, 8)));
		assertTrue(targets.contains(board.getCellAt(19, 8)));
		assertTrue(targets.contains(board.getCellAt(23, 8)));
		assertTrue(targets.contains(board.getCellAt(25, 8)));
		assertTrue(targets.contains(board.getCellAt(18, 9)));
		assertTrue(targets.contains(board.getCellAt(20, 9)));
		assertTrue(targets.contains(board.getCellAt(22, 9)));
		assertTrue(targets.contains(board.getCellAt(24, 9)));
		assertTrue(targets.contains(board.getCellAt(20, 11)));
		assertTrue(targets.contains(board.getCellAt(18, 7)));

		
		// Includes a path that doesn't have enough length
		board.calcTargets(4, 25, 4);
		targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(6, 23)));
		assertTrue(targets.contains(board.getCellAt(7, 25)));	
	}	
		
	// Tests of just walkways plus one door, 6 steps
	// These are LIGHT BLUE on the planning spreadsheet

	@Test
	public void testTargetsSixSteps() {
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
	
	// Test getting into a room
	// These are LIGHT BLUE on the planning spreadsheet

	@Test 
	public void testTargetsIntoRoom()
	{
		// One room is exactly 2 away
		board.calcTargets(17, 9, 2);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(5, targets.size());
		// directly left (can't go right 2 steps)
		assertTrue(targets.contains(board.getCellAt(17, 7)));
		// directly up and down
		assertTrue(targets.contains(board.getCellAt(15, 9)));
		assertTrue(targets.contains(board.getCellAt(19, 9)));
		// one up/down, one left/right
		assertTrue(targets.contains(board.getCellAt(18, 8)));
		assertTrue(targets.contains(board.getCellAt(16, 8)));
	}
	
	// Test getting into room, doesn't require all steps
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsIntoRoomShortcut() 
	{
		board.calcTargets(25, 9, 3);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(5, targets.size());
		// directly up
		assertTrue(targets.contains(board.getCellAt(22, 9)));
		// into the room
		assertTrue(targets.contains(board.getCellAt(24, 10)));
		// 
		assertTrue(targets.contains(board.getCellAt(25, 8)));		
		assertTrue(targets.contains(board.getCellAt(24, 9)));	
		assertTrue(targets.contains(board.getCellAt(23, 8)));				
	}

	// Test getting out of a room
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testRoomExit()
	{
		// Take one step, essentially just the adj list
		board.calcTargets(15, 7, 1);
		Set<BoardCell> targets= board.getTargets();
		// Ensure doesn't exit through the wall
		assertEquals(1, targets.size());
		assertTrue(targets.contains(board.getCellAt(15, 8)));
		// Take two steps
		board.calcTargets(15, 7, 2);
		targets= board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCellAt(15, 9)));
		assertTrue(targets.contains(board.getCellAt(14, 8)));
		assertTrue(targets.contains(board.getCellAt(16, 8)));
	}

}