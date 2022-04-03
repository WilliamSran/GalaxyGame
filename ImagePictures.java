import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 */

/**
 * @author alira
 * Description: Draws pictures using images
 *
 */
public class ImagePictures extends Pictures{

	/**
	 * Instance variables or attributes
	 */
	private ImageIcon image;
	
	/**
	 * First Constructor
	 */
	public ImagePictures(ImageIcon img) {
		super();
		this.image = img;
		setMyWidth(img.getIconWidth());
		setMyHeight(img.getIconHeight());
		repaint();
	}
	
	/**
	 * Second Constructor
	 */
	
	public ImagePictures (ImageIcon img, int x, int y) {
		super(x, y, img.getIconWidth(), img.getIconHeight());
		this.image = img;
		repaint();
	}
	
	public void paint(Graphics g) {
		this.image.paintIcon(this, g, getxPos(), getyPos());
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// create a JFrame
		JFrame f = new JFrame("Testing");
		
		//create ImagePictures object
		ImagePictures i = new ImagePictures(new ImageIcon("minion.png"));
		
		//set the size, add the image and set visible
		f.setSize(400, 250);
		f.add(i);
		f.setVisible(true);
		
		JOptionPane.showMessageDialog(null,  "wait");
		
		//move my image
		i.setxPos(200);
		i.setyPos(70);
		
		JOptionPane.showMessageDialog(null,  "wait");
		
		//test the second constructor
		ImagePictures i1 = new ImagePictures(new ImageIcon("Gru.jpg"), 100, 50);
		
		f.add(i1); // add the p2 object to the frame
		f.setVisible(true);
		
		JOptionPane.showMessageDialog(null,  "wait");
		
		i1.setxPos(i1.getxPos() + 20);
		i.setyPos(i.getyPos() + 10);
		}

}
