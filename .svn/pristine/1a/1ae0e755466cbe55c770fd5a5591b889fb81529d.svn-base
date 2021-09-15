package nl.hz.ict.popp0038.jxplorer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

public class ListCellRenderer extends DefaultListCellRenderer 
{
	private static final long serialVersionUID = 8107578264271043737L;

	public ListCellRenderer() {
        super();
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {
        Component result = super.getListCellRendererComponent(list, value,
                index, isSelected, cellHasFocus);

        JLabel label = (JLabel) result;

        if (value instanceof JXploreFile) {
            JXploreFile files = (JXploreFile) value;
            label.setText(files.getName());
            label.setIcon(files.getIcon());
        }
        return result;
    }
}