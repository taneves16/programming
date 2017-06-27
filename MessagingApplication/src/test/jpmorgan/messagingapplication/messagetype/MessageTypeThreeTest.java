package test.jpmorgan.messagingapplication.messagetype;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.jpmorgan.messagingapplication.exception.MessageTypeException;
import com.jpmorgan.messagingapplication.messagetype.MessageTypeThree;

public class MessageTypeThreeTest {

	@Test(expected=MessageTypeException.class)
	public void validateMessageEmtpyProductTest() throws MessageTypeException{
		MessageTypeThree msg = new MessageTypeThree("", 2, "Add");
		assertEquals(false, msg.validateMessage(msg.getProduct(), msg.getPrice(), msg.getOperation()));
	}
	
	@Test(expected=MessageTypeException.class)
	public void validateMessageNullProductTest() throws MessageTypeException{
		MessageTypeThree msg = new MessageTypeThree(null, 2, "Add");
		assertEquals(false, msg.validateMessage(msg.getProduct(), msg.getPrice(), msg.getOperation()));
	}

	@Test(expected=MessageTypeException.class)
	public void validateMessageNegativePriceTest() throws MessageTypeException{
		MessageTypeThree msg = new MessageTypeThree("Apple", -2, "Add");
		assertEquals(false, msg.validateMessage(msg.getProduct(), msg.getPrice(), msg.getOperation()));
	}
	
	@Test(expected=MessageTypeException.class)
	public void validateMessageZeroPriceTest() throws MessageTypeException{
		MessageTypeThree msg = new MessageTypeThree("Apple", 0, "Add");
		assertEquals(false, msg.validateMessage(msg.getProduct(), msg.getPrice(), msg.getOperation()));
	}
	
	@Test(expected=MessageTypeException.class)
	public void validateMessageInvalidOperationTest() throws MessageTypeException{
		MessageTypeThree msg = new MessageTypeThree("Apple", 2, "Addition");
		assertEquals(false, msg.validateMessage(msg.getProduct(), msg.getPrice(), msg.getOperation()));
	}
	
	@Test
	public void validateMessageValidAddTest() throws MessageTypeException{
		MessageTypeThree msg = new MessageTypeThree("Apple", 2, "Add");
		assertEquals(true, msg.validateMessage(msg.getProduct(), msg.getPrice(), msg.getOperation()));
	}
	
	@Test
	public void validateMessageValidSubtractTest() throws MessageTypeException{
		MessageTypeThree msg = new MessageTypeThree("Apple", 2, "subtract");
		assertEquals(true, msg.validateMessage(msg.getProduct(), msg.getPrice(), msg.getOperation()));
	}
	
	@Test
	public void validateMessageValidMultiplyTest() throws MessageTypeException{
		MessageTypeThree msg = new MessageTypeThree("Apple", 2, "MULTIPLy");
		assertEquals(true, msg.validateMessage(msg.getProduct(), msg.getPrice(), msg.getOperation()));
	}
}
