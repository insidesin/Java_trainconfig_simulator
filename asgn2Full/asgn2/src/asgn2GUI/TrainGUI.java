package asgn2GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import java.awt.Color;
import java.util.Stack;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.FreightCar;
import asgn2RollingStock.Locomotive;
import asgn2RollingStock.PassengerCar;
import asgn2RollingStock.RollingStock;
import asgn2Train.DepartingTrain;
import java.awt.Font;

/**
 * 
 * @author Yeo Fei Wen, Jackson Powell
 * 
 */
public class TrainGUI extends JFrame implements ActionListener {

	// Static Variables (Magic numbers)
	private final static int WIDTH = 610;
	private final static int HEIGHT = 780;

	// canvas figure variables
	private static final int LOCOMOTIVE_PAINT = 0;
	private static final int PASSENGERCAR_PAINT = 1;
	private static final int FREIGHTCAR_PAINT = 2;

	// GUI Panels
	private JPanel mainPane;
	private JPanel carriagePanel;
	private JPanel locomotivePanel;
	private JPanel trainDriverPanel;
	private JPanel conductorPanel;

	// GUI Buttons
	private JButton startTrainBtn;
	private JButton addLocomotiveBtn;
	private JButton addPassengerCarBtn;
	private JButton addFreightCarBtn;
	private JButton removeCarriageBtn;
	private JButton boardBtn;

	// GUI ComboBox
	private JComboBox powerRating;
	private JComboBox engineType;
	private JComboBox goodsType;
	private JComboBox boardComboBox;

	// GUI Text Fields
	private JTextField locoWeightField;
	private JTextField freightWeight;
	private JTextField passengerLimitField;
	private JTextField passengerWeight;

	private JLabel locomotiveSetupLabel;
	private JLabel freightCarSetupLabel;
	private JLabel passengerCarSetupLabel;
	private JLabel errorMessageBoxLabel;
	private JLabel boardLabel;
	private JLabel totalPassengerLabel;
	private JLabel totalWeightLabel;
	private JLabel trainPullingLimitLabel;
	private JLabel trainCanMoveLabel;

	// GUI TextArea
	private JTextArea ErrorMessageBox;

	// DepartingTrain object declaration
	private DepartingTrain departingTrain = new DepartingTrain();

	// Class variables
	private int totalGrossWeight;
	private int passengersBoarded;
	private int maxPassengerCapacity;
	private int spaceAvaliable;
	private int trainMaxPull;

	private RollingStock currentCarriage;

