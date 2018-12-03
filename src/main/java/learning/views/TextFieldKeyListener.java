package learning.views;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JTextField;

public class TextFieldKeyListener implements KeyListener{
	private List<String> items;
	
	public TextFieldKeyListener(List<String> items) {
		this.items = items;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		String text = ((JTextField) e.getComponent()).getText();
		if(!text.equals("")) {
			items.add(text);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
