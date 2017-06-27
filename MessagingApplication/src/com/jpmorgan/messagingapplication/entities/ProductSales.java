package com.jpmorgan.messagingapplication.entities;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

public class ProductSales {

    static HashMap<String,ArrayList<Sale>> productSales;
    static Logger logger = Logger.getLogger(ProductSales.class);
    
    public static HashMap<String, ArrayList<Sale>> getProductSales(){
    	if(null == productSales )
    		productSales = new HashMap<String,ArrayList<Sale>>();
        return productSales;
    }

    public static void printReport(Logger logger,HashMap<String, ArrayList<Sale>> productSale){
    	if(null == productSale || null == logger)
    		return;
        if(logger.isDebugEnabled()){
            productSale.forEach((key,value)->{
            logger.debug("Product : " + key + ", No Of sales : " + value.size() + ", Total Value :"+value.stream().mapToDouble(Sale::getPrice).sum());
            });   
        }
    }
}
