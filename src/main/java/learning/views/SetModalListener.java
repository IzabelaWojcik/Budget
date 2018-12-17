package learning.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

public class SetModalListener implements ActionListener{
	private JDialog dialog;
	
	public SetModalListener(JDialog dialog) {
		this.dialog = dialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dialog.setModal(true);
        dialog.setVisible(true);
	}
}
