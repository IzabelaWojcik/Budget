package learning.views;

import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JPanel;

import learning.budget.DatabaseNotInitialized;

public class PanelWithButtons extends JPanel implements INotifier{
	public final int identifier;
	private Set<IListener> listeners;
	
	public PanelWithButtons(int id) { identifier = id; listeners = new HashSet<IListener>();};
	
	public void createButtons(Set<String> buttonsNames) {
		removeAll();
		for (String name : buttonsNames) {
			JButton button = new JButton(name);
			button.addActionListener(e -> {
				listeners.stream().forEach(listener -> {
					try {
						listener.notify(new ButtonsData(identifier, name));
					} catch (DatabaseNotInitialized | BudgetNotFoundException e1) {
						e1.printStackTrace();
					}
				});
			});
			add(button);
		}
		revalidate();
		repaint();
	}
	
	public void clearPanel() {
		removeAll();
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
