package com.ECommerce.POJO;
import java.util.List;
public class createOrderRequest {
	private List<createProductSubRequest> orders;
	public List<createProductSubRequest> getOrders() {
		return orders;
	}

	public void setOrders(List<createProductSubRequest> orders) {
		this.orders = orders;
	}

}
