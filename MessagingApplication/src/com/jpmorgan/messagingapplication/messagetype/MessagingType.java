package com.jpmorgan.messagingapplication.messagetype;

import com.jpmorgan.messagingapplication.entities.Product;
import com.jpmorgan.messagingapplication.exception.MessageTypeException;

public interface MessagingType {
	
	String getMessage();
	String getProduct();
	double getPrice();
	
	default void verifyProductType(String productName) throws MessageTypeException{
		if(null == Product.getProductType(productName))
			throw new MessageTypeException("The product type does not exist in our system!");
	}

}
