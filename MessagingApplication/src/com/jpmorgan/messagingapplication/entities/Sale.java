package com.jpmorgan.messagingapplication.entities;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.jpmorgan.messagingapplication.exception.SalesException;
import com.jpmorgan.messagingapplication.messagetype.MessageTypeOne;
import com.jpmorgan.messagingapplication.messagetype.MessageTypeThree;
import com.jpmorgan.messagingapplication.messagetype.MessageTypeTwo;
import com.jpmorgan.messagingapplication.messagetype.MessagingType;

public class Sale {
	
	private static int saleId = 0;
	private String product;
	private double price;
	static Logger logger = Logger.getLogger(Sale.class);
	
	public Sale(String product, double price) {
		saleId++;
		this.product = product;
		this.price = price;
	}

	public int getSaleId() {
		return saleId;
	}

	public String getProduct() {
		return product;
	}

	public double getPrice() {
		return price;
	}
	
	/**
	 * Method allows modifies price of Sale
	 */
	public void setPrice(double price){
	    this.price = price;
	}
	
	/**
	 * Method returns a sale
	 * @throws SalesException 
	 */
	public static void getAndUpdateSale(HashMap<String, ArrayList<Sale>> productSales,MessagingType messageType) throws SalesException{
		String product = messageType.getProduct();
		double price = messageType.getPrice();

		if(messageType instanceof MessageTypeOne){
			updateProductSale(productSales,new Sale(product, price));
		}else if(messageType instanceof MessageTypeTwo){
		    for(int i=0;i<((MessageTypeTwo) messageType).getNoOfSales();i++)
		        updateProductSale(productSales,new Sale(product,price));
		}else if(messageType instanceof MessageTypeThree){
		    if(!productSales.containsKey(product))
		        throw new SalesException("There are no sales for this product!");
		    else{
		        ArrayList<Sale> sales = productSales.get(product);
		        for(Sale sale : sales){
            	    switch(((MessageTypeThree) messageType).getOperation()){
            		      case "added" : sale.setPrice(sale.getPrice()+price);break;
            		      case "subtracted" : sale.setPrice(sale.getPrice() - price);
            		                          if(sale.getPrice() < 0)
            		                        	  throw new SalesException("Sales can't be negative");
            		                          break;
            		      case "multiplied" : sale.setPrice(sale.getPrice() * price);break;
            	    }
		        }
		        productSales.put(product, sales);
		    }
		}
			
	}
	
	
	private static void updateProductSale(HashMap<String, ArrayList<Sale>> productSales, Sale sale){
	    ArrayList<Sale> sales = new ArrayList<>();
	    if(productSales.containsKey(sale.getProduct()))
	        sales = productSales.get(sale.getProduct());
        sales.add(sale);

        productSales.put(sale.getProduct(), sales);
	}
	
	public static void printAdjustments(ArrayList<MessageTypeThree> messageList){
		MessageTypeThree.fetchMessage(messageList, logger);
	}

}
