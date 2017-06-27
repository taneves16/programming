package com.jpmorgan.messagingapplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

import com.jpmorgan.messagingapplication.entities.Sale;
import com.jpmorgan.messagingapplication.exception.MessageTypeException;
import com.jpmorgan.messagingapplication.messagetype.MessageTypeOne;
import com.jpmorgan.messagingapplication.messagetype.MessageTypeThree;
import com.jpmorgan.messagingapplication.messagetype.MessageTypeTwo;
import com.jpmorgan.messagingapplication.messagetype.MessagingType;

public class Main {

	static Logger logger = Logger.getLogger(Main.class);
	static HashMap<String, ArrayList<Sale>> productSales = new HashMap<String, ArrayList<Sale>>();
	MessageProcesser mp = new MessageProcesser();
	static LinkedBlockingQueue<MessagingType> messageQueue = new LinkedBlockingQueue<>();
	
	public static void main(String[] args) throws MessageTypeException{
		
		 boolean runProcess = true;
	        String product;
	        double price; 
	        int noOfSales;
	        String operation;
	        System.out.println("Welcome to the Message Processing Application");
	        System.out.println("We have three types of messages. They are :");
	        System.out.println("1. Message Type 1 - contains the details of 1 sale. E.g. Apple at 10p");
	        System.out.println("2. Message Type 2 - contains the details of a sale and the number of occurrences of that sale. E.g. Sales of Apples at 10p each");
	        System.out.println("3. Message Type 3 - contains the details of a sale and an adjustment operation to be applied to all stored salesof this product type.");
	        System.out.println("                    Operations can be add, subtract or multiply. E.g. Add 20p Apples would instruct your application to add 20p to each sale of apples you have recorded");
	        Scanner sc = new Scanner(System.in);
	        while(runProcess){
	            System.out.println("Enter the Message type No :");
	            int messageTypeNo = sc.nextInt();
	            try {
	                switch(messageTypeNo){
	                 case 1 : System.out.println("Enter the Product Name :");
	                          product = sc.next();
	                          System.out.println("Enter the Price :");
	                          price = sc.nextDouble();
	                          messageQueue.add(new MessageTypeOne(product, price));
	                          break;
	                 case 2 : System.out.println("Enter the Product Name :");
	                          product = sc.next();
	                          System.out.println("Enter the Price :");
	                          price = sc.nextDouble();
	                          System.out.println("Enter the No of Sales :");
	                          noOfSales = sc.nextInt();
	                          messageQueue.add(new MessageTypeTwo(product, price, noOfSales));
	                          break;
	                 case 3 : System.out.println("Enter the Product Name :");
	                          product = sc.next();
	                          System.out.println("Enter the Price :");
	                          price = sc.nextDouble();
	                          System.out.println("Enter the Operation :");
	                          operation = sc.next();
	                          messageQueue.add(new MessageTypeThree(product, price, operation));
	                          break;
	                  default : System.out.println("Wrong message type number entered");
	                            break;  
	                }
	            } catch (MessageTypeException e) {
	                System.out.println("Invalid Input!");
	            }
	            LinkedBlockingQueueConsumer consumer = new LinkedBlockingQueueConsumer(productSales,messageQueue);
	            new Thread(consumer).start();
	            System.out.println("Continue? Y or N");
	            String run = sc.next();
	            if(null != run && run.toUpperCase().contentEquals("N"))
	                runProcess = false;
	            
	        }
	        if(!runProcess)
	        	System.exit(1);
	    }
}


