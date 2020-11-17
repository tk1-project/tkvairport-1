import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class ClientUI {

	private JFrame frame;
	private JTable table;

	/**
	 * Create the application.
	 */
	public ClientUI() {
		initialize();
	}
	
	public void launch() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientUI window = new ClientUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("TK Airport Arrivals / Departures");
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		String data[][] = {{"Lufthansa", "LH", "591", "", "", "", "", ""},
				{"Lufthansa", "LH", "1241", "", "", "", "", ""}};
		String column[] = {"Operating Airlines", "IATA Code", "Tracking Number",
				"Departure", "Arrival", "Terminal", "Scheduled Time", "Estimated Time"};
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{361, 72, 356, 0};
		gridBagLayout.rowHeights = new int[]{517, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		frame.getContentPane().add(scrollPane, gbc_scrollPane);
		
		table = new JTable(data, column);
		scrollPane.setViewportView(table);
		
		
		// button
		JButton btn1 = new JButton("New");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog frame = new JDialog();
				frame.setTitle("Flight Details");
				frame.setBounds(150, 150, 800, 600);
				frame.setVisible(true);
			}
		});
		GridBagConstraints gbc_btn1 = new GridBagConstraints();
		gbc_btn1.anchor = GridBagConstraints.NORTHEAST;
		gbc_btn1.insets = new Insets(0, 0, 0, 5);
		gbc_btn1.gridx = 0;
		gbc_btn1.gridy = 1;
		frame.getContentPane().add(btn1, gbc_btn1);
		
		JButton btn2 = new JButton("Edit");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog frame = new JDialog();
				frame.setTitle("Flight Details");
				frame.setBounds(150, 150, 800, 600);
				frame.setVisible(true);
			}
		});
		GridBagConstraints gbc_btn2 = new GridBagConstraints();
		gbc_btn2.anchor = GridBagConstraints.NORTH;
		gbc_btn2.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn2.insets = new Insets(0, 0, 0, 5);
		gbc_btn2.gridx = 1;
		gbc_btn2.gridy = 1;
		frame.getContentPane().add(btn2, gbc_btn2);
		
		JButton btn3 = new JButton("Delete");
		GridBagConstraints gbc_btn3 = new GridBagConstraints();
		gbc_btn3.anchor = GridBagConstraints.NORTHWEST;
		gbc_btn3.gridx = 2;
		gbc_btn3.gridy = 1;
		frame.getContentPane().add(btn3, gbc_btn3);

	}
}
