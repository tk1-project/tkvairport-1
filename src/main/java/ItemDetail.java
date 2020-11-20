import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class ItemDetail extends JDialog {

	private final JPanel wrapper = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_8;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private JTextField textField_18;
	private JTextField textField_20;
	private JTextField textField_22;
	private JTextField textField_23;
	private JComboBox comboBox;

	
	/*private class Constraints extends GridBagConstraints {
		
		public Constraints(int gridx, int gridy) {
			
			this.gridx = gridx;
	        this.gridy = gridy;
	        this.gridwidth = 1;
	        this.gridheight = 1;
	        this.fill = NONE;
	        this.ipadx = 20;
	        this.ipady = 16;
	        this.insets = new Insets(0, 0, 5, 5);
	        this.anchor  = EAST;
	        this.weightx = 0;
	        this.weighty = 0;
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public ItemDetail() {
		
		
		setBounds(150, 150, 800, 600);
		getContentPane().setLayout(new BorderLayout());
		wrapper.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(wrapper, BorderLayout.NORTH);
		
		GridBagLayout gbl_wrapper = new GridBagLayout();
		gbl_wrapper.columnWidths = new int[]{0, 20, 0, 0, 0};
		gbl_wrapper.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_wrapper.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_wrapper.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		wrapper.setLayout(gbl_wrapper);
		
		JLabel[] label = new JLabel[] {
				new JLabel("IATA Code"),
				new JLabel("Operating Airline"),
				new JLabel("AirCraft Model Name"),
				new JLabel("Tracking Number"),
				new JLabel("Departure Airport"),
				new JLabel("Arrival Airport"),
				new JLabel("Origin Data"),
				new JLabel("Scheduled Departure Time"),
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
		
		JTextField[] text = new JTextField[20];

		{
			textField = new JTextField();
			textField.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.insets = new Insets(0, 0, 5, 5);
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.gridx = 1;
			gbc_textField.gridy = 0;
			wrapper.add(textField, gbc_textField);
			textField.setColumns(10);
		}
		{
			textField_1 = new JTextField();
			GridBagConstraints gbc_textField_1 = new GridBagConstraints();
			gbc_textField_1.insets = new Insets(0, 0, 5, 0);
			gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_1.gridx = 3;
			gbc_textField_1.gridy = 0;
			wrapper.add(textField_1, gbc_textField_1);
			textField_1.setColumns(10);
		}

		{
			textField_2 = new JTextField();
			GridBagConstraints gbc_textField_2 = new GridBagConstraints();
			gbc_textField_2.insets = new Insets(0, 0, 5, 5);
			gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_2.gridx = 1;
			gbc_textField_2.gridy = 1;
			wrapper.add(textField_2, gbc_textField_2);
			textField_2.setColumns(10);
		}

		{
			textField_4 = new JTextField();
			GridBagConstraints gbc_textField_4 = new GridBagConstraints();
			gbc_textField_4.insets = new Insets(0, 0, 5, 5);
			gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_4.gridx = 1;
			gbc_textField_4.gridy = 2;
			wrapper.add(textField_4, gbc_textField_4);
			textField_4.setColumns(10);
		}

		{
			textField_5 = new JTextField();
			GridBagConstraints gbc_textField_5 = new GridBagConstraints();
			gbc_textField_5.insets = new Insets(0, 0, 5, 5);
			gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_5.gridx = 1;
			gbc_textField_5.gridy = 3;
			wrapper.add(textField_5, gbc_textField_5);
			textField_5.setColumns(10);
		}
		{
			textField_6 = new JTextField();
			GridBagConstraints gbc_textField_6 = new GridBagConstraints();
			gbc_textField_6.insets = new Insets(0, 0, 5, 0);
			gbc_textField_6.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_6.gridx = 3;
			gbc_textField_6.gridy = 3;
			wrapper.add(textField_6, gbc_textField_6);
			textField_6.setColumns(10);
		}
		{
			textField_8 = new JTextField();
			GridBagConstraints gbc_textField_8 = new GridBagConstraints();
			gbc_textField_8.insets = new Insets(0, 0, 5, 5);
			gbc_textField_8.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_8.gridx = 1;
			gbc_textField_8.gridy = 4;
			wrapper.add(textField_8, gbc_textField_8);
			textField_8.setColumns(10);
		}
		{
			textField_10 = new JTextField();
			GridBagConstraints gbc_textField_10 = new GridBagConstraints();
			gbc_textField_10.insets = new Insets(0, 0, 5, 5);
			gbc_textField_10.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_10.gridx = 1;
			gbc_textField_10.gridy = 5;
			wrapper.add(textField_10, gbc_textField_10);
			textField_10.setColumns(10);
		}
		{
			textField_11 = new JTextField();
			GridBagConstraints gbc_textField_11 = new GridBagConstraints();
			gbc_textField_11.insets = new Insets(0, 0, 5, 0);
			gbc_textField_11.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_11.gridx = 3;
			gbc_textField_11.gridy = 5;
			wrapper.add(textField_11, gbc_textField_11);
			textField_11.setColumns(10);
		}
		{
			textField_12 = new JTextField();
			GridBagConstraints gbc_textField_12 = new GridBagConstraints();
			gbc_textField_12.insets = new Insets(0, 0, 5, 5);
			gbc_textField_12.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_12.gridx = 1;
			gbc_textField_12.gridy = 6;
			wrapper.add(textField_12, gbc_textField_12);
			textField_12.setColumns(10);
		}
		{
			textField_13 = new JTextField();
			GridBagConstraints gbc_textField_13 = new GridBagConstraints();
			gbc_textField_13.insets = new Insets(0, 0, 5, 0);
			gbc_textField_13.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_13.gridx = 3;
			gbc_textField_13.gridy = 6;
			wrapper.add(textField_13, gbc_textField_13);
			textField_13.setColumns(10);
		}
		{
			textField_14 = new JTextField();
			GridBagConstraints gbc_textField_14 = new GridBagConstraints();
			gbc_textField_14.insets = new Insets(0, 0, 5, 5);
			gbc_textField_14.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_14.gridx = 1;
			gbc_textField_14.gridy = 7;
			wrapper.add(textField_14, gbc_textField_14);
			textField_14.setColumns(10);
		}
		{
			textField_15 = new JTextField();
			GridBagConstraints gbc_textField_15 = new GridBagConstraints();
			gbc_textField_15.insets = new Insets(0, 0, 5, 0);
			gbc_textField_15.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_15.gridx = 3;
			gbc_textField_15.gridy = 7;
			wrapper.add(textField_15, gbc_textField_15);
			textField_15.setColumns(10);
		}
		{
			textField_16 = new JTextField();
			GridBagConstraints gbc_textField_16 = new GridBagConstraints();
			gbc_textField_16.insets = new Insets(0, 0, 5, 5);
			gbc_textField_16.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_16.gridx = 1;
			gbc_textField_16.gridy = 8;
			wrapper.add(textField_16, gbc_textField_16);
			textField_16.setColumns(10);
		}
		{
			textField_17 = new JTextField();
			GridBagConstraints gbc_textField_17 = new GridBagConstraints();
			gbc_textField_17.insets = new Insets(0, 0, 5, 0);
			gbc_textField_17.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_17.gridx = 3;
			gbc_textField_17.gridy = 8;
			wrapper.add(textField_17, gbc_textField_17);
			textField_17.setColumns(10);
		}
		{
			textField_18 = new JTextField();
			GridBagConstraints gbc_textField_18 = new GridBagConstraints();
			gbc_textField_18.insets = new Insets(0, 0, 5, 5);
			gbc_textField_18.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_18.gridx = 1;
			gbc_textField_18.gridy = 9;
			wrapper.add(textField_18, gbc_textField_18);
			textField_18.setColumns(10);
		}
		{
			textField_20 = new JTextField();
			GridBagConstraints gbc_textField_20 = new GridBagConstraints();
			gbc_textField_20.insets = new Insets(0, 0, 5, 5);
			gbc_textField_20.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_20.gridx = 1;
			gbc_textField_20.gridy = 10;
			wrapper.add(textField_20, gbc_textField_20);
			textField_20.setColumns(10);
		}
		{
			textField_22 = new JTextField();
			GridBagConstraints gbc_textField_22 = new GridBagConstraints();
			gbc_textField_22.insets = new Insets(0, 0, 5, 5);
			gbc_textField_22.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_22.gridx = 1;
			gbc_textField_22.gridy = 11;
			wrapper.add(textField_22, gbc_textField_22);
			textField_22.setColumns(10);
		}
		{
			textField_23 = new JTextField();
			GridBagConstraints gbc_textField_23 = new GridBagConstraints();
			gbc_textField_23.insets = new Insets(0, 0, 5, 0);
			gbc_textField_23.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_23.gridx = 3;
			gbc_textField_23.gridy = 11;
			wrapper.add(textField_23, gbc_textField_23);
			textField_23.setColumns(10);
		}
		{
			comboBox = new JComboBox();
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.gridwidth = 3;
			gbc_comboBox.insets = new Insets(0, 0, 0, 5);
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 1;
			gbc_comboBox.gridy = 12;
			wrapper.add(comboBox, gbc_comboBox);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btn_ccl = new JButton("Cancel");
				btn_ccl.setActionCommand("Cancel");
				buttonPane.add(btn_ccl);
			}
			{
				JButton btn_save = new JButton("Save");
				btn_save.setActionCommand("Save");
				buttonPane.add(btn_save);
				getRootPane().setDefaultButton(btn_save);
			}
		}
	}
}

