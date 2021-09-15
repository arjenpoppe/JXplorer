package nl.hz.ict.popp0038.jxplorer;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class JXAddressView extends JXView {

	/**
	 * field declaration
	 */
	private static final long serialVersionUID = 7016933874262518720L;
	private JTextField addressTextField;
	private String test;


	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	/**
	 * Constructor for the JXAddressView class
	 * @param data to set
	 */
	public JXAddressView(JXplorer data) {
		super(data);
		makePanel();
	}
	
	/**
	 * Update the JTextField to a new currentFile path
	 */
	public void newCurrentFile() {
		addressTextField.setText(getData().getCurrentFilePath());
	}
	
	/**
	 * Update the JTextField to a new selectedFile path
	 */
	public void newSelectedFile() {
		addressTextField.setText(getData().getSelectedFilePath());
	}
	
	/**
	 * Make the panel layout
	 */
	private void makePanel() {
		//local variables
		JLabel addressLabel = new JLabel("Address");
		JButton goButton = new JButton("Go");
		JButton backButton = new JButton("<");
		JButton forwardButton = new JButton(">");
		JButton refreshButton = new JButton("<html>&#8635</html>");
		addressTextField = new JTextField();
		
		//set the layout and set some components size
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		addressLabel.setPreferredSize(new Dimension(80, 10));
		addressLabel.setHorizontalAlignment(SwingConstants.CENTER);
		refreshButton.setMaximumSize(new Dimension(10, 40));
			
		//add all components to JPanel
		add(backButton);
		add(forwardButton);
		add(refreshButton);
		add(addressLabel);
		add(addressTextField);
		add(goButton);
		
		//update data
		newCurrentFile();

		//add action listeners
		addressTextField.addActionListener(new GoButtonAction());
		goButton.addActionListener(new GoButtonAction());
		backButton.addActionListener(new BackButtonAction());
		forwardButton.addActionListener(new ForwardButtonAction());
		refreshButton.addActionListener(new RefreshButtonAction());
	}
	
	 /**
	  * Action to be executed by the "Go" button
	  */
	public class GoButtonAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
		String text = addressTextField.getText();
		JXploreFile file = new JXploreFile(text);
			getData().setCurrentFile(file);
			getData().updateGUI();
		}
	}
	
	/**
	 * Action te be executed by the "<" button
	 */
	public class BackButtonAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			getData().setPreviousFile();
			getData().updateGUI();
		}
	}
	
	/**
	 * Action te be executed by the ">" button
	 */
	public class ForwardButtonAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			getData().setNextFile();
			getData().updateGUI();
		}
	}
	
	/**
	 * Action te be executed by the "refresh" button
	 */
	public class RefreshButtonAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			getData().getCurrentFile().emptyCache();
			getData().updateGUI();
		}
	}
}