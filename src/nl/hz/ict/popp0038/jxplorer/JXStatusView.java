package nl.hz.ict.popp0038.jxplorer;

import java.awt.BorderLayout;

import javax.swing.JLabel;

public class JXStatusView extends JXView {

	/**
	 * field declaration
	 */
	private static final long serialVersionUID = 8865387594051352174L;
	private JLabel label;
	
	public JXStatusView(JXplorer data) {
		super(data);
		makeStatus();
	}

	/**
	 * Update statusView to new currentFile
	 */
	public void newCurrentFile() {
		label.setText(getData().getCurrentFile().getDescription() + "   " 
					+ getData().getCurrentFile().lastModified());
	}
	
	/**
	 * Update statusView to new selectedFile
	 */
	public void newSelectedFile() {
		label.setText(getData().getSelectedFile().getDescription() + "   " 
					+ getData().getSelectedFile().lastModified());
	}
	
	/**
	 * make the statusView layout
	 */
	public void makeStatus() {
		label = new JLabel("StatusView");
		setLayout(new BorderLayout());
		newCurrentFile();
		add(label, BorderLayout.CENTER);
	}
}
