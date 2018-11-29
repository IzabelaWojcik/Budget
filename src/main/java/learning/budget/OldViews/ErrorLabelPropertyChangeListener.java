package learning.budget.OldViews;

import java.awt.Color;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;

public class ErrorLabelPropertyChangeListener extends JLabel implements PropertyChangeListener {
	JButton button;
	public ErrorLabelPropertyChangeListener(JButton button, Color color, Dimension dimension, int alignment) {
		setForeground(color);
		setPreferredSize(dimension);
		setHorizontalAlignment(alignment);
		this.button = button;
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		JFormattedTextField field = (JFormattedTextField)evt.getSource();

		if((field.isValid() && field.isEditValid()) && !field.getText().isEmpty() )
		{
			setText("");
			button.setEnabled(true);
				
		}
		else if(field.getText().isEmpty()) 
		{
			setText("");
			button.setEnabled(false);
		}
		else
		{
			setText("Poprawny format #,##");
			button.setEnabled(false);
		}
	}

}
