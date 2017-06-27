package test.jpmorgan.messagingapplication.messagetype;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.jpmorgan.messagingapplication.exception.MessageTypeException;
import com.jpmorgan.messagingapplication.messagetype.MessageTypeOne;

public class MessageTypeOneTest {
	
	@Test(expected=MessageTypeException.class)
	public void validateMessageEmtpyProductTest() throws MessageTypeException{
		MessageTypeOne msg = new MessageTypeOne("", 2);
		assertEquals(false, msg.validateMessage(msg.getProduct(), msg.getPrice()));
	}
	
	@Test(expected=MessageTypeException.class)
	public void validateMessageNullProductTest() throws MessageTypeException{
		MessageTypeOne msg = new MessageTypeOne(null, 2);
		assertEquals(false, msg.validateMessage(msg.getProduct(), msg.getPrice()));
	}

	@Test(expected=MessageTypeException.class)
	public void validateMessageNegativePriceTest() throws MessageTypeException{
		MessageTypeOne msg = new MessageTypeOne("Apple", -2);
		assertEquals(false, msg.validateMessage(msg.getProduct(), msg.getPrice()));
	}
	
	@Test(expected=MessageTypeException.class)
	public void validateMessageZeroPriceTest() throws MessageTypeException{
		MessageTypeOne msg = new MessageTypeOne("Apple", 0);
		assertEquals(false, msg.validateMessage(msg.getProduct(), msg.getPrice()));
	}
	
	@Test
	public void validateMessagevalidTest() throws MessageTypeException{
		MessageTypeOne msg = new MessageTypeOne("Apple", 2);
		assertEquals(true, msg.validateMessage(msg.getProduct(), msg.getPrice()));
	}
}
