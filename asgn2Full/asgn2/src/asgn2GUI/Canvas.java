package asgn2GUI;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.JProgressBar;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.Point;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Dimension;

public class Canvas extends JPanel {
	public static final int RECTANGLE=0;
	public static final int SQUARE=1;
	public static final int STRING=2;
	public int figure=0; 
	
	public Canvas(){
		setPreferredSize(new Dimension(100, 100));
		
		JLabel lblS = new JLabel("");
		lblS.setPreferredSize(new Dimension(50, 85));
		lblS.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblS.setVerticalAlignment(SwingConstants.BOTTOM);
		add(lblS);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setPreferredSize(new Dimension(100, 15));
		add(progressBar);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); 
		switch(this.figure) {
		case 0:
	      g.setColor(Color.RED);
		  g.drawRect(0, 0, 100, 100);
		  g.fillRect(0, 0, 100, 100);
		  g.setColor(Color.BLACK);
		  g.drawString("Locomotive", 25, 25);
		  break; 
		case 1:
	      g.setColor(Color.GREEN);
		  g.drawRect(0, 0, 100, 100);
		  g.fillRect(0, 0, 100, 100);
		  g.setColor(Color.BLACK);
		  g.drawString("Passenger", 25, 25);
		  break;
		case 2:
	      g.setColor(Color.ORANGE);
		  g.drawRect(0, 0, 100, 100);
		  g.fillRect(0, 0, 100, 100);
		  g.setColor(Color.BLACK);
		  g.drawString("Freight", 25, 25);
		  break;
		}
	}
}
