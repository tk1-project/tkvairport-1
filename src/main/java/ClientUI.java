import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import model.Flight;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;

public class ClientUI implements Serializable {
	
	private static Logger logger = Logger.getLogger(FlightServer.class.getName());

	private JFrame frame;
	private myTableModel tableModel;
	private JScrollPane tableScroll;
	private JTable table;
	private JPanel wrapper, buttonWrapper;
	private JButton btn_new, btn_edit, btn_del;
	private boolean rowSelected;
	
	private List<Flight> flights;

	private FlightClient client;
	
	public ClientUI(FlightClient client) {
		this.client = client;
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				initializeGUI();
			}
		});
	}	
	
	// initialize the user interface
	private void initializeGUI() {
		
		frame = new JFrame("TK Airport Arrivals / Departures");
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// inform server when client logs out
		frame.addWindowListener(new WindowListener() {
			public void windowOpened(WindowEvent e) {}
	        public void windowIconified(WindowEvent e) {}
	        public void windowDeiconified(WindowEvent e) {}
	        public void windowDeactivated(WindowEvent e) {}
	        public void windowClosed(WindowEvent e) {}
	        public void windowActivated(WindowEvent e) {}
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					client.stub.logout(client.getClientName());
				} catch (RemoteException ex) {
					ex.printStackTrace();
				}
			}	
		});
		
		addComponents(frame.getContentPane());	
		addData();
		frame.setVisible(true);
	}
	
	
	public void updateUI(List<Flight> flights) {
		this.flights = flights; 
		logger.log(Level.INFO, "Flights received: " + this.flights.toString());
		addData();
	}
	
	// add UI components to the UI
	private void addComponents(Container pane) {
		
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
        
        // the client can only select one row from table
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // add a listener to listen whether a row is selected or not
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				ListSelectionModel lsm = (ListSelectionModel)e.getSource();
				if(lsm.isSelectionEmpty()) {
					rowSelected = false;
				} else {
					rowSelected = true;
				}
			}
		});
        table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        table.setRowHeight(20);
        table.setRowMargin(2);
        wrapper.add(tableScroll, BorderLayout.CENTER);
        
        buttonWrapper= new JPanel();
        wrapper.add(buttonWrapper, BorderLayout.SOUTH);
        
        btn_new = addButton("New");
        buttonWrapper.add(btn_new);
        
        btn_edit = addButton("Edit");
        buttonWrapper.add(btn_edit);
        
        btn_del = addButton("Delete");
        buttonWrapper.add(btn_del); 
	}
	
	private ClientUI getClientUI() {
		return this;
	}
	
	// add button and set its clicking operation
	private JButton addButton(String text) {
		JButton button = new JButton(text);
		if (text == "New") {
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					
					// show new window and set iatacode and flight number editable
					ItemDetail dialog = new ItemDetail(getClientUI(), frame, "Flight Details - Add New Flight", true);
					dialog.setBounds(150, 150, 800, 600);
					dialog.setVisible(true);
				}
			});
		} else if(text == "Edit") {
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					int rowNumber = table.getSelectedRow();
					
					// if the client selected a row
					if(rowNumber >= 0 && rowSelected) {
						
						// show new window and set iatacode and flight number not editable
						ItemDetail dialog = new ItemDetail(getClientUI(), frame, "Flight Details - Edit Flight", false);
						
						// get flight info from the selected row
						String iataCode = table.getValueAt(rowNumber, 1).toString();
						String flightNumber = table.getValueAt(rowNumber, 2).toString();
						System.out.println("iataCode: " + iataCode);
						System.out.println("flightNumber: " + flightNumber);
						for(Flight f: flights) {
							if(f.getIataCode() == iataCode && f.getFlightNumber() == flightNumber) {

								// send the selected flight to the 'Flight Details' window
								dialog.addFlightData(f);
								break;
							}
						}
						dialog.setBounds(150, 150, 800, 600);
						dialog.setVisible(true);
					} else {
						showWarning();
					}
				}
			});
		} else if (text == "Delete") {
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent evt) {
					int rowNumber = table.getSelectedRow();
					
					// if the client selected a row
					if(rowNumber >= 0 && rowSelected) {
						
						// get flight info from the selected row
						String iataCode = table.getValueAt(rowNumber, 1).toString();
						String flightNumber = table.getValueAt(rowNumber, 2).toString();
						System.out.println("iataCode: " + iataCode);
						System.out.println("flightNumber: " + flightNumber);
						for(Flight f: flights) {
							if(f.getIataCode() == iataCode && f.getFlightNumber() == flightNumber) {
								flights.remove(f);
								
								if(client != null) {
									deleteFlight(f);
								}
								break;
							}
						}
					} else {
						showWarning();
					}
				}
			});
		}
		return button;
	}
	
	// show warning window if no row is selected
	private void showWarning() {
		JDialog dialog = new JDialog(frame, "Warning!");
		dialog.setBounds(350, 350, 300, 100);
		dialog.getContentPane().setLayout(new BorderLayout());
			
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		dialog.getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblPleaseSelectA = new JLabel("Please select a flight!");
			contentPanel.add(lblPleaseSelectA);
		}
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}
	
	// invocate remote method of server 'updateFlight'
	public void updateFlight(Flight f) {
		try {
			this.client.stub.updateFlight(this.client.getClientName(), f);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	// invocate remote method of server 'deleteFlight'
	public void deleteFlight(Flight f) {
		try {
			this.client.stub.deleteFlight(client.getClientName(), f);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	// add flight's data into the table
	private void addData() {
		
		Runnable newdo = new Runnable() {

			@Override
            public void run() {
				
				// clear the table
                tableModel.resetTable();
                
                Vector<String> col = new Vector<String>();
                Vector<Object> data = new Vector<Object>();
                
                // add column name to the table
                col.add("Operating Airlines");
                col.add("IATA Code");
                col.add("Tracking Number");
                col.add("Departure");
                col.add("Arrival");
                col.add("Terminal");
                col.add("Scheduled Time");
                col.add("Estimated Time");
                
                tableModel.setColumnNames(col);
                
                // add each flight information to the table as a row
                for(Flight f:flights) {
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
            }
        };
        SwingUtilities.invokeLater(newdo);
	}

	// define own table model
	private class myTableModel extends AbstractTableModel {
		
		private static final long serialVersionUID = 1L;
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
