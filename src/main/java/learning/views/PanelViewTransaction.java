package learning.views;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.javatuples.Pair;
import org.javatuples.Triplet;

public class PanelViewTransaction extends JPanel{
	private JTable table;
	private Object rows[][];
	private String columns[];
	private TableModel model = null;
	
	public PanelViewTransaction() {
		setPreferredSize(new Dimension(210, 20));
		setLayout(new FlowLayout());
		
		table = new JTable(model);
		
		//JScrollPane scrollPane = new JScrollPane(table);
		
		//add(scrollPane);
		add(table);
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
		//add(table);
		//revalidate();
		//repaint();
	}
}
