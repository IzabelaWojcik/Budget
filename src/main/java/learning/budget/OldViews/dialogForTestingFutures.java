package learning.budget.OldViews;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;

public class dialogForTestingFutures extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel buttonPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialogForTestingFutures dialog = new dialogForTestingFutures();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public dialogForTestingFutures() {
		setBounds(100, 100, 450, 300);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, 432, GroupLayout.PREFERRED_SIZE)
				.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, 432, GroupLayout.PREFERRED_SIZE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
					.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(21)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(75, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(33, Short.MAX_VALUE))
		);
		
		
		
		
		
		List<Pair<String, String>> list = new ArrayList<Pair<String, String>>() {{add(new Pair("a","1"));add(new Pair("a","1"));add(new Pair("a","1"));add(new Pair("a","1"));add(new Pair("a","1"));add(new Pair("a","1")); }};//{{add(new Triplet("ala", "blue", "23"));add(new Triplet("ola", "green", "15")); add(new Triplet("tom", "black", "33"));add(new Triplet("ala", "blue", "23"));add(new Triplet("ala", "blue", "23"));add(new Triplet("ala", "blue", "23")); add(new Triplet("ala", "blue", "23"));add(new Triplet("ala", "blue", "23"));add(new Triplet("ala", "blue", "23"));add(new Triplet("ala", "blue", "23"));add(new Triplet("ala", "blue", "23"));add(new Triplet("ala", "blue", "23"));}};
		Pair<String, String> triplet = new Pair<String, String>("s", "d");
		String columnsNames[] = {triplet.getValue0(), triplet.getValue1()};
		Object rows[][] = new String[list.size()][columnsNames.length];
		
		for(int i = 0; i < list.size(); i++) {
			for(int j = 0; j < columnsNames.length; j++) {
				rows[i][j] = list.get(i).getValue(j);
				System.out.println("rows[" + i + "][" + j + "] = " + i + " " + j);
			}
		}
		TableModel model = new DefaultTableModel(rows, columnsNames);
		table = new JTable(model);
		
		
		
		
//		table.setModel(new DefaultTableModel(
//			new Object[][] {
//				{null, null, null},
//				{"3e", null, null},
//				{null, null, null},
//				{null, null, null},
//				{null, null, null},
//				{null, null, null},
//				{null, null, null},
//				{null, null, null},
//				{null, null, null},
//				{null, null, null},
//				{null, null, null},
//				{null, null, null},
//			},
//			new String[] {
//				"t1", "t2", "t3"
//			}
//		));
		
	
		
		scrollPane.setViewportView(table);
		contentPanel.setLayout(gl_contentPanel);
		getContentPane().setLayout(groupLayout);
	}
}
