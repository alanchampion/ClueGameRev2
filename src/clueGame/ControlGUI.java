package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ControlGUI extends JFrame {
	
	public ControlGUI() {
		setTitle("Clue Control");
		setSize(800, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 3));
		add(panel, BorderLayout.SOUTH);
		
		Header header = new Header();
		panel.add(header);
		nextPlayerButton nextPlayer = new nextPlayerButton();
		panel.add(nextPlayer);
		makeAccusationButton makeAccusation = new makeAccusationButton();
		panel.add(makeAccusation);
		Die die = new Die();
		panel.add(die);
		Guess guess = new Guess();
		panel.add(guess);
		Result result = new Result();
		panel.add(result);
	}

	public static void main(String[] args) {
		ControlGUI gui = new ControlGUI();
		gui.setVisible(true);
	}
	
	public class Header extends JPanel {
		
		public Header() {
			// Create layout
			setLayout(new GridLayout(2, 1));
			
			// Create GUI elements
			JLabel turnLabel = new JLabel("Whose turn?");
			JTextField turn = new JTextField();
			
			// Add elements to JPanel
			add(turnLabel);
			add(turn);
			
			// Prevent the textfield from being user editable
			turn.setEditable(false);
		}
	}
	
	public class nextPlayerButton extends JPanel {
		
		public nextPlayerButton() {
			// Create layout
			setLayout(new GridLayout(1, 1));
			
			// Create GUI elements
			JButton nextPlayer = new JButton("Next Player");
			
			// Add elements to JPanel
			add(nextPlayer);
		}
	}
	
	public class makeAccusationButton extends JPanel {
		
		public makeAccusationButton() {
			setLayout(new GridLayout(1, 1));
			
			JButton makeAccusation = new JButton("Make Accusation");
			
			add(makeAccusation);
		}
	}
	
	public class Die extends JPanel {
		
		public Die() {
			// Create layout
			setLayout(new GridLayout(1, 2));
			setBorder(new TitledBorder (new EtchedBorder(), "Die"));
			
			// Create GUI elements
			JLabel rollLabel = new JLabel("Roll:");
			JTextField roll = new JTextField();
			
			// Add elements to JPanel
			add(rollLabel);
			add(roll);
			
			// Prevent the textfield from being user editable
			roll.setEditable(false);
		}
	}
	
	public class Guess extends JPanel {
		
		public Guess() {
			// Create layout
			setLayout(new GridLayout(2, 1));
			setBorder(new TitledBorder (new EtchedBorder(), "Guess"));
			
			// Create GUI elements
			JLabel guessLabel = new JLabel("Guess:");
			JTextField guess = new JTextField();
			
			// Add elements to JPanel
			add(guessLabel);
			add(guess);
			
			// Prevent the textfield from being user editable
			guess.setEditable(false);
		}
	}
	
	public class Result extends JPanel {
		
		public Result() {
			// Create layout
			setLayout(new GridLayout(1, 2));
			setBorder(new TitledBorder (new EtchedBorder(), "Guess Result"));
						
			// Create GUI elements
			JLabel resultLabel = new JLabel("Response:");
			JTextField result = new JTextField();
						
			// Add elements to JPanel
			add(resultLabel);
			add(result);
			
			// Prevent the textfield from being user editable
			result.setEditable(false);
		}
	}
}
