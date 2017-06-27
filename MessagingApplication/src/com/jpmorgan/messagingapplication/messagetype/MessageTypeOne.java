package com.jpmorgan.messagingapplication.messagetype;

import com.jpmorgan.messagingapplication.exception.MessageTypeException;

public class MessageTypeOne implements MessagingType{

	String product;
	double price;
	String message;
	
	public MessageTypeOne(String product, double price) throws MessageTypeException {
		if(validateMessage(product,price)){
			this.product = product;
			this.price = price;
			this.message = product + " at " + price +"p";
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

	public boolean validateMessage(String product,double price) throws MessageTypeException {
		if(null != product && !product.isEmpty() && price > 0){
		    verifyProductType(product);
			return true;
		}
		return false;
	}

	
}
