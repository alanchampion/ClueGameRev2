package clueGame;

public class BoardCell {
	int row;
	int column;
	char initial;
	
	private DoorDirection direction;
	
	/**
	 * Initializer for board cell
	 * 
	 * @param row Location variable
	 * @param column Location variable
	 * @param r Name of the board cell with the first letter being the initial.
	 * @param d If it is a door, sets a direction. Otherwise defaults to NONE. 
	 */
	public BoardCell(int row, int column, String r, char d) {
		this.row = row;
		this.column = column;
		this.initial = r.charAt(0);
		if (d == 'D')
			direction = DoorDirection.DOWN;
		else if (d == 'U')
			direction = DoorDirection.UP;
		else if (d == 'L')
			direction = DoorDirection.LEFT;
		else if (d == 'R')
			direction = DoorDirection.RIGHT;
		else{
			direction = DoorDirection.NONE;
		}
	}
	
	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public DoorDirection getDoorDirection(){
		return direction;
	}
	
	/**
	 * Checks if the board cell is a walkway or not
	 * 
	 * @return true if it is a walkway. False otherwise.
	 */
	public boolean isWalkway(){
		if (initial=='W')
			return true;
		else{
			return false;
		}
	}
	
	/**
	 * Checks if the board cell is a room or not
	 * 
	 * @return true if it is a room. False otherwise. 
	 */
	public boolean isRoom(){
		if (initial != 'W') // ????? && initial != 'X')
			return true;
		else{
			return false;
		}
	}
	
	/**
	 * Checks if the board cell is a doorway or not. 
	 * 
	 * @return true if it is a doorway. False otherwise. 
	 */
	public boolean isDoorway(){
		if (direction.equals(DoorDirection.NONE))
			return false;
		else
			return true;
	}
	
	public char getInitial(){
		return initial;
	}
}
