//package learning.budget.views;
//
//import static org.junit.Assert.*;
//
//import java.time.LocalDate;
//import java.time.ZoneId;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.Matchers;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//
//import learning.budget.IDatabaseWriter;
//
//public class AddTransactionToDatabaseListenerTest {
//
//	private AddTransactionToDatabaseListener sut;
//	HashMap<Integer, String> categoryMap = new HashMap<>();
//	private String tablename;
//	private PanelAddTransactionWithComboBoxCategoryTestable panelToAddTransaction;
//	private int clickedBudgetId;
//	private int categoryId;
//	private ArrayList<String> categoryList =  new ArrayList<String>();
//	
//	@Mock
//	IDatabaseWriter databaseWriter;
//	
//	@Before
//	public void setUp() {
//		MockitoAnnotations.initMocks(this);
//		categoryMap.put(1, "kredyt");
//		categoryMap.put(2, "zakupy");
//		categoryMap.put(3, "opłaty");
//		
//		tablename = "Wydatki";
//		clickedBudgetId = 1;
//		categoryId = 1;
//		
//		panelToAddTransaction = new PanelAddTransactionWithComboBoxCategoryTestable(categoryMap.values());
//		sut = new AddTransactionToDatabaseListener(categoryMap, tablename, panelToAddTransaction, clickedBudgetId, databaseWriter);
//	}
//	
//	@Test
//	public void test() {
//		DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd-MM-uuuu");
//		LocalDate date = LocalDate.parse("01-01-2018", formatters);
//		String transactionAmount = "4.0f";
//		
//		panelToAddTransaction.setComboBoxCategory(categoryMap.get(categoryId));
//		panelToAddTransaction.setAmount(transactionAmount);
//		panelToAddTransaction.setDate(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
//		
//		panelToAddTransaction.clickButton();
//
//		Mockito.verify(databaseWriter, Mockito.times(1)).writeExpenditureOrSavingsToDatabase(Double.parseDouble(transactionAmount), date, categoryId, clickedBudgetId, tablename);
//	}
//}
