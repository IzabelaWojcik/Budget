package learning.views;

public class ButtonsData extends NotificationData{

		public String name;

		public ButtonsData(int notifierId, String name) {
			super(notifierId);
			this.name = name;
		}
		
		@Override
		public String toString() {
			return super.notifierId + " " + name;
		}
		
		@Override
		public boolean equals(Object obj) {
			return super.equals(obj) && obj instanceof ButtonsData && ((ButtonsData)obj).name == name;
		}
		
}
