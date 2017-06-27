package test.jpmorgan.messagingapplication.entities;


import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import com.jpmorgan.messagingapplication.entities.Sale;
import com.jpmorgan.messagingapplication.exception.MessageTypeException;
import com.jpmorgan.messagingapplication.exception.SalesException;
import com.jpmorgan.messagingapplication.messagetype.MessageTypeOne;
import com.jpmorgan.messagingapplication.messagetype.MessageTypeThree;
import com.jpmorgan.messagingapplication.messagetype.MessageTypeTwo;
import com.jpmorgan.messagingapplication.messagetype.MessagingType;

public class SaleTest {

	@SuppressWarnings("serial")
	static HashMap<String,ArrayList<Sale>> productSales = new HashMap<String,ArrayList<Sale>>(){{
		ArrayList<Sale> saleList1 = new ArrayList<Sale>();
		saleList1.add(new Sale("Apple", 1));

		ArrayList<Sale> saleList2 = new ArrayList<Sale>();
		saleList2.add(new Sale("Potato",1));
		put("Apple", saleList1);
		put("Potato", saleList2);
	}};
	
	
	private Method updateProductSale() throws NoSuchMethodException, SecurityException {
        Method updateProductSale = Sale.class.getDeclaredMethod("updateProductSale", HashMap.class, Sale.class);
        updateProductSale.setAccessible(true);
        return updateProductSale;
    }
	
	@Test(expected=MessageTypeException.class)
	public void getAndUpdateSaleInvalidMessageTypeOneTest() throws MessageTypeException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, SalesException{
		MessagingType messageTypeOne = new MessageTypeOne("Cars", 1);
		Sale saleObj = new Sale("Cars", 1);
		updateProductSale().invoke(saleObj, productSales, saleObj);
		Sale.getAndUpdateSale(productSales,messageTypeOne);
	}
	
	@Test(expected=MessageTypeException.class)
	public void getAndUpdateSaleInvalidMessageTypeTwoTest() throws MessageTypeException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, SalesException{
		MessagingType messageTypeTwo = new MessageTypeTwo("Cars", 1, 2);
		Sale saleObj = new Sale("Cars", 1);
		updateProductSale().invoke(saleObj, productSales, saleObj);
		Sale.getAndUpdateSale(productSales,messageTypeTwo);
	}
	
	@Test(expected=MessageTypeException.class)
	public void getAndUpdateSaleInvalidMessageTypeThreeTest() throws MessageTypeException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, SalesException{
		MessagingType messageTypeThree = new MessageTypeThree("Cars", 1, "Add");
		Sale saleObj = new Sale("Cars", 1);
		updateProductSale().invoke(saleObj, productSales, saleObj);
		Sale.getAndUpdateSale(productSales,messageTypeThree);
	}
	
	@Test
	public void getAndUpdateSaleValidMessageTypeThreeTest() throws MessageTypeException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, SalesException{
		MessagingType messageTypeOne = new MessageTypeThree("Apple", 1, "Add");
		Sale saleObj = new Sale("Apple", 1);
		updateProductSale().invoke(saleObj, productSales, saleObj);
		Sale.getAndUpdateSale(productSales,messageTypeOne);
		assertEquals(2, productSales.get("Apple").size());
		assertEquals(1, productSales.get("Potato").size());
	}
	
}


