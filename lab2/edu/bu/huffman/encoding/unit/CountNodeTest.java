package edu.bu.huffman.encoding.unit;


// SYSTEM IMPORTS
import org.junit.Assert;
import org.junit.Test;

// JAVA PROJECT IMPORTS
import edu.bu.huffman.encoding.CountNode;


public class CountNodeTest
	extends Object
{
	@Test
	public void testCharacterConstructor()
	{
		CountNode node = new CountNode('c');
		Assert.assertEquals('c', (char)node.getChar());
		Assert.assertFalse(node.isFinalized());
		Assert.assertEquals(0, node.getCount());
	}
}