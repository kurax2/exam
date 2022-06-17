package p1;

import java.util.Comparator;

public class Order implements Comparator<Order>{
	private String userName;
	private String category;
	private String orderCost;
	private String date;
	private String action;
	

	@Override
	public String toString() {
		return "\n"+"Order [Username=" + userName + ", category=" + category + ", Cost=" + orderCost +", date="+date+", action="+action+ "]\n";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getOrderCost() {
		return orderCost;
	}

	public void setOrderCost(String orderCost) {
		this.orderCost = orderCost;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public int compare(Order o1, Order o2) {
		// TODO Auto-generated method stub
		return Integer.parseInt(o1.getOrderCost())-Integer.parseInt(o2.getOrderCost());
	}

}
