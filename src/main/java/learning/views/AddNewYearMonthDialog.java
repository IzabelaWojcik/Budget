package learning.views;

import java.util.HashSet;
import java.util.Set;

import javax.swing.JDialog;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

import learning.budget.DatabaseNotInitialized;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class AddNewYearMonthDialog extends JDialog implements INotifier{
	public final int identifier;
	private Set<IListener> listeners;
	
	public AddNewYearMonthDialog(int id) {
		identifier = id;
		listeners = new HashSet<IListener>();
		
		setTitle("Nowy miesiąc");
		setBounds(120, 120, 280, 150);
		
		JMonthChooser monthChooser = new JMonthChooser();
		
		JYearChooser yearChooser = new JYearChooser();
		
		JButton btnCancel = new JButton("Anuluj");
		ButtonCancelListener buttonCancelListener = new ButtonCancelListener(this);
		btnCancel.addActionListener(buttonCancelListener);
		
		JButton btnAdd = new JButton("Dodaj");
		
		btnAdd.addActionListener(e -> {listeners.stream().forEach(listener -> {
			try {
				int month = monthChooser.getMonth() + 1;
				listener.notify(new ButtonAddNewMonthData(identifier, yearChooser.getYear(), month, this));
			} catch (DatabaseNotInitialized | BudgetNotFoundException e1) {
				e1.printStackTrace();
			}});
		});
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(monthChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnCancel)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(yearChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnAdd)))
					.addContainerGap(18, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(yearChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(monthChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnAdd))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
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
