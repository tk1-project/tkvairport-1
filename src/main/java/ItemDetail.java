import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import model.ArrivalFlight;
import model.DepartureFlight;
import model.Flight;
import model.FlightStatus;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JComboBox;

public class ItemDetail extends JDialog {

	private final JPanel wrapper = new JPanel();
	private JTextField iataCode;
	private JTextField airline;
	private JTextField model;
	private JTextField flightNumber;
	private JTextField departureAirport;
	private JTextField arrivalAirport;
	private JTextField originDate;
	private JTextField sDepTime;
	private JTextField sArrTime;
	private JTextField depTerminal;
	private JTextField arrTerminal;
	private JTextField depGates;
	private JTextField arrGates;
	private JTextField eDeparture;
	private JTextField eArrival;
	private JTextField cLocation;
	private JTextField cCounter;
	private JTextField cTimeMin;
	private JTextField cTimeMax;
	private JComboBox flightStates;
	
	JRadioButton bArr, bDep;
	
	private boolean depFlight;
	private boolean editFlight;
	
	private ClientUI ui;

	public ItemDetail(ClientUI ui, JFrame frame, String title, boolean editable) {
		super(frame, title);
		this.ui = ui;
		this.editFlight = editable;
		
		initializeGUI();
	}
	
	private void initializeGUI() {
		setBounds(150, 150, 800, 600);
		getContentPane().setLayout(new BorderLayout());
		wrapper.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(wrapper, BorderLayout.NORTH);

		GridBagLayout gbl_wrapper = new GridBagLayout();
		gbl_wrapper.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0};
		wrapper.setLayout(gbl_wrapper);

		JLabel[] label = new JLabel[] {
				new JLabel("IATA Code"),
				new JLabel("Operating Airline"),
				new JLabel("AirCraft Model Name"),
				new JLabel("Tracking Number"),
				new JLabel("Departure Airport"),
				new JLabel("Arrival Airport"),
				new JLabel("Origin Date"),
				new JLabel("Scheduled Departure"),
				new JLabel("Scheduled Arrival"),
				new JLabel("Departure Terminal"),
				new JLabel("Arrival Terminal"),
				new JLabel("Departure Gates"),
				new JLabel("Arrival Gates"),
				new JLabel("Estimated Departure"),
				new JLabel("Estimated Arrival"),
				new JLabel("Check-in Location"),
				new JLabel("Check-in Counter"),
				new JLabel("Check-in Start"),
				new JLabel("Check-in End"),
				new JLabel("Flight States")
		};

		GridBagConstraints[] gbc = new GridBagConstraints[] {
				new GridBagConstraints(0,0,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(0, 0, 5, 5),20,16),
				new GridBagConstraints(2,0,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(0, 0, 5, 5),20,16),
				new GridBagConstraints(0,1,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(0, 0, 5, 5),20,16),
				new GridBagConstraints(0,2,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(0, 0, 5, 5),20,16),
				new GridBagConstraints(0,3,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(0, 0, 5, 5),20,16),
				new GridBagConstraints(2,3,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(0, 0, 5, 5),20,16),
				new GridBagConstraints(0,4,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(0, 0, 5, 5),20,16),
				new GridBagConstraints(0,5,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(0, 0, 5, 5),20,16),
				new GridBagConstraints(2,5,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(0, 0, 5, 5),20,16),
				new GridBagConstraints(0,6,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(0, 0, 5, 5),20,16),
				new GridBagConstraints(2,6,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(0, 0, 5, 5),20,16),
				new GridBagConstraints(0,7,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(0, 0, 5, 5),20,16),
				new GridBagConstraints(2,7,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(0, 0, 5, 5),20,16),
				new GridBagConstraints(0,8,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(0, 0, 5, 5),20,16),
				new GridBagConstraints(2,8,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(0, 0, 5, 5),20,16),
				new GridBagConstraints(0,9,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(0, 0, 5, 5),20,16),
				new GridBagConstraints(0,10,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(0, 0, 5, 5),20,16),
				new GridBagConstraints(0,11,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(0, 0, 5, 5),20,16),
				new GridBagConstraints(2,11,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(0, 0, 5, 5),20,16),
				new GridBagConstraints(0,12,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(0, 0, 5, 5),20,16),

		};

		for(int num = 0; num < label.length; num++) {
			wrapper.add(label[num], gbc[num]);
		}
		
