package learning.budget.views;

import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelWithButtons extends JPanel implements INotifier{
	public final String identifier;
	private Set<IListener> listeners;
	
	public PanelWithButtons(String id) { identifier = id;};
	
	public PanelWithButtons(String id, SortedSet<String> buttonsNames) {
		identifier = id;
		createButtons(buttonsNames);
	}

	public void createButtons(SortedSet<String> buttonsNames) {
		for(String name: buttonsNames) {
			JButton button = new JButton(name);
			button.addActionListener(e -> {listeners.stream().forEach(listener -> {listener.notify(identifier, name);});});
			add(button);
		}
		
		listeners = new HashSet<IListener>();
	}

	
	@Override
	public void register(IListener listener) {
		listeners.add(listener);
	}

	@Override
	public void deregister(IListener listener) {
		listeners.remove(listener);
	}
}
