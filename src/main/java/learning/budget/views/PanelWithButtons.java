package learning.budget.views;

import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelWithButtons extends JPanel implements INotifier{
	private Set<IListener> listeners;
	
	public PanelWithButtons(SortedSet<String> buttonsNames) {
		
		for(String name: buttonsNames) {
			JButton button = new JButton(name);
			button.addActionListener(e -> {listeners.stream().forEach(listener -> {listener.notify(name);});});
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
