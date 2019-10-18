package com.store.local;

public class inSales {
	public String itemName;
	public int itemCode, reqQuantity,stockQuantity;
	public float cost,itemPrice;
	public inSales(int code,String name, int quantity, float price, float cost) {
		this.itemCode=code;
		this.itemName=name;
		this.itemPrice=price;
		this.reqQuantity=quantity;
		this.cost=cost;
	}

}
