package clueGame;

public class BoardCell {
	int row;
	int column;
	char initial;
	
	private DoorDirection direction;

	
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
	
	public boolean isWalkway(){
		if (initial=='W')
			return true;
		else{
			return false;
		}
	}
	
	public boolean isRoom(){
		if (initial != 'W') // ????? && initial != 'X')
			return true;
		else{
			return false;
		}
	}
	
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
