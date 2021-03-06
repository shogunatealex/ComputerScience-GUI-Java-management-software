import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Display provider records. Allow for adding, editing, and deleting a provider
 * record.
 * 
 * @author Alex Anderson
 *
 */
public class ProviderRecordGUI extends JDialog implements ActionListener {

	private DefaultListModel results;

	private JButton AddButton = null;
	private JButton DeleteButton = null;
	private JButton EditButton = null;
	private JButton BackButton = null;
	private JTable table;
	private DefaultTableModel recs;

	/*
	 * /** Used primarily for testing/ independently launching. / public static
	 * void main(String[] args) { try { ProviderRecordGUI dialog = new
	 * ProviderRecordGUI();
	 * dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	 * dialog.setVisible(true); } catch (Exception e) { e.printStackTrace(); } }
	 */
	/**
	 * Create the dialog with contents.
	 */
	public ProviderRecordGUI() {
		Container window = getContentPane();
		setTitle("Provider Record Editor");
		setBounds(100, 100, 450, 300);
		window.setLayout(null);
		setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		AddButton = new JButton("Add");
		AddButton.addActionListener(this);
		AddButton.setBounds(59, 207, 98, 33);
		window.add(AddButton);

		DeleteButton = new JButton("Delete");
		DeleteButton.addActionListener(this);
		DeleteButton.setBounds(318, 207, 100, 32);
		window.add(DeleteButton);

		EditButton = new JButton("Edit");
		EditButton.addActionListener(this);
		EditButton.setBounds(191, 207, 98, 33);
		window.add(EditButton);

		BackButton = new JButton("Back");
		BackButton.setBounds(383, 242, 89, 23);
		BackButton.addActionListener(this);
		window.add(BackButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 31, 451, 167);
		window.add(scrollPane);

		results = new DefaultListModel();

		ArrayList<ProviderRecord> temp = ChocAnSystem.PRC.retrieveRecords();
		recs = new DefaultTableModel() {
			// prevents users from editing the table, must use buttons
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable();
		String header[] = new String[] { "Name", "ID", "Zipcode", "Address", "City", "State" };
		recs.setColumnIdentifiers(header);
		table.setModel(recs);
		for (ProviderRecord record : temp) {
			recs.addRow((new Object[] { record.getName(), String.format("%09d", record.getProviderNumber()),
					record.getZipCode(), record.getAddress(), record.getCity(), record.getState() }));
		}

		scrollPane.setFocusable(false);
		scrollPane.setViewportView(table);

		JLabel ProviderRecordsLabel = new JLabel("Provider Records");
		ProviderRecordsLabel.setBounds(21, 0, 100, 20);
		getContentPane().add(ProviderRecordsLabel);

		setSize(500, 310);
		setLocation(100, 100);
		setVisible(true);
	}

	/**
	 * 
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 *      If back selected, do not update provider record. If edit selected,
	 *      edit selected provider record. If add selected, add provider record.
	 *      If delete selected, delete selected provider record.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == BackButton) {
			// nothing happens
			setVisible(false);
		} else if (e.getSource() == AddButton) {
			//adds new record to table and array
			ManageProviderRecord PMR = new ManageProviderRecord();
			if (PMR.isCanceled() == false) {
				ChocAnSystem.PRC.addRecord(PMR.getName(), PMR.getProviderNumber(), PMR.getZipCode(), PMR.getAddress(),
						PMR.getCity(), PMR.getState());
				recs.addRow((new Object[] { PMR.getName(), PMR.getProviderNumber(), PMR.getZipCode(), PMR.getAddress(),
						PMR.getCity(), PMR.getState() }));
			}

		} else if (e.getSource() == EditButton) {
			try {
				// takes currently selected arrow and sets up GUI to be edited
				int index = table.getSelectedRow();

				ProviderRecord toEdit = ChocAnSystem.PRC.getSpecificRecord(index);
				ManageProviderRecord PMR = new ManageProviderRecord(toEdit);
				if (PMR.isCanceled() == false) {
					ChocAnSystem.PRC.editRecord(index, PMR.getName(), PMR.getProviderNumber(), PMR.getZipCode(),
							PMR.getAddress(), PMR.getCity(), PMR.getState());
					recs.removeRow(index);
					recs.insertRow(index, (new Object[] { PMR.getName(), String.format("%09d", PMR.getProviderNumber()),
							PMR.getZipCode(), PMR.getAddress(), PMR.getCity(), PMR.getState() }));
				}
			} catch (ArrayIndexOutOfBoundsException e1) { // catches the
															// exception for no
															// selected row
			}
		} else if (e.getSource() == DeleteButton) {
			try {
				// deletes currently selected reocrd
				int index = table.getSelectedRow();
				ChocAnSystem.PRC.removeRecord(index);
				recs.removeRow(index);
			} catch (ArrayIndexOutOfBoundsException e1) { // catches the
															// exception for no
															// selected row
			}
		}
	}
}
