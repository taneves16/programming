package test.jpmorgan.messagingapplication.entities;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.jpmorgan.messagingapplication.entities.Product;

public class ProductTest {

	@Test
	public void productTypeExistsTest(){
		assertEquals("Fruit", Product.getProductType("Apple"));
	}
	
	@Test
	public void productTypeDoesNotExistTest(){
		assertEquals(null, Product.getProductType("Cars"));
	}
	
	@Test
	public void productTypeProductNameEmptyTest(){
		assertEquals(null, Product.getProductType(""));
	}
	
	@Test
	public void productTypeProductNameNullTest(){
		assertEquals(null, Product.getProductType(null));
	}
	
	@Test
	public void addNewProductProductNameNullTest(){
		assertEquals(false, Product.addNewProduct(null, null, 0));
	}
	
	@Test
	public void addNewProductProductNameEmptyTest(){
		assertEquals(false, Product.addNewProduct("", null, 0));
	}
	
	@Test
	public void addNewProductProductTypeNullTest(){
		assertEquals(false, Product.addNewProduct("Car", null, 0));
	}
	
	@Test
	public void addNewProductProductTypeEmptyTest(){
		assertEquals(false, Product.addNewProduct("Car", "", 0));
	}
	
	@Test
	public void addNewProductNegativePriceTest(){
		assertEquals(false, Product.addNewProduct("Car", "Vehicles", -1));
	}
	
	@Test
	public void addNewProductZeroPriceTest(){
		assertEquals(false, Product.addNewProduct("Car", "Vehicles", 0));
	}
	
	@Test
	public void addNewProductProductExistsTest(){
		assertEquals(false, Product.addNewProduct("Apple", "Fruits", 1));
	}
	
	@Test
	public void addNewProductNewProductTest(){
		assertEquals(true, Product.addNewProduct("Car", "Vehicle", 50));
	}
	
}
