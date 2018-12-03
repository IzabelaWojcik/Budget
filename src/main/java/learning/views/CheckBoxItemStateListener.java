package learning.views;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JCheckBox;

public class CheckBoxItemStateListener implements ItemListener{
	private List<String> items;
	
	public CheckBoxItemStateListener(List<String> items) {
		this.items = items;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		String text = ((JCheckBox) e.getItem()).getText();
		addCheckedItemsToList(text, e);
	}
	
	private void addCheckedItemsToList(String text, ItemEvent e){
		if(e.getStateChange() == ItemEvent.SELECTED){
			items.add(text);
		}
		if(e.getStateChange() == ItemEvent.DESELECTED && items.contains(text)){
			items.remove(text);
		}
	}
}
