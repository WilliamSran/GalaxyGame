
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * 
 */

/**
 * @author alira
 * Date: March. 2021
 * Description: This program stores the number of steps that an image should move, and allows other classes to access that data
 * Method List: 
 *				public void setSteps (int stepsToMove)
 *				public int getSteps ()
 *				public SmartMinion(ImageIcon img)
 *				public static void main(String[] args)
 */
public class SmartMinion extends ImagePictures{

	private int stepsToMove;						// variable for the steps to move the image

	public void setSteps (int stepsToMove) {		// setter for steps to move
		this.stepsToMove = stepsToMove;
	}

	public int getSteps () {						// getter for steps to move
		return stepsToMove;
	}

	/**
	 * 
	 */
	public SmartMinion(ImageIcon img) {				// constructor for smart minion
		super(img);
		stepsToMove = 100;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {							// self testing main
		// create a JFrame
		JFrame f = new JFrame("Testing");

		//create SmartMinion object
		SmartMinion i = new SmartMinion(new ImageIcon("minion.png"));

		//set the size, add the image and set visible
		f.setSize(400, 250);
		f.add(i);
		f.setVisible(true);

		//move my image
		i.setSteps(200);

		//output the number of steps that were chosen
		System.out.println(i.getSteps());

	}

}
