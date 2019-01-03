package learning.views;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

public class CategoryTextFieldListener implements DocumentListener {
	private Map<Object, String> textFieldsToValues;
	
	public CategoryTextFieldListener() {
		this.textFieldsToValues = new HashMap<Object, String>();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		update(e);
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		update(e);
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		update(e);
	}
	
	private void update(DocumentEvent e) {
		try {
			String value = e.getDocument().getText(0, e.getDocument().getLength());
			
			textFieldsToValues.put(e.getDocument(), value);
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
	}
	
	public List<String> getValues(){
		return new ArrayList<>(textFieldsToValues.values());
	}
}
