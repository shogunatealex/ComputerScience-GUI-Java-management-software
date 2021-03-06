import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.Color;

/**
 * Allow for entering and editing service record information.
 * 
 * @author Alex Anderson
 *
 */
public class ManageServiceRecord extends JDialog implements ActionListener {
	private DefaultListModel results;

	private JButton OkButton;
	private JButton BackButton = null;
	private JFormattedTextField DateTextField;
	private JTextArea CommentsTextField;
	private JTextField MemberNumberTextField;
	private JTextField ServiceCodeTextField;
	private JFormattedTextField TimeTextField;
	private JTextField ProviderNumberTextField;
	protected boolean Cancel = true;

	/*
	 * /** Used primarily for testing/ independently launching. / public static
	 * void main(String[] args) { try { ManageServiceRecord dialog = new
	 * ManageServiceRecord();
	 * dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	 * dialog.setVisible(true); } catch (Exception e) { e.printStackTrace(); } }
	 */
	/**
	 * @return date
	 */
	public String getDate() {
		return DateTextField.getText();
	}

	/**
	 * @return comments
	 */
	public String getComments() {
		return CommentsTextField.getText();
	}

	/**
	 * @return memberNumber
	 */
	public int getMemberNumber() {
		return Integer.parseInt(MemberNumberTextField.getText());
	}

	/**
	 * @return serviceCode
	 */
	public int getServiceCode() {
		return Integer.parseInt(ServiceCodeTextField.getText());

	}

	/**
	 * @return time
	 */
	public String getTime() {
		return TimeTextField.getText();
	}

	/**
	 * @return providerNumber
	 */
	public int getProviderNumber() {
		return Integer.parseInt(ProviderNumberTextField.getText());
	}

	/**
	 * @return canceled if canceled
	 */
	public boolean isCanceled() {
		return Cancel;
	}

