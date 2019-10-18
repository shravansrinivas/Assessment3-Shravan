package com.store.local;

public class itemTemplate {
public String itemName;
public int itemCode, stockQuantity;
public float cost,itemPrice;
	public itemTemplate(int code, String name,  float price, int quantity) {
		this.itemCode=code;
		this.itemName=name;
		this.itemPrice=price;
		this.stockQuantity=quantity;	
	}
	

}
