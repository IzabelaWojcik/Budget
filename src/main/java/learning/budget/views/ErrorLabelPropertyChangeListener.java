package learning.budget.views;

import java.awt.Color;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;

public class ErrorLabelPropertyChangeListener extends JLabel implements PropertyChangeListener {
	ErrorLabelPropertyChangeListener(Color color, Dimension dimension, int alignment) {
		setForeground(color);
		setPreferredSize(dimension);
		setHorizontalAlignment(alignment);
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		JFormattedTextField field = (JFormattedTextField)evt.getSource();

		if((field.isValid() && field.isEditValid()) || field.getText().isEmpty())
		{
			setText("");
		}
		else
		{
			setText("Poprawny format #.##");
		}
	}

}
