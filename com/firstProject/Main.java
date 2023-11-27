package com.firstProject;
import java.awt.*;

import javax.swing.*;
public class Main {

	public static void main(String[] args) {
		System.out.println("The game is starting...");
		JFrame w = new JFrame("TicTacToe");
		w.setSize(400, 400);
		w.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		w.setLayout(new BorderLayout());
		w.setLocationRelativeTo(null);
		w.setVisible(true);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image iconImage = toolkit.getImage("C:\\Users\\arina\\Pictures\\Сохранённые\\TicTacToe.png");
		w.setIconImage(iconImage);
		
		TicTacToe game = new TicTacToe();
		w.add(game);
		game.revalidate();
		System.out.println("The end...");
		
	}

}
