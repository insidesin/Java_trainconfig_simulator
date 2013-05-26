package asgn2GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.text.NumberFormat;
import java.util.Vector;

import javax.swing.border.MatteBorder;
import javax.swing.JSplitPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.FreightCar;
import asgn2RollingStock.Locomotive;
import asgn2RollingStock.PassengerCar;
import asgn2Train.DepartingTrain;
import java.awt.Font;

public class newGui extends JFrame implements ActionListener {
	
	//Static Variables (Magic numbers)
	private final static int WIDTH = 800;
	private final static int HEIGHT = 800;
	
	private static final int LOCOMOTIVE_PAINT = 0;
	private static final int PASSENGERCAR_PAINT = 1;
	private static final int FREIGHTCAR_PAINT = 2;

	//GUI Panels
	private JPanel mainPane;
	private JPanel carriagePanel;
	private JPanel locomotivePanel;
	private JPanel trainDriverPanel;
	private JPanel conductorPanel;
	
	//GUI Buttons
	private JButton startTrainBtn;
	private JButton addLocomotiveBtn;
	private JButton addPassengerCarBtn;
	private JButton addFreightCarBtn;
	private JButton removeCarraigeBtn;
	private JButton boardBtn;
	
	//GUI ComboBox
	private JComboBox powerRating;
	private JComboBox engineType;
	private JComboBox goodsType;
	private JComboBox boardComboBox;
	
	//GUI Text Fields
	private JTextField locoWeightField;
	private JTextField freightWeight;
	private JTextField passengerLimitField;
	private JTextField passengerWeight;
	
	//GUI Label
	private JLabel locomotiveSetupLabel;
	private JLabel freightCarSetupLabel;
	private JLabel passengerCarSetupLabel;
	private JLabel errorMessageBoxLabel;
	private JLabel boardLabel;
	private JLabel totalPassengerLabel;
	
	//GUI TextArea
	private JTextArea ErrorMessageBox;

	//DepartingTrain object declaration
	private DepartingTrain departingTrain = new DepartingTrain();
	
	//Class variables
	private int totalGrossWeight;
	private int trainPower;
	private int passengersBoarded;
	private int maxPassengerCapacity;
	private int spaceAvaliable;
	
	private Vector<Integer> boardComboBoxItems = new Vector<Integer>();
	
	//private NumberFormat NUM_ONLY = new NumberFormat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					newGui frame = new newGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public newGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, WIDTH, HEIGHT);
		mainPane = new JPanel();
		mainPane.setMaximumSize(new Dimension(800, 800));
		mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPane.setLayout(new BorderLayout(0, 0));
		setContentPane(mainPane);
		
