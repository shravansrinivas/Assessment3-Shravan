package com.store.local;
//import java.text.SimpleDateFormat; 
import java.util.*;
public class localStoreManagement {
List<itemTemplate> inStock;
List<inSales> forSales;
public boolean stkCtrl=true,check=true;
Scanner inp=new Scanner(System.in);
	public localStoreManagement() {
		inStock=new ArrayList<>();
		forSales=new ArrayList<>();
	}
public void manageStock()
	{
	Scanner inp=new Scanner(System.in);
	do {
	System.out.println("What do you want to do in Stock management?");
	System.out.println("1.Add a new Item.");
	System.out.println("2.Update details of an existing item.");
	System.out.println("3.Go to Sales");
	System.out.print("\nChoice>>>");
	int stkChoice=inp.nextInt();
	switch(stkChoice)
	{
	case 1:
		stkAddItem();
		break;
	case 2:
		stkUpdateItem();
		break;
	case 3:
		//System.out.println("Invalid Choice!Try again");
		stkCtrl=false;
		break;
	default:
		System.out.println("Stock Management Exited.");
		
	}
	}while(stkCtrl);
	}
public void manageSales()
{String salesCtrl;
	if(inStock.size()==0)
{
System.out.println("-----------------------------------------------------------------------------------");
System.out.println("\t\t\t\t\tNo item to display,Add Items First");
System.out.println("-----------------------------------------------------------------------------------");
}
else {
	displayAvailableItems();
	do {
	System.out.println("\nChoose Item Code from List");
	
	addItemToSales();
	System.out.println("More Items to add?");
	System.out.println("Y=Yes,Anything else to exit");
	salesCtrl=inp.next();
	}while(salesCtrl.equalsIgnoreCase("Y"));
	displayInvoice();
	
}
	
	
}
	public static void main(String[] args) {
	localStoreManagement manage=new localStoreManagement();
	Scanner inp=new Scanner(System.in);
	System.out.println("\t\t\t\t\tWelcome to the store!");
	while(true) {
	System.out.println("What do you want to do?");
	System.out.println("1.Manage Item Stock.");
	System.out.println("2.Manage Sales.");
	System.out.println("3.Exit.");
	System.out.print("\nChoice>>>");
	int choice=inp.nextInt();
	switch(choice)
	{
	case 1:
		manage.manageStock();
		break;

	case 2:
		List<inSales> forSales;
		manage.manageSales();
		System.out.println("\t\t\t\tBYE..Have a nice day");
		
		break;
	case 3:
		System.out.println("\t\t\t\tBYE..Have a nice day");
		System.exit(0);
	default:
		System.out.println("Enter valid Choice!!");
	}
	}
	}
public void stkAddItem() {
	int itemID, quantity;
	float  price;
	String name;
	
while(true)
{
System.out.println("Enter Code for New Item:");
itemID=inp.nextInt();
if(itemID<1000&&itemID>0)
	break;
else
	System.out.println("Enter valid Item Code");
	
}
System.out.println("Enter Name of the New Item:");
name=inp.next();

System.out.println("Enter price of New Item:");
price=inp.nextFloat();

System.out.println("Enter Quantity of the New Item in Stock:");
quantity=inp.nextInt();

inStock.add(new itemTemplate(itemID,name,price,quantity));
System.out.printf("\nItem %s Added!\n\n",name);

}
public void stkUpdateItem() {
	int itemID,reqID=-1;
	

System.out.println("Enter the code of the Item to update:");
itemID=inp.nextInt();
for(int i=0;i<inStock.size();i++)
{
	if(inStock.get(i).itemCode==itemID)
	{	reqID=i;
	break;
	}
}
if(reqID==-1)
	System.out.println("Invalid Code.");
else
{
	System.out.println("Enter new Name of the Item:");
	inStock.get(reqID).itemName=inp.next();

	System.out.println("Enter new price ofItem:");
	inStock.get(reqID).itemPrice=inp.nextInt();

	System.out.println("Enter new Quantity of the Item in Stock:");
	inStock.get(reqID).stockQuantity=inp.nextInt();
}
System.out.println("Item Details Updated.");
}
public void displayAvailableItems()
{
	
	{
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t\tAvailable Items");
		//System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.format("%20s %20s %20s %20s","Item Code","Item Name", "Price for one", "Quantity in stock\n");
		System.out.println();
		//System.out.println("-----------------------------------------------------------------------------------");
		for(itemTemplate item:inStock) {
		//System.out.println("-----------------------------------------------------------------------------------");
			System.out.println();System.out.println();System.out.format("%20s %20s %20s %20s",item.itemCode,item.itemName,item.itemPrice,item.stockQuantity);
		//System.out.println("-----------------------------------------------------------------------------------");
		}
	}
}
public void addItemToSales()
{int req=-1;

System.out.println("Enter Item code to add:");
int code=inp.nextInt();
for(int i=0;i<inStock.size();i++) {
	if(inStock.get(i).itemCode==code)
		{req=i;
		break;
}
}
if(req==-1) {
System.out.println("Item Not present,Enter Valid Code");		
}
else
{

System.out.println("Enter the quantity of item required");
do{
	System.out.println("Quantity>>>");
	
	int qnty=inp.nextInt();
itemTemplate item=new itemTemplate(inStock.get(req).itemCode,inStock.get(req).itemName,inStock.get(req).itemPrice,inStock.get(req).stockQuantity);
if(qnty<=inStock.get(req).stockQuantity)
{
	float cost=qnty*inStock.get(req).itemPrice;
	inStock.get(req).stockQuantity-=qnty;
	forSales.add(new inSales(inStock.get(req).itemCode,inStock.get(req).itemName,qnty, inStock.get(req).itemPrice,cost));
	check=false;
	System.out.printf("%s Added",inStock.get(req).itemName);
}
else
{
	System.out.printf("Only %d of selected item available",inStock.get(req).stockQuantity);
	System.out.println("Required Quantity of Item not available, Reselect Quantity.");	
}
}while(check==true);



}
}
public void displayInvoice() {
	if(forSales.size()!=0) {
		Date date = new Date();  
		float total=0;
	System.out.println("------------------------------------------------------------------------------------------------------------------------------");
	System.out.print("\n\t\t\t\t\t\t\t SALES INVOICE\t");
	System.out.println(date);
	System.out.println("------------------------------------------------------------------------------------------------------------------------------");
	System.out.println("------------------------------------------------------------------------------------------------------------------------------");
	System.out.format("%20s %20s %20s %20s %20s","Item Code","Item Name", "Price for one","Cost", "Quantitybought");
	System.out.println();
	System.out.println("------------------------------------------------------------------------------------------------------------------------------");
	for(inSales i:forSales) {
		System.out.println();
		System.out.format("%20s %20s %20s %20s %20s",i.itemCode,i.itemName,i.itemPrice,i.cost,i.reqQuantity);
		total+=i.cost;
		
		
			
	}
	System.out.println("\n------------------------------------------------------------------------------------------------------------------------------");
	System.out.println("\t\t\t\t\tTotal:\tRs"+(total));
	System.out.println("------------------------------------------------------------------------------------------------------------------------------");
	System.out.println("\t\t\t\t\t\t\t Thank You for shopping, Visit Again");
	System.out.println("------------------------------------------------------------------------------------------------------------------------------");
	}
	else
	{
		System.out.println("------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t\tNo Items in the list to bill");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------");
		}
		
}
}
