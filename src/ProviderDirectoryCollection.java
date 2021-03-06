import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;;

//Trevor
/**
 * Collection of Provider directories. Allowing for reading, saving, adding,
 * editing, and removing provider provider directory.
 * 
 * @author Trevor Gentner
 * @return ProviderDirectoryCollection collection of provider directories
 */
public class ProviderDirectoryCollection {

	private ArrayList<ProviderDirectory> pArray = new ArrayList<ProviderDirectory>();
	private File fileName;

	public ArrayList<ProviderDirectory> retrieveRecords() {
		return pArray;
	}

	public ProviderDirectoryCollection(String FileName) {

		fileName = new File(FileName);
		collectRecords();

	}

	public void sorter() {
		Collections.sort(pArray, new ProviderComparator());
	}

	/**
	 * Read in provider directories from file.
	 */
	public void collectRecords() {
		try {
			pArray.clear();
			ObjectInputStream input = null;
			try {
				input = new ObjectInputStream(new FileInputStream(fileName.getAbsoluteFile()));
			} // end catch
			catch (FileNotFoundException e2) {
				fileName.createNewFile();
				input = new ObjectInputStream(new FileInputStream(fileName.getAbsoluteFile()));
			} // end catch
			try {
				while (input != null) {
					// add it to the jlist
					ProviderDirectory temp = (ProviderDirectory) input.readObject();
					pArray.add(temp);

				} // end while
			} // end try

			catch (ClassNotFoundException e1) {

			} // end catch
		} catch (IOException e) {
		} // end catch
		this.sorter();
	}

	/**
	 * Add a provider directory given a record.
	 * 
	 * @param PD
	 */
	public void addRecord(ProviderDirectory PD) {
		pArray.add(PD);
		saveRecords();
		collectRecords();
	}// end removeRecord

	/**
	 * Add a provider directory given information.
	 * 
	 * @param sNumbers
	 * @param Services
	 * @param costs
	 */
	public void addRecord(int sNumbers, String Services, double costs) {
		ProviderDirectory temp = new ProviderDirectory(sNumbers, Services, costs);
		pArray.add(temp);
		saveRecords();
		collectRecords();
	}

	/**
	 * Create a new provider record.
	 */
	public void createReport() {
		// formats and writes report for providerDirectory request
		JFrame window = new JFrame();
		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showSaveDialog(window);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			String fileName = chooser.getSelectedFile().getAbsolutePath();

			try {
				FileWriter output = new FileWriter(fileName);
				
				
				
				for (ProviderDirectory record : this.pArray) {
				
					output.write(record.toString() + "\n");
				}
				output.flush();
				output.close();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Remove selected provider directory.
	 */
	public void removeRecord(int index) {
		pArray.remove(index);
		saveRecords();
	}// end removeRecord

	/**
	 * Edit selected provider directory.
	 */

	public void editRecord(int index, int sNumbers, String Services, double costs) {
		ProviderDirectory temp = new ProviderDirectory(sNumbers, Services, costs);
		pArray.remove(index);
		pArray.add(index, temp);
		this.sorter();
	}

	/**
	 * @return providerDirectory selected provider directory.
	 */
	public ProviderDirectory getSpecificRecord(int index) {
		return pArray.get(index);
	}

	public ProviderDirectory getSpecificRecordByServiceNumber(int sNumber) {
		for (int i = 0; i < pArray.size(); i++) {
			if (pArray.get(i).get_sNumber() == sNumber) {
				return pArray.get(i);
			}
		}
		return null;
	}

	/**
	 * Save provider directories to a file.
	 */
	public void saveRecords() {
		try {
			fileName.delete();
			fileName.createNewFile();
		} // end try
		catch (IOException e1) {

		} // end catch
		ObjectOutputStream writer = null;
		// writes to file
		try {
			writer = new ObjectOutputStream(new FileOutputStream(fileName.getAbsolutePath()));
			for (int i = 0; i < pArray.size(); i++) {
				// formats string so it works correctly
				writer.writeObject(pArray.get(i));
			} // end for
		} catch (IOException ex) {
		} // end catch
		finally {
			try {
				writer.close();
			} catch (Exception ex) {
				/* ignore */}
		} // end finally...finally
		this.sorter();
	}// end saveRecords

}
