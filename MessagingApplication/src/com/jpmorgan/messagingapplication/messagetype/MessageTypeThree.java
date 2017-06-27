package com.jpmorgan.messagingapplication.messagetype;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.jpmorgan.messagingapplication.exception.MessageTypeException;

public class MessageTypeThree implements MessagingType {
	
	String product;
	double price;
	String operation;
	String message;
	
	public MessageTypeThree(String product, double price, String operation) throws MessageTypeException {
		if(validateMessage(product,price,operation)){
			this.product = product;
			this.price = price;
			this.operation = checkOperation(operation);
			this.message = price + "p was " + this.operation + " to each " + product;
		}else
			throw new MessageTypeException("Invalid parameters passed for message!");
	}
	
	private String checkOperation(String operation) throws MessageTypeException{
		switch(operation.toUpperCase()){
			case "ADD": return "added"; 
			case "SUBTRACT": return "subtracted"; 
			case "MULTIPLY" : return "multiplied"; 
			default : throw new MessageTypeException("Allowed types are ADD, SUBTRACT and MULTILPY");
		}
	}
	
	public static void fetchMessage(ArrayList<MessageTypeThree> messageList,Logger logger) {
		if(logger.isDebugEnabled())
			for(MessageTypeThree message : messageList){
				logger.debug(message.message);
			}
	}

	@Override
	public String getProduct() {
		return this.product;
	}

	@Override
	public double getPrice() {
		return this.price;
	}
	
	public String getOperation(){
		return this.operation;
	}

	public boolean validateMessage(String product, double price, String operation) throws MessageTypeException {
		if(null != product && !product.isEmpty() && price > 0){
			verifyProductType(product);
			return true;
		}
		return false;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

}
