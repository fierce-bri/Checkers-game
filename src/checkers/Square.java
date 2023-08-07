package checkers;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Square {
	
	public static final int SIZE = 60;
	private Piece p = null;
	private int x, y;
	private boolean isDark;
	private int color;
	private boolean highLight;
	
	public Square(int row, int col, boolean isDark) {
		x = col * SIZE;
		y = row * SIZE;
		this.isDark = isDark;
	}
	
	public int getX() {return x; }
	
	public int getY() {return y;}

	public Piece getPiece() {return p;}

	public void addPiece(Piece p){this.p = p;}
	
	public void removePiece(){p = null;} 
	
	public int getXIndex() { return x / SIZE;} 
	
	public int getYIndex() { return y / SIZE;}
	
	public void setHighlight() {
		highLight = true;
	}
	
	public void removeHighlight() {
		highLight = false;
	}
	
	public boolean hasPiece() {
		if (p == null) {
			return false;
		}else {
			return true;
		}
	}

	public void draw(Graphics g) {

		if (highLight) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(5));
			g2.setColor(Color.GREEN);
			g2.draw(new Rectangle2D.Double(x, y, SIZE, SIZE));
		}else {
		  if (isDark) {
		    if (color == Piece.BLACK) {
			  g.setColor(Color.LIGHT_GRAY);
		      g.fillRect(x, y, SIZE, SIZE); 
		    }
		   }else {
			  g.setColor(Color.WHITE);
			  g.fillRect(x, y, SIZE, SIZE);
		   } 
		}
		if (p != null) {
			p.draw(g, x, y, SIZE, SIZE); 
		}
	 }	
}
