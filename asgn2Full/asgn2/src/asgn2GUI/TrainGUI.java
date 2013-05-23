package asgn2GUI;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.*;
import asgn2Train.DepartingTrain;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.border.EtchedBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.util.Stack;
import javax.swing.JTextArea;

public class TrainGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = -7031008862559936404L;
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;

	private static final int LOCOMOTIVE_PAINT = 0;
	private static final int PASSENGERCAR_PAINT = 1;
	private static final int FREIGHTCAR_PAINT = 2;

	Stack<Canvas> carriagePanelStack;
	
	private int totalWeight = 0;

	private JComboBox<String> cmbBoxCarriageType;
	private JLabel lblPullingPower;
	private JLabel lblTotalWeight;
	private JLabel lblTrainMoveStatus;
	private JLabel lblPowerClass;
	private JTextField txtBoxPowerInput;
	private JLabel lblSeatCount;
	private JSpinner txtBoxSeatInput;
	private JLabel lblGoodsType;
	private JTextField txtBoxGoodsInput;
	private JLabel lblWeight;
	private JSpinner txtBoxWeightInput;

	private JPanel carriagePanel;
	private JPanel carriageEditPanelDriver;

	private DepartingTrain trainConfiguration;
	private RollingStock currentCarriage;

	/**
	 * @param arg0
	 * @throws HeadlessException
	 * @throws TrainException
	 */
	public TrainGUI(String arg0) throws HeadlessException {
		super(arg0);
		createGUI();
		trainConfiguration = new DepartingTrain();
		carriagePanelStack = new Stack<Canvas>();
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
		carriageScrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		setupViewContainer.add(carriageScrollPane);

		carriagePanel = new JPanel();
		carriageScrollPane.setViewportView(carriagePanel);
		carriagePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JPanel contentPanel = new JPanel();
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));

		JPanel leftDriverPanel = new JPanel();
		contentPanel.add(leftDriverPanel);
		leftDriverPanel.setLayout(new BorderLayout(0, 0));

		JPanel leftDriverPanelFloatRight = new JPanel();
		leftDriverPanel.add(leftDriverPanelFloatRight, BorderLayout.EAST);
		leftDriverPanelFloatRight.setAlignmentX(Component.RIGHT_ALIGNMENT);
		leftDriverPanelFloatRight.setBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null));
		leftDriverPanelFloatRight.setPreferredSize(new Dimension(375, 10));
		leftDriverPanelFloatRight.setMinimumSize(new Dimension(350, 10));
		leftDriverPanelFloatRight.setLayout(new BorderLayout(0, 0));

		JPanel carriageInfoPanelDriver = new JPanel();
		carriageInfoPanelDriver.setPreferredSize(new Dimension(10, 110));
		carriageInfoPanelDriver.setBorder(new EtchedBorder(EtchedBorder.LOWERED,
				null, null));
		leftDriverPanelFloatRight.add(carriageInfoPanelDriver, BorderLayout.NORTH);
		carriageInfoPanelDriver.setLayout(null);
		
		JLabel lblTrainWeightTitle = new JLabel("Train weight (t):");
		lblTrainWeightTitle.setBounds(12, 12, 100, 16);
		carriageInfoPanelDriver.add(lblTrainWeightTitle);
		
		lblTotalWeight = new JLabel();
		lblTotalWeight.setBounds(119, 12, 60, 16);
		carriageInfoPanelDriver.add(lblTotalWeight);
		
		JLabel lblPullingPowerTitle = new JLabel("Pulling power (t):");
		lblPullingPowerTitle.setBounds(191, 12, 100, 16);
		carriageInfoPanelDriver.add(lblPullingPowerTitle);
		
		lblPullingPower = new JLabel();
		lblPullingPower.setBounds(299, 12, 60, 16);
		carriageInfoPanelDriver.add(lblPullingPower);
		
		JLabel lblCanTheTrain = new JLabel("Can the train move?");
		lblCanTheTrain.setFont(new Font("Dialog", Font.BOLD, 14));
		lblCanTheTrain.setBounds(72, 71, 154, 31);
		carriageInfoPanelDriver.add(lblCanTheTrain);
		
		lblTrainMoveStatus = new JLabel();
		lblTrainMoveStatus.setFont(new Font("Dialog", Font.BOLD, 16));
		lblTrainMoveStatus.setBounds(231, 71, 60, 31);
		carriageInfoPanelDriver.add(lblTrainMoveStatus);

		carriageEditPanelDriver = new JPanel();
		leftDriverPanelFloatRight.add(carriageEditPanelDriver, BorderLayout.CENTER);
		carriageEditPanelDriver.setLayout(null);

		// Select a carriage Type combo box
		cmbBoxCarriageType = new JComboBox<String>();
		cmbBoxCarriageType.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (cmbBoxCarriageType.getSelectedIndex() == 0) {
					hideNewCarriageInput();
					lblWeight.setVisible(false);
					txtBoxWeightInput.setVisible(false);
				} else if (cmbBoxCarriageType.getSelectedIndex() == 1) {
					hideNewCarriageInput();
					lblPowerClass.setVisible(true);
					txtBoxPowerInput.setVisible(true);
					lblWeight.setVisible(true);
					txtBoxWeightInput.setVisible(true);
				} else if (cmbBoxCarriageType.getSelectedIndex() == 2) {
					hideNewCarriageInput();
					lblSeatCount.setVisible(true);
					txtBoxSeatInput.setVisible(true);
					lblWeight.setVisible(true);
					txtBoxWeightInput.setVisible(true);
				} else if (cmbBoxCarriageType.getSelectedIndex() == 3) {
					hideNewCarriageInput();
					lblGoodsType.setVisible(true);
					txtBoxGoodsInput.setVisible(true);
					lblWeight.setVisible(true);
					txtBoxWeightInput.setVisible(true);
				}
			}
		});
		cmbBoxCarriageType.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Enter a carriage type.", "Locomotive",
						"PassengerCar", "FreightCar" }));
		cmbBoxCarriageType.setBounds(12, 42, 163, 25);
		carriageEditPanelDriver.add(cmbBoxCarriageType);

		// PowerClass label
		lblPowerClass = new JLabel("Power class:");
		lblPowerClass.setVisible(false);
		lblPowerClass.setBounds(12, 79, 92, 16);
		carriageEditPanelDriver.add(lblPowerClass);

		// PowerClass input box
		txtBoxPowerInput = new JTextField();
		txtBoxPowerInput.setVisible(false);
		txtBoxPowerInput.setBounds(12, 101, 163, 20);
		carriageEditPanelDriver.add(txtBoxPowerInput);

		// Seat count label
		lblSeatCount = new JLabel("Seat count:");
		lblSeatCount.setVisible(false);
		lblSeatCount.setBounds(12, 79, 107, 16);
		carriageEditPanelDriver.add(lblSeatCount);

		// Seat count input box
		txtBoxSeatInput = new JSpinner();
		txtBoxSeatInput.setVisible(false);
		txtBoxSeatInput.setBounds(12, 101, 163, 20);
		carriageEditPanelDriver.add(txtBoxSeatInput);

		// Goods type label
		lblGoodsType = new JLabel("Goods Type:");
		lblGoodsType.setVisible(false);
		lblGoodsType.setBounds(12, 79, 124, 16);
		carriageEditPanelDriver.add(lblGoodsType);

		// Goods type input box
		txtBoxGoodsInput = new JTextField();
		txtBoxGoodsInput.setVisible(false);
		txtBoxGoodsInput.setBounds(12, 101, 163, 20);
		carriageEditPanelDriver.add(txtBoxGoodsInput);

		// Weight label
		lblWeight = new JLabel("Weight (kg):");
		lblWeight.setVisible(false);
		lblWeight.setBounds(187, 79, 124, 16);
		carriageEditPanelDriver.add(lblWeight);

		// Weight input box
		txtBoxWeightInput = new JSpinner();
		txtBoxWeightInput.setVisible(false);
		txtBoxWeightInput.setBounds(187, 101, 163, 20);
		carriageEditPanelDriver.add(txtBoxWeightInput);

		// Add Carriage to Train label
		JLabel lblAddCarriageTitle = new JLabel("Add a Carriage to Train:");
		lblAddCarriageTitle.setFont(new Font("Dialog", Font.BOLD, 14));
		lblAddCarriageTitle.setBounds(12, 12, 217, 25);
		carriageEditPanelDriver.add(lblAddCarriageTitle);

		// Add Carriage button
		JButton btnAddCarriage = new JButton("Add Carriage");
		btnAddCarriage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Canvas newCarriageCanvas = new Canvas();

				try {
					if (cmbBoxCarriageType.getSelectedItem() == "Enter a carriage type.") {
						return;
					} else if (cmbBoxCarriageType.getSelectedItem() == "Locomotive") {
						trainConfiguration.addCarriage(new Locomotive(
								(Integer) txtBoxWeightInput.getValue(),
								txtBoxPowerInput.getText()));
						newCarriageCanvas.figure = LOCOMOTIVE_PAINT;
					} else if (cmbBoxCarriageType.getSelectedItem() == "PassengerCar") {
						trainConfiguration.addCarriage(new PassengerCar(
								(Integer) txtBoxWeightInput.getValue(),
								(Integer) txtBoxSeatInput.getValue()));
						newCarriageCanvas.figure = PASSENGERCAR_PAINT;
					} else if (cmbBoxCarriageType.getSelectedItem() == "FreightCar") {
						trainConfiguration.addCarriage(new FreightCar(
								(Integer) txtBoxWeightInput.getValue(),
								txtBoxGoodsInput.getText()));
						newCarriageCanvas.figure = FREIGHTCAR_PAINT;
					}
				} catch (TrainException e) {
					e.printStackTrace();
					return;
				}

				// Find the train recently created so we can work with it
				// multiple times.
				if (carriagePanelStack.size() == 0)
					currentCarriage = trainConfiguration.firstCarriage();
				else
					currentCarriage = trainConfiguration.nextCarriage();

				// Stop the possible null carriage creating further exceptions.
				if (currentCarriage == null)
					return;
				
				totalWeight += currentCarriage.getGrossWeight();
				lblTotalWeight.setText("" + totalWeight);

				// Display their text readable description.
				newCarriageCanvas.getCarriageLabel().setText(
						currentCarriage.toString());

				// Check their capacity and display progressBar accordingly.
				if (currentCarriage instanceof Locomotive) {
					newCarriageCanvas.getProgressBar().setMaximum(
							((Locomotive) currentCarriage).power());
					newCarriageCanvas.getProgressBar().setValue(
							currentCarriage.getGrossWeight());
					lblPullingPower.setText("" + ((Locomotive) currentCarriage).power());
				} else if (currentCarriage instanceof PassengerCar) {
					newCarriageCanvas.getProgressBar().setMaximum(
							((PassengerCar) currentCarriage).numberOfSeats());
				} else if (currentCarriage instanceof FreightCar) {
					newCarriageCanvas.getProgressBar().setMaximum(
							currentCarriage.getGrossWeight());
					newCarriageCanvas.getProgressBar().setValue(
							currentCarriage.getGrossWeight());
				}

				// Add to logical list.
				carriagePanelStack.push(newCarriageCanvas);

				carriagePanel.add(newCarriageCanvas);
				newCarriageCanvas.setLayout(new FlowLayout(FlowLayout.LEFT, 0,
						0));

				//Update TrainCanMove status
				if(trainConfiguration.trainCanMove()) {
					lblTrainMoveStatus.setText("Yes");
					lblTrainMoveStatus.setForeground(Color.GREEN);
				} else {
					lblTrainMoveStatus.setText("No");
					lblTrainMoveStatus.setForeground(Color.RED);
				}
				
				validate();
				repaint();
			}
		});
		btnAddCarriage.setBounds(12, 133, 163, 39);
		carriageEditPanelDriver.add(btnAddCarriage);

		// Remove Carriage Button
		JButton btnRemoveCarriage = new JButton("Remove Carriage");
		btnRemoveCarriage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					// Remove from both logical list and physical GUI.
					if (!carriagePanelStack.isEmpty()) {
						// Remove Carriage from both physical window and logical
						// configuration stack.
						trainConfiguration.removeCarriage();
						carriagePanel.remove(carriagePanelStack.peek());
						carriagePanelStack.pop();
						
						totalWeight -= currentCarriage.getGrossWeight();

						//Return to the carriage before.
						trainConfiguration.firstCarriage();
						for (int i = 0; i < carriagePanelStack.size(); i++)
							trainConfiguration.nextCarriage();
						
						//Update TrainCanMove status
						if(trainConfiguration.trainCanMove()) {
							lblTrainMoveStatus.setText("Yes");
							lblTrainMoveStatus.setForeground(Color.GREEN);
						} else {
							lblTrainMoveStatus.setText("No");
							lblTrainMoveStatus.setForeground(Color.RED);
						}
						
						lblTotalWeight.setText("" + totalWeight);
					}
					validate();
					repaint();
				} catch (TrainException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnRemoveCarriage.setBounds(187, 133, 163, 39);
		carriageEditPanelDriver.add(btnRemoveCarriage);

		JPanel rightConductorPanel = new JPanel();
		contentPanel.add(rightConductorPanel);
		rightConductorPanel.setLayout(new BorderLayout(0, 0));

		JPanel rightConductorPanelFloatLeft = new JPanel();
		rightConductorPanel.add(rightConductorPanelFloatLeft, BorderLayout.WEST);
		rightConductorPanelFloatLeft.setAlignmentX(Component.LEFT_ALIGNMENT);
		rightConductorPanelFloatLeft.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		rightConductorPanelFloatLeft.setPreferredSize(new Dimension(375, 10));
		rightConductorPanelFloatLeft.setLayout(new BorderLayout(0, 0));

		JPanel carriageInfoPanelConductor = new JPanel();
		carriageInfoPanelConductor.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		FlowLayout fl_carriageInfoPanelConductor = (FlowLayout) carriageInfoPanelConductor.getLayout();
		fl_carriageInfoPanelConductor.setVgap(25);
		fl_carriageInfoPanelConductor.setHgap(25);
		rightConductorPanelFloatLeft.add(carriageInfoPanelConductor, BorderLayout.NORTH);

		JPanel carriageEditPanelConductor = new JPanel();
		carriageEditPanelConductor.setLayout(null);
		rightConductorPanelFloatLeft.add(carriageEditPanelConductor, BorderLayout.CENTER);

		JButton btnBoard = new JButton("Board");
		btnBoard.setBounds(12, 188, 99, 39);
		carriageEditPanelConductor.add(btnBoard);

		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(196, 188, 163, 39);
		carriageEditPanelConductor.add(btnReset);

		JSpinner txtBoxBoardAmountInput = new JSpinner();
		txtBoxBoardAmountInput.setFont(new Font("Dialog", Font.BOLD, 14));
		txtBoxBoardAmountInput.setBounds(108, 188, 64, 39);
		carriageEditPanelConductor.add(txtBoxBoardAmountInput);

		JLabel lblBoardPassengers = new JLabel("Board Passengers:");
		lblBoardPassengers.setFont(new Font("Dialog", Font.BOLD, 14));
		lblBoardPassengers.setBounds(12, 161, 160, 19);
		carriageEditPanelConductor.add(lblBoardPassengers);

		// Exception Panel
		JPanel exceptionPanel = new JPanel();
		exceptionPanel.setPreferredSize(new Dimension(10, 100));
		getContentPane().add(exceptionPanel, BorderLayout.SOUTH);
		exceptionPanel.setLayout(new BorderLayout(5, 5));

		// Exception Panel label
		JLabel lblErrorConsole = new JLabel("Error console:");
		exceptionPanel.add(lblErrorConsole, BorderLayout.NORTH);

		// Exception Panel text box
		JTextPane exceptionTextPane = new JTextPane();
		exceptionTextPane.setEditable(false);
		exceptionTextPane.setToolTipText("Error messages");
		exceptionTextPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED,
				null, null));
		exceptionPanel.add(exceptionTextPane);
	}

	private void hideNewCarriageInput() {
		lblGoodsType.setVisible(false);
		txtBoxGoodsInput.setVisible(false);
		lblSeatCount.setVisible(false);
		txtBoxSeatInput.setVisible(false);
		lblPowerClass.setVisible(false);
		txtBoxPowerInput.setVisible(false);
	}

	public void actionPerformed(ActionEvent e) {
		String buttonString = e.getActionCommand();

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TrainGUI gui = new TrainGUI("Departing Train");
		gui.setVisible(true);

	}

	public JPanel getCarriagePanel() {
		return carriagePanel;
	}
}
