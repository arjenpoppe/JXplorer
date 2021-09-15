package nl.hz.ict.popp0038.jxplorer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

public class JXTreeView extends JXView {

	/**
	 * field declaration
	 */
	private static final long serialVersionUID = 4727368866001789328L;
	private DefaultTreeModel treeModel;
	private JTree tree;
	
	public JXTreeView(JXplorer data) {
		super(data);
		makeTree();
	}
	
	/**
	 * Action to be executed when currentFile gets updated
	 */
	public void newCurrentFile() {
		//Not required in this View
	}
	
	/**
	 * Action to be executed when selectedFile gets updated
	 */
	public void newSelectedFile() {
		//Not required in this View
	}
	
	/**
	 * Make the tree layout
	 */
	public void makeTree() {
		TreeNode rootNode = new JXploreFile();
		treeModel = new DefaultTreeModel(rootNode);
		tree = new JTree(treeModel);
		TreeCellRenderer renderer = new TreeCellRenderer();
		tree.setCellRenderer(renderer);
		JScrollPane scrollPane = new JScrollPane(tree);
		setLayout(new BorderLayout());
		scrollPane.setPreferredSize(new Dimension(150, 600));
		this.add(scrollPane, BorderLayout.CENTER);
			
		tree.addMouseListener(new JXTreeMouseListener());
		tree.addKeyListener(new JXTreeKeyListener());
	}
		
	/**
	 * set current value and update frame
	 */
	private void setCurrentValue() {
    	JXploreFile selectedValue = (JXploreFile) tree.getLastSelectedPathComponent();
		if(selectedValue != null) {
	    	getData().setCurrentFile(selectedValue);
	    	getData().updateGUI();
        } 
	}
	 /**
	  * Action to be executed on clicking a tree node
	  */
	class JXTreeMouseListener extends MouseAdapter {
		
		public void mouseClicked(MouseEvent e) {
			setCurrentValue(); 
	    }
	}
	
	/**
	 * Action to be executed on pressing enter
	 */
	class JXTreeKeyListener extends KeyAdapter {
		
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_ENTER) {
				setCurrentValue();
			}
        }
	}
}
