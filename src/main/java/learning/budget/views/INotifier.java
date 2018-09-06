package learning.budget.views;

public interface INotifier {
	
	public void register(IListener listener);
	
	public void deregister(IListener listener);

}
