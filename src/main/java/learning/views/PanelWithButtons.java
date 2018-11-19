package learning.views;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelWithButtons extends JPanel implements INotifier{
	public final int identifier;
	private Set<IListener> listeners = new HashSet<IListener>();
	
	public PanelWithButtons(int id) { identifier = id;};
	
	public PanelWithButtons(int id, SortedSet<String> buttonsNames) {
		identifier = id;
		createButtons(buttonsNames);
	}

	public void createButtons(SortedSet<String> buttonsNames) {
		for(String name: buttonsNames) {
			JButton button = new JButton(name);
			button.addActionListener(e -> {listeners.stream().forEach(listener -> {listener.notify(new ButtonsData(identifier, name));});});
			add(button);
		}
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
