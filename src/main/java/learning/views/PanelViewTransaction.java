package learning.views;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.javatuples.Triplet;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ScrollPaneConstants;

public class PanelViewTransaction extends JPanel{
	private JTable table;
	private JScrollPane scrollPane;
	
	public PanelViewTransaction() {
		setPreferredSize(new Dimension(215, 200));
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		setLayout(groupLayout);
	}
	
	public void fillPanel(List<Triplet<String, String, String>> list, Triplet<String, String, String> namesForColumns) {
		String columnsNames[] = {namesForColumns.getValue0(), namesForColumns.getValue1(), namesForColumns.getValue2()};
		Object rows[][] = new String[list.size()][columnsNames.length];
	
		for(int i = 0; i < list.size(); i++) {
			for(int j = 0; j < columnsNames.length; j++) {
				rows[i][j] = list.get(i).getValue(j);
			}
		}
		
		TableModel model = new DefaultTableModel(rows, columnsNames);
		table = new JTable(model);
		scrollPane.setViewportView(table);
		scrollPane.revalidate();
		scrollPane.repaint();
	}
}
