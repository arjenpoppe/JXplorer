package nl.hz.ict.popp0038.jxplorer;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;

public class JXplorer extends JFrame{

	/**
	 * field declaration
	 */
	private static final long serialVersionUID = 1724355266680027244L;
	private JXploreFile currentFile;
	private JXploreFile selectedFile;
	private Stack<JXploreFile> previousFiles;
	private Stack<JXploreFile> nextFiles;
	private ArrayList<JXView> viewArray;
	
	
	public JXplorer()  {
		previousFiles = new Stack<JXploreFile>();
		nextFiles = new Stack<JXploreFile>();
		viewArray = new ArrayList<>();
		currentFile = new JXploreFile();
	}
	
	public JXplorer(JXploreFile file)  {
		this.currentFile = file;
	}
	
	/**
	 * @return the currentFile
	 */
	public JXploreFile getCurrentFile() {
		return currentFile;
	}

	/**
	 * @param file the currentFile to set
	 */
	public void setCurrentFile(JXploreFile file) {
		if(! file.isDirectory()) {
			try {
				Desktop.getDesktop().open(file.getFile());
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this, "Failed to open file " + file.getPath());
			} catch (IllegalArgumentException e) {
				JOptionPane.showMessageDialog(this, e.getMessage());
			}
			return;
		}
		previousFiles.push(this.currentFile); 
		this.currentFile = file;
	}

	/**
	 * @param currentFile the currentFile to set
	 */
	public String getCurrentFilePath() {
		return currentFile.getPath();
	}
	
	/**
	 * Sets previous file as the currentFile
	 */
	public void setPreviousFile() {
		if(! previousFiles.empty()) {
			nextFiles.push(this.currentFile);
			currentFile = previousFiles.pop();
		}
	}
	
	/**
	 * Sets next file as the currentFile
	 */
	public void setNextFile() {
		if(! nextFiles.empty()) {
			previousFiles.push(this.currentFile); 
			currentFile = nextFiles.pop();
		}
	}
	
	/**
	 * get file path
	 * @return selected file path string
	 */
	public String getSelectedFilePath() {
		return selectedFile.getPath();
	}
	
	/**
	 * get selected file
	 * @return the selected file
	 */
	public JXploreFile getSelectedFile() {
		return selectedFile;
	}
	
	/**
	 * set a new selected file
	 * @param selectedFile selected file to set
	 */
	public void setSelectedFile(JXploreFile selectedFile) {
		this.selectedFile = selectedFile;
	}
	
	/**
	 * check if there is a selected file available
	 * @return true/false
	 */
	public boolean hasSelectedFile() {
		return (selectedFile != null);
	}

	/**
	 * Build  GUI
	 */
	public void buildGUI() {
		//local variables
		JSplitPane splitPane = new JSplitPane();
		JXAddressView addressView = new JXAddressView(this);	
		JXListView listView = new JXListView(this);
		JXStatusView statusView = new JXStatusView(this);
		JXTreeView treeView = new JXTreeView(this);
		
		//add views to JFrame
		viewArray.add(addressView);
		viewArray.add(listView);
		viewArray.add(statusView);
		viewArray.add(treeView);
		  
		//add components and properties to frame 
		setLayout(new BorderLayout());
		add(addressView, BorderLayout.NORTH);
		add(splitPane, BorderLayout.CENTER);
		add(statusView, BorderLayout.SOUTH);
		setSize(1000, 600);
		setMinimumSize(new Dimension(400, 300));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		  
		//add components to splitpane
		splitPane.add(listView,JSplitPane.RIGHT);
		splitPane.add(treeView,JSplitPane.LEFT);
	}
	
	/**
	 * Print the filename.
	 * @param file the file to use.
	 */
	public void printName(JXploreFile file)  {
		System.out.println(file.getName());
	}
	

	/**
	 * Print a list of subfiles and folders
	 * @param file the directory to use
	 */	
	public void printSubFiles(JXploreFile file) {
		JXploreFile[] files = file.getSubFiles();
		for(JXploreFile f : files) {
			printName(f);
		}
	}
	
	/**
	 * Print a list of subfolders only
	 * @param file the directory to use
	 */
	public void printSubFolders(JXploreFile file) {
		JXploreFile[] folders = file.getSubFolders();
		for(JXploreFile folder : folders) {
			printName(folder);
		}
	}
	
	/**
	 * get an array of subfiles from the currentobject
	 * @return an array of JXploreFiles
	 */
	public JXploreFile[] getSubFiles() {
		 return currentFile.getSubFiles();
	}
	
	/**
	 * update the GUI
	 */
	public void updateGUI() {
		if(selectedFile == null) {
			for(JXView view: viewArray) {
				view.newCurrentFile();
			}
		}
		else {
			for(JXView view: viewArray) {
				view.newSelectedFile();
			}
		}
		selectedFile = null;
	}
	
	public static void main (String[] args){ 
		 EventQueue.invokeLater(new Runnable() {
			 public void run(){ 
				 JXplorer jxplorer = new JXplorer();
				 jxplorer.buildGUI();
			 }
		 });
	 }


}