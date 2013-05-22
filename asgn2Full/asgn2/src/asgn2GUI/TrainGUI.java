
package asgn2GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import javax.swing.BoxLayout;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.border.EtchedBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TrainGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = -7031008862559936404L;
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	private JTextField txtBoxGoodsInput;
	 
	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public TrainGUI(String arg0) throws HeadlessException {
		super(arg0);
		createGUI();
	}
	
	private void createGUI() {
		setSize(WIDTH, HEIGHT);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    BorderLayout borderLayout = new BorderLayout();
	    getContentPane().setLayout(borderLayout);
	    
	    JPanel setupViewContainer = new JPanel();
	    setupViewContainer.setBorder(null);
	    getContentPane().add(setupViewContainer, BorderLayout.NORTH);
	    setupViewContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 15));
	    
	    JScrollPane carriageScrollPane = new JScrollPane();
	    carriageScrollPane.setBorder(null);
	    carriageScrollPane.setPreferredSize(new Dimension(750, 135));
	    carriageScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
	    setupViewContainer.add(carriageScrollPane);
	    
	    JPanel carriagePanel = new JPanel();
	    carriageScrollPane.setViewportView(carriagePanel);
	    carriagePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
	    
	    Canvas canvas_1 = new Canvas();
	    canvas_1.setPreferredSize(new Dimension(100, 100));
	    canvas_1.setMinimumSize(new Dimension(100, 100));
	    carriagePanel.add(canvas_1);
	    FlowLayout fl_canvas_1 = new FlowLayout(FlowLayout.CENTER, 0, 0);
	    canvas_1.setLayout(fl_canvas_1);
	    
	    Canvas canvas_2 = new Canvas();
	    canvas_2.figure = 1;
	    canvas_2.setPreferredSize(new Dimension(100, 100));
	    canvas_2.setMinimumSize(new Dimension(100, 100));
	    carriagePanel.add(canvas_2);
	    canvas_2.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
	    
	    Canvas canvas_3 = new Canvas();
	    canvas_3.figure = 2;
	    canvas_3.setPreferredSize(new Dimension(100, 100));
	    canvas_3.setMinimumSize(new Dimension(100, 100));
	    carriagePanel.add(canvas_3);
	    canvas_3.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
	    
	    Canvas canvas_4 = new Canvas();
	    canvas_4.figure = 2;
	    canvas_4.setPreferredSize(new Dimension(100, 100));
	    canvas_4.setMinimumSize(new Dimension(100, 100));
	    carriagePanel.add(canvas_4);
	    canvas_4.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
	    
	    JPanel contentPanel = new JPanel();
	    getContentPane().add(contentPanel, BorderLayout.CENTER);
	    contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
	    
	    JPanel leftDriverPanel = new JPanel();
	    contentPanel.add(leftDriverPanel);
	    leftDriverPanel.setLayout(new BorderLayout(0, 0));
	    
	    JPanel leftDriverPanelFloatRight = new JPanel();
	    leftDriverPanel.add(leftDriverPanelFloatRight, BorderLayout.EAST);
	    leftDriverPanelFloatRight.setAlignmentX(Component.RIGHT_ALIGNMENT);
	    leftDriverPanelFloatRight.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    leftDriverPanelFloatRight.setPreferredSize(new Dimension(375, 10));
	    leftDriverPanelFloatRight.setMinimumSize(new Dimension(350, 10));
	    leftDriverPanelFloatRight.setLayout(new BorderLayout(0, 0));
	    
	    JPanel carriageInfoPanel = new JPanel();
	    carriageInfoPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    FlowLayout fl_carriageInfoPanel = (FlowLayout) carriageInfoPanel.getLayout();
	    fl_carriageInfoPanel.setVgap(25);
	    fl_carriageInfoPanel.setHgap(25);
	    leftDriverPanelFloatRight.add(carriageInfoPanel, BorderLayout.NORTH);
	    
	    JPanel carriageEditPanel = new JPanel();
	    leftDriverPanelFloatRight.add(carriageEditPanel, BorderLayout.CENTER);
	    carriageEditPanel.setLayout(null);
	    
	    JButton btnAddCarriage = new JButton("Add Carriage");
	    btnAddCarriage.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent arg0) {
	    	}
	    });
	    btnAddCarriage.setBounds(12, 188, 163, 39);
	    carriageEditPanel.add(btnAddCarriage);
	    
	    JComboBox cmbBoxcarriageType = new JComboBox();
	    cmbBoxcarriageType.setModel(new DefaultComboBoxModel(new String[] {"Enter a carriage type.", "Locomotive", "PassengerCar", "FreightCar"}));
	    cmbBoxcarriageType.setName("test");
	    cmbBoxcarriageType.setBounds(12, 97, 163, 25);
	    carriageEditPanel.add(cmbBoxcarriageType);
	    
	    JButton btnRemoveCarriage = new JButton("Remove Carriage");
	    btnRemoveCarriage.setBounds(187, 188, 163, 39);
	    carriageEditPanel.add(btnRemoveCarriage);
	    
	    JLabel lblAddCarriageTitle = new JLabel("Add a Carriage to Train:");
	    lblAddCarriageTitle.setFont(new Font("Dialog", Font.BOLD, 14));
	    lblAddCarriageTitle.setBounds(12, 67, 217, 25);
	    carriageEditPanel.add(lblAddCarriageTitle);
	    
	    txtBoxGoodsInput = new JTextField();
	    txtBoxGoodsInput.setBounds(12, 156, 163, 20);
	    carriageEditPanel.add(txtBoxGoodsInput);
	    txtBoxGoodsInput.setColumns(10);
	    
	    JLabel lblGoodsType = new JLabel("Goods Type:");
	    lblGoodsType.setBounds(12, 134, 124, 16);
	    carriageEditPanel.add(lblGoodsType);
	    
	    JLabel lblWeightkg = new JLabel("Weight (kg):");
	    lblWeightkg.setBounds(187, 134, 124, 16);
	    carriageEditPanel.add(lblWeightkg);
	    
	    JSpinner txtBoxWeightInput = new JSpinner();
	    txtBoxWeightInput.setAlignmentX(Component.LEFT_ALIGNMENT);
	    txtBoxWeightInput.setBounds(187, 156, 163, 20);
	    carriageEditPanel.add(txtBoxWeightInput);
	    
	    JPanel panel_8 = new JPanel();
	    contentPanel.add(panel_8);
	    panel_8.setLayout(new BorderLayout(0, 0));
	    
	    JPanel panel_1 = new JPanel();
	    panel_8.add(panel_1, BorderLayout.WEST);
	    panel_1.setAlignmentX(Component.LEFT_ALIGNMENT);
	    panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    panel_1.setPreferredSize(new Dimension(375, 10));
	    panel_1.setLayout(new BorderLayout(0, 0));
	    
	    JPanel panel_3 = new JPanel();
	    panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
	    flowLayout_1.setVgap(25);
	    flowLayout_1.setHgap(25);
	    panel_1.add(panel_3, BorderLayout.NORTH);
	    
	    JPanel panel_6 = new JPanel();
	    panel_6.setLayout(null);
	    panel_1.add(panel_6, BorderLayout.CENTER);
	    
	    JButton btnBoard = new JButton("Board");
	    btnBoard.setBounds(12, 188, 99, 39);
	    panel_6.add(btnBoard);
	    
	    JButton btnReset = new JButton("Reset");
	    btnReset.setBounds(196, 188, 163, 39);
	    panel_6.add(btnReset);
	    
	    JSpinner spinner = new JSpinner();
	    spinner.setFont(new Font("Dialog", Font.BOLD, 14));
	    spinner.setBounds(108, 188, 64, 39);
	    panel_6.add(spinner);
	    
	    JLabel lblBoardPassengers = new JLabel("Board Passengers:");
	    lblBoardPassengers.setFont(new Font("Dialog", Font.BOLD, 14));
	    lblBoardPassengers.setBounds(12, 161, 160, 19);
	    panel_6.add(lblBoardPassengers);
	    
	    JPanel exceptionPanel = new JPanel();
	    exceptionPanel.setPreferredSize(new Dimension(10, 100));
	    getContentPane().add(exceptionPanel, BorderLayout.SOUTH);
	    exceptionPanel.setLayout(new BorderLayout(5, 5));
	    
	    JTextPane exceptionTextPane = new JTextPane();
	    exceptionTextPane.setEditable(false);
	    exceptionTextPane.setToolTipText("Error messages");
	    exceptionTextPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    exceptionPanel.add(exceptionTextPane);
	    
	    JLabel lblErrorConsole = new JLabel("Error console:");
	    exceptionPanel.add(lblErrorConsole, BorderLayout.NORTH);

	    //carriageViewPanel.setPreferredSize(new Dimension(300,150));
        
	}

	public void actionPerformed(ActionEvent e) {
	  String buttonString = e.getActionCommand();

	  if (buttonString.equals("Add Carriage")) {
		  	
	  } //else if (buttonString.equals("Square")) {
//		 drawPanel.figure=Canvas.SQUARE;
//		 drawPanel.repaint();
//	  } else if (buttonString.equals("String")) {
//		 drawPanel.figure=Canvas.STRING;
//		 drawPanel.repaint();
//	  } else {
//	     System.err.println("Unexpected Error");
//	  }
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TrainGUI gui = new TrainGUI("DumbGraphicsExample");
	    gui.setVisible(true);

	}
}
