/**
 * 
 */

/**
 * @author alira
 *
 */
public class Die {

	/**
	 * Data/attributes/instance variables
	 */
	private int faces;
	private int value;
	
	/**
	 * Default Constructor for a "normal" Die
	 */
	public Die() {
		// initialize its instance variables
		this.faces = 6;
		rollDie();
	}
	
	/**
	 * New overloaded constructor
	 */
	public Die (int faces) {
		// initialize the instance variables
		this.faces = faces;
		rollDie();
	}
	
	/**
	 * method to roll the die
	 */
	public void rollDie() {
		this.value = (int)(Math.random() * faces + 1);
	}
	
	/**
	 * method to read its value
	 */
	public int getValue() {
		return this.value;
	}

	/**
	 * self - testing main
	 * @param args
	 */
	public static void main(String[] args) {
		// create a die object to test the constructor
		Die d;
		d = new Die();
		
		System.out.println(d.getValue());
		
		Die d1 = new Die();
		
		System.out.println(d1.getValue());
		
		// roll the die 10 times
		for (int i = 0; i <= 9; i++) {
			d.rollDie();
			d1.rollDie();
			System.out.println(d.getValue() + " " + d1.getValue());
		}
		
		// test the new type of dice
		Die d2 = new Die(12);
		
		//get its value
		System.out.println(d2.getValue());

	}

}
