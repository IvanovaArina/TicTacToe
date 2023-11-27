package com.firstProject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.*;
public class TicTacToe extends JComponent {
	
public static final int FIELD_EMPTY = 0;
public static final int FIELD_X = 10;
public static final int FIELD_O = 200;
int[][] field;
boolean isXturn;
	
	TicTacToe()  {
		enableEvents(AWTEvent.MOUSE_EVENT_MASK);
		field = new int[3][3];
		initGame();
	}
	
	public void initGame() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				field[i][j] = FIELD_EMPTY;
			}
		}
	}
	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		graphics.clearRect(0, 0, getWidth(), getHeight());
//		graphics.setColor(Color.black);
//		((Graphics2D) graphics).setStroke(new BasicStroke(5));
//		graphics.drawOval(10, 10, 100, 100);
		drawGrid(graphics);
		drawXO(graphics);
		int result = checkState();
		if(result != 0 && result != -1) {
			strikeOut(graphics);
		}
	}
	
	@Override
	protected void processMouseEvent(MouseEvent mouseEvent) {
		super.processMouseEvent(mouseEvent);
		if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
			int x = mouseEvent.getX();
			int y = mouseEvent.getY();
			
			int i = (int)((float) y / getHeight() * 3);
			int j = (int)((float) x / getWidth() * 3);
			
			
			if (field[i][j] == FIELD_EMPTY) {
				field[i][j] = isXturn?FIELD_X:FIELD_O;
				isXturn = !isXturn;
				repaint();
				int result = checkState();
				if(result != 0) {
					if(result == 3 * FIELD_X) {
						//crosses have won
						JOptionPane.showMessageDialog(this, "Выиграли крестики!", "Победа!", JOptionPane.INFORMATION_MESSAGE);
					}
					else if(result == 3 * FIELD_O) {
						//zeros have won
						JOptionPane.showMessageDialog(this, "Выиграли нолики!", "Победа!", JOptionPane.INFORMATION_MESSAGE);
					}
					else JOptionPane.showMessageDialog(this, "Ничья!", "Ничья!", JOptionPane.INFORMATION_MESSAGE);
					initGame();
					repaint();
				}
			}
		}
	}
	
	private void drawGrid(Graphics graphics) {
		int w = getWidth();
		int h = getHeight();
		int dh = h/3;
		int dw = w/3;
		
		((Graphics2D)graphics).setStroke(new BasicStroke(3));
		graphics.setColor(Color.DARK_GRAY);
		
		for(int i = 1; i < 3; i ++) {
			graphics.drawLine(0, dh * i, w, dh * i); //horizontal lines
			graphics.drawLine(dw * i, 0,  dw * i, h); //vertical lines
		}
	}
	
	private void drawX(int i, int j, Graphics graphics) {
		graphics.setColor(Color.BLACK);
		int dw = getWidth() / 3;
		int dh = getHeight() / 3;
		int x = j * dw;
		int y = i * dh;
		graphics.drawLine(x + 10, y + 10, x + dw - 10, y + dh - 10);
		graphics.drawLine(x + dw - 10, y + 10, x + 10, y + dh - 10);
	}
	
	private void drawO(int i, int j, Graphics graphics) {
		graphics.setColor(Color.GRAY);
		int dw = getWidth() / 3;
		int dh = getHeight() / 3;
		int x = j * dw;
		int y = i * dh;
		graphics.drawOval(x + 5 , y + 5, dw - 10, dh - 10);
		
	}
	
	private void drawXO(Graphics graphics) {
		for (int i = 0; i < 3; i ++) {
			for (int j = 0; j < 3; j ++) {
				if (field[i][j] == FIELD_X) {
					drawX(i, j, graphics);
				} else if (field[i][j] == FIELD_O) {
					drawO(i, j, graphics);
				}
			}
		}
	}
	
	private int checkState() {
		//check diagonals
		int diagonal1 = 0;
		int diagonal2 = 0;
		
		for(int i = 0; i < 3; i ++) {
			diagonal1 += field[i][i];
			diagonal2 += field[i][2 - i];
		}
		
		if(diagonal1 == 3 * FIELD_X || diagonal1 == 3* FIELD_O) 
			return diagonal1;
		if(diagonal2 == 3 * FIELD_X || diagonal2 == 3* FIELD_O) 
			return diagonal2; 
		
		int row, column;
		boolean hasEmpty = false;
		
		for (int i = 0; i < 3; i ++) {
			row = 0;
			column = 0;
			for (int j = 0; j < 3; j ++) {
				if(field[i][j] == FIELD_EMPTY) {
					hasEmpty = true;
				}
				row += field[i][j];
				column += field[j][i];
			}
			if (row == 3 * FIELD_X || row == 3* FIELD_O) 
				return row;
			if (column == 3 * FIELD_X || column == 3* FIELD_O) 
				return column;			
		}
		if(hasEmpty) return 0;//the game is still running
		else return -1; // the game ended in a tie
		
	}
	
	private String checkPosition() {
		//check diagonals
				int diagonal1 = 0;
				int diagonal2 = 0;
				
				for(int i = 0; i < 3; i ++) {
					diagonal1 += field[i][i];
					diagonal2 += field[i][2 - i];
				}
				
				if(diagonal1 == 3 * FIELD_X || diagonal1 == 3* FIELD_O) 
					return "diagonal1";
				if(diagonal2 == 3 * FIELD_X || diagonal2 == 3* FIELD_O) 
					return "diagonal2"; 
				
				int row, column;
				boolean hasEmpty = false;
				int checkI = 0;
				
				for (int i = 0; i < 3; i ++) {
					row = 0;
					column = 0;
					for (int j = 0; j < 3; j ++) {
						if(field[i][j] == FIELD_EMPTY) {
							hasEmpty = true;
						}
						row += field[i][j];
						column += field[j][i];
						checkI = i;
						
					}
					if (row == 3 * FIELD_X || row == 3* FIELD_O) 
						return "row" + checkI;
					if (column == 3 * FIELD_X || column == 3* FIELD_O) 
						return "column" + checkI;			
				}
				if(hasEmpty) return "";//the game is still running
				else return "tie"; // the game ended in a tie
				
	}
	
	private void strikeOut(Graphics graphics) {
		int w = getWidth();
		int h = getHeight();
		int dh = h/3;
		int dw = w/3;
		((Graphics2D)graphics).setStroke(new BasicStroke(4));
		graphics.setColor(Color.red);
		String currentSituation = checkPosition();
		
			if(currentSituation.equals("diagonal1")) {
				graphics.drawLine(0, 0, w, h);
			}
			if(currentSituation.equals("diagonal2")) {
				graphics.drawLine(w, 0, 0, h);
			}
			if(currentSituation.equals("row0")) {
				graphics.drawLine(0, dh / 2, w, dh / 2);
			}
			if(currentSituation.equals("row1")) {
				graphics.drawLine(0, dh + dh / 2, w, dh + dh / 2);
			}
			if(currentSituation.equals("row2")) {
				graphics.drawLine(0, 2 * dh + dh / 2, w, 2 * dh + dh / 2);
			}
			if(currentSituation.equals("column0")) {
				graphics.drawLine(dw / 2, 0, dw / 2, h);
			}
			if(currentSituation.equals("column1")) {
				graphics.drawLine(dw + dw / 2, 0, dw + dw / 2, h);
			}
			if(currentSituation.equals("column2")) {
				graphics.drawLine(2 * dw + dw / 2, 0, 2 * dw + dw / 2, h);
			}
		
		
}
		
	}
	
