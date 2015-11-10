package clueGame;

import java.awt.Color;
import java.awt.Graphics;

public class BoardCell {
	public static final int TILE_SIZE = 20;
	
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
	
	public void draw(Graphics g) {
		// Draw Tile
		if (initial == 'W') {
			g.setColor(Color.BLACK);
			g.drawRect((column * TILE_SIZE), (row * TILE_SIZE), TILE_SIZE, TILE_SIZE);
		} else {
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect((column * TILE_SIZE), (row * TILE_SIZE), TILE_SIZE, TILE_SIZE);
		}
		
		// Draw Door
		if (direction != DoorDirection.NONE) {
			g.setColor(Color.DARK_GRAY);
			
			if (direction == DoorDirection.DOWN) {
				g.fillRect((column * TILE_SIZE) + 1, (row * TILE_SIZE) + (TILE_SIZE / 2) + 1, TILE_SIZE, (TILE_SIZE / 2));
			} else if (direction == DoorDirection.UP) {
				g.fillRect((column * TILE_SIZE) + 1, (row * TILE_SIZE) + 1, TILE_SIZE, (TILE_SIZE / 2));
			} else if (direction == DoorDirection.LEFT) {
				g.fillRect((column * TILE_SIZE) + 1, (row * TILE_SIZE) + 1, (TILE_SIZE / 2), TILE_SIZE);
			} else {
				g.fillRect((column * TILE_SIZE) + (TILE_SIZE / 2) + 1, (row * TILE_SIZE) + 1, (TILE_SIZE / 2), TILE_SIZE);
			}
		}
	}
}