	private Vector<Integer> boardComboBoxItems = new Vector<Integer>();
	private Stack<Canvas> carriagePanelStack = new Stack<Canvas>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrainGUI frame = new TrainGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create and popualte the frame.
	 */
	public TrainGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 780);
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
		carriageScrollPane.setPreferredSize(new Dimension(580, 135));
		carriageScrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		setupViewContainer.add(carriageScrollPane);

		carriagePanel = new JPanel();
		carriagePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
				null));
		carriageScrollPane.setViewportView(carriagePanel);
		carriagePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JPanel contentPanel = new JPanel();
		contentPanel.setPreferredSize(new Dimension(800, 800));
		contentPanel.setMaximumSize(new Dimension(800, 800));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);

		trainDriverPanel = new JPanel();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, trainDriverPanel, 10,
				SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, trainDriverPanel, 10,
				SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, trainDriverPanel,
				-10, SpringLayout.SOUTH, contentPanel);
		trainDriverPanel.setMaximumSize(new Dimension(400, 270));
		trainDriverPanel.setSize(new Dimension(400, 270));
		trainDriverPanel.setVisible(true);
		trainDriverPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
				null));
		trainDriverPanel.setPreferredSize(new Dimension(400, 270));
		trainDriverPanel.setMinimumSize(new Dimension(400, 270));
		contentPanel.add(trainDriverPanel);

		ErrorMessageBox = new JTextArea();
		sl_contentPanel.putConstraint(SpringLayout.EAST, trainDriverPanel, -10,
				SpringLayout.WEST, ErrorMessageBox);
		sl_contentPanel.putConstraint(SpringLayout.NORTH, ErrorMessageBox, 327,
				SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, ErrorMessageBox, -53,
				SpringLayout.SOUTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, ErrorMessageBox, -10,
				SpringLayout.EAST, contentPanel);
		ErrorMessageBox.setEditable(false);
		ErrorMessageBox.setBackground(Color.WHITE);
		ErrorMessageBox.setBorder(new BevelBorder(BevelBorder.LOWERED, null,
				null, null, null));
		ErrorMessageBox.setSize(new Dimension(600, 50));
		ErrorMessageBox.setMaximumSize(new Dimension(600, 50));
		ErrorMessageBox.setMinimumSize(new Dimension(600, 50));
		ErrorMessageBox.setPreferredSize(new Dimension(600, 50));
		ErrorMessageBox.setLineWrap(true);
		ErrorMessageBox.setWrapStyleWord(true);
		contentPanel.add(ErrorMessageBox);

		JPanel conductorPanel = new JPanel();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, conductorPanel, 10,
				SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, ErrorMessageBox, 0,
				SpringLayout.WEST, conductorPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, conductorPanel, 329,
				SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, conductorPanel, -10,
				SpringLayout.EAST, contentPanel);
		conductorPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
				null));
		SpringLayout sl_trainDriverPanel = new SpringLayout();
		trainDriverPanel.setLayout(sl_trainDriverPanel);

		JLabel trainDriverTitle = new JLabel("Train Driver Controls");
		sl_trainDriverPanel.putConstraint(SpringLayout.WEST, trainDriverTitle,
				8, SpringLayout.WEST, trainDriverPanel);
		sl_trainDriverPanel.putConstraint(SpringLayout.SOUTH, trainDriverTitle,
				-519, SpringLayout.SOUTH, trainDriverPanel);
		trainDriverTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
		trainDriverPanel.add(trainDriverTitle);

		locomotivePanel = new JPanel();
		sl_trainDriverPanel.putConstraint(SpringLayout.WEST, locomotivePanel,
				8, SpringLayout.WEST, trainDriverPanel);
		sl_trainDriverPanel.putConstraint(SpringLayout.SOUTH, locomotivePanel,
				-331, SpringLayout.SOUTH, trainDriverPanel);
		sl_trainDriverPanel.putConstraint(SpringLayout.EAST, locomotivePanel,
				-26, SpringLayout.EAST, trainDriverPanel);
		locomotivePanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null,
				null, null, null));
		trainDriverPanel.add(locomotivePanel);
		SpringLayout sl_locomotivePanel = new SpringLayout();
		locomotivePanel.setLayout(sl_locomotivePanel);

		JLabel lblPowerType = new JLabel("Power:");
		sl_locomotivePanel.putConstraint(SpringLayout.NORTH, lblPowerType, 10,
				SpringLayout.NORTH, locomotivePanel);
		sl_locomotivePanel.putConstraint(SpringLayout.WEST, lblPowerType, 10,
				SpringLayout.WEST, locomotivePanel);
		locomotivePanel.add(lblPowerType);

		powerRating = new JComboBox();
		sl_locomotivePanel.putConstraint(SpringLayout.NORTH, powerRating, -3,
				SpringLayout.NORTH, lblPowerType);
		sl_locomotivePanel.putConstraint(SpringLayout.WEST, powerRating, 6,
				SpringLayout.EAST, lblPowerType);
		powerRating.setModel(new DefaultComboBoxModel(new String[] { "1", "2",
				"3", "4", "5", "6", "7", "8", "9" }));
		locomotivePanel.add(powerRating);

		JLabel lblEngineType = new JLabel("Engine Type:");
		sl_locomotivePanel.putConstraint(SpringLayout.NORTH, lblEngineType, 0,
				SpringLayout.NORTH, lblPowerType);
		sl_locomotivePanel.putConstraint(SpringLayout.WEST, lblEngineType, 6,
				SpringLayout.EAST, powerRating);
		locomotivePanel.add(lblEngineType);

		engineType = new JComboBox();
		sl_locomotivePanel.putConstraint(SpringLayout.NORTH, engineType, -3,
				SpringLayout.NORTH, lblPowerType);
		sl_locomotivePanel.putConstraint(SpringLayout.WEST, engineType, 6,
				SpringLayout.EAST, lblEngineType);
		engineType.addActionListener(this);
		engineType.setModel(new DefaultComboBoxModel(new String[] { "Diesel",
				"Electric", "Steam" }));
		locomotivePanel.add(engineType);

		JLabel lblWeight = new JLabel("Weight:");
		sl_locomotivePanel.putConstraint(SpringLayout.NORTH, lblWeight, 11,
				SpringLayout.SOUTH, lblPowerType);
		sl_locomotivePanel.putConstraint(SpringLayout.WEST, lblWeight, 0,
				SpringLayout.WEST, lblPowerType);
		locomotivePanel.add(lblWeight);

		locoWeightField = new JTextField();
		sl_locomotivePanel.putConstraint(SpringLayout.NORTH, locoWeightField,
				6, SpringLayout.SOUTH, powerRating);
		sl_locomotivePanel.putConstraint(SpringLayout.WEST, locoWeightField, 0,
				SpringLayout.WEST, powerRating);
		locomotivePanel.add(locoWeightField);
		locoWeightField.setColumns(10);

		addLocomotiveBtn = new JButton("Add Locomotive");
		sl_locomotivePanel.putConstraint(SpringLayout.NORTH, addLocomotiveBtn,
				5, SpringLayout.SOUTH, locoWeightField);
		sl_locomotivePanel.putConstraint(SpringLayout.WEST, addLocomotiveBtn,
				0, SpringLayout.WEST, lblPowerType);
		locomotivePanel.add(addLocomotiveBtn);
		addLocomotiveBtn.addActionListener(this);
		conductorPanel.setPreferredSize(new Dimension(350, 270));
		conductorPanel.setMinimumSize(new Dimension(350, 270));
		contentPanel.add(conductorPanel);
		SpringLayout sl_conductorPanel = new SpringLayout();
		conductorPanel.setLayout(sl_conductorPanel);

		JLabel conductorTitle = new JLabel("Conductor Panel");
		conductorTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
		sl_conductorPanel.putConstraint(SpringLayout.NORTH, conductorTitle, 10,
				SpringLayout.NORTH, conductorPanel);
		sl_conductorPanel.putConstraint(SpringLayout.WEST, conductorTitle, 10,
				SpringLayout.WEST, conductorPanel);
		conductorPanel.add(conductorTitle);

		boardLabel = new JLabel("Passengers to board:");
		sl_conductorPanel.putConstraint(SpringLayout.WEST, boardLabel, 0,
				SpringLayout.WEST, conductorTitle);
		conductorPanel.add(boardLabel);

		totalPassengerLabel = new JLabel("Total Passengers: ");
		sl_conductorPanel.putConstraint(SpringLayout.NORTH, boardLabel, 24,
				SpringLayout.SOUTH, totalPassengerLabel);
		sl_conductorPanel.putConstraint(SpringLayout.NORTH,
				totalPassengerLabel, 16, SpringLayout.SOUTH, conductorTitle);
		sl_conductorPanel.putConstraint(SpringLayout.WEST, totalPassengerLabel,
				0, SpringLayout.WEST, conductorTitle);
		conductorPanel.add(totalPassengerLabel);

		final DefaultComboBoxModel<Integer> model = new DefaultComboBoxModel<Integer>(
				boardComboBoxItems);
		boardComboBox = new JComboBox<Integer>(model);
		sl_conductorPanel.putConstraint(SpringLayout.WEST, boardComboBox, 16,
				SpringLayout.EAST, boardLabel);
		sl_conductorPanel.putConstraint(SpringLayout.EAST, boardComboBox, -46,
				SpringLayout.EAST, conductorPanel);
		sl_conductorPanel.putConstraint(SpringLayout.NORTH, boardComboBox, 75,
				SpringLayout.NORTH, conductorPanel);
		conductorPanel.add(boardComboBox);

		boardBtn = new JButton("Click to Board");
		boardBtn.setEnabled(false);
		boardBtn.addActionListener(this);
		sl_conductorPanel.putConstraint(SpringLayout.NORTH, boardBtn, 17,
				SpringLayout.SOUTH, boardLabel);
		sl_conductorPanel.putConstraint(SpringLayout.WEST, boardBtn, 0,
				SpringLayout.WEST, conductorTitle);
		conductorPanel.add(boardBtn);

		JPanel freightCarPanel = new JPanel();
		sl_trainDriverPanel.putConstraint(SpringLayout.WEST, freightCarPanel,
				0, SpringLayout.WEST, trainDriverTitle);
		sl_trainDriverPanel.putConstraint(SpringLayout.SOUTH, freightCarPanel,
				-53, SpringLayout.SOUTH, trainDriverPanel);
		sl_trainDriverPanel.putConstraint(SpringLayout.EAST, freightCarPanel,
				-18, SpringLayout.EAST, trainDriverPanel);
		freightCarPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null,
				null, null, null));
		freightCarPanel.setEnabled(false);
		trainDriverPanel.add(freightCarPanel);

		freightCarSetupLabel = new JLabel("Freight Car Setup");
		sl_trainDriverPanel.putConstraint(SpringLayout.NORTH, freightCarPanel,
				6, SpringLayout.SOUTH, freightCarSetupLabel);
		sl_trainDriverPanel.putConstraint(SpringLayout.WEST,
				freightCarSetupLabel, 0, SpringLayout.WEST, trainDriverTitle);
		freightCarSetupLabel.setFont(new Font("Tahoma", Font.ITALIC, 11));
		freightCarSetupLabel.setEnabled(false);
		SpringLayout sl_freightCarPanel = new SpringLayout();
		freightCarPanel.setLayout(sl_freightCarPanel);

		JLabel lblGoodsType = new JLabel("Goods Type");
		sl_freightCarPanel.putConstraint(SpringLayout.NORTH, lblGoodsType, 8,
				SpringLayout.NORTH, freightCarPanel);
		sl_freightCarPanel.putConstraint(SpringLayout.WEST, lblGoodsType, 10,
				SpringLayout.WEST, freightCarPanel);
		freightCarPanel.add(lblGoodsType);

		goodsType = new JComboBox();
		sl_freightCarPanel.putConstraint(SpringLayout.NORTH, goodsType, -3,
				SpringLayout.NORTH, lblGoodsType);
		sl_freightCarPanel.putConstraint(SpringLayout.WEST, goodsType, 6,
				SpringLayout.EAST, lblGoodsType);
		goodsType.setModel(new DefaultComboBoxModel(new String[] { "General",
				"Refrigerated", "Dangerous" }));
		goodsType.addActionListener(this);
		goodsType.setEnabled(false);
		freightCarPanel.add(goodsType);

		JLabel lblWeight_1 = new JLabel("Weight:");
		sl_freightCarPanel.putConstraint(SpringLayout.NORTH, lblWeight_1, 14,
				SpringLayout.SOUTH, lblGoodsType);
		sl_freightCarPanel.putConstraint(SpringLayout.WEST, lblWeight_1, 0,
				SpringLayout.WEST, lblGoodsType);
		freightCarPanel.add(lblWeight_1);

		freightWeight = new JTextField();
		sl_freightCarPanel.putConstraint(SpringLayout.NORTH, freightWeight, -3,
				SpringLayout.NORTH, lblWeight_1);
		sl_freightCarPanel.putConstraint(SpringLayout.WEST, freightWeight, 0,
				SpringLayout.WEST, goodsType);
		freightWeight.setEnabled(false);
		freightCarPanel.add(freightWeight);
		freightWeight.setColumns(10);

		addFreightCarBtn = new JButton("Add Freight Car");
		sl_freightCarPanel.putConstraint(SpringLayout.NORTH, addFreightCarBtn,
				8, SpringLayout.SOUTH, lblWeight_1);
		sl_freightCarPanel.putConstraint(SpringLayout.WEST, addFreightCarBtn,
				0, SpringLayout.WEST, lblGoodsType);
		addFreightCarBtn.addActionListener(this);
		addFreightCarBtn.setEnabled(false);
		freightCarPanel.add(addFreightCarBtn);
		trainDriverPanel.add(freightCarSetupLabel);

		passengerCarSetupLabel = new JLabel("Passenger Car Setup");
		sl_trainDriverPanel.putConstraint(SpringLayout.NORTH,
				passengerCarSetupLabel, 6, SpringLayout.SOUTH, locomotivePanel);
		sl_trainDriverPanel.putConstraint(SpringLayout.WEST,
				passengerCarSetupLabel, 0, SpringLayout.WEST, trainDriverTitle);
		passengerCarSetupLabel.setFont(new Font("Tahoma", Font.ITALIC, 11));
		passengerCarSetupLabel.setEnabled(false);
		trainDriverPanel.add(passengerCarSetupLabel);

		JPanel passengerCarPanel = new JPanel();
		sl_trainDriverPanel
				.putConstraint(SpringLayout.NORTH, freightCarSetupLabel, 10,
						SpringLayout.SOUTH, passengerCarPanel);
		sl_trainDriverPanel.putConstraint(SpringLayout.SOUTH,
				passengerCarPanel, -194, SpringLayout.SOUTH, trainDriverPanel);
		sl_trainDriverPanel.putConstraint(SpringLayout.NORTH,
				passengerCarPanel, 6, SpringLayout.SOUTH,
				passengerCarSetupLabel);
		sl_trainDriverPanel.putConstraint(SpringLayout.WEST, passengerCarPanel,
				0, SpringLayout.WEST, trainDriverTitle);
		sl_trainDriverPanel.putConstraint(SpringLayout.EAST, passengerCarPanel,
				-18, SpringLayout.EAST, trainDriverPanel);
		passengerCarPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null,
				null, null, null));
		trainDriverPanel.add(passengerCarPanel);
		SpringLayout sl_passengerCarPanel = new SpringLayout();
		passengerCarPanel.setLayout(sl_passengerCarPanel);

		JLabel passengerLimitLabel = new JLabel("Passenger Limit:");
		sl_passengerCarPanel.putConstraint(SpringLayout.NORTH,
				passengerLimitLabel, 10, SpringLayout.NORTH, passengerCarPanel);
		sl_passengerCarPanel.putConstraint(SpringLayout.WEST,
				passengerLimitLabel, 10, SpringLayout.WEST, passengerCarPanel);
		passengerCarPanel.add(passengerLimitLabel);

		JLabel passengerWeightLabel = new JLabel("Weight:");
		sl_passengerCarPanel.putConstraint(SpringLayout.NORTH,
				passengerWeightLabel, 16, SpringLayout.SOUTH,
				passengerLimitLabel);
		sl_passengerCarPanel.putConstraint(SpringLayout.WEST,
				passengerWeightLabel, 10, SpringLayout.WEST, passengerCarPanel);
		passengerCarPanel.add(passengerWeightLabel);

		addPassengerCarBtn = new JButton("Add Passenger Car");
		sl_passengerCarPanel.putConstraint(SpringLayout.WEST,
				addPassengerCarBtn, 0, SpringLayout.WEST, passengerLimitLabel);
		addPassengerCarBtn.addActionListener(this);
		addPassengerCarBtn.setEnabled(false);
		passengerCarPanel.add(addPassengerCarBtn);

		passengerLimitField = new JTextField();
		passengerLimitField.setEnabled(false);
		sl_passengerCarPanel.putConstraint(SpringLayout.WEST,
				passengerLimitField, 6, SpringLayout.EAST, passengerLimitLabel);
		sl_passengerCarPanel
				.putConstraint(SpringLayout.SOUTH, passengerLimitField, 0,
						SpringLayout.SOUTH, passengerLimitLabel);
		passengerCarPanel.add(passengerLimitField);
		passengerLimitField.setColumns(10);

		passengerWeight = new JTextField();
		sl_passengerCarPanel.putConstraint(SpringLayout.NORTH,
				addPassengerCarBtn, 7, SpringLayout.SOUTH, passengerWeight);
		sl_passengerCarPanel.putConstraint(SpringLayout.NORTH, passengerWeight,
				-3, SpringLayout.NORTH, passengerWeightLabel);
		sl_passengerCarPanel.putConstraint(SpringLayout.EAST, passengerWeight,
				0, SpringLayout.EAST, passengerLimitField);
		passengerWeight.setEnabled(false);
		passengerCarPanel.add(passengerWeight);
		passengerWeight.setColumns(10);

		removeCarriageBtn = new JButton("Remove Carriage");
		sl_trainDriverPanel.putConstraint(SpringLayout.NORTH,
				removeCarriageBtn, 6, SpringLayout.SOUTH, freightCarPanel);
		sl_trainDriverPanel.putConstraint(SpringLayout.WEST, removeCarriageBtn,
				10, SpringLayout.WEST, freightCarPanel);
		removeCarriageBtn.setEnabled(false);
		removeCarriageBtn.addActionListener(this);
		trainDriverPanel.add(removeCarriageBtn);

		startTrainBtn = new JButton("Reset Configuration");
		sl_trainDriverPanel.putConstraint(SpringLayout.NORTH, startTrainBtn, 6,
				SpringLayout.SOUTH, freightCarPanel);
		sl_trainDriverPanel.putConstraint(SpringLayout.EAST, startTrainBtn, 0,
				SpringLayout.EAST, freightCarPanel);
		sl_contentPanel.putConstraint(SpringLayout.NORTH, startTrainBtn, -4,
				SpringLayout.NORTH, freightCarSetupLabel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, startTrainBtn, -316,
				SpringLayout.EAST, trainDriverPanel);
		trainDriverPanel.add(startTrainBtn);

		totalWeightLabel = new JLabel("Train Total Weight:");
		sl_trainDriverPanel.putConstraint(SpringLayout.NORTH, totalWeightLabel,
				6, SpringLayout.SOUTH, trainDriverTitle);
		sl_trainDriverPanel.putConstraint(SpringLayout.WEST, totalWeightLabel,
				0, SpringLayout.WEST, trainDriverTitle);
		trainDriverPanel.add(totalWeightLabel);

		trainPullingLimitLabel = new JLabel("Train Pulling Capacity:");
		sl_trainDriverPanel.putConstraint(SpringLayout.NORTH,
				trainPullingLimitLabel, 50, SpringLayout.NORTH,
				trainDriverPanel);
		sl_trainDriverPanel.putConstraint(SpringLayout.WEST,
				trainPullingLimitLabel, 0, SpringLayout.WEST, trainDriverTitle);
		trainDriverPanel.add(trainPullingLimitLabel);

		trainCanMoveLabel = new JLabel("Train Can Move:");
		sl_trainDriverPanel.putConstraint(SpringLayout.NORTH, locomotivePanel,
				30, SpringLayout.SOUTH, trainCanMoveLabel);
		sl_trainDriverPanel.putConstraint(SpringLayout.NORTH,
				trainCanMoveLabel, 6, SpringLayout.SOUTH,
				trainPullingLimitLabel);
		sl_trainDriverPanel.putConstraint(SpringLayout.WEST, trainCanMoveLabel,
				0, SpringLayout.WEST, trainDriverTitle);
		trainDriverPanel.add(trainCanMoveLabel);

		errorMessageBoxLabel = new JLabel("Error Console");
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, conductorPanel, -81,
				SpringLayout.NORTH, errorMessageBoxLabel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, errorMessageBoxLabel,
				0, SpringLayout.WEST, ErrorMessageBox);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, errorMessageBoxLabel,
				-6, SpringLayout.NORTH, ErrorMessageBox);

		locomotiveSetupLabel = new JLabel("Locomotive Setup");
		locomotiveSetupLabel.setFont(new Font("Tahoma", Font.ITALIC, 11));
		sl_trainDriverPanel.putConstraint(SpringLayout.WEST,
				locomotiveSetupLabel, 0, SpringLayout.WEST, trainDriverTitle);
		sl_trainDriverPanel.putConstraint(SpringLayout.SOUTH,
				locomotiveSetupLabel, -6, SpringLayout.NORTH, locomotivePanel);
		trainDriverPanel.add(locomotiveSetupLabel);
		errorMessageBoxLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPanel.add(errorMessageBoxLabel);
		startTrainBtn.addActionListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		ErrorMessageBox.setText("");

		String buttonString = e.getActionCommand();

		switch (buttonString) {

		// Resets Train Configuration
		case "Reset Configuration":

			// Resets the panels to default
			departingTrain = new DepartingTrain();

			resetToDefaultLayoutAndValues();

			// Remove Carriage from physical window and logical line-up.
			carriagePanel.removeAll();
			carriagePanelStack.clear();

			validate();
			repaint();

			break;

		// Add Locomotive Button Action
		case "Add Locomotive":

			// checks that input for the locomotive weight
			if (locoWeightField.getText().equalsIgnoreCase("")) {
				ErrorMessageBox
						.append("Please input a value into locomotive weight");
			} else if (Integer.parseInt(locoWeightField.getText()) <= 0) {
				ErrorMessageBox.append("Please input a weight higher than 0.");
			} else {
				StringBuilder locomotivePowerToString = new StringBuilder();
				int newLocoWeight = 0;

				// assigns the string value for power rating
				int locomotivePowerRate = Integer.parseInt((String) powerRating
						.getSelectedItem());
				locomotivePowerToString.append("");
				locomotivePowerToString.append(locomotivePowerRate);
				String engineTypeToSingleChar = "";

				// assigns the string value for the engine type
				if (engineType.getSelectedIndex() == 0) {
					engineTypeToSingleChar = "D";
				} else if (engineType.getSelectedIndex() == 1) {
					engineTypeToSingleChar = "E";
				} else if (engineType.getSelectedIndex() == 2) {
					engineTypeToSingleChar = "S";
				}

				// combines the power rating and engine type into a
				// valid classification string
				String locomotiveClassification = locomotivePowerToString
						.toString() + engineTypeToSingleChar;

				newLocoWeight = Integer.parseInt(locoWeightField.getText());
				totalGrossWeight += newLocoWeight;

				// Attempt to create a new locomotive based off the
				// user input values
				try {
					departingTrain.addCarriage(new Locomotive(newLocoWeight,
							locomotiveClassification));

					// enables the valid actions that the user
					// have after adding a locomotive
					locomotiveSetupLabel.setEnabled(false);
					addLocomotiveBtn.setEnabled(false);
					addFreightCarBtn.setEnabled(true);
					addPassengerCarBtn.setEnabled(true);
					removeCarriageBtn.setEnabled(true);
					freightWeight.setEnabled(true);
					goodsType.setEnabled(true);
					passengerLimitField.setEnabled(true);
					passengerWeight.setEnabled(true);
					passengerCarSetupLabel.setEnabled(true);
					freightCarSetupLabel.setEnabled(true);

				} catch (TrainException e1) {
					ErrorMessageBox.append(e1.getMessage());
					locomotiveSetupLabel.setEnabled(false);
					removeCarriageBtn.setEnabled(false);
				}

				// Find the train recently created so we can work with it
				// multiple times.
				if (carriagePanelStack.size() == 0)
					currentCarriage = departingTrain.firstCarriage();
				else
					currentCarriage = departingTrain.nextCarriage();

				Canvas newCarriageCanvasLoco = new Canvas();

				// Display their text readable description.
				newCarriageCanvasLoco.getCarriageLabel().setText(
						currentCarriage.toString());

				// Check their capacity and display progressBar accordingly.
				trainMaxPull = ((Locomotive) currentCarriage).power();
				newCarriageCanvasLoco.getProgressBar().setMaximum(
						((Locomotive) currentCarriage).power());
				newCarriageCanvasLoco.getProgressBar().setValue(
						totalGrossWeight);

				// Add to logical list.
				carriagePanelStack.push(newCarriageCanvasLoco);

				newCarriageCanvasLoco.figure = LOCOMOTIVE_PAINT;

				carriagePanel.add(newCarriageCanvasLoco);
				newCarriageCanvasLoco.setLayout(new FlowLayout(FlowLayout.LEFT,
						0, 0));

				validate();
				repaint();

				// trainDriverControls();
			}

			break;

		// Add Freight Car Button Action
		case "Add Freight Car":
			String goodsTypeString = "";
			int newFreightWeight = 0;

			// sets the goods type as to what is valid for a Freight Car
			// parameter
			if (goodsType.getSelectedIndex() == 0) {
				goodsTypeString = "G";
			} else if (goodsType.getSelectedIndex() == 1) {
				goodsTypeString = "R";
			} else if (goodsType.getSelectedIndex() == 2) {
				goodsTypeString = "D";
			}

			// checks if the textfield is blank
			if (freightWeight.getText().equalsIgnoreCase("")) {
				ErrorMessageBox
						.append("Please input a value into freight weight");
			}
			// Ensure no negative values
			else if (Integer.parseInt(freightWeight.getText()) <= 0) {
				ErrorMessageBox.append("Please input a weight higher than 0.");
			}
			// Executes the valid action of adding a freight car
			else {

				// save the value to local case variable
				newFreightWeight = Integer.parseInt(freightWeight.getText());

				// adds the local case variable unto the total weight of the
				// train
				totalGrossWeight += newFreightWeight;

				// try to add a freight car base on the valid values
				try {
					departingTrain.addCarriage(new FreightCar(newFreightWeight,
							goodsTypeString));
				} catch (TrainException e1) {
					ErrorMessageBox.append(e1.getMessage());

				}

				// Find the train recently created so we can work with it
				// multiple times.
				if (carriagePanelStack.size() == 0)
					currentCarriage = departingTrain.firstCarriage();
				else
					currentCarriage = departingTrain.nextCarriage();

				if (currentCarriage instanceof FreightCar) {
					// prevCarriage = currentCarriage;
					Canvas newCarriageCanvasFreight = new Canvas();

					// Display their text readable description.
					newCarriageCanvasFreight.getCarriageLabel().setText(
							currentCarriage.toString());

					// Check their capacity and display progressBar accordingly.
					newCarriageCanvasFreight.getProgressBar().setMaximum(
							currentCarriage.getGrossWeight());
					newCarriageCanvasFreight.getProgressBar().setValue(
							currentCarriage.getGrossWeight());

					// Add to logical list.
					carriagePanelStack.push(newCarriageCanvasFreight);

					newCarriageCanvasFreight.figure = FREIGHTCAR_PAINT;

					carriagePanel.add(newCarriageCanvasFreight);
					newCarriageCanvasFreight.setLayout(new FlowLayout(
							FlowLayout.LEFT, 0, 0));
				}

				// Disables adding of passengers after a freight car is added
				passengerLimitField.setEnabled(false);
				passengerWeight.setEnabled(false);
				addPassengerCarBtn.setEnabled(false);
				updateLocomotiveProgressBar();

				validate();
				repaint();

				trainDriverControls(departingTrain.trainCanMove(), e);
			}
			break;

		// Add Passenger Car Button Action
		case "Add Passenger Car":

			int passengerCapacity = 0;
			int newPassengerWeight = 0;

			if (passengerLimitField.getText().equalsIgnoreCase("")
					|| passengerWeight.getText().equalsIgnoreCase("")) {
				ErrorMessageBox
						.append("Please fill in the both fields for the passenger car");
			} else {

				passengerCapacity = Integer.parseInt(passengerLimitField
						.getText());

				newPassengerWeight = Integer
						.parseInt(passengerWeight.getText());
				totalGrossWeight += newPassengerWeight;

				// Set the number of passengers allowed to board

				try {
					departingTrain.addCarriage(new PassengerCar(
							newPassengerWeight, passengerCapacity));
				} catch (TrainException e1) {
					ErrorMessageBox.append(e1.getMessage());
				}

				// Updates the combox Box (Display)
				updateBoardComboBox();
				boardBtn.setEnabled(true);

				// Find the train recently created so we can work with it
				// multiple times.
				if (carriagePanelStack.size() == 0)
					currentCarriage = departingTrain.firstCarriage();
				else
					currentCarriage = departingTrain.nextCarriage();

				// draws the passenger car whilst ensuring that
				if ((currentCarriage instanceof PassengerCar && currentCarriage != null)) {
					// prevCarriage = currentCarriage;

					Canvas newCarriageCanvasPassenger = new Canvas();

					// Display their text readable description.

					newCarriageCanvasPassenger.getCarriageLabel().setText(
							currentCarriage.toString());

					// Check their capacity and display progressBar accordingly.
					newCarriageCanvasPassenger.getProgressBar().setMaximum(
							((PassengerCar) currentCarriage).numberOfSeats());

					// Add to logical list.
					carriagePanelStack.push(newCarriageCanvasPassenger);

					newCarriageCanvasPassenger.figure = PASSENGERCAR_PAINT;

					carriagePanel.add(newCarriageCanvasPassenger);
					newCarriageCanvasPassenger.setLayout(new FlowLayout(
							FlowLayout.LEFT, 0, 0));
				}

				updateLocomotiveProgressBar();
				trainDriverControls(departingTrain.trainCanMove(), e);

				validate();
				repaint();

			}

			break;

		// Remove Carriage Button Action
		case "Remove Carriage":

			// try to remove a carriage from departing train
			try {

				// removes the weight of the carriage fromt the total weight
				if (!(currentCarriage instanceof Locomotive)
						&& carriagePanelStack.size() > 1) {
					totalGrossWeight = totalGrossWeight
							- currentCarriage.getGrossWeight();

				} else if (carriagePanelStack.size() == 1) {
					totalGrossWeight = departingTrain.firstCarriage()
							.getGrossWeight();
				} else {
					resetToDefaultLayoutAndValues();
				}

				departingTrain.removeCarriage();
			} catch (TrainException e1) {
				ErrorMessageBox.append(e1.getMessage());
			}

			// Remove Carriage from physical window and logical line-up.
			carriagePanel.remove(carriagePanelStack.peek());
			carriagePanelStack.pop();

			currentCarriage = departingTrain.firstCarriage();

			// set the current carriage to the last carriage in the train
			// if there is more than one RollingStock object
			if (carriagePanelStack.size() > 1) {
				for (int i = 1; i < carriagePanelStack.size(); i++) {
					currentCarriage = departingTrain.nextCarriage();
				}
			}

			trainDriverControls(departingTrain.trainCanMove(), e);
			updateLocomotiveProgressBar();
			updateBoardComboBox();
			validate();
			repaint();

			break;

		// Boarding button action
		case "Click to Board":

			int passengersBoarding = (int) boardComboBox.getSelectedItem();
			passengersBoarded += passengersBoarding;
			spaceAvaliable = maxPassengerCapacity - passengersBoarded;

			// Resets the combo box to the size of number of passengers allowed
			// to board
			boardComboBox.removeAllItems();
			updateBoardComboBox();

			// try to board the number of passengers to the train
			try {
				departingTrain.board(passengersBoarding);
			} catch (TrainException e1) {
				ErrorMessageBox.append(e1.getMessage());
			}

			addFreightCarBtn.setEnabled(false);
			addPassengerCarBtn.setEnabled(false);
			removeCarriageBtn.setEnabled(false);

			// checks through the list of carriages and update the value of
			// the passenger car/s' progress bar
			departingTrain.firstCarriage();
			for (int i = 1; i < carriagePanelStack.size(); i++) {
				currentCarriage = departingTrain.nextCarriage();
				if (currentCarriage instanceof PassengerCar) {
					carriagePanelStack.get(i).getCarriageLabel()
							.setText(currentCarriage.toString());
					carriagePanelStack
							.get(i)
							.getProgressBar()
							.setValue(
									((PassengerCar) currentCarriage)
											.numberOnBoard());
				}
			}

			validate();
			repaint();
			break;
		}

		totalWeightLabel.setText("Train Total Weight: " + totalGrossWeight);
		trainPullingLimitLabel.setText("Train Pulling Capacity: "
				+ trainMaxPull);
		trainCanMoveLabel.setText("Train Can Move: "
				+ departingTrain.trainCanMove());
		totalPassengerLabel.setText("Total Passengers: " + passengersBoarded);

	}

	/**
	 * updates the Boarding of passenger combo box to always remain within the
	 * avaliable number of seats the train have left
	 * 
	 */
	private void updateBoardComboBox() {
		maxPassengerCapacity = (int) departingTrain.numberOfSeats();
		boardComboBox.removeAllItems();
		int defaultBoardItem = 0;

		boardComboBoxItems.add(new Integer(defaultBoardItem));
		spaceAvaliable = maxPassengerCapacity - passengersBoarded;
		for (int i = 0; i < spaceAvaliable; i++) {
			boardComboBoxItems.add(i + 1);

		}
		boardComboBox.setSelectedIndex(0);
	}

	/**
	 * Updates the progress bar for the locomotive canvas object
	 * 
	 */
	private void updateLocomotiveProgressBar() {
		if (carriagePanelStack.size() >= 1) {
			carriagePanelStack.get(0).getProgressBar().setMaximum(trainMaxPull);
			carriagePanelStack.get(0).getProgressBar()
					.setValue(totalGrossWeight);
		}
	}

	/**
	 * Ensures that when train cannot move, all the panels are disabled, unless
	 * a carriage is removed
	 * 
	 */
	private void trainDriverControls(Boolean trainCanMove, ActionEvent e) {
		if (!trainCanMove) {
			boardBtn.setEnabled(false);
			addPassengerCarBtn.setEnabled(false);
			addFreightCarBtn.setEnabled(false);
			ErrorMessageBox.append("The train is overladen and cannot move.");
		} else {
			if (e.getActionCommand() == "Add Freight Car") {
				addPassengerCarBtn.setEnabled(false);
			} else if (carriagePanelStack.size() == 0) {
				resetToDefaultLayoutAndValues();
			} else {
				passengerWeight.setEnabled(true);
				passengerLimitField.setEnabled(true);
				addPassengerCarBtn.setEnabled(true);
				addFreightCarBtn.setEnabled(true);
				boardBtn.setEnabled(true);
			}
		}
	}

	/**
	 * Resets the layout and instance variables to default
	 */
	private void resetToDefaultLayoutAndValues() {

		departingTrain.firstCarriage();
		trainDriverPanel.setVisible(true);

		locomotivePanel.setVisible(true);
		locomotiveSetupLabel.setEnabled(true);
		addLocomotiveBtn.setEnabled(true);
		locoWeightField.setText("");
		powerRating.setSelectedIndex(0);
		engineType.setSelectedIndex(0);

		passengerCarSetupLabel.setEnabled(false);
		addPassengerCarBtn.setEnabled(false);
		passengerLimitField.setEnabled(false);
		passengerWeight.setEnabled(false);
		passengerLimitField.setText("");
		passengerWeight.setText("");

		freightCarSetupLabel.setEnabled(false);
		addFreightCarBtn.setEnabled(false);
		freightWeight.setEnabled(false);
		goodsType.setSelectedIndex(0);
		freightWeight.setText("");

		goodsType.setEnabled(false);
		removeCarriageBtn.setEnabled(false);

		boardBtn.setEnabled(false);

		// Resets class variables to default
		totalGrossWeight = 0;
		passengersBoarded = 0;
		maxPassengerCapacity = 0;
		spaceAvaliable = 0;
		trainMaxPull = 0;
	}

}
