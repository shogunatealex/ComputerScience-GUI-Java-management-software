import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class ManageProviderRecord extends ManageRecordGUI implements ActionListener{

	private JTextField ProvNumTextField;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ManageProviderRecord dialog = new ManageProviderRecord();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ManageProviderRecord() {
		super.buildPane();
		setTitle("Manage Provider Record Editor");

		
		JLabel ProviderRecordsLabel = new JLabel("Provider Records");
		ProviderRecordsLabel.setFont(new Font("Myriad Pro", Font.BOLD, 19));
		ProviderRecordsLabel.setBounds(272, 11, 271, 20);
		window.add(ProviderRecordsLabel);
		
		
		ProvNumTextField = new JTextField();
		ProvNumTextField.setBounds(159, 105, 86, 20);
		window.add(ProvNumTextField);
		ProvNumTextField.setColumns(10);
		

		
		JLabel ProviderNumberLabel = new JLabel("Provider Number");
		ProviderNumberLabel.setForeground(new Color(0, 100, 0));
		ProviderNumberLabel.setFont(new Font("Myriad Pro", Font.PLAIN, 11));
		ProviderNumberLabel.setBounds(43, 108, 111, 14);
		window.add(ProviderNumberLabel);
		

	    setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == BackButton){
			setVisible(false);
		}
		
	}
}