		JPanel setupViewContainer = new JPanel();
		setupViewContainer.setBorder(null);
		getContentPane().add(setupViewContainer, BorderLayout.NORTH);
		setupViewContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 15));

		JScrollPane carriageScrollPane = new JScrollPane();
		carriageScrollPane.setBorder(null);
		carriageScrollPane.setPreferredSize(new Dimension(750, 135));
		carriageScrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		setupViewContainer.add(carriageScrollPane);

		carriagePanel = new JPanel();
		carriagePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		carriageScrollPane.setViewportView(carriagePanel);
		carriagePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JPanel contentPanel = new JPanel();
		contentPanel.setPreferredSize(new Dimension(800, 800));
		contentPanel.setMaximumSize(new Dimension(800, 800));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		
		trainDriverPanel = new JPanel();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, trainDriverPanel, 10, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, trainDriverPanel, -53, SpringLayout.SOUTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, trainDriverPanel, -438, SpringLayout.EAST, contentPanel);
		trainDriverPanel.setMaximumSize(new Dimension(400, 270));
		sl_contentPanel.putConstraint(SpringLayout.WEST, trainDriverPanel, 10, SpringLayout.WEST, contentPanel);
		trainDriverPanel.setSize(new Dimension(400, 270));
		trainDriverPanel.setVisible(true);
		trainDriverPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		trainDriverPanel.setPreferredSize(new Dimension(400, 270));
		trainDriverPanel.setMinimumSize(new Dimension(400, 270));
		contentPanel.add(trainDriverPanel);
		
		ErrorMessageBox = new JTextArea();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, ErrorMessageBox, 410, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, ErrorMessageBox, 6, SpringLayout.EAST, trainDriverPanel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, ErrorMessageBox, 0, SpringLayout.SOUTH, trainDriverPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, ErrorMessageBox, -160, SpringLayout.EAST, contentPanel);
		ErrorMessageBox.setEditable(false);
		ErrorMessageBox.setBackground(Color.WHITE);
		ErrorMessageBox.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		ErrorMessageBox.setSize(new Dimension(600, 50));
		ErrorMessageBox.setMaximumSize(new Dimension(600, 50));
		ErrorMessageBox.setMinimumSize(new Dimension(600, 50));
		ErrorMessageBox.setPreferredSize(new Dimension(600, 50));
		contentPanel.add(ErrorMessageBox);
		
		JPanel conductorPanel = new JPanel();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, conductorPanel, 10, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, conductorPanel, 6, SpringLayout.EAST, trainDriverPanel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, conductorPanel, -72, SpringLayout.NORTH, ErrorMessageBox);
		sl_contentPanel.putConstraint(SpringLayout.EAST, conductorPanel, 0, SpringLayout.EAST, ErrorMessageBox);
		conductorPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		SpringLayout sl_trainDriverPanel = new SpringLayout();
		trainDriverPanel.setLayout(sl_trainDriverPanel);
		
		JLabel trainDriverTitle = new JLabel("Train Driver Controls");
		trainDriverTitle.setFont(new Font("Tahoma", Font.BOLD, 11));
		sl_trainDriverPanel.putConstraint(SpringLayout.NORTH, trainDriverTitle, 10, SpringLayout.NORTH, trainDriverPanel);
		sl_trainDriverPanel.putConstraint(SpringLayout.WEST, trainDriverTitle, 10, SpringLayout.WEST, trainDriverPanel);
		trainDriverPanel.add(trainDriverTitle);
		
		locomotivePanel = new JPanel();
		sl_trainDriverPanel.putConstraint(SpringLayout.WEST, locomotivePanel, 11, SpringLayout.WEST, trainDriverPanel);
		locomotivePanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		trainDriverPanel.add(locomotivePanel);
		SpringLayout sl_locomotivePanel = new SpringLayout();
		locomotivePanel.setLayout(sl_locomotivePanel);
		
		JLabel lblPowerType = new JLabel("Power:");
		sl_locomotivePanel.putConstraint(SpringLayout.NORTH, lblPowerType, 10, SpringLayout.NORTH, locomotivePanel);
		sl_locomotivePanel.putConstraint(SpringLayout.WEST, lblPowerType, 10, SpringLayout.WEST, locomotivePanel);
		locomotivePanel.add(lblPowerType);
		
		powerRating = new JComboBox();
		sl_locomotivePanel.putConstraint(SpringLayout.NORTH, powerRating, -3, SpringLayout.NORTH, lblPowerType);
		sl_locomotivePanel.putConstraint(SpringLayout.WEST, powerRating, 6, SpringLayout.EAST, lblPowerType);
		powerRating.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9"}));
		locomotivePanel.add(powerRating);
		
		JLabel lblEngineType = new JLabel("Engine Type:");
		sl_locomotivePanel.putConstraint(SpringLayout.NORTH, lblEngineType, 0, SpringLayout.NORTH, lblPowerType);
		sl_locomotivePanel.putConstraint(SpringLayout.WEST, lblEngineType, 6, SpringLayout.EAST, powerRating);
		locomotivePanel.add(lblEngineType);
		
		engineType = new JComboBox();
		sl_locomotivePanel.putConstraint(SpringLayout.NORTH, engineType, -3, SpringLayout.NORTH, lblPowerType);
		sl_locomotivePanel.putConstraint(SpringLayout.WEST, engineType, 6, SpringLayout.EAST, lblEngineType);
		engineType.addActionListener(this);
		engineType.setModel(new DefaultComboBoxModel(new String[] {"Diesel", "Electric", "Steam"}));
		locomotivePanel.add(engineType);
		
		JLabel lblWeight = new JLabel("Weight:");
		sl_locomotivePanel.putConstraint(SpringLayout.NORTH, lblWeight, 11, SpringLayout.SOUTH, lblPowerType);
		sl_locomotivePanel.putConstraint(SpringLayout.WEST, lblWeight, 0, SpringLayout.WEST, lblPowerType);
		locomotivePanel.add(lblWeight);
		
		locoWeightField = new JTextField();
		sl_locomotivePanel.putConstraint(SpringLayout.NORTH, locoWeightField, 6, SpringLayout.SOUTH, powerRating);
		sl_locomotivePanel.putConstraint(SpringLayout.WEST, locoWeightField, 0, SpringLayout.WEST, powerRating);
		locomotivePanel.add(locoWeightField);
		locoWeightField.setColumns(10);
		
		addLocomotiveBtn = new JButton("Add Locomotive");
		sl_locomotivePanel.putConstraint(SpringLayout.NORTH, addLocomotiveBtn, 5, SpringLayout.SOUTH, locoWeightField);
		sl_locomotivePanel.putConstraint(SpringLayout.WEST, addLocomotiveBtn, 0, SpringLayout.WEST, lblPowerType);
		locomotivePanel.add(addLocomotiveBtn);
		addLocomotiveBtn.addActionListener(this);
		conductorPanel.setPreferredSize(new Dimension(350, 270));
		conductorPanel.setMinimumSize(new Dimension(350, 270));
		contentPanel.add(conductorPanel);
		SpringLayout sl_conductorPanel = new SpringLayout();
		conductorPanel.setLayout(sl_conductorPanel);
		
		JLabel conductorTitle = new JLabel("Conductor Panel");
		conductorTitle.setFont(new Font("Tahoma", Font.BOLD, 11));
		sl_conductorPanel.putConstraint(SpringLayout.NORTH, conductorTitle, 10, SpringLayout.NORTH, conductorPanel);
		sl_conductorPanel.putConstraint(SpringLayout.WEST, conductorTitle, 10, SpringLayout.WEST, conductorPanel);
		conductorPanel.add(conductorTitle);
		
		boardLabel = new JLabel("Passengers to board:");
		sl_conductorPanel.putConstraint(SpringLayout.WEST, boardLabel, 0, SpringLayout.WEST, conductorTitle);
		conductorPanel.add(boardLabel);
		
		totalPassengerLabel = new JLabel("Total Passengers:");
		sl_conductorPanel.putConstraint(SpringLayout.NORTH, totalPassengerLabel, 16, SpringLayout.SOUTH, conductorTitle);
		sl_conductorPanel.putConstraint(SpringLayout.WEST, totalPassengerLabel, 0, SpringLayout.WEST, conductorTitle);
		conductorPanel.add(totalPassengerLabel);
		
		
		final DefaultComboBoxModel<Integer> model = new DefaultComboBoxModel<Integer>(boardComboBoxItems);
		boardComboBox = new JComboBox<Integer>(model);
		sl_conductorPanel.putConstraint(SpringLayout.WEST, boardComboBox, 16, SpringLayout.EAST, boardLabel);
		sl_conductorPanel.putConstraint(SpringLayout.EAST, boardComboBox, -78, SpringLayout.EAST, conductorPanel);
		sl_conductorPanel.putConstraint(SpringLayout.NORTH, boardLabel, 3, SpringLayout.NORTH, boardComboBox);
		sl_conductorPanel.putConstraint(SpringLayout.NORTH, boardComboBox, 75, SpringLayout.NORTH, conductorPanel);
		conductorPanel.add(boardComboBox);
		
		boardBtn = new JButton("Click to Board");
		boardBtn.addActionListener(this);
		sl_conductorPanel.putConstraint(SpringLayout.NORTH, boardBtn, 17, SpringLayout.SOUTH, boardLabel);
		sl_conductorPanel.putConstraint(SpringLayout.WEST, boardBtn, 0, SpringLayout.WEST, conductorTitle);
		conductorPanel.add(boardBtn);
		
		JPanel freightCarPanel = new JPanel();
		sl_trainDriverPanel.putConstraint(SpringLayout.NORTH, freightCarPanel, 368, SpringLayout.NORTH, trainDriverPanel);
		sl_trainDriverPanel.putConstraint(SpringLayout.WEST, freightCarPanel, 11, SpringLayout.WEST, trainDriverPanel);
		sl_trainDriverPanel.putConstraint(SpringLayout.EAST, freightCarPanel, 0, SpringLayout.EAST, locomotivePanel);
		freightCarPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		freightCarPanel.setEnabled(false);
		trainDriverPanel.add(freightCarPanel);
		
		locomotiveSetupLabel = new JLabel("Locomotive Setup");
		sl_trainDriverPanel.putConstraint(SpringLayout.SOUTH, locomotiveSetupLabel, -419, SpringLayout.SOUTH, trainDriverPanel);
		sl_trainDriverPanel.putConstraint(SpringLayout.NORTH, locomotivePanel, 9, SpringLayout.SOUTH, locomotiveSetupLabel);
		locomotiveSetupLabel.setFont(new Font("Tahoma", Font.ITALIC, 11));
		sl_trainDriverPanel.putConstraint(SpringLayout.NORTH, locomotiveSetupLabel, 65, SpringLayout.SOUTH, trainDriverTitle);
		sl_trainDriverPanel.putConstraint(SpringLayout.WEST, locomotiveSetupLabel, 0, SpringLayout.WEST, trainDriverTitle);
		sl_trainDriverPanel.putConstraint(SpringLayout.EAST, locomotiveSetupLabel, 102, SpringLayout.WEST, trainDriverPanel);
		trainDriverPanel.add(locomotiveSetupLabel);
		
		freightCarSetupLabel = new JLabel("Freight Car Setup");
		freightCarSetupLabel.setFont(new Font("Tahoma", Font.ITALIC, 11));
		sl_trainDriverPanel.putConstraint(SpringLayout.WEST, freightCarSetupLabel, 0, SpringLayout.WEST, trainDriverTitle);
		sl_trainDriverPanel.putConstraint(SpringLayout.SOUTH, freightCarSetupLabel, -6, SpringLayout.NORTH, freightCarPanel);
		freightCarSetupLabel.setEnabled(false);
		SpringLayout sl_freightCarPanel = new SpringLayout();
		freightCarPanel.setLayout(sl_freightCarPanel);
		
		JLabel lblGoodsType = new JLabel("Goods Type");
		sl_freightCarPanel.putConstraint(SpringLayout.NORTH, lblGoodsType, 8, SpringLayout.NORTH, freightCarPanel);
		sl_freightCarPanel.putConstraint(SpringLayout.WEST, lblGoodsType, 10, SpringLayout.WEST, freightCarPanel);
		freightCarPanel.add(lblGoodsType);
		
		goodsType = new JComboBox();
		sl_freightCarPanel.putConstraint(SpringLayout.NORTH, goodsType, -3, SpringLayout.NORTH, lblGoodsType);
		sl_freightCarPanel.putConstraint(SpringLayout.WEST, goodsType, 6, SpringLayout.EAST, lblGoodsType);
		goodsType.setModel(new DefaultComboBoxModel(new String[] {"General", "Refrigerated", "Dangerous"}));
		goodsType.addActionListener(this);
		goodsType.setEnabled(false);
		freightCarPanel.add(goodsType);
		
		JLabel lblWeight_1 = new JLabel("Weight:");
		sl_freightCarPanel.putConstraint(SpringLayout.NORTH, lblWeight_1, 14, SpringLayout.SOUTH, lblGoodsType);
		sl_freightCarPanel.putConstraint(SpringLayout.WEST, lblWeight_1, 0, SpringLayout.WEST, lblGoodsType);
		freightCarPanel.add(lblWeight_1);
		
		freightWeight = new JTextField();
		sl_freightCarPanel.putConstraint(SpringLayout.NORTH, freightWeight, -3, SpringLayout.NORTH, lblWeight_1);
		sl_freightCarPanel.putConstraint(SpringLayout.WEST, freightWeight, 0, SpringLayout.WEST, goodsType);
		freightWeight.setEnabled(false);
		freightCarPanel.add(freightWeight);
		freightWeight.setColumns(10);
		
		addFreightCarBtn = new JButton("Add Freight Car");
		sl_freightCarPanel.putConstraint(SpringLayout.NORTH, addFreightCarBtn, 8, SpringLayout.SOUTH, lblWeight_1);
		sl_freightCarPanel.putConstraint(SpringLayout.WEST, addFreightCarBtn, 0, SpringLayout.WEST, lblGoodsType);
		addFreightCarBtn.addActionListener(this);
		addFreightCarBtn.setEnabled(false);
		freightCarPanel.add(addFreightCarBtn);
		trainDriverPanel.add(freightCarSetupLabel);
		
		passengerCarSetupLabel = new JLabel("Passenger Car Setup");
		passengerCarSetupLabel.setFont(new Font("Tahoma", Font.ITALIC, 11));
		sl_trainDriverPanel.putConstraint(SpringLayout.SOUTH, locomotivePanel, -2, SpringLayout.NORTH, passengerCarSetupLabel);
		sl_trainDriverPanel.putConstraint(SpringLayout.WEST, passengerCarSetupLabel, 0, SpringLayout.WEST, trainDriverTitle);
		passengerCarSetupLabel.setEnabled(false);
		trainDriverPanel.add(passengerCarSetupLabel);
		
		JPanel passengerCarPanel = new JPanel();
		sl_trainDriverPanel.putConstraint(SpringLayout.NORTH, passengerCarPanel, 233, SpringLayout.NORTH, trainDriverPanel);
		sl_trainDriverPanel.putConstraint(SpringLayout.SOUTH, passengerCarPanel, -176, SpringLayout.SOUTH, trainDriverPanel);
		sl_trainDriverPanel.putConstraint(SpringLayout.SOUTH, passengerCarSetupLabel, -6, SpringLayout.NORTH, passengerCarPanel);
		sl_trainDriverPanel.putConstraint(SpringLayout.WEST, passengerCarPanel, 11, SpringLayout.WEST, trainDriverPanel);
		sl_trainDriverPanel.putConstraint(SpringLayout.EAST, passengerCarPanel, 0, SpringLayout.EAST, locomotivePanel);
		passengerCarPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		trainDriverPanel.add(passengerCarPanel);
		SpringLayout sl_passengerCarPanel = new SpringLayout();
		passengerCarPanel.setLayout(sl_passengerCarPanel);
		
		JLabel passengerLimitLabel = new JLabel("Passenger Limit:");
		sl_passengerCarPanel.putConstraint(SpringLayout.NORTH, passengerLimitLabel, 10, SpringLayout.NORTH, passengerCarPanel);
		sl_passengerCarPanel.putConstraint(SpringLayout.WEST, passengerLimitLabel, 10, SpringLayout.WEST, passengerCarPanel);
		passengerCarPanel.add(passengerLimitLabel);
		
		JLabel passengerWeightLabel = new JLabel("Weight:");
		sl_passengerCarPanel.putConstraint(SpringLayout.NORTH, passengerWeightLabel, 16, SpringLayout.SOUTH, passengerLimitLabel);
		sl_passengerCarPanel.putConstraint(SpringLayout.WEST, passengerWeightLabel, 10, SpringLayout.WEST, passengerCarPanel);
		passengerCarPanel.add(passengerWeightLabel);
		
		addPassengerCarBtn = new JButton("Add Passenger Car");
		sl_passengerCarPanel.putConstraint(SpringLayout.WEST, addPassengerCarBtn, 0, SpringLayout.WEST, passengerLimitLabel);
		addPassengerCarBtn.addActionListener(this);
		addPassengerCarBtn.setEnabled(false);
		passengerCarPanel.add(addPassengerCarBtn);
		
		passengerLimitField = new JTextField();
		passengerLimitField.setEnabled(false);
		sl_passengerCarPanel.putConstraint(SpringLayout.WEST, passengerLimitField, 6, SpringLayout.EAST, passengerLimitLabel);
		sl_passengerCarPanel.putConstraint(SpringLayout.SOUTH, passengerLimitField, 0, SpringLayout.SOUTH, passengerLimitLabel);
		passengerCarPanel.add(passengerLimitField);
		passengerLimitField.setColumns(10);
		
		passengerWeight = new JTextField();
		sl_passengerCarPanel.putConstraint(SpringLayout.NORTH, addPassengerCarBtn, 7, SpringLayout.SOUTH, passengerWeight);
		sl_passengerCarPanel.putConstraint(SpringLayout.NORTH, passengerWeight, -3, SpringLayout.NORTH, passengerWeightLabel);
		sl_passengerCarPanel.putConstraint(SpringLayout.EAST, passengerWeight, 0, SpringLayout.EAST, passengerLimitField);
		passengerWeight.setEnabled(false);
		passengerCarPanel.add(passengerWeight);
		passengerWeight.setColumns(10);
		
		removeCarraigeBtn = new JButton("Remove Carraige");
		sl_trainDriverPanel.putConstraint(SpringLayout.SOUTH, freightCarPanel, -6, SpringLayout.NORTH, removeCarraigeBtn);
		sl_trainDriverPanel.putConstraint(SpringLayout.WEST, removeCarraigeBtn, 0, SpringLayout.WEST, trainDriverTitle);
		removeCarraigeBtn.setEnabled(false);
		removeCarraigeBtn.addActionListener(this);
		trainDriverPanel.add(removeCarraigeBtn);
		
		startTrainBtn = new JButton("Reset Configuration");
		sl_trainDriverPanel.putConstraint(SpringLayout.EAST, locomotivePanel, 0, SpringLayout.EAST, startTrainBtn);
		sl_trainDriverPanel.putConstraint(SpringLayout.NORTH, removeCarraigeBtn, 0, SpringLayout.NORTH, startTrainBtn);
		sl_trainDriverPanel.putConstraint(SpringLayout.WEST, startTrainBtn, 153, SpringLayout.WEST, trainDriverPanel);
		sl_trainDriverPanel.putConstraint(SpringLayout.SOUTH, startTrainBtn, -10, SpringLayout.SOUTH, trainDriverPanel);
		sl_contentPanel.putConstraint(SpringLayout.NORTH, startTrainBtn, -4, SpringLayout.NORTH, freightCarSetupLabel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, startTrainBtn, -316, SpringLayout.EAST, trainDriverPanel);
		trainDriverPanel.add(startTrainBtn);
		
		JLabel lblTrainTotalWeight = new JLabel("Train Total Weight:");
		sl_trainDriverPanel.putConstraint(SpringLayout.NORTH, lblTrainTotalWeight, 6, SpringLayout.SOUTH, trainDriverTitle);
		sl_trainDriverPanel.putConstraint(SpringLayout.WEST, lblTrainTotalWeight, 10, SpringLayout.WEST, trainDriverPanel);
		trainDriverPanel.add(lblTrainTotalWeight);
		
		JLabel lblTrainPullingCapacity = new JLabel("Train Pulling Capacity");
		sl_trainDriverPanel.putConstraint(SpringLayout.NORTH, lblTrainPullingCapacity, 14, SpringLayout.SOUTH, lblTrainTotalWeight);
		sl_trainDriverPanel.putConstraint(SpringLayout.WEST, lblTrainPullingCapacity, 0, SpringLayout.WEST, trainDriverTitle);
		trainDriverPanel.add(lblTrainPullingCapacity);
		
		JLabel lblTrainCanMove = new JLabel("Train Can Move:");
		sl_trainDriverPanel.putConstraint(SpringLayout.NORTH, lblTrainCanMove, 0, SpringLayout.NORTH, lblTrainTotalWeight);
		sl_trainDriverPanel.putConstraint(SpringLayout.WEST, lblTrainCanMove, 63, SpringLayout.EAST, lblTrainTotalWeight);
		trainDriverPanel.add(lblTrainCanMove);
		
		errorMessageBoxLabel = new JLabel("Error Console");
		errorMessageBoxLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		sl_contentPanel.putConstraint(SpringLayout.WEST, errorMessageBoxLabel, 7, SpringLayout.EAST, trainDriverPanel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, errorMessageBoxLabel, -6, SpringLayout.NORTH, ErrorMessageBox);
		contentPanel.add(errorMessageBoxLabel);
		startTrainBtn.addActionListener(this);
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		String buttonString = e.getActionCommand();
		
		switch(buttonString){
		
		//Resets Train Configuration
		case "Reset Configuration":
			departingTrain = new DepartingTrain();
			
			trainDriverPanel.setVisible(true);
			
			locomotivePanel.setVisible(true);
			addLocomotiveBtn.setEnabled(true);
			
			passengerCarSetupLabel.setEnabled(false);
			addPassengerCarBtn.setEnabled(false);
			passengerLimitField.setEnabled(false);
			passengerWeight.setEnabled(false);
			
			freightCarSetupLabel.setEnabled(false);
			addFreightCarBtn.setEnabled(false);
			freightWeight.setEnabled(false);
			
			goodsType.setEnabled(false);			
			removeCarraigeBtn.setEnabled(false);
			
			//Resets class variables to default
			totalGrossWeight = 0;
			trainPower = 0;
			passengersBoarded = 0;
			maxPassengerCapacity = 0;
			spaceAvaliable = 0;
			
			break;
		
		//Add Locomotive Button Action
		case "Add Locomotive":
			StringBuilder locomotivePowerToString = new StringBuilder();
			int newLocoWeight = 0;
			
			int locomotivePowerRate = Integer.parseInt((String) powerRating.getSelectedItem());
			locomotivePowerToString.append("");
			locomotivePowerToString.append(locomotivePowerRate);
			String engineTypeToSingleChar = "";
			
			
			if(engineType.getSelectedIndex() == 0){
				engineTypeToSingleChar = "D";
			}else if(engineType.getSelectedIndex() == 1){
				engineTypeToSingleChar = "S";
			}else if(engineType.getSelectedIndex() == 2){
				engineTypeToSingleChar = "E";
			}
			
			String locomotiveClassification = locomotivePowerToString.toString() + engineTypeToSingleChar;
			
			
			try{Integer.parseInt(locoWeightField.getText());
			
			}catch(NumberFormatException x){
				ErrorMessageBox.append("Invalid Weight");
			}
			
			newLocoWeight = Integer.parseInt(locoWeightField.getText());
			totalGrossWeight += newLocoWeight;	
			
			try {
				departingTrain.addCarriage(new Locomotive(newLocoWeight, locomotiveClassification));
				//ErrorMessageBox.append(departingTrain.toString());
			} catch (TrainException e1) {
				// TODO Auto-generated catch block
				ErrorMessageBox.append(e1.getMessage());
			}
			
			locomotiveSetupLabel.setEnabled(false);
			addLocomotiveBtn.setEnabled(false);
			addFreightCarBtn.setEnabled(true);
			addPassengerCarBtn.setEnabled(true);
			removeCarraigeBtn.setEnabled(true);
			freightWeight.setEnabled(true);
			goodsType.setEnabled(true);
			passengerLimitField.setEnabled(true);
			passengerWeight.setEnabled(true);
			
			
			break;
			
		//Add Freight Car Button Action	
		case "Add Freight Car":
			String goodsTypeString = "";
			int newFreightWeight = 0;
			if(goodsType.getSelectedIndex() == 0){
				goodsTypeString = "G";
			}else if(goodsType.getSelectedIndex() == 1){
				goodsTypeString = "R";
			}else if(goodsType.getSelectedIndex() == 2){
				goodsTypeString = "D";
			}
			
			try{Integer.parseInt(freightWeight.getText());
			
			}catch(NumberFormatException x){
				ErrorMessageBox.append("Invalid Weight");
			}
			
			newFreightWeight = Integer.parseInt(freightWeight.getText());
			totalGrossWeight += newFreightWeight;
			
			try {
				departingTrain.addCarriage(new FreightCar(newFreightWeight, goodsTypeString));
				//ErrorMessageBox.append(departingTrain.toString());
			} catch (TrainException e1) {
				// TODO Auto-generated catch block
				ErrorMessageBox.append(e1.getMessage());
			}
			break;
			
		
		//Add Passenger Car Button Action
		case "Add Passenger Car":
			int passengerCapacity = 0;
			int newPassengerWeight = 0;
			
			try{Integer.parseInt(passengerLimitField.getText());
			
			}catch(NumberFormatException x){
				ErrorMessageBox.append("Invalid Number of Passengers");
			}	
			
			try{Integer.parseInt(passengerWeight.getText());
			
			}catch(NumberFormatException x){
				ErrorMessageBox.append("Invalid Weight");
			}
			passengerCapacity = Integer.parseInt(passengerLimitField.getText());
			maxPassengerCapacity += passengerCapacity;
			newPassengerWeight = Integer.parseInt(passengerWeight.getText());
			totalGrossWeight += newPassengerWeight;
			
			//Set the number of passengers allowed to board
			updateBoardComboBox();
			
			
			try {
				departingTrain.addCarriage(new PassengerCar(newPassengerWeight, passengerCapacity));
				//ErrorMessageBox.append(departingTrain.toString());
			} catch (TrainException e1) {
				// TODO Auto-generated catch block
				ErrorMessageBox.append(e1.getMessage());
			}
			
			//Updates the combox Box (Display)
			boardComboBox.setSelectedIndex(spaceAvaliable);
			boardComboBox.setSelectedIndex(0);
			
			break;
		
		//Remove Carraige Button Action
		case "Remove Carraige":
			
			try{departingTrain.removeCarriage();
			}catch(TrainException e1){
				ErrorMessageBox.append(e1.getMessage());
			}
			
			if(departingTrain.firstCarriage() == null){
				removeCarraigeBtn.setEnabled(false);
			}else{
				removeCarraigeBtn.setEnabled(true);
			}
		
			
			break;
			
			
		case "Click to Board":
			passengersBoarded += boardComboBox.getSelectedIndex();
			spaceAvaliable = maxPassengerCapacity - passengersBoarded;
			
			//Resets the combobox to the size of number of passengers allowed to board
			boardComboBox.removeAllItems();
			updateBoardComboBox();
			boardComboBox.setSelectedIndex(0);
			
			
			
			;
		}
		
	}
	
	/*
	 * Updates the Conductor's Panel Boarding Combo Box
	 */
	private void updateBoardComboBox(){
		int defaultBoardItem = 0;
		boardComboBoxItems.add(new Integer(defaultBoardItem));
		spaceAvaliable = maxPassengerCapacity - passengersBoarded;
		for(int i = 0; i < spaceAvaliable; i++){
			boardComboBoxItems.add(i+1);	
			
		}
	}
}
