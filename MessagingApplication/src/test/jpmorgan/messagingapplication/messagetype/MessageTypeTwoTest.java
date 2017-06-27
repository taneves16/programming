package test.jpmorgan.messagingapplication.messagetype;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.jpmorgan.messagingapplication.exception.MessageTypeException;
import com.jpmorgan.messagingapplication.messagetype.MessageTypeTwo;

public class MessageTypeTwoTest {

	@Test(expected=MessageTypeException.class)
	public void validateMessageEmtpyProductTest() throws MessageTypeException{
		MessageTypeTwo msg = new MessageTypeTwo("", 2, 1);
		assertEquals(false, msg.validateMessage(msg.getProduct(), msg.getPrice(), msg.getNoOfSales()));
	}
	
	@Test(expected=MessageTypeException.class)
	public void validateMessageNullProductTest() throws MessageTypeException{
		MessageTypeTwo msg = new MessageTypeTwo(null, 2, 1);
		assertEquals(false, msg.validateMessage(msg.getProduct(), msg.getPrice(), msg.getNoOfSales()));
	}

	@Test(expected=MessageTypeException.class)
	public void validateMessageNegativePriceTest() throws MessageTypeException{
		MessageTypeTwo msg = new MessageTypeTwo("Apple", -2, 1);
		assertEquals(false, msg.validateMessage(msg.getProduct(), msg.getPrice(), msg.getNoOfSales()));
	}
	
	@Test(expected=MessageTypeException.class)
	public void validateMessageZeroPriceTest() throws MessageTypeException{
		MessageTypeTwo msg = new MessageTypeTwo("Apple", 0, 1);
		assertEquals(false, msg.validateMessage(msg.getProduct(), msg.getPrice(), msg.getNoOfSales()));
	}
	
	@Test(expected=MessageTypeException.class)
	public void validateMessageNegativeNoOfSalesTest() throws MessageTypeException{
		MessageTypeTwo msg = new MessageTypeTwo("Apple", 2, -1);
		assertEquals(false, msg.validateMessage(msg.getProduct(), msg.getPrice(), msg.getNoOfSales()));
	}
	
	@Test(expected=MessageTypeException.class)
	public void validateMessageZeroNoOfSalesTest() throws MessageTypeException{
		MessageTypeTwo msg = new MessageTypeTwo("Apple", 2, 0);
		assertEquals(false, msg.validateMessage(msg.getProduct(), msg.getPrice(), msg.getNoOfSales()));
	}
	
	@Test
	public void validateMessagevalidTest() throws MessageTypeException{
		MessageTypeTwo msg = new MessageTypeTwo("Apple", 2, 1);
		assertEquals(true, msg.validateMessage(msg.getProduct(), msg.getPrice(), msg.getNoOfSales()));
	}
}
