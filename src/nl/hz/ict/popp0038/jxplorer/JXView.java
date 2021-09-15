package nl.hz.ict.popp0038.jxplorer;

import javax.swing.JPanel;

public abstract class JXView extends JPanel {

	/**
	 * field declaration
	 */
	private static final long serialVersionUID = 2264753992032407917L;
	private JXplorer data;
	
	public JXView(JXplorer data) {
		this.data = data;
	}

	protected void setData(JXplorer jxplorer) {
		this.data = jxplorer;
	}
	
	protected JXplorer getData() {
		return data;
	}
	
	protected abstract void newCurrentFile();
	
	protected abstract void newSelectedFile();
}
