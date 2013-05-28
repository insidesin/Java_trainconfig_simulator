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

	public int figure = 0;
	
	private static final int LOCOMOTIVE_PAINT = 0;
	private static final int PASSENGERCAR_PAINT = 1;
	private static final int FREIGHTCAR_PAINT = 2;
	
	private JProgressBar progressBar;
	private JLabel lblCarriage;
	
	public Canvas(){
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		//Set the label of what carriage this is. (will be)
		lblCarriage = new JLabel("");
		lblCarriage.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCarriage.setHorizontalAlignment(SwingConstants.CENTER);
		lblCarriage.setPreferredSize(new Dimension(WIDTH, 85));
		add(lblCarriage);
		
		/* Progress bar defines a simple way to see how much capacity is
		 * currently taken up in this carriage. i.e. Power used before being
		 * unable to move for Locomotive and Passengers on board with 
		 * PassengerCars.
		 */
		progressBar = new JProgressBar();
		progressBar.setPreferredSize(new Dimension(WIDTH, 15));
		add(progressBar);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); 
		//Switch different figures for each type of RollingStock.
		switch(this.figure) {
		case LOCOMOTIVE_PAINT:
	      g.setColor(Color.YELLOW);
		  g.drawRect(0, 0, WIDTH, HEIGHT);
		  g.fillRect(0, 0, WIDTH, HEIGHT);
		  break; 
		case PASSENGERCAR_PAINT:
	      g.setColor(Color.GREEN);
		  g.drawRect(0, 0, WIDTH, HEIGHT);
		  g.fillRect(0, 0, WIDTH, HEIGHT);
		  break;
		case FREIGHTCAR_PAINT:
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