		{
			iataCode = new JTextField();
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.gridx = 1;
			gbc_textField.gridy = 0;
			wrapper.add(iataCode, gbc_textField);
			iataCode.setColumns(10);
		}
		{
			airline = new JTextField();
			GridBagConstraints gbc_textField_1 = new GridBagConstraints();
			gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_1.gridx = 3;
			gbc_textField_1.gridy = 0;
			wrapper.add(airline, gbc_textField_1);
			airline.setColumns(10);
		}

		{
			model = new JTextField();
			GridBagConstraints gbc_textField_2 = new GridBagConstraints();
			gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_2.gridx = 1;
			gbc_textField_2.gridy = 1;
			wrapper.add(model, gbc_textField_2);
			model.setColumns(10);
		}

		{
			flightNumber = new JTextField();
			GridBagConstraints gbc_textField_4 = new GridBagConstraints();
			gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_4.gridx = 1;
			gbc_textField_4.gridy = 2;
			wrapper.add(flightNumber, gbc_textField_4);
			flightNumber.setColumns(10);
		}

		{
			departureAirport = new JTextField();
			GridBagConstraints gbc_textField_5 = new GridBagConstraints();
			gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_5.gridx = 1;
			gbc_textField_5.gridy = 3;
			wrapper.add(departureAirport, gbc_textField_5);
			departureAirport.setColumns(10);
		}
		{
			arrivalAirport = new JTextField();
			GridBagConstraints gbc_textField_6 = new GridBagConstraints();
			gbc_textField_6.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_6.gridx = 3;
			gbc_textField_6.gridy = 3;
			wrapper.add(arrivalAirport, gbc_textField_6);
			arrivalAirport.setColumns(10);
		}
		{
			originDate = new JTextField();
			GridBagConstraints gbc_textField_8 = new GridBagConstraints();
			gbc_textField_8.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_8.gridx = 1;
			gbc_textField_8.gridy = 4;
			wrapper.add(originDate, gbc_textField_8);
			originDate.setColumns(10);
		}
		{
			sDepTime = new JTextField();
			GridBagConstraints gbc_textField_10 = new GridBagConstraints();
			gbc_textField_10.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_10.gridx = 1;
			gbc_textField_10.gridy = 5;
			wrapper.add(sDepTime, gbc_textField_10);
			sDepTime.setColumns(10);
		}
		{
			sArrTime = new JTextField();
			GridBagConstraints gbc_textField_11 = new GridBagConstraints();
			gbc_textField_11.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_11.gridx = 3;
			gbc_textField_11.gridy = 5;
			wrapper.add(sArrTime, gbc_textField_11);
			sArrTime.setColumns(10);
		}
		{
			depTerminal = new JTextField();
			GridBagConstraints gbc_textField_12 = new GridBagConstraints();
			gbc_textField_12.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_12.gridx = 1;
			gbc_textField_12.gridy = 6;
			wrapper.add(depTerminal, gbc_textField_12);
			depTerminal.setColumns(10);
		}
		{
			arrTerminal = new JTextField();
			GridBagConstraints gbc_textField_13 = new GridBagConstraints();
			gbc_textField_13.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_13.gridx = 3;
			gbc_textField_13.gridy = 6;
			wrapper.add(arrTerminal, gbc_textField_13);
			arrTerminal.setColumns(10);
		}
		{
			depGates = new JTextField();
			GridBagConstraints gbc_textField_14 = new GridBagConstraints();
			gbc_textField_14.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_14.gridx = 1;
			gbc_textField_14.gridy = 7;
			wrapper.add(depGates, gbc_textField_14);
			depGates.setColumns(10);
		}
		{
			arrGates = new JTextField();
			GridBagConstraints gbc_textField_15 = new GridBagConstraints();
			gbc_textField_15.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_15.gridx = 3;
			gbc_textField_15.gridy = 7;
			wrapper.add(arrGates, gbc_textField_15);
			arrGates.setColumns(10);
		}
		{
			eDeparture = new JTextField();
			GridBagConstraints gbc_textField_16 = new GridBagConstraints();
			gbc_textField_16.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_16.gridx = 1;
			gbc_textField_16.gridy = 8;
			wrapper.add(eDeparture, gbc_textField_16);
			eDeparture.setColumns(10);
		}
		{
			eArrival = new JTextField();
			GridBagConstraints gbc_textField_17 = new GridBagConstraints();
			gbc_textField_17.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_17.gridx = 3;
			gbc_textField_17.gridy = 8;
			wrapper.add(eArrival, gbc_textField_17);
			eArrival.setColumns(10);
		}
		{
			cLocation = new JTextField();
			GridBagConstraints gbc_textField_18 = new GridBagConstraints();
			gbc_textField_18.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_18.gridx = 1;
			gbc_textField_18.gridy = 9;
			wrapper.add(cLocation, gbc_textField_18);
			cLocation.setColumns(10);
		}
		{
			cCounter = new JTextField();
			GridBagConstraints gbc_textField_20 = new GridBagConstraints();
			gbc_textField_20.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_20.gridx = 1;
			gbc_textField_20.gridy = 10;
			wrapper.add(cCounter, gbc_textField_20);
			cCounter.setColumns(10);
		}
		{
			cTimeMin = new JTextField();
			GridBagConstraints gbc_textField_22 = new GridBagConstraints();
			gbc_textField_22.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_22.gridx = 1;
			gbc_textField_22.gridy = 11;
			wrapper.add(cTimeMin, gbc_textField_22);
			cTimeMin.setColumns(10);
		}
		{
			cTimeMax = new JTextField();
			GridBagConstraints gbc_textField_23 = new GridBagConstraints();
			gbc_textField_23.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_23.gridx = 3;
			gbc_textField_23.gridy = 11;
			wrapper.add(cTimeMax, gbc_textField_23);
			cTimeMax.setColumns(10);
		}
		{
			flightStates = new JComboBox(FlightStatus.getValues());
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.gridwidth = 3;
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 1;
			gbc_comboBox.gridy = 12;
			wrapper.add(flightStates, gbc_comboBox);
		}		
		
		originDate.setText("1970-01-01");
		iataCode.setEditable(this.editFlight);
		flightNumber.setEditable(this.editFlight);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				
				JButton btn_ccl = addButton("Cancel");
		        buttonPane.add(btn_ccl);
		        
			}
			{

				JButton btn_save = addButton("Save");
		        buttonPane.add(btn_save);
				
				getRootPane().setDefaultButton(btn_save);
			}
			{
				ButtonGroup bGroup = new ButtonGroup();
				
				bDep = new JRadioButton("Departure");
				bDep.setSelected(true);
				
				bDep.addActionListener(new ActionListener() {
			        @Override
			        public void actionPerformed(ActionEvent e) {
			            setDepFlight(true);
			        }
			    });
				
				bArr = new JRadioButton("Arrival");
				
				bArr.addActionListener(new ActionListener() {
			        @Override
			        public void actionPerformed(ActionEvent e) {
			        	setDepFlight(false);
			        }
			    });
				
				bGroup.add(bDep);
				bGroup.add(bArr);
				
				buttonPane.add(bDep);
				buttonPane.add(bArr);
				
				setDepFlight(true);
			}
		}
	}
	
	public void addFlightData(Flight f) {
		if(f != null) {
			iataCode.setText(f.getIataCode());
			airline.setText(f.getAirline());
			model.setText(f.getModel());
			flightNumber.setText(f.getFlightNumber());
			departureAirport.setText(f.getDepartureAirport());
			arrivalAirport.setText(f.getArrivalAirport());
			originDate.setText(f.getOriginDate().toString());
			flightStates.setSelectedItem(f.getStatus().getText());
			
			
			if (f instanceof DepartureFlight) {
				setDepFlight(true);
				DepartureFlight handle = (DepartureFlight) f;
				sDepTime.setText(handle.getsTime().toString());
				depTerminal.setText(handle.getTerminal());
				depGates.setText(handle.getGates());
				eDeparture.setText(handle.geteTime().toString());
				cLocation.setText(handle.getcLocation());
				cCounter.setText(handle.getcCounter());
				cTimeMin.setText(handle.getcTimeMin().toString());
				cTimeMax.setText(handle.getcTimeMax().toString());
				
			} else if (f instanceof ArrivalFlight) {
				setDepFlight(false);
				sArrTime.setText(f.getsTime().toString());
				arrTerminal.setText(f.getTerminal());
				arrGates.setText(f.getGates());
				eArrival.setText(f.geteTime().toString());
			}
		}
	}
	
	private JButton addButton(String text) {
		JButton button = new JButton(text);
		if (text == "Cancel") {
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					System.out.println("Cancel...");
					setVisible(false);
				}
			});
		} else if(text == "Save") {
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					System.out.println("Save...");

					Flight f;
					if(isDataValid() && depFlight) {
						f = new DepartureFlight(iataCode.getText(), airline.getText(), model.getText(), flightNumber.getText(),
								departureAirport.getText(), arrivalAirport.getText(), LocalDate.parse(originDate.getText()), 
								FlightStatus.valueOfIndex(flightStates.getSelectedIndex()), LocalDateTime.parse(sDepTime.getText()), 
								depTerminal.getText(), depGates.getText(), LocalDateTime.parse(eDeparture.getText()), cLocation.getText(), 
								cCounter.getText(), LocalDateTime.parse(cTimeMin.getText()), LocalDateTime.parse(cTimeMax.getText()));
						ui.updateFlight(f);
						setVisible(false);
					} else if (isDataValid() && !depFlight) {
						f = new ArrivalFlight(iataCode.getText(), airline.getText(), model.getText(), flightNumber.getText(),
								departureAirport.getText(), arrivalAirport.getText(), LocalDate.parse(originDate.getText()), 
								FlightStatus.valueOfIndex(flightStates.getSelectedIndex()), LocalDateTime.parse(sArrTime.getText()), 
								arrTerminal.getText(), arrGates.getText(), LocalDateTime.parse(eArrival.getText()));

						ui.updateFlight(f);
						setVisible(false);
					}
				}
			});
		}
		return button;
	}
	
	private void setDepFlight(boolean depFlight) {
		this.depFlight = depFlight;
		
		for (JTextField t: new JTextField[] {
				sDepTime,
				depTerminal,
				depGates,
				eDeparture,
				cLocation,
				cCounter,
				cTimeMin,
				cTimeMax
		}) {
			bDep.setSelected(depFlight);
			t.setEditable(depFlight);
			
		}
		
		for (JTextField t: new JTextField[] {
				sArrTime,
				arrTerminal,
				arrGates,
				eArrival
		}) {
			bArr.setSelected(!depFlight);
			t.setEditable(!depFlight);
		}
		
		if (depFlight) {
			sDepTime.setText("1970-01-01T00:00");
			eDeparture.setText("1970-01-01T00:00");
			cTimeMin.setText("1970-01-01T00:00");
			cTimeMax.setText("1970-01-01T00:00");
			sArrTime.setText(null);
			eArrival.setText(null);
		} else {
			sDepTime.setText(null);
			eDeparture.setText(null);
			cTimeMin.setText(null);
			cTimeMax.setText(null);
			sArrTime.setText("1970-01-01T00:00");
			eArrival.setText("1970-01-01T00:00");
		}
	}
	
	private boolean isDataValid() {
		try {
			//TODO: error
			boolean errorOccured = false;
			for(JTextField t: new JTextField[] {
					iataCode,
					flightNumber
			}) {
				if(t.getText() == null || t.getText().length() == 0) {
					t.setBorder(BorderFactory.createLineBorder(Color.RED));
					errorOccured = true;
				} else {
					t.setBorder(BorderFactory.createLineBorder(Color.WHITE));
				}
			}
			if (originDate.getText() == null || !originDate.getText().matches("([0-9]{4})-([0-9]{2})-([0-9]{2})")) {
				originDate.setBorder(BorderFactory.createLineBorder(Color.RED));
				errorOccured = true;
			}
			
			JTextField[] depFields = new JTextField[] {
					sDepTime,
					eDeparture,
					cTimeMin,
					cTimeMax
			};
			JTextField[] arrFields = new JTextField[] {
					sArrTime,
					eArrival
			};
			
			JTextField[] checkFields;
			JTextField[] resetFields;
			
			
			if (depFlight) {
				checkFields = depFields;
				resetFields = arrFields;
			} else {
				checkFields = arrFields;
				resetFields = depFields;
			}
			for(JTextField t: resetFields) {
				t.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			}
			for(JTextField t: checkFields) {
					if(t.getText() == null || !t.getText().matches("([0-9]{4})-([0-9]{2})-([0-9]{2})T([0-9]{2}):([0-9]{2})")) {
						t.setBorder(BorderFactory.createLineBorder(Color.RED));
						errorOccured = true;
					} else {
						t.setBorder(BorderFactory.createLineBorder(Color.WHITE));
					}
			}
			return !errorOccured;	
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}

