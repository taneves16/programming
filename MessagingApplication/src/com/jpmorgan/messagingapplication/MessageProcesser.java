package com.jpmorgan.messagingapplication;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.jpmorgan.messagingapplication.entities.ProductSales;
import com.jpmorgan.messagingapplication.entities.Sale;
import com.jpmorgan.messagingapplication.exception.SalesException;
import com.jpmorgan.messagingapplication.messagetype.MessageTypeThree;
import com.jpmorgan.messagingapplication.messagetype.MessagingType;

public class MessageProcesser {
	
	private static int msgCount = 0;
	private static ArrayList<MessageTypeThree> messageList = new ArrayList<>();
	static Logger logger = Logger.getLogger(MessageProcesser.class);
	
	public void processMessage(HashMap<String, ArrayList<Sale>> productSales, MessagingType messagingType){

		try {
			Sale.getAndUpdateSale(productSales,messagingType);
		} catch (SalesException e) {
			if(logger.isDebugEnabled())
				logger.debug(e.getMessage());
			return;
		}
		if(messagingType instanceof MessageTypeThree)
		    messageList.add((MessageTypeThree) messagingType);
		    
		msgCount++;
		
		if(msgCount % 10 == 0){
			if(logger.isDebugEnabled())
				logger.debug("\nREPORT");
		    ProductSales.printReport(logger,productSales);
		}
		
		if(msgCount % 50 == 0){
		    if(logger.isDebugEnabled()){
		        logger.debug("\n Application is Paused to do processing");
		        Sale.printAdjustments(messageList);
		        messageList = new ArrayList<>();
		    }
		}
	}
	
}