	/**
	 * Display contents.
	 */
	private void buildLocalPane() {
		// Builds GUI
		Container window = getContentPane();
		setTitle("Manage Service Record Editor");
		setBounds(100, 100, 450, 300);
		setModal(true);
		window.setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		OkButton = new JButton("Ok");
		OkButton.addActionListener(this);
		OkButton.setBounds(381, 242, 89, 23);
		window.add(OkButton);

		BackButton = new JButton("Back");
		BackButton.setBounds(480, 242, 89, 23);
		BackButton.addActionListener(this);
		window.add(BackButton);

		JLabel ServiceRecordsLabel = new JLabel("Service Records");
		ServiceRecordsLabel.setFont(new Font("Myriad Pro", Font.BOLD, 19));
		ServiceRecordsLabel.setBounds(227, 11, 226, 20);
		getContentPane().add(ServiceRecordsLabel);

		// formats text field to only take certain items.
		try {
			MaskFormatter mf1 = new MaskFormatter("##-##-####");
			mf1.setPlaceholderCharacter('_');
			DateTextField = new JFormattedTextField(mf1);
			DateTextField.setBounds(106, 42, 142, 20);
			getContentPane().add(DateTextField);
			DateTextField.setColumns(10);

		} catch (ParseException e) {
		}

		CommentsTextField = new JTextArea();
		CommentsTextField.setBounds(363, 42, 206, 69);
		getContentPane().add(CommentsTextField);
		CommentsTextField.setColumns(10);

		MemberNumberTextField = new JTextField();
		MemberNumberTextField.setBounds(106, 203, 142, 20);
		getContentPane().add(MemberNumberTextField);
		MemberNumberTextField.setColumns(10);

		ServiceCodeTextField = new JTextField();
		ServiceCodeTextField.setBounds(363, 168, 206, 20);
		getContentPane().add(ServiceCodeTextField);
		ServiceCodeTextField.setColumns(10);
		
		// formats text field to only take certain numbers
		try {
			MaskFormatter mf1 = new MaskFormatter("##-##-#### ##:##:##");
			mf1.setPlaceholderCharacter('_');
			TimeTextField = new JFormattedTextField(mf1);
			TimeTextField.setBounds(106, 91, 142, 20);
			String timeStamp = new SimpleDateFormat("MMddyyyyHHmmss").format(Calendar.getInstance().getTime());
			TimeTextField.setText(timeStamp);
			getContentPane().add(TimeTextField);
			TimeTextField.setColumns(10);

		} catch (ParseException e) {
		}

		ProviderNumberTextField = new JTextField();
		ProviderNumberTextField.setBounds(106, 145, 142, 20);
		getContentPane().add(ProviderNumberTextField);
		ProviderNumberTextField.setColumns(10);

		JLabel DateLabel = new JLabel("Date");
		DateLabel.setFont(new Font("Myriad Pro", Font.PLAIN, 11));
		DateLabel.setForeground(new Color(0, 100, 0));
		DateLabel.setBounds(21, 46, 75, 14);
		getContentPane().add(DateLabel);

		JLabel TimeLabel = new JLabel("Time");
		TimeLabel.setForeground(new Color(0, 128, 0));
		TimeLabel.setFont(new Font("Myriad Pro", Font.PLAIN, 11));
		TimeLabel.setBounds(21, 97, 75, 14);
		getContentPane().add(TimeLabel);

		JLabel ProviderNumberLabel = new JLabel("Provider Number");
		ProviderNumberLabel.setForeground(new Color(0, 128, 0));
		ProviderNumberLabel.setFont(new Font("Myriad Pro", Font.PLAIN, 11));
		ProviderNumberLabel.setBounds(21, 149, 111, 14);
		getContentPane().add(ProviderNumberLabel);

		JLabel CommentsLabel = new JLabel("Comments");
		CommentsLabel.setFont(new Font("Myriad Pro", Font.PLAIN, 11));
		CommentsLabel.setForeground(new Color(0, 128, 0));
		CommentsLabel.setBounds(284, 70, 68, 14);
		getContentPane().add(CommentsLabel);

		JLabel MemberLabel = new JLabel("Member Number");
		MemberLabel.setFont(new Font("Myriad Pro", Font.PLAIN, 11));
		MemberLabel.setForeground(new Color(0, 128, 0));
		MemberLabel.setBounds(21, 207, 111, 14);
		getContentPane().add(MemberLabel);

		JLabel ServiceCodeLabel = new JLabel("Service Code");
		ServiceCodeLabel.setForeground(new Color(0, 128, 0));
		ServiceCodeLabel.setFont(new Font("Myriad Pro", Font.PLAIN, 11));
		ServiceCodeLabel.setBounds(284, 172, 111, 14);
		getContentPane().add(ServiceCodeLabel);

		setSize(595, 322);
		setLocation(100, 100);
	}

	public ManageServiceRecord() {
		buildLocalPane();
		setVisible(true);
	}

	/**
	 * 
	 * @param SR
	 *            serviceRecord
	 */
	public ManageServiceRecord(ServiceRecord SR) {
		// builds it for the edit feature, sets all field to service record values
		buildLocalPane();
		DateTextField.setText(SR.getDate());
		TimeTextField.setText(SR.getTime());
		ProviderNumberTextField.setText(String.format("%09d", SR.getProviderNumber()));
		MemberNumberTextField.setText(String.format("%09d", SR.getMemberNumber()));
		ServiceCodeTextField.setText(String.format("%06d", SR.getServiceCode()));
		CommentsTextField.setText(SR.getComments());
		setVisible(true);
	}

	/**
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 *      Remove window. If back selected, do not update service record. If Ok
	 *      selected, update service record.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == BackButton) {
			// nothing happens
			Cancel = true;
			setVisible(false);
		} else if (e.getSource() == OkButton) {
			// builds
			Cancel = false;
			setVisible(false);
		}

	}
}
