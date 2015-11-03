package clueGame;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import java.util.Scanner;

public class Board {

	private Map<BoardCell, LinkedList<BoardCell>> adjMtx;
	private Map<Character, String> rooms;
	private Set<BoardCell> targets;
	private Set<BoardCell> visited;
	private LinkedList<BoardCell> adjCell;
	//private LinkedList<BoardCell> tempAdjCell;
	//private BoardCell temp;
	private ArrayList<Card> deck;
	private ArrayList<Card> fullDeck;
	private ArrayList<String> name;
	public Player[] players;
	private Solution solution;
	//private int[][] startCoords;

	private int numRows;
	private int numCols;
	private String layoutName;
	private String key;
	private String playersFile;
	private String weaponsFile;
	public static final int BOARD_SIZE = 50;

	BoardCell [][] board;
	
	//Constructor requires the name of the layout file, name of the key file, name of characters file, and name of weapons file. 
	public Board(String layoutName, String key, String playersList, String weaponsList){
		
		this.layoutName = layoutName;
		this.key = key;
		this.playersFile = playersList;
		this.weaponsFile = weaponsList;
		adjMtx = new HashMap<BoardCell, LinkedList<BoardCell>>();
		rooms = new HashMap<Character, String>();
		targets = new HashSet<BoardCell>();
		visited = new HashSet<BoardCell>();
		adjCell = new LinkedList<BoardCell>();
		//tempAdjCell = new LinkedList<BoardCell>();
		deck = new ArrayList<Card>();
		name = new ArrayList<String>();
		fullDeck = new ArrayList<Card>(3);
		players = new Player[6];
		//startCoords = new int[6][6];
		
		initialize();
	}

