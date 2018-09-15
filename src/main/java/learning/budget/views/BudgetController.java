package learning.budget.views;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import learning.budget.DatabaseNotInitialized;
import learning.budget.IDatabaseReader;
import learning.budget.Transaction;

public class BudgetController implements IListener{
	private PanelWithButtons panelWithBudget, panelWithYears, panelWithMonths;
	private IDatabaseReader databaseReader;
	private Map<Integer, String> budgetIdToName;
	private List<Transaction> transactions;
	
	public BudgetController(IDatabaseReader databaseReader, PanelWithButtons panelBudget, PanelWithButtons panelYears, PanelWithButtons panelMonths) {
		panelWithBudget = panelBudget;
		panelWithYears = panelYears;
		panelWithMonths = panelMonths;
		
		panelWithBudget.register(this);
		panelWithYears.register(this);
		panelWithMonths.register(this);
		
		this.databaseReader = databaseReader;
	}
	
	public void initializePanelBudget() throws DatabaseNotInitialized {
		budgetIdToName = databaseReader.readBudgetIdNameFromDatabase();
		SortedSet<String> names = new TreeSet<String>(budgetIdToName.values());
		panelWithBudget.createButtons(names);
	}
	
	private int getBudgetId(String budgetName)
	{
		for(Entry<Integer, String> e: budgetIdToName.entrySet())
		{
			if(e.getValue().equals(budgetName))
				return e.getKey();
		}
		throw new NoSuchElementException("Budget id not found");
	}
	
	@Override
	public void notify(String notifierId, String data) {
		if(notifierId == panelWithBudget.identifier)
		{
			String budgetName = data;
			try {
				transactions = databaseReader.readBudgetFromDatabase(getBudgetId(budgetName));
			} catch (DatabaseNotInitialized e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
			
			Set<String> years = transactions.stream()
										.map(Transaction::getYear)
										.map(year -> year.toString())
										.collect(Collectors.toSet());
			panelWithYears.createButtons(new TreeSet<String>(years));
		}
	}

}
