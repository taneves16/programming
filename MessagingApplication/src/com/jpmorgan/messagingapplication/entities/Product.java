package com.jpmorgan.messagingapplication.entities;

import java.util.HashMap;
import java.util.Map;

public class Product {

	String name;
	String type;
	double price;
	static int id = 0;
	
	private static Map<String, Product> productMap = new HashMap<String, Product>(){
		{
		 put("Apple",new Product("Apple","Fruit",2));
		 put("Banana",new Product("Banana","Fruit",1));
		 put("Potato",new Product("Potato","Vegetable",1.5));
		 put("Tomato",new Product("Tomato","Vegetable",1.5));
		 put("Mobile",new Product("Mobile","Electronics",50));
		 put("Laptop",new Product("Laptop","Electronics",70));
		 put("Coke",new Product("Coke","Beverages",2.5));
		 put("Ale",new Product("Ale","Beverages",1.75));
		 put("TShirt",new Product("TShirt","Clothes",3));
		}
	};
	
	public Product(String name, String type, double price) {
		this.name = name;
		this.type = type;
		this.price = price;
		id++;
	}
	
	public static String getProductType(String productName){
		if(productMap.containsKey(productName))
			return productMap.get(productName).type;
		return null;
	}
	
	public static boolean addNewProduct(String name,String type,double price){
		if(null == name || name.isEmpty() || null == type || type.isEmpty() || price <= 0)
			return false;
		if(!productMap.containsKey(name)){
			productMap.put(name, new Product(name, type, price));
			return true;
		}
		return false;
			
	}
	
}
