package com.jpmorgan.messagingapplication.messagetype;

import com.jpmorgan.messagingapplication.exception.MessageTypeException;

public class MessageTypeTwo implements MessagingType {
	
	String product;
	double price;
	int noOfSales;
	String message;
	
	public MessageTypeTwo(String product, double price, int noOfSales) throws MessageTypeException {
		if(validateMessage(product,price,noOfSales)){
			this.product = product;
			this.price = price;
			this.noOfSales = noOfSales;
			this.message = noOfSales +" sales of "+ product + " at "+price+"p each";
		}else
			throw new MessageTypeException("Invalid parameters passed for message!");
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}

	@Override
	public String getProduct() {
		return this.product;
	}

	@Override
	public double getPrice() {
		return this.price;
	}
	
	public int getNoOfSales(){
		return this.noOfSales;
	}

	public boolean validateMessage(String product, double price, int noOfSales) throws MessageTypeException {
		if(null != product && !product.isEmpty() && price > 0 && noOfSales > 0){
			verifyProductType(product);
			return true;
		}
		return false;
	}
}
