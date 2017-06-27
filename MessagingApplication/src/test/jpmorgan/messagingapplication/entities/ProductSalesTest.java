package test.jpmorgan.messagingapplication.entities;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.mockito.Mockito;

import com.jpmorgan.messagingapplication.entities.ProductSales;
import com.jpmorgan.messagingapplication.entities.Sale;




public class ProductSalesTest {
	
	ProductSales productSalesObj = Mockito.mock(ProductSales.class);
	
	@SuppressWarnings("serial")
	static HashMap<String,ArrayList<Sale>> productSales = new HashMap<String,ArrayList<Sale>>(){{
		ArrayList<Sale> saleList1 = new ArrayList<Sale>();
		saleList1.add(new Sale("Apple", 1));

		ArrayList<Sale> saleList2 = new ArrayList<Sale>();
		saleList2.add(new Sale("Potato",1));
		put("Apple", saleList1);
		put("Potato", saleList2);
	}};
	
	@Test
	public void getProductSalesReturnNewHashmap(){
		assertEquals(true, ProductSales.getProductSales().isEmpty());
	}
	
	@Test
	public void printReportValidTest(){
		Logger logger = Mockito.mock(Logger.class);
		when(logger.isDebugEnabled()).thenReturn(true);
		doNothing().when(logger).debug(any(String.class));
		ProductSales.printReport(logger, productSales);
		verify(logger, times(2)).debug(any(String.class));
	}
	
	@Test
	public void printReportInvalidHashmapTest(){
		Logger logger = Mockito.mock(Logger.class);
		when(logger.isDebugEnabled()).thenReturn(true);
		doNothing().when(logger).debug(any(String.class));
		ProductSales.printReport(logger, null);
		verify(logger, times(0)).debug(any(String.class));
	}
	
	@Test
	public void printReportInvalidLoggerTest(){
		Logger logger = Mockito.mock(Logger.class);
		when(logger.isDebugEnabled()).thenReturn(true);
		doNothing().when(logger).debug(any(String.class));
		ProductSales.printReport(null, productSales);
		verify(logger, times(0)).debug(any(String.class));
	}
	
	@Test
	public void printReportEmptyHashMapTest(){
		Logger logger = Mockito.mock(Logger.class);
		when(logger.isDebugEnabled()).thenReturn(true);
		doNothing().when(logger).debug(any(String.class));
		ProductSales.printReport(logger, new HashMap<String,ArrayList<Sale>>());
		verify(logger, times(0)).debug(any(String.class));
	}
}
