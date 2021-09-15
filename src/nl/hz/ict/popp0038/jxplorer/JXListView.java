package nl.hz.ict.popp0038.jxplorer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class JXListView extends JXView {

	/**
	 * field declaration
	 */
	private static final long serialVersionUID = 3447555675370626875L;
	private DefaultListModel<JXploreFile> listModel;
	private JList<JXploreFile> fileList;
	
	public JXListView(JXplorer data) {
		super(data);
		listModel = new DefaultListModel<>();
		fileList = new JList<>(listModel);
		makeFile();
	}	
	
	/**
	 * Update the JList with new data
	 */
	public void newCurrentFile() {
		listModel.clear();
		JXploreFile[] files = getData().getSubFiles();
		for(JXploreFile file : files) {
			listModel.addElement(file);
		}
	}
	
	/**
	 * Action to be executed on new selectedFile
	 */
	public void newSelectedFile() {
		//method not required for this view
	}
	
	/**
	 * Make a JList with a defaultListModel and scrollPane
	 */
	private void makeFile() {
		newCurrentFile();
		
		//add cellrenderer
		ListCellRenderer renderer = new ListCellRenderer();
		fileList.setCellRenderer(renderer);
		
		//create scrollpane
		JScrollPane scrollPane = new JScrollPane(fileList);
		scrollPane.setPreferredSize(new Dimension(300, 300));
		
		//
		setLayout(new BorderLayout());
		add(scrollPane, BorderLayout.CENTER);
		
		fileList.addMouseListener(new JXListMouseListener());
		fileList.addKeyListener(new JXListKeyListener());
	}
	
	/**
	 * add mouselistener to the JList
	 */
	class JXListMouseListener extends MouseAdapter {
		
		public void mouseClicked(MouseEvent e) {
            JXploreFile selectedValue = fileList.getSelectedValue();
            if(e.getClickCount() == 1 && e.getButton() == MouseEvent.BUTTON1) {
            	//Action executed on item select (1 click)
            	getData().setSelectedFile(selectedValue);
            	getData().updateGUI();
            	
            } else if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
            	//Action executed on doubleclick
            	getData().setCurrentFile(selectedValue);
	            getData().updateGUI();
	        } 
	    }
	}
	
	class JXListKeyListener extends KeyAdapter {
		
		/**
		 * Action to be executed on key press
		 */
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			JXploreFile selectedValue = fileList.getSelectedValue();
			if (key == KeyEvent.VK_ENTER) {
				if(selectedValue != null) {
			    	//action executed when enter is pressed
					getData().setCurrentFile(selectedValue);
			    	getData().updateGUI();
				}
			}
			else {
				//action to be executed on item select
				getData().setSelectedFile(selectedValue);
	        	getData().updateGUI();
			}
        }
	}
}
