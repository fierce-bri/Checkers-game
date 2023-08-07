package checkers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Board extends JPanel implements MouseListener{
	private static final int numRowCol = 8;
	private Square[][] squares = new Square[numRowCol][numRowCol];
	private Square firstSelected = null;
	private Square secondSelected = null;
	private boolean isBlackTurn = true;
	private int blackPieces = 12;
	private int redPieces = 12;
	public static final int UP = -1;
	public static final int DOWN = 1;
	private int direction;
	private JTextField infoTxt;
	private Square capturedPiece;

	public Square getFirstSelectedSquare() {
		return firstSelected;
	}
	public Board(JTextField infoTxt){
		
		this.infoTxt = infoTxt;
		this.addMouseListener(this);
		this.requestFocusInWindow(true);
		this.setFocusable(true);
		setPreferredSize(new Dimension(Square.SIZE * numRowCol, Square.SIZE * numRowCol));
		
		JPanel infoPanel = new JPanel ();
		infoPanel.setLayout(new FlowLayout()); 
		infoTxt.setEditable(false); 
		infoTxt.setFont(new Font("Bold", Font.BOLD, 16));
		infoTxt.setText("Welcome to the checkers game, move your piece to start"); 
		infoTxt.setHorizontalAlignment(JTextField.CENTER);
		infoTxt.setPreferredSize(new Dimension (600, 45));
		infoPanel.add(infoTxt); 
		
		this.add(infoPanel);
		setPreferredSize(new Dimension(Square.SIZE * 8, Square.SIZE * 9));
		this.setLayout(new BorderLayout());
		this.add(infoPanel, BorderLayout.SOUTH); 

		boolean lastColor = false;
		
		for (int i = 0; i < numRowCol; i ++) {
			for (int j = 0; j < numRowCol; j++) {
				if (lastColor) {
				  squares [i] [j] = new Square (i, j, false);
				}else {
				  squares [i] [j] = new Square(i, j, true);
				 lastColor = !lastColor;
				 
				if (i < 3) {
					squares [i] [j].addPiece(new Piece(Piece.BLACK));  
				  }
				  if (i > 4) {
					squares [i] [j].addPiece(new Piece(Piece.RED));
				  }
				}
				if ((i % 2 == 0 && j % 2 != 0 )|| (i % 2 != 0 && j % 2 == 0)) 
					lastColor = false;
				else 
					lastColor = true;
			  }
			lastColor = !lastColor;   
		}	
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < numRowCol; i++) {
			for (int j = 0; j < numRowCol; j++) {
				squares[i][j].draw(g); 
			}
		}
	}
	
	public Square getSelectedSquare(int row, int col){
		return squares [row] [col]; 
	}
    
    public Square getFirstSelected() {return firstSelected;}
    
    private boolean isInvalidOptionSquare(Square moveOptionSquare, Square SelectedSquare) {
		if (moveOptionSquare == null) {
			return true;
		} else if (moveOptionSquare.getPiece() != null
				&& SelectedSquare.getPiece().getColor() == moveOptionSquare.getPiece().getColor()) {
			//Same color piece in adjacent square
			return true;
		}
		return false;
	}

	public void getSelectedFirstSquare(Square currentSelected) throws InvalidMoveException{
		
		if (currentSelected.getPiece() != null) {
			// Make sure one player can play at a time
			if ((currentSelected.getPiece().getColor() == Piece.BLACK && isBlackTurn)||
			     (currentSelected.getPiece().getColor() == Piece.RED && !isBlackTurn)) {

				Square optionSquare1 = new Square(0, 0, false);
				Square optionSquare2 = new Square(0, 0, false); 
				Square optionSquare3 = new Square(0, 0, false);
				Square optionSquare4 = new Square(0, 0, false);

				// Check if the piece cannot be moved i.e. it will move out of bounds or is
				// blocked by pieces of the same color
				if (currentSelected.getXIndex() - 1 < 0
						|| currentSelected.getYIndex() + 1 * direction > squares.length - 1) {
					optionSquare1 = null;
				} else {
					optionSquare1 = squares[currentSelected.getYIndex() + 1 * direction][currentSelected.getXIndex()- 1];
				     if (currentSelected.getXIndex() + 1 > squares.length - 1
						|| currentSelected.getYIndex() + 1 * direction > squares.length - 1) {
					optionSquare2 = null;
				     } else {
					     optionSquare2 = squares[currentSelected.getYIndex() + 1 * direction]
							[currentSelected.getXIndex()+ 1];
			          }
				  if (currentSelected.getPiece().isCrown()) {
					 if (currentSelected.getXIndex() - 1 < 0|| currentSelected.getYIndex() + 1 * -direction > squares.length - 1) {
					  	optionSquare3 = null;
			        } else {
					     optionSquare3 = squares[currentSelected.getYIndex() + 1 * -direction][currentSelected.getXIndex() - 1];
					}
			  if (currentSelected.getXIndex() + 1 > squares.length - 1|| currentSelected.getYIndex() + 1 * -direction > squares.length- 1){
					  optionSquare4 = null;
				} else {
					  optionSquare4 = squares[currentSelected.getYIndex() + 1 * -direction][currentSelected.getXIndex() + 1];
				  }
				}
				if (currentSelected.getPiece().isCrown() && (!isInvalidOptionSquare(optionSquare1, currentSelected)
					|| !isInvalidOptionSquare(optionSquare2, currentSelected)|| !isInvalidOptionSquare(optionSquare3, currentSelected)
					|| !isInvalidOptionSquare(optionSquare4, currentSelected))) {
					     firstSelected = currentSelected;
					     infoTxt.setText("First Square Selected");
				} else if (!isInvalidOptionSquare(optionSquare1, currentSelected)|| !isInvalidOptionSquare(optionSquare2, currentSelected)){
					     firstSelected = currentSelected;
					     }
			} //else {
				//throw new InvalidMoveException("Cannot select a square that has no possible moves to make");
			//}
		} else {
			throw new InvalidMoveException("Cannot control enemy pieces");
		}
	 } else {
		throw new InvalidMoveException("Select a square with a piece from your team");
	 }
  }
       
	private Square getCapturedSquare(Square firstSelectedSquare, Square secondSelectedSquare) {
		return capturedPiece = squares[firstSelectedSquare.getYIndex()
				- ((firstSelectedSquare.getYIndex() - secondSelectedSquare.getYIndex()) / 2)][firstSelectedSquare.getX()
						/ Square.SIZE - ((firstSelectedSquare.getXIndex() - secondSelectedSquare.getXIndex()) / 2)];
	}
	
	public void getSelectSecondSquare(Square s) throws InvalidMoveException{
		if (s.getPiece() == null && s != firstSelected) {
			if ((((s.getX()/ Square.SIZE) % 2 != 0) && ((s.getY() / Square.SIZE) % 2 == 0))
					||(((s.getX() / Square.SIZE) % 2 == 0) && ((s.getY() / Square.SIZE) % 2 != 0))){ 
					throw new InvalidMoveException("You can't make a move on the white square, select again!");
			}
			if (firstSelected.getPiece().isCrown()) {
				if ((firstSelected.getYIndex() - s.getYIndex() == -1)
						|| (firstSelected.getYIndex() - s.getYIndex() == 1)
								&& ((firstSelected.getXIndex() - s.getXIndex() == 1)
										|| (firstSelected.getXIndex() - s.getXIndex() == -1))) {
					secondSelected = s;
					move();
				} else if ((firstSelected.getYIndex() - s.getYIndex() == -2)
						|| (firstSelected.getYIndex() - s.getYIndex() == 2)
								&& ((firstSelected.getXIndex() - s.getXIndex() == 2)
										|| (firstSelected.getXIndex() - s.getXIndex() == -2))) {
					secondSelected = s;
					infoTxt.setText("Second Square Selected");
					capturedPiece = getCapturedSquare(firstSelected, secondSelected);
					if (capturedPiece.getPiece() == null) {
						throw new InvalidMoveException(
								"Attempting a capture when there is no enemy piece in adjacent square");
					} else {
						capture();
					}
				} else {
					throw new InvalidMoveException("Invalid move for a crowned piece. Reselect your second square.");
				}
			} else {
				// Diagonal square selected in the correct direction
				if (firstSelected.getYIndex() - s.getYIndex() == direction * -1
						&& ((firstSelected.getXIndex() - s.getXIndex() == 1)
								|| (firstSelected.getXIndex() - s.getXIndex() == -1))) {
					secondSelected = s;
					move();

					} else if (firstSelected.getYIndex() - s.getYIndex() == direction * -2
							&& ((firstSelected.getXIndex() - s.getXIndex() == 2)
									|| (firstSelected.getXIndex() - s.getXIndex() == -2))) {
						secondSelected = s;
						infoTxt.setText("Second Square Selected");
						capturedPiece = getCapturedSquare(firstSelected, secondSelected);
						if (capturedPiece.getPiece() == null) {
							throw new InvalidMoveException(
									"Attempting a capture when there is no enemy piece in adjacent square");
						} else {
							capture();
						}
					} else {
						throw new InvalidMoveException("Invalid move. Reselect your second square.");
					}
				}
			} else {
				throw new InvalidMoveException("Invalid move. Cannot move to the same or an occupied square.");
			}
		}

	public void mouseClicked(MouseEvent e) { 
 
		int col = (int) ((e.getX()) / Square.SIZE);
		int row = (int) ((e.getY()) / Square.SIZE);
		Square s = squares[row] [col];
        
		if (isBlackTurn) {
			direction = DOWN;
		} else {
			direction = UP;
		} 

		if (this.firstSelected == null) {
			try {
				getSelectedFirstSquare(s);
			} catch (InvalidMoveException err1) {
				infoTxt.setText(err1.getMessage());
			}
		} else {
			try {
				firstSelected.setHighlight();
				getSelectSecondSquare(s);
			} catch (InvalidMoveException err2) {
				infoTxt.setText(err2.getMessage());
			}
		}
	}
	public void capture() {
		
		if (capturedPiece.getPiece().getColor() == Piece.BLACK && firstSelected.getPiece().getColor() == Piece.RED) {
			blackPieces--;
		} else if (capturedPiece.getPiece().getColor() == Piece.RED
				&& firstSelected.getPiece().getColor() == Piece.BLACK) {
			redPieces--;
		}
		capturedPiece.removePiece();
		move();
	}
	
	public void move() {
	
		Piece p = firstSelected.getPiece();
        firstSelected.removeHighlight();
		firstSelected.removePiece();
		secondSelected.addPiece(p);

		if ((isBlackTurn && secondSelected.getYIndex() == 7) || (secondSelected.getY() / Square.SIZE == 0 && !isBlackTurn)) {
			secondSelected.getPiece().makeCrown();
			infoTxt.setText("\t\tCrown created!");
		}
		firstSelected = null;
	    secondSelected = null;
	    
		if (isBlackTurn) {
			infoTxt.setForeground(Color.BLACK);
			infoTxt.setText("Black made a move");
			isBlackTurn = false;
		}else {
			infoTxt.setForeground(Color.RED);
			infoTxt.setText("Red made a move");
			isBlackTurn = true;
		}
		repaint();
		checkWin();    
	}
	
	public void checkWin() {
		
		if(blackPieces == 0) {
			infoTxt.setText("Red wins, Game over!"); 
		}
		if (redPieces == 0) {
			infoTxt.setText("Black wins, Game over!");
		} 
	}

	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {} 
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