	public void initialize() {
		
		try {
			// Create board and movement locations
			loadRoomConfig();
			loadBoardConfig();
			calcAdjacencies();
			
			// Create the deck
			loadCards();
			
			// Create the players
			loadPlayers();
		} catch (BadConfigFormatException e) {
			System.out.println(e.getMessage());
		} catch (FileNotFoundException e){
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		// Deal the deck to players
		deal();
	}
	
	//Creates the solution and distributes cards. 
	private void deal() {
		
		boolean isWeapon = false;
		boolean isRoom = false;
		boolean isPerson = false;
		
		ArrayList<Card> cards = new ArrayList<Card>();
		ArrayList<Card> cardsToRemove = new ArrayList<Card>();
		
		// Create the solution
		for (Card card: deck)
		{
			// Get first weapon card
			if(!isWeapon && card.getType() == CardType.WEAPON){
				cards.add(card);
				cardsToRemove.add(card);
				isWeapon = true;
			}
			// Get first room card
			if(!isRoom && card.getType() == CardType.ROOM){
				cards.add(card);
				cardsToRemove.add(card);
				isRoom = true;
			}
			// Get first character card
			if(!isPerson && card.getType() == CardType.PERSON){
				cards.add(card);
				cardsToRemove.add(card);
				isPerson = true;
			}
			// Create solution after getting each card type
			if(isPerson && isWeapon && isRoom) {
				solution = new Solution(cards);
				break;
			}
		}
		
		// Remove cards used for solution from the deck
		deck.removeAll(cardsToRemove);
		
		// Deal out remaining cards
		for(Player player: players) {
			for(int i = 0; i < 3; i++) {
				player.addCard(deck.get(0));
				deck.remove(0);
			}
		}
	}

	private void loadPlayers() throws BadConfigFormatException, FileNotFoundException {
		// Create list of players with name, color, and starting locations
		players[0] = new HumanPlayer("Human", Color.RED, 0, 3);
		players[1] = new ComputerPlayer("Computer 1", Color.ORANGE, 6, 0);
		players[2] = new ComputerPlayer("Computer 2", Color.YELLOW, 0, 15);
		players[3] = new ComputerPlayer("Computer 3", Color.GREEN, 3, 25);
		players[4] = new ComputerPlayer("Computer 4", Color.BLUE, 8, 25);
		players[5] = new ComputerPlayer("Computer 5", Color.MAGENTA, 20, 25);
		
		// Give each player a full list of cards
		players[0].addUnknownCards(deck);
		players[1].addUnknownCards(deck);
		players[2].addUnknownCards(deck);
		players[3].addUnknownCards(deck);
		players[4].addUnknownCards(deck);
		players[5].addUnknownCards(deck);
	}

	private void loadCards() throws BadConfigFormatException, IOException {
		String tempName;
		
		FileReader frPlayers = new FileReader(playersFile);
		FileReader frWeapons = new FileReader(weaponsFile);
		
		Scanner sc = new Scanner(frPlayers);
		int counter = 0;
		
		// Get character cards from playersFile
		while(sc.hasNextLine()) {
			tempName = sc.nextLine();
			deck.add(new Card(tempName, CardType.PERSON));
			name.add(tempName);
			counter++;
		}
		
		// Check that 6 characters were loaded
		if (counter != 6) {
			frWeapons.close();
			sc.close();
			throw new BadConfigFormatException();
		}
		sc.close();
		
		// Get weapon cards from file
		counter = 0;
		sc = new Scanner(frWeapons);
		while(sc.hasNextLine()) {
			deck.add(new Card(sc.nextLine(), CardType.WEAPON));
			counter++;
		}
		
		// Check that 6 weapons were loaded
		if (counter != 6) {
			sc.close();
			throw new BadConfigFormatException();
		}
			
		// Create a copy of the deck that will not be modified
		fullDeck.addAll(deck);
		
		// Shuffle the deck
		Collections.shuffle(deck);
		
		sc.close();
	}
	
	//Adds the rooms to the deck as well. 
	public void loadRoomConfig() throws BadConfigFormatException, FileNotFoundException {
		
		String[] entries;
		String line = " ";

		FileReader keyIn = new FileReader(key);
		Scanner readKey = new Scanner(keyIn);

		while (readKey.hasNextLine()) {
			line = readKey.nextLine();
			entries = line.split(",");
			if (entries.length != 3) {
				readKey.close();
				throw new BadConfigFormatException();
			}
			Character key = new Character(entries[0].charAt(0));
			String roomName = entries[1].trim();
			rooms.put(key, roomName);
			
			if (entries[2].trim().equals("Card")) {
				deck.add(new Card(entries[1].trim(), CardType.ROOM, key));
			}
		}
		
		readKey.close();
	}

	public void loadBoardConfig() throws FileNotFoundException, BadConfigFormatException {
		
		board = new BoardCell[BOARD_SIZE][BOARD_SIZE];

		//Read in each row to string
		ArrayList<String> tempRows=new ArrayList<String>();
		//Split rows into elements
		String[] tempColumnEntries;

		FileReader layoutIn = new FileReader(layoutName);
		Scanner readLayout = new Scanner(layoutIn);

		int counter = 0;

		while (readLayout.hasNextLine()){
			tempRows.add(readLayout.nextLine());
			counter++;
		}
		numRows = counter;
		char doorDirection = ' ';
		tempColumnEntries = tempRows.get(0).split(",");
		numCols = tempColumnEntries.length;
		for(int i = 0; i < numRows; i++){
			//loop through each row to get individual cells
			tempColumnEntries = tempRows.get(i).split(",");
			if (tempColumnEntries.length != numCols){
				readLayout.close();
				throw new BadConfigFormatException();
			}
			for( int j = 0; j < numCols; j++){
				if (! rooms.containsKey(tempColumnEntries[j].charAt(0))){
					readLayout.close();
					throw new BadConfigFormatException();
				}
				//loop through each row to set each cell
				if (tempColumnEntries[j].length() == 2)
					doorDirection = (tempColumnEntries[j].charAt(1));
				else{
					doorDirection = (' ');
				}
				board[i][j]= new BoardCell(i,j,tempColumnEntries[j], doorDirection);
			}
		}
		readLayout.close();
	}

	public BoardCell getCellAt(int x, int y){
		return board[x][y];
	}

	public Map<BoardCell, LinkedList<BoardCell>> calcAdjacencies(){
		
		for (int i = 0; i < numRows; i++){
			for( int j = 0; j < numCols; j++){
				LinkedList<BoardCell> temp = new LinkedList<BoardCell>();
				BoardCell tempCell = getCellAt(i,j);
				if (tempCell.isDoorway()){
					if (tempCell.getDoorDirection().equals(DoorDirection.RIGHT)){
						tempCell = getCellAt(i, j+1);
						if (tempCell.getInitial() == 'W')
							temp.add(getCellAt(i, j+1));
					}
					else if (tempCell.getDoorDirection() == DoorDirection.LEFT){
						tempCell = getCellAt(i, j-1);
						if (tempCell.getInitial() == 'W')
							temp.add(getCellAt(i, j-1));
					}
					else if (tempCell.getDoorDirection() == DoorDirection.UP){
						tempCell = getCellAt(i-1, j);
						if (tempCell.getInitial() == 'W')
							temp.add(getCellAt(i-1, j));
					}
					else if (tempCell.getDoorDirection() == DoorDirection.DOWN){
						tempCell = getCellAt(i+1, j);
						if (tempCell.getInitial() == 'W')
							temp.add(getCellAt(i+1, j));
					}
				}
				else if(tempCell.isRoom()){
					//do nothing
				}
				else{
					if(isValid(i-1, j)){
						if(getCellAt(i-1, j).isDoorway()){
							if(getCellAt(i-1, j).getDoorDirection() == DoorDirection.DOWN)
								temp.add(getCellAt(i-1, j));
						}
						else{
						temp.add(getCellAt(i-1, j));
						}
					}
					if(isValid(i, j-1)){
						if(getCellAt(i, j-1).isDoorway()){
							if(getCellAt(i, j-1).getDoorDirection() == DoorDirection.RIGHT)
								temp.add(getCellAt(i, j-1));
						}
						else{
						temp.add(getCellAt(i, j-1));
						}					
					}
					if(isValid(i+1, j)){
						if(getCellAt(i+1, j).isDoorway()){
							if(getCellAt(i+1, j).getDoorDirection() == DoorDirection.UP)
								temp.add(getCellAt(i+1, j));
						}
						else{
						temp.add(getCellAt(i+1, j));
						}
					}
					if(isValid(i, j+1)){
						if(getCellAt(i, j+1).isDoorway()){
							if(getCellAt(i, j+1).getDoorDirection() == DoorDirection.LEFT)
								temp.add(getCellAt(i, j+1));
						}
						else{
						temp.add(getCellAt(i, j+1));
						}
					}
				}
				adjMtx.put(getCellAt(i, j), temp);
			}
		}
		return adjMtx;
	}
	
	public boolean isValid(int x, int y){
		
		if(x < 0 || x > numRows - 1 || y < 0 || y > numCols - 1) {
			return false;
		}
		else if (! getCellAt(x,y).isWalkway() && getCellAt(x,y).getDoorDirection() == DoorDirection.NONE) {
			return false;
		}
		else {
			return true;
		}
	}

	public LinkedList<BoardCell> getAdjList(int x, int y){
		BoardCell cell = getCellAt(x,y);
		return adjMtx.get(cell);
	}
	
	//Checks an accusation. Returns true if accusation is correct. False otherwise. 
	public boolean checkAccusation(ArrayList<Card> accusation){
		return solution.testAccusation(accusation.get(0), accusation.get(1), accusation.get(2));
	}
	
	//Checks the suggestion. Returns false if no disprove is found. Otherwise returns true. 
	public Card checkSuggestion(Player myTurn, ArrayList<Card>  cards) {
		Card tempCard = null;
		
		for(Player player : players)
		{
			if(!player.equals(myTurn))
			{
				//System.out.println("Not my turn");
				tempCard = player.disproveSuggestion(cards);
			}
			if(tempCard != null)
				return tempCard;
		}
		return tempCard;
	}
	
	//Returns a copy of the solution cards as an Array list. 
	//Usefully for testing. 
	public ArrayList<Card> getSolution() {
		return new ArrayList<Card>(solution.solutionCards);
	}
	
	public Set<BoardCell> getTargets(){
		return targets;
	}

	public int getX(BoardCell c){
		return c.row;
	}

	public int getY(BoardCell c){
		return c.column;
	}

	public Set<BoardCell> calcTargets(int x, int y, int pathLength){
		
		visited.clear();
		adjCell.clear();
		targets.clear();
		BoardCell startCell = getCellAt(x,y);
		visited.add(startCell);
		findAllTargets(startCell, pathLength);
		if(targets.contains(startCell))
			targets.remove(startCell);
		for(BoardCell c : targets){
			printCell(c);
		}
		return targets;
	}

	public void findAllTargets(BoardCell cell, int pathLength){
		
		LinkedList<BoardCell> tempAdjCell = new LinkedList<BoardCell>(getAdjList(cell.getRow(), cell.getColumn()));
		for (BoardCell c : tempAdjCell){
			if(visited.contains(c)){
			//do nothing
			}
			else if (pathLength == 1 || c.isDoorway()){
				targets.add(c);
			}
			else{
				visited.add(c);
				findAllTargets(c, pathLength - 1);
				visited.remove(c);
			}
		}
	}
	
	// Getters
	public int getNumColumns(){
		return numCols;
	}
	
	public int getNumRows(){
		return numRows;
	}

	public Map<Character, String> getRooms(){
		return this.rooms;
	}
	
	// Returns size of the deal-able deck
	public int getDeckSize() {
		return deck.size();
	}
	// Returns the whole list of cards
	public ArrayList<Card> getFullDeck() {
		return fullDeck;
	}
	
	public void printCell(BoardCell c){
		int x = getX(c);
		int y = getY(c);
		System.out.println("(" + x + " " + y + ")");
	}
	
	public void printDeck() {
		for (Card card: deck) {
			System.out.println(card);
		}
	}
	
	public Card getRoomFromInitial(char initial) {
		for(Card card : fullDeck)
		{
			if(card.getRoomInitial() == initial)
			{
				return card;
			}
		}
		return null;
	}
}