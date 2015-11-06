package clueGame;

public class BadConfigFormatException extends Exception {
	private static final long serialVersionUID = 1L;
	/**
	 * Initializer. Creates the exception.
	 * Calls super to set the message. 
	 */
	public BadConfigFormatException() {
		super("Error, input file was not correct.");
	}
}
