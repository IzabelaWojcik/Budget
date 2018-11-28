package learning.views;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelWithButtons extends JPanel implements INotifier{
	public final int identifier;
	private Set<IListener> listeners;
	
	public PanelWithButtons(int id) { identifier = id; listeners = new HashSet<IListener>();};
	
	public void createButtons(SortedSet<String> buttonsNames) {
		removeAll();
		for(String name: buttonsNames) {
			JButton button = new JButton(name);
			button.addActionListener(e -> {listeners.stream().forEach(listener -> {listener.notify(new ButtonsData(identifier, name));});});
			add(button);
		}
		revalidate();
		repaint();
	}
	
	public void clearPanel() {
		this.removeAll();
		revalidate();
		repaint();
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
