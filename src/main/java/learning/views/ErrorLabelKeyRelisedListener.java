package learning.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;

public class ErrorLabelKeyRelisedListener extends JLabel implements KeyListener {
	private JButton button;
	
	public ErrorLabelKeyRelisedListener(JButton button, Color color, Dimension dimension, int alignment) {
		setForeground(color);
		setPreferredSize(dimension);
		setHorizontalAlignment(alignment);
		this.button = button;
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent evt) {
		JFormattedTextField field = (JFormattedTextField)evt.getSource();

		if((field.isValid() && field.isEditValid()) && !field.getText().isEmpty())
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

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

}
