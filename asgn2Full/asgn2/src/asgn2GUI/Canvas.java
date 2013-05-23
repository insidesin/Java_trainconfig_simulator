package asgn2GUI;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JLabel;

import java.awt.Dimension;
import javax.swing.SwingConstants;

public class Canvas extends JPanel {

	public static final int WIDTH = 120;
	public static final int HEIGHT = 100;
	public static final int RECTANGLE = 0;
	public static final int SQUARE = 1;
	public static final int STRING = 2;

	public int figure = 0;
	
	private JProgressBar progressBar;
	private JLabel lblCarriage;
	
	public Canvas(){
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		lblCarriage = new JLabel("");
		lblCarriage.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCarriage.setHorizontalAlignment(SwingConstants.CENTER);
		lblCarriage.setPreferredSize(new Dimension(WIDTH, 85));
		add(lblCarriage);
		
		progressBar = new JProgressBar();
		progressBar.setPreferredSize(new Dimension(WIDTH, 15));
		add(progressBar);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); 
		switch(this.figure) {
		case 0:
	      g.setColor(Color.YELLOW);
		  g.drawRect(0, 0, WIDTH, HEIGHT);
		  g.fillRect(0, 0, WIDTH, HEIGHT);
		  break; 
		case 1:
	      g.setColor(Color.GREEN);
		  g.drawRect(0, 0, WIDTH, HEIGHT);
		  g.fillRect(0, 0, WIDTH, HEIGHT);
		  break;
		case 2:
	      g.setColor(Color.ORANGE);
		  g.drawRect(0, 0, WIDTH, HEIGHT);
		  g.fillRect(0, 0, WIDTH, HEIGHT);
		  break;
		}
	}
	
	public JProgressBar getProgressBar() {
		return progressBar;
	}

	public JLabel getCarriageLabel() {
		return lblCarriage;
	}
}
