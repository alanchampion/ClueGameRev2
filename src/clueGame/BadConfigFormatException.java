package clueGame;

public class BadConfigFormatException extends Exception {
	public BadConfigFormatException() {
		super("Error, input file was not correct.");
	}
}
