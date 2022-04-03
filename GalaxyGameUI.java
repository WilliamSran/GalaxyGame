import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author Ali Rauf and William Sran
 * Date: March 2021
 * Description: This program lets the user race 4 bots around a galaxy, and then tells the user which bot won, with how many steps it took.
 * Method List:
 * public GalaxyGameUI() - constructor for galaxy game
 * public void actionPerformed (ActionEvent e) - listens to actions performed and executes certain actions depending on such action executed
 * public static void main(String[] args) - main method
 */

public class GalaxyGameUI extends JFrame implements ActionListener{

	//instance variable for components
	private JPanel drawingPanel, controlPanel, textPanel;
	private JButton btnStart, btnExit, btnStop, btnReset; 

	//instance variables for droid array, timer, and die
	private Droid pic[];
	private Timer timer;  
	private Die d[];   

	private boolean end, timerCheck; //boolean to be used throughout the timer d2uration(doesnt have to be initialized because Java auto initializes booleans as false)

	/**
	 * Creates an instance of Galaxy Game
	 */
	public GalaxyGameUI(){

		super("GalaxyGame");  // title for the frame

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();     //create variables for dimension, width and height of frame, background image name, background ImageIcon, and TextPixture for title
		int width, height;
		ImageIcon galaxy = new ImageIcon ("galaxy.jpg");
		ImagePictures background = new ImagePictures (galaxy);
		TextPicture text;

		pic = new Droid [4];				// initializing the pic array to the droid object 

		d = new Die[2]; //create 2 die

		String imageName[] = new String[] {"C-3PO.png", "R2-D2.png", "Chopper.png", "BB-8.png"};    //create array for droid picture names


		// initialize the width of the frame
		width = 900;
		height = 650;

		// create the dice for the number of steps to be taken
		d[0] = new Die(12);  
		d[1] = new Die(12);

		setLayout(null);  // layout for my frame

		controlPanel = new JPanel();  // panel for buttons
		drawingPanel = new JPanel(); // used only to set the boundaries to draw
		textPanel = new JPanel();  //title panel

		// create and set text objects
		textPanel.setBounds(0, 0, width, 50);
		text = new TextPicture("Galaxy Game!", 270, 40);
		text.setFont(new Font("Algerian", Font.ITALIC + Font.BOLD, 50));
		text.setBounds(0, 0, width, 50);
		textPanel.setBackground(Color.BLACK);
		textPanel.add(text);

		btnStart = new JButton("Let's move");  // create buttons
		btnExit = new JButton("Exit");
		btnStop = new JButton("Stop");
		btnReset = new JButton("Reset");

		//change font and color of buttons
		btnStart.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 20));
		btnStart.setForeground(Color.RED);
		btnExit.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 20));
		btnExit.setForeground(Color.RED);
		btnStop.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 20));
		btnStop.setForeground(Color.RED);
		btnReset.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 20));
		btnReset.setForeground(Color.RED);

		// set the boundary and layout of drawing panel
		drawingPanel.setBounds(0, 50, width, height - 150);
		drawingPanel.setLayout(null);  

		for (int i = 0; i < pic.length; i++) {      //create droids, set the boundarys, add droid to drawing panel, and set initial starting position of droid

			pic[i] = new Droid (new ImageIcon (imageName[i]));
			pic[i].setBounds(0, 0, drawingPanel.getWidth(), drawingPanel.getHeight());   // set its boundaries
			drawingPanel.add(pic[i]);     // add the image to the panel 
			pic[i].setxPos(0);
			pic[i].setyPos(0);
		}

		background.setBounds(0, 0, drawingPanel.getWidth(), drawingPanel.getHeight());     //set bounds for background picture and add it to drawing panel
		drawingPanel.add(background);

		// set the size and position of the control panel
		controlPanel.setBounds(0, height - 100, width, 100);

		btnStart.setBackground(Color.BLACK);    //change background color of buttons 
		btnExit.setBackground(Color.BLACK);
		btnStop.setBackground(Color.BLACK);
		btnReset.setBackground(Color.BLACK);

		// add components to panels
		controlPanel.add(btnStart);    //add button to control panel
		controlPanel.add(btnExit);
		controlPanel.add(btnStop);
		controlPanel.add(btnReset);

		controlPanel.setBackground(Color.BLACK);  
		drawingPanel.setBackground(Color.BLACK);

		add(text);
		add(controlPanel);  // add the button, drawing, and text panels to the frame
		add(drawingPanel);
		add(textPanel);

		timer = new Timer (60, this);  //creates a timer and this class as the listener. set to fire every 60ms
		timer.setInitialDelay (5); //set the initial delay to 5 ms before it starts


		// add the buttons as listeners in this frame
		btnStart.addActionListener(this);
		btnExit.addActionListener(this);
		btnStop.addActionListener(this);
		btnReset.addActionListener(this);
		timer.addActionListener(this);

		// set size and location of frame
		setSize(width,height);  
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		setVisible(true);

		//		drawingPanel.add(background);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}


	@SuppressWarnings("static-access")  //suppress warnings create by UIManager class
	// method for actions events
	public void actionPerformed (ActionEvent e){

		String droidNames[];        //create variables and arrays
		int order[];
		Integer leaderboard[];
		UIManager ui;
		JTextArea chart;

		leaderboard = new Integer [pic.length];                                    //allocate and initialize
		droidNames = new String[] {"C-3PO", "R2-D2", "Chopper", "BB-8"};
		order = new int[4];

		chart = new JTextArea(10, 10);      //edit the look of the JTextArea
		chart.setEditable(false);
		chart.setBackground(Color.BLACK);
		chart.setFont(new Font ("Serif", Font.ITALIC + Font.BOLD, 12));
		chart.setForeground(Color.RED);

		ui = new UIManager();  //create UI manager

		if (e.getSource() == btnStart){  // if button is clicked

			if (end == false) {						// if the program has only run once, start the timer
				timer.start();
				timerCheck = true;
			}

			else {						// else, tell the user to reset

				ui.put("OptionPane.messageForeground", Color.red);
				ui.put("Panel.background", Color.BLACK);

				JOptionPane.showMessageDialog(null, "Uh Oh! The game is already over!\nDon't worry, just press the reset button to play again!", "Listen!", JOptionPane.PLAIN_MESSAGE);
			}
		}

		else if (e.getSource() == btnReset) {	 //reset button

			if (timerCheck == false) {      //restart timer
				timer.start();
			}

			for (int i = 0; i < pic.length; i++) {    //clear droid data
				pic[i].setxPos(0);
				pic[i].setyPos(0);
				pic[i].setRegion(0);
				pic[i].setTotalSteps(0);
				pic[i].setStepsToMove(0);

			}
			end = false;
		}
		else if (e.getSource() == btnExit) {	// if button is clicked, end the program
			System.exit(0);
		}
		else if (e.getSource() == btnStop) {	// if button is clicked, stop the images where they are
			timer.stop();
			timerCheck = false;
		}
		else if (e.getSource()==timer){	
			// if the timer fires, go through each of the 
			// droids and set its new position

			loop:  //label
				for (int i = 0; i < pic.length; i++) {					//loops through each bot

					d[0].rollDie(); //roll the dice
					d[1].rollDie();

					pic[i].setStepsDroid(d[0].getValue(), d[1].getValue());    //set the steps the certain droid should take depending on the dice roll


					for (int j = 0; j < pic[i].getStepsToMove(); j++) {					// loop through all the steps to be moved for the certain droid

						if (pic[i].getRegion() == 0) {			// if the bot is at the top on the drawing panel 

							pic[i].botMove(drawingPanel.getWidth(), drawingPanel.getHeight());  //move right
						}

						else if (pic[i].getRegion() == 1) { 	// if the bot is at the right side of the drawing panel 

							pic[i].botMove(drawingPanel.getWidth(), drawingPanel.getHeight());  //move down
						}

						else if (pic[i].getRegion() == 2) {	// if the bot is at the bottom of the drawing panel

							pic[i].botMove(drawingPanel.getWidth(), drawingPanel.getHeight()); //move left
						}

						else if (pic[i].getRegion() == 3){			// if the bot is at the laft side of the drawing panel

							pic[i].botMove(drawingPanel.getWidth(), drawingPanel.getHeight()); //move upwards
						}

						else if (pic[i].getRegion() == 4) {		// if the bot reaches the origin after one rotation of the field

							timer.stop();												// stop the timer 
							timerCheck = false;

							if (end == false) {

								for (int k = 0; k < pic.length; k++) {    //set steps to move as 0 for each bot and store each bot's total steps inside an array
									pic[k].setStepsToMove(0);
									leaderboard[k] = pic[k].getTotalSteps();
								}

								Arrays.sort(leaderboard, Collections.reverseOrder());  //sort the leaderboard array in descending order

								for(int z = 0; z < leaderboard.length; z++) { //determine the order of wwhich droid won and store the rankings inside order array

									for(int x = 0; x < pic.length; x++) {   

										if(leaderboard[z] == pic[x].getTotalSteps()) {
											order[z] = x;
										}
									}

								}
								ui.put("OptionPane.background", Color.black); //change background color of JTextArea and JOptionPane
								ui.put("Panel.background", Color.black);

								chart.setText("");  //clear JTextArea

								for(int q = 0; q < order.length; q++) {   //add each droid and their steps to a JTextArea

									chart.append((q+1) + ": " + droidNames[order[q]] + "---Steps Taken: " + pic[order[q]].getTotalSteps() + "\n");
								}

								JOptionPane.showMessageDialog(null, chart, "Results", JOptionPane.PLAIN_MESSAGE);   //Display the race leaderboard


								end = true;
							}

							break loop; //break out of first for loop
						}

					}

				}

		}// if timer

	}// actionPerformed

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {

		GalaxyGameUI animation = new GalaxyGameUI();   //make a galaxygame object
	}
}