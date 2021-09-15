package nl.hz.ict.popp0038.jxplorer;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

public class TreeCellRenderer extends DefaultTreeCellRenderer 
{
	private static final long serialVersionUID = 8107578264271043737L;

	public TreeCellRenderer() {
        super();
    }
	
	@Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected,
    		boolean expanded, boolean leaf, int row, boolean hasFocus) {
        Component result = super.getTreeCellRendererComponent(tree, value,selected, expanded, 
        		leaf, row, hasFocus);

        JLabel label = (JLabel) result;

        if (value instanceof JXploreFile) {
            JXploreFile files = (JXploreFile) value;
            label.setText(files.getName());
            label.setIcon(files.getIcon());
        }
        return result;
    }
}