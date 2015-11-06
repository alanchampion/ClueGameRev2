package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class DetectiveGUI extends JDialog {
	public DetectiveGUI() {
		setTitle("Detective Notes");
		setSize(500, 500);
		setVisible(false);
		
		JPanel detectivePanel = new JPanel();
		detectivePanel.setLayout(new GridLayout(3, 2));
		add(detectivePanel, BorderLayout.CENTER);
		
		PeopleNotes peopleNotes = new PeopleNotes();
		PersonGuess personGuess = new PersonGuess();
		RoomNotes roomNotes = new RoomNotes();
		RoomGuess roomGuess = new RoomGuess();
		WeaponNotes weaponNotes = new WeaponNotes();
		WeaponGuess weaponGuess = new WeaponGuess();
		
		detectivePanel.add(peopleNotes);
		detectivePanel.add(personGuess);
		detectivePanel.add(roomNotes);
		detectivePanel.add(roomGuess);
		detectivePanel.add(weaponNotes);
		detectivePanel.add(weaponGuess);
	}

	public class PeopleNotes extends JPanel {
		public PeopleNotes() {
			setLayout(new GridLayout(3, 2));
			setBorder(new TitledBorder (new EtchedBorder(), "Persons"));
			
			JRadioButton missScarlet = new JRadioButton("Miss Scarlet");
			JRadioButton mrGreen = new JRadioButton("Mr. Green");
			JRadioButton mrsPeacock = new JRadioButton("Mrs. Peacock");
			JRadioButton colonelMustard = new JRadioButton("Colonel Mustard");
			JRadioButton mrsWhite = new JRadioButton("Mrs. White");
			JRadioButton professorPlum = new JRadioButton("Professor Plum");
			
			add(missScarlet);
			add(mrGreen);
			add(mrsPeacock);
			add(colonelMustard);
			add(mrsWhite);
			add(professorPlum);
		}
	}
	
	public class PersonGuess extends JPanel {
		public PersonGuess() {
			setLayout(new GridLayout(1, 1));
			setBorder(new TitledBorder (new EtchedBorder(), "Person Guess"));
			
			JComboBox<String> personGuessComboBox = new JComboBox<String>();
			
			add(personGuessComboBox);
	
			personGuessComboBox.addItem("Miss Scarlet");
			personGuessComboBox.addItem("Mr. Green");
			personGuessComboBox.addItem("Mrs. Peacock");
			personGuessComboBox.addItem("Colonel Mustard");
			personGuessComboBox.addItem("Mrs. White");
			personGuessComboBox.addItem("Professor Plum");
		}
	}
	
	public class RoomNotes extends JPanel {
		public RoomNotes() {
			setLayout(new GridLayout(5, 2));
			setBorder(new TitledBorder (new EtchedBorder(), "Rooms"));
			
			JRadioButton conservatory = new JRadioButton("Conservatory");
			JRadioButton kitchen = new JRadioButton("Kitchen");
			JRadioButton ballroom = new JRadioButton("Ballroom");
			JRadioButton billiard = new JRadioButton("Billiard Room");
			JRadioButton library = new JRadioButton("Library");
			JRadioButton study = new JRadioButton("Study");
			JRadioButton dining = new JRadioButton("Dining Room");
			JRadioButton lounge = new JRadioButton("Lounge");
			JRadioButton hall = new JRadioButton("Hall");
			
			add(conservatory);
			add(kitchen);
			add(ballroom);
			add(billiard);
			add(library);
			add(study);
			add(dining);
			add(lounge);
			add(hall);
		}
	}
	
	public class RoomGuess extends JPanel {
		public RoomGuess() {
			setLayout(new GridLayout(1, 1));
			setBorder(new TitledBorder (new EtchedBorder(), "Room Guess"));
			
			JComboBox<String> roomGuessComboBox = new JComboBox<String>();
			
			add(roomGuessComboBox);
			
			roomGuessComboBox.addItem("Conservatory");
			roomGuessComboBox.addItem("Kitchen");
			roomGuessComboBox.addItem("Ballroom");
			roomGuessComboBox.addItem("Billiard Room");
			roomGuessComboBox.addItem("Library");
			roomGuessComboBox.addItem("Study");
			roomGuessComboBox.addItem("Dining Room");
			roomGuessComboBox.addItem("Lounge");
			roomGuessComboBox.addItem("Hall");
		}
	}
	
	public class WeaponNotes extends JPanel {
		public WeaponNotes() {
			setLayout(new GridLayout(3, 2));
			setBorder(new TitledBorder (new EtchedBorder(), "Weapons"));
			
			JRadioButton candlestick = new JRadioButton("Candlestick");
			JRadioButton revolver = new JRadioButton("Revolver");
			JRadioButton rope = new JRadioButton("Rope");
			JRadioButton wrench = new JRadioButton("Wrench");
			JRadioButton leadPipe = new JRadioButton("Lead Pipe");
			JRadioButton knife = new JRadioButton("Knife");
			
			add(candlestick);
			add(revolver);
			add(rope);
			add(wrench);
			add(leadPipe);
			add(knife);
		}
	}
	
	public class WeaponGuess extends JPanel {
		public WeaponGuess() {
			setLayout(new GridLayout(1, 1));
			setBorder(new TitledBorder (new EtchedBorder(), "Weapon Guess"));
			
			JComboBox<String> weaponGuessComboBox = new JComboBox<String>();
			
			add(weaponGuessComboBox);
			
			weaponGuessComboBox.addItem("Candlestick");
			weaponGuessComboBox.addItem("Knife");
			weaponGuessComboBox.addItem("Lead Pipe");
			weaponGuessComboBox.addItem("Revolver");
			weaponGuessComboBox.addItem("Rope");
			weaponGuessComboBox.addItem("Wrench");
		}
	}
}
