package learning.views;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Dimension;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;

public class PanelAddUsersToNewBudget extends JPanel{
	private JTextField textFieldBugdetName;
	private JButton btnNext;
	private JButton btnCancel;
	private JFormattedTextField formattedTextField;
	public PanelAddUsersToNewBudget() {
		
		JLabel labelInfo = new JLabel("Ilość użytkowników gospodarstwa domowego posiadających dochody:");
		
		JLabel labelBudgetName = new JLabel("Nazwa nowego budżetu:");
		
		textFieldBugdetName = new JTextField();
		textFieldBugdetName.setColumns(10);
		
		JButton button = new JButton("Dodaj użytkowników");
		
		JPanel panelForUsers = new JPanel();
		panelForUsers.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panelForButtons = new JPanel();
		
		formattedTextField = new JFormattedTextField();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(61)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(labelInfo)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(labelBudgetName, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
								.addComponent(formattedTextField, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(button, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldBugdetName, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)))
						.addComponent(panelForUsers, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(254, Short.MAX_VALUE)
					.addComponent(panelForButtons, GroupLayout.PREFERRED_SIZE, 367, GroupLayout.PREFERRED_SIZE)
					.addGap(36))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(36, Short.MAX_VALUE)
					.addComponent(labelInfo)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelBudgetName)
						.addComponent(textFieldBugdetName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(formattedTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button))
					.addGap(35)
					.addComponent(panelForUsers, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
					.addGap(19)
					.addComponent(panelForButtons, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(25))
		);
		
		JButton btnPrevious = new JButton("Cofnij");
		panelForButtons.add(btnPrevious);
		btnPrevious.setEnabled(false);
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnPrevious.setPreferredSize(new Dimension(80, 25));
		
		btnNext = new JButton("Dalej");
		btnNext.setEnabled(false);
		panelForButtons.add(btnNext);
		btnNext.setPreferredSize(new Dimension(80, 25));
		
		btnCancel = new JButton("Anuluj");
		panelForButtons.add(btnCancel);
		btnCancel.setPreferredSize(new Dimension(80, 25));
		
		JButton btnFinish = new JButton("Zakończ");
		btnFinish.setEnabled(false);
		panelForButtons.add(btnFinish);
		btnFinish.setPreferredSize(new Dimension(80, 25));
		setLayout(groupLayout);
	}
}
