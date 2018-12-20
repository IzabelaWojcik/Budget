package learning.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

import learning.budget.DatabaseConnection;
import learning.budget.DatabaseReader;
import learning.budget.DatabaseWriter;
import learning.budget.IDatabaseWriter;

public class AddNewMonthDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static int budgetId;

	public static void main(String[] args) {
		IDatabaseWriter databaseWriter = DatabaseWriter.getInstance();
		DatabaseWriter.setConnection(DatabaseConnection.getInstance());
		
		try {
			AddNewMonthDialog dialog = new AddNewMonthDialog(databaseWriter, budgetId);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AddNewMonthDialog(IDatabaseWriter databaseWriter, int budgetId) {
		setTitle("Dodaj nowy miesiąc");
		setBounds(100, 100, 282, 131);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JMonthChooser monthChooser = new JMonthChooser();
		
		JYearChooser yearChooser = new JYearChooser();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(monthChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(yearChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(91, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(yearChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(monthChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(73, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnCancel = new JButton("Anuluj");
				btnCancel.setActionCommand("Anuluj");
				buttonPane.add(btnCancel);
				getRootPane().setDefaultButton(btnCancel);
				
				ButtonCancelListener buttonCancelListener = new ButtonCancelListener(this);
				btnCancel.addActionListener(buttonCancelListener);
			}
			{
				JButton btnAdd = new JButton("Dodaj");
				btnAdd.setActionCommand("Dodaj");
				buttonPane.add(btnAdd);
				
				ButtonAddNewMonthListener buttonAddNewMonthListener = new ButtonAddNewMonthListener(budgetId, yearChooser, monthChooser, databaseWriter);
				btnAdd.addActionListener(buttonAddNewMonthListener);
			}
		}
	}
}
