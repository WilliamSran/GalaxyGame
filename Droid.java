
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 * @author Ali Rauf and William Sran
 * Date: March 2021
 * Description: inherits from image picture to create droid objects and keeps track of each droids region and steps
 * Method List:
 * public Droid2(ImageIcon img) - default contructor for droids
 * public void setStepsDroid(int d, int d1) - sets the steps a droid must take based on two integers and an algorithm
 * public void botMove(int panelWidth, int panelHeight) - moves the droid along a certain path
 * public int getStepsToMove() - returns steps a droid should move 
 * public void setStepsToMove(int stepsToMove) - takes in an integer and sets it as the droid's steps it should move 
 * public int getRegion() - returns the region the droid is in
 * public void setRegion(int region) - sets the region the droid is in
 * public static void main(String[] args) - self testing main method
 */
public class Droid extends ImagePictures{

	private int stepsToMove;  //instance data for droid
	private int totalSteps;
	private int region;

	//default constructor
	public Droid(ImageIcon img) {

		super(img);

		this.stepsToMove = 100; //initialize attributes of droid
		this.totalSteps = 0;
		this.region = 0;
	}

	public void setStepsDroid(int d, int d1) {   

		int steps = 0;

		steps = d + d1;  //the amount of steps is equal to the sum of the two integers intputted 

		if(steps == 4 || steps == 6 || steps == 24) {  //except if the sum equals 4, 6, or 24 then the steps is 0
			steps = 0;
		}
		this.stepsToMove = steps;
	}

	public void botMove(int panelWidth, int panelHeight) {

		if(this.getyPos() == 0 && (this.getxPos() + this.getMyWidth()) < panelWidth) {    //if droid is at top of panel

			this.setxPos(this.getxPos() + 1);  //move it right and add one to total steps
			this.totalSteps += 1;

			if(this.getyPos() == 0 && (this.getxPos() + this.getMyWidth()) == panelWidth) {      //if the droid has hit the top right corner of the panel then change the region it is in
				this.region = 1;
			}
		}
		else if((this.getyPos() + this.getMyHeight()) < panelHeight && (this.getxPos() + this.getMyWidth()) == panelWidth) {   //if the droid is on the right side of the panel 

			this.setyPos(this.getyPos() + 1);  //move the droid down and add one to total steps
			this.totalSteps += 1;

			if((this.getyPos() + this.getMyHeight()) == panelHeight && (this.getxPos() + this.getMyWidth()) == panelWidth) {  //if the droid has hit the bottom right corner of the panel then change the region it is in
				this.region = 2;
			}
		}
		else if((this.getyPos() + this.getMyHeight()) == panelHeight && this.getxPos() > 0) {  //if the droid is at the botton of the panel

			this.setxPos(this.getxPos() - 1);  //move it left and add one to total steps
			this.totalSteps += 1;

			if((this.getyPos() + this.getMyHeight()) == panelHeight && this.getxPos() == 0) {  //if the droid has hit the bottom left corner of the panel then update the region
				this.region = 3;
			}
		}
		else if(this.getyPos() > 0 && this.getxPos() == 0) {   //if the droid is located on the left side of the panel

			this.setyPos(this.getyPos() - 1);  //move it upwards and add one total steps 
			this.totalSteps += 1;

			if(this.getxPos() == 0 && this.getyPos() == 0) {   //if the droid has hit the origin position then change the region
				this.region = 4;
			}
		}
	}


	/**
	 * @return the stepsToMove
	 */
	public int getStepsToMove() {
		return stepsToMove;
	}

	/**
	 * @param stepsToMove the stepsToMove to set
	 */
	public void setStepsToMove(int stepsToMove) {
		this.stepsToMove = stepsToMove;
	}

	/**
	 * @return the totalSteps
	 */
	public int getTotalSteps() {
		return totalSteps;
	}

	/**
	 * @param totalSteps the totalSteps to set
	 */
	public void setTotalSteps(int totalSteps) {
		this.totalSteps = totalSteps;
	}

	/**
	 * @return the region
	 */
	public int getRegion() {
		return region;
	}

	/**
	 * @param region the region to set
	 */
	public void setRegion(int region) {
		this.region = region;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// create a JFrame

		JFrame f = new JFrame("Testing");
		int width = 800;
		int height = 600;

		//set the size, set the bounds and set visible
		f.setSize(width, height);


		//create Droid object and setting its attributes in the frame
		Droid i = new Droid(new ImageIcon("C-3PO.png"));
		i.setBounds(0, 0, width, height);
		f.add(i);
		i.setxPos(0);
		i.setyPos(0);

		f.setVisible(true);





		// delaying to show the user the difference between the image's old and new positions in first row
		JOptionPane.showMessageDialog(null, "Wait");

		//move the image and output the region it is in
		i.setStepsDroid(100, 150);

		for (int k = 0; k < i.getStepsToMove(); k++) {
			i.botMove(width, height - i.getMyHeight());
		}

		System.out.println("The image's region is currently region " + i.getRegion());

		// testing the move to the vertical 
		i.setStepsToMove(800);

		JOptionPane.showMessageDialog(null, "Wait");

		for (int k = 0; k < i.getStepsToMove(); k++) {
			i.botMove(width, height - i.getMyHeight());
		}

		System.out.println("The image's region is currently region " + i.getRegion());

		// testing the move to the second horizontal
		i.setStepsToMove(600);

		JOptionPane.showMessageDialog(null, "Wait");

		for (int k = 0; k < i.getStepsToMove(); k++) {
			i.botMove(width, height - i.getMyHeight());
		}

		System.out.println("The image's region is currently region " + i.getRegion());

		// testing the move to the second vertical
		i.setStepsToMove(800);

		JOptionPane.showMessageDialog(null, "Wait");

		for (int k = 0; k < i.getStepsToMove(); k++) {
			i.botMove(width, height - i.getMyHeight());
		}

		System.out.println("The image's region is currently region " + i.getRegion());

		// outputting x and y positions
		System.out.println("The x position is " + i.getxPos() + "\nThe y position is " + i.getyPos());

		// testing the get and set total steps methods
		System.out.println("The image went " + i.getTotalSteps() + " steps");

		i.setTotalSteps(0);

		System.out.println("The step reset left the bot at " + i.getTotalSteps() + " steps");

		// testing the set and get region methods
		i.setRegion(0);

		System.out.println("The region reset left the bot at " + i.getRegion());


	}

}
