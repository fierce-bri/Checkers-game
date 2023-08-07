package checkers;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Piece {

	public static final int BLACK = 0;
	public static final int RED = 1;

	private int color;
	private Image image = null;  
	private boolean isCrown = false;

	public Piece(int color) {
		this.color = color;
		try {
			  if (color == BLACK) {
				   image = ImageIO.read(new File("images/black.gif"));
			   }
			   else {
				   image = ImageIO.read(new File("images/red.gif"));
			   }
			} catch (IOException e) {
				System.out.println(e.getStackTrace());
		}
	}
	
    public void makeCrown() {
		
		isCrown = true;
		try {
			  if (color == BLACK) {
				  image = ImageIO.read(new File("images/blackcrown.gif"));
			  }
			  else if (color == RED){
				  image = ImageIO.read(new File("images/redcrown.gif"));
			  }
		  } catch (IOException e) {
			   System.out.println(e.getMessage());  
		}
	}

	public int getColor() {return color;}
	public Image getImage() {return image;}
	public boolean isCrown() {return isCrown;}

	public void draw(Graphics g, int x, int y, int height, int width) {
			   g.drawImage(image, x + 6, y + 6, (int) (width * 0.8), (int)(height * 0.8), null); 
	}
}
