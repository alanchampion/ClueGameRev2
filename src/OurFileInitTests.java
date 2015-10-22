import static org.junit.Assert.*;


import java.io.FileNotFoundException;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class OurFileInitTests {
	public static final int NUM_ROOMS = 11;
	public static final int NUM_ROWS = 26;
	public static final int NUM_COLUMNS = 26;

	private static Board board;
	
	@BeforeClass
	public static void setUp() throws FileNotFoundException, BadConfigFormatException {
		// Create a new Board using the valid files. Note that
		// the default filenames must be attributes of the Board class. 
		board = new Board("Map.csv", "MapKey.txt");
		// Initialize will load BOTH config files 
		board.initialize();
	}
	@Test
	public void testRooms() {
		// rooms is static, see discussion in lab writeup
		Map<Character, String> rooms = board.getRooms();
		// Ensure we read the correct number of rooms
		assertEquals(NUM_ROOMS, rooms.size());
		// To ensure data is correctly loaded, test retrieving a few rooms 
		// from the hash, including the first and last in the file and a few others
		assertEquals("Dungeon", rooms.get('D'));
		assertEquals("Tavern", rooms.get('T'));
		assertEquals("Solarium", rooms.get('S'));
		assertEquals("Office", rooms.get('O'));
		assertEquals("Walkway", rooms.get('W'));
	}
	
	@Test
	public void testBoardDimensions() {
		// Ensure we have the proper number of rows and columns
		assertEquals(NUM_ROWS, board.getNumRows());
		assertEquals(NUM_COLUMNS, board.getNumColumns());		
	}
	
	// Test a doorway in each direction, plus two cells that are not
	// a doorway.
	// These cells are white on the planning spreadsheet
	@Test
	public void FourDoorDirections() {
		// Test one each RIGHT/LEFT/UP/DOWN
		BoardCell room = board.getCellAt(8, 7);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.RIGHT, room.getDoorDirection());
		room = board.getCellAt(3, 16);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.DOWN, room.getDoorDirection());
		room = board.getCellAt(23, 20);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.LEFT, room.getDoorDirection());
		room = board.getCellAt(2, 6);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.UP, room.getDoorDirection());
		// Test that room pieces that aren't doors know it
		room = board.getCellAt(10, 10);
		assertFalse(room.isDoorway());	
		// Test that walkways are not doors
		BoardCell cell = board.getCellAt(0, 6);
		assertFalse(cell.isDoorway());		

	}
	
	// Test that we have the correct number of doors
	@Test
	public void testNumberOfDoorways() 
	{
		int numDoors = 0;
		int totalCells = board.getNumColumns() * board.getNumRows();
		Assert.assertEquals(676, totalCells);
		for (int row = 0; row < board.getNumRows(); row++)
			for (int col = 0; col<board.getNumColumns(); col++) {
				BoardCell cell = board.getCellAt(row, col);
				if (cell.isDoorway())
					numDoors++;
			}
		Assert.assertEquals(18, numDoors);
	}

	// Test a few room cells to ensure the room initial is correct.
	@Test
	public void testRoomInitials() {
		assertEquals('C', board.getCellAt(0, 0).getInitial());
		assertEquals('D', board.getCellAt(4, 9).getInitial());
		assertEquals('B', board.getCellAt(9, 0).getInitial());
		assertEquals('O', board.getCellAt(19, 11).getInitial());
		assertEquals('K', board.getCellAt(12, 22).getInitial());
	}
	
	// Test that an exception is thrown for a bad config file
	@Test (expected = BadConfigFormatException.class)
	public void testBadColumns() throws BadConfigFormatException, FileNotFoundException {
		// overloaded Game ctor takes config file names. See discussion of 
		// testing exceptions in lab writeup. 
		// Note that we are using a LOCAL Board variable, not the static one 
		// set up by @BeforeClass
		Board board = new Board("ClueLayoutBadColumns.csv", "ClueLegend.txt");
		// Instead of initialize, we call the two load functions directly
		board.loadRoomConfig();
		// This one should throw an exception
		board.loadBoardConfig();
	}
	// Test that an exception is thrown for a bad config file
	@Test (expected = BadConfigFormatException.class)
	public void testBadRoom() throws BadConfigFormatException, FileNotFoundException {
		Board board = new Board("ClueLayoutBadRoom.csv", "ClueLegend.txt");
		board.loadRoomConfig();
		board.loadBoardConfig();
	}
	// Test that an exception is thrown for a bad room config file
	@Test (expected = BadConfigFormatException.class)
	public void testBadRoomFormat() throws BadConfigFormatException, FileNotFoundException {
		Board board = new Board("ClueLayout.csv", "ClueLegendBadFormat.txt");
		board.loadRoomConfig();
	}
}
