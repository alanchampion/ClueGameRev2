package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
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
	private LinkedList<BoardCell> tempAdjCell;
	private BoardCell temp;
	private ArrayList<Card> deck;
	private ArrayList<String> name;

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
		tempAdjCell = new LinkedList<BoardCell>();
		deck = new ArrayList<Card>();
	}

	public void initialize(){
		try {
			//The board
			loadRoomConfig();
			loadBoardConfig();
			calcAdjacencies();
			
			//The cards
			loadCards();
			
			//The people
			loadPlayers();
		} catch (BadConfigFormatException e) {
			System.out.println(e.getMessage());
		} catch (FileNotFoundException e){
			System.out.println(e.getMessage());
		}
	}

	private void loadPlayers() throws BadConfigFormatException, FileNotFoundException {
		
	}

	private void loadCards() throws BadConfigFormatException, FileNotFoundException {
		//Need 6, no duplicates
		FileReader fr = new FileReader(weaponsFile);
		Scanner sc = new Scanner(fr);
		int startSize = deck.size();
		String tempName;
		
		while(sc.hasNextLine()) {
			deck.add(new Card(sc.nextLine(), CardType.WEAPON));
		}
		if(deck.size() - startSize == 6)
			throw new BadConfigFormatException();
		
		//Need 6, no duplicates
		startSize = deck.size();
		fr = new FileReader(playersFile);
		while(sc.hasNextLine()) {
			tempName = sc.nextLine();
			deck.add(new Card(tempName, CardType.PERSON));
			name.add(tempName);
		}
		if(deck.size() - startSize == 6)
			throw new BadConfigFormatException();
		
		Collections.shuffle(deck);
		
		//The addition of the rooms happened in loadRoomConfig()
	}
	
	//Adds the rooms to the deck as well. 
	public void loadRoomConfig() throws BadConfigFormatException, FileNotFoundException {
		
		String[] entries;
		String line = " ";

		FileReader keyIn = new FileReader(key);
		Scanner readKey = new Scanner(keyIn);

		while(readKey.hasNextLine()){
			line = readKey.nextLine();
			entries = line.split(",");
			if (entries.length != 3){
				throw new BadConfigFormatException();
			}
			Character key = new Character(entries[0].charAt(0));
			String roomName = entries[1].trim();
			rooms.put(key, roomName);
			
			if(entries[2].trim().equals("Card"))
			{
				deck.add(new Card(entries[1].trim(), CardType.ROOM));
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
				throw new BadConfigFormatException();
			}
			for( int j = 0; j < numCols; j++){
				if (! rooms.containsKey(tempColumnEntries[j].charAt(0))){
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
	
	public int getNumColumns(){
		return numCols;
	}
	
	public int getNumRows(){
		return numRows;
	}

	public Map<Character, String> getRooms(){
		return this.rooms;
	}
	
	public void printCell(BoardCell c){
		int x = getX(c);
		int y = getY(c);
		System.out.println("(" + x + " " + y + ")");
	}
	
	public static void main(String[] args) {
		
	}
}