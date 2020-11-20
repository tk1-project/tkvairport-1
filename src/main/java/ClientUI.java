import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

import model.ArrivalFlight;
import model.DepartureFlight;
import model.Flight;
import model.FlightStatus;

import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;

public class ClientUI implements Serializable {
	
	private static Logger logger = Logger.getLogger(FlightServer.class.getName());

	private JFrame frame;
	private myTableModel tableModel;
	private JScrollPane tableScroll;
	private JTable table;
	private JPanel wrapper, buttonWrapper;
	private JButton btn_new, btn_edit, btn_del;
	
	private List<Flight> flights;

	public ClientUI() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				creatAndShowGUI();
			}
		});
	}	
	
	public void receiveFlights(List<Flight> flights) {
		this.flights = flights; 
		logger.log(Level.INFO, "Flights received: " + this.flights.toString());
	}
	
	private void addComponentsToPane(Container pane) {
		
		wrapper = new JPanel();
	    wrapper.setLayout(new BorderLayout(5, 5));
	    pane.add(wrapper);
		
		tableModel = new myTableModel();
		table = new JTable(tableModel);
		tableScroll = new JScrollPane(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
        tableScroll = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tableScroll.setBorder(null);
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        table.setRowHeight(20);
        table.setRowMargin(2);
        wrapper.add(tableScroll, BorderLayout.CENTER);
        
        buttonWrapper= new JPanel();
        wrapper.add(buttonWrapper, BorderLayout.SOUTH);
        
        btn_new = new JButton("New");
        buttonWrapper.add(btn_new);
        
        btn_edit = new JButton("Edit");
        buttonWrapper.add(btn_edit);
        
        btn_del = new JButton("Delete");
        buttonWrapper.add(btn_del); 
		
	}
	
	private void addData() {
		
		ArrayList<String> arrivalGates = new ArrayList();
		arrivalGates.add("B54");
		arrivalGates.add("B55");

		ArrayList<String> departureGates = new ArrayList();
		departureGates.add("A11");
		departureGates.add("B13");
		
		Flight[] showFlights = new Flight[] {
				new DepartureFlight("LH", "Lufthansa", "A380", "591", "NBO", "FRA", 
						LocalDate.of(2020, 11, 11), FlightStatus.B, LocalDateTime.of(2020, 11, 11, 12, 0), "terminal", departureGates, 
						"cLocation", "cCounter", LocalDateTime.of(2020, 11, 11, 13, 0), LocalDateTime.of(2020, 11, 11, 13, 0)),
				new ArrivalFlight("LH", "Lufthansa", "A380", "999", "BER", "SFO", 
						LocalDate.of(2019,  11,  11), FlightStatus.D, LocalDateTime.of(2020, 12, 12, 12, 0), "1", 
						arrivalGates, LocalDateTime.of(2020,  12, 12, 13, 0))
		};
		
		Runnable newdo = new Runnable() {

			@Override
            public void run() {
                tableModel.resetTable();
                Vector<String> col = new Vector<String>();
                Vector<Object> data = new Vector<Object>();
                col.add("Operating Airlines");
                col.add("IATA Code");
                col.add("Tracking Number");
                col.add("Departure");
                col.add("Arrival");
                col.add("Terminal");
                col.add("Scheduled Time");
                col.add("Estimated Time");
                
                for(Flight f:showFlights) {
                	data = null;
                    data = new Vector<Object>();
                    data.addElement(f.getAirline());
                    data.addElement(f.getIataCode());
                    data.addElement(f.getFlightNumber());
                    data.addElement(f.getDepartureAirport());
                    data.addElement(f.getArrivalAirport());
                    data.addElement(f.getTerminal());
                    data.addElement(f.getOriginDate());
                    data.addElement(f.getsTime());
                    tableModel.addRow(data);
                }
                addTableListener();
            }
        };
        SwingUtilities.invokeLater(newdo);
	}
	
	private void addTableListener() {
		
	}

 	private void creatAndShowGUI() {
		
		//Create and set up the window
		frame = new JFrame("TK Airport Arrivals / Departures");
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		addComponentsToPane(frame.getContentPane());	
		addData();
		frame.setVisible(true);
	}
	
	private class myTableModel extends AbstractTableModel {
		
		private Vector<Vector<Object>> data;
		private Vector<String> colNames;
		
		myTableModel() {
			this.colNames = new Vector<String>();
			this.data = new Vector<Vector<Object>>();
		}
		
		public void resetTable() {
			this.colNames.removeAllElements();
			this.data.removeAllElements();
		}
		
		public void setColumnNames(Vector<String> colNames) {
			this.colNames = colNames;
			this.fireTableStructureChanged();
		}
		
		public void addRow(Vector<Object> data) {
			this.data.add(data);
			this.fireTableDataChanged();
			this.fireTableStructureChanged();
		}
		
		public void removeRowAt(int row) {
			this.data.removeElementAt(row);
			this.fireTableDataChanged();
		}
		
		@Override
		public int getColumnCount() {
            return this.colNames.size();
        }
		
		@Override
		public boolean isCellEditable(int row, int colNum) {
        	return false;
        }

        @Override
        public String getColumnName(int colNum) {
            return this.colNames.get(colNum);
        }
        
        @Override
        public int getRowCount() {
            return this.data.size();
        }

        @Override
        public Object getValueAt(int row, int col) {
            Vector<Object> value = this.data.get(row);
            return value.get(col);
        }
        
        @Override
        public void setValueAt(Object value, int row, int col) {
        	Vector<Object> aRow = data.elementAt(row);
        	aRow.remove(col);
        	aRow.insertElementAt(value, col);
        	fireTableCellUpdated(row, col);
        }
	}
}
