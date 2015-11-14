
//Haylie Helmold
//Haylie Helmold

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

public class FirstGUI extends JFrame implements ActionListener {

	private JButton pButton;
	private JButton oButton;
	private JButton mButton;
	private JLabel ChocAn1 = null;
	private JLabel ChocAn2 = null;

	private Font font = new Font("Cooper Black", Font.PLAIN, 40);

	public FirstGUI() {
		super("Welcome to Chocoholics Anonymous!");

		Container c = getContentPane();
		c.setLayout(null);

		// Chocoholics
		ChocAn1 = new JLabel("Chocoholics");
		ChocAn1.setSize(275, 50);
		ChocAn1.setForeground(Color.BLACK);
		ChocAn1.setLocation(70, 20);
		ChocAn1.setFont(font);
		c.add(ChocAn1);

		// Anonymous
		ChocAn2 = new JLabel("Anonymous");
		ChocAn2.setSize(275, 50);
		ChocAn2.setForeground(Color.BLACK);
		ChocAn2.setLocation(70, 60);
		ChocAn2.setFont(font);
		c.add(ChocAn2);

		// Implement Buttons
		pButton = new JButton("Provider");
		pButton.addActionListener(this);
		pButton.setSize(200, 100);
		pButton.setLocation(100, 150);
		c.add(pButton);

		oButton = new JButton("Operator");
		oButton.addActionListener(this);
		oButton.setSize(200, 100);
		oButton.setLocation(100, 300);
		c.add(oButton);

		mButton = new JButton("Manager");
		mButton.addActionListener(this);
		mButton.setSize(200, 100);
		mButton.setLocation(100, 450);
		c.add(mButton);

		setSize(400, 600);
		setVisible(true);
		setResizable(false);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == pButton) {
			MainGUI providerActions = new MainGUI();
			providerActions.MainMenu.setVisible(true);
		}
		else if (e.getSource() == oButton) {
			MainGUI operatorActions = new MainGUI();
			operatorActions.MainMenu.setVisible(true);
		}
		else if (e.getSource() == mButton){
			MainGUI managerActions = new MainGUI();
			managerActions.MainMenu.setVisible(true);
		}
	}

	public static void main(String[] args) {
		FirstGUI titleScreen = new FirstGUI();
		titleScreen.setVisible(true);
	}

}