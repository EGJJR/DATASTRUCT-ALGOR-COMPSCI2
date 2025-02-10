package edu.bu.huffman.encoding.unit;


// SYSTEM IMPORTS
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeSet; // balanced binary tree in Java
import java.io.StringReader;
import java.io.Reader;

// JAVA PROJECT IMPORTS
import edu.bu.huffman.encoding.CountNode;
import edu.bu.huffman.encoding.Encoder;
import edu.bu.huffman.utils.HuffmanNode;
import edu.bu.huffman.utils.HuffmanTree;
import edu.bu.huffman.utils.EncodingPair;


public class EncoderTest
	extends Object
{
	@Test
	public void testGetUniqueCharacterCounts()
	{
		String inputString = "hello";
		// Reader r =  new StringReader(inputString);

		List<CountNode> nodes = Encoder.getUniqueCharacterCounts(inputString); // r);
		Assert.assertEquals(4, nodes.size());

		// check that each node is finalized so the counts can't
		// accidentally be changed by anything
		for(CountNode node : nodes)
		{
			Assert.assertTrue(node.isFinalized());
		}

		// check that the characters are correct
		char uniqueChars[] = {'h', 'e', 'l', 'o'};
		for(int idx = 0; idx < uniqueChars.length; idx += 1)
		{
			Assert.assertTrue(
				nodes.contains(new CountNode(uniqueChars[idx]))
			);
		}

		// check that the counts are correct
		for(CountNode node : nodes)
		{
			int correctCount = 1;
			if(node.getChar() == 'l')
			{
				correctCount = 2;
			}
			Assert.assertEquals(correctCount, node.getCount());
		}
	}

	@Test
	public void testCountNodesToHuffmanNodes()
	{
		// make a tree

		// start with leaf nodes
		CountNode leafNodes[] = {
			new CountNode('e', 1, true),
			new CountNode('h', 1, true),
			new CountNode('o', 1, true),
			new CountNode('l', 2, true)
		};
		TreeSet<Character> uniqueChars = new TreeSet<Character>(
			Arrays.asList(
				new Character[]{'e', 'h', 'o', 'l'}
			)
		);

		// join 'e' and 'h'
		CountNode innerNode1 = new CountNode('*', 2, true);
		innerNode1.setLeftChild(leafNodes[0]);
		innerNode1.setRightChild(leafNodes[1]);

		// join 'o' and 'l'
		CountNode innerNode2 = new CountNode('*', 3, true);
		innerNode2.setLeftChild(leafNodes[2]);
		innerNode2.setRightChild(leafNodes[3]);

		// join innerNode1 and innerNode 2
		CountNode root = new CountNode('*', 5, true);
		root.setLeftChild(innerNode1);
		root.setRightChild(innerNode2);

		// convert to huffman nodes
		HuffmanNode treeRoot = Encoder.countNodesToHuffmanNodes(root);

		// check the tree
		Queue<HuffmanNode> queue = new LinkedList<HuffmanNode>();
		queue.add(treeRoot);

		// check that bits are set for non-root nodes
		// and check that '*' is the char for inner nodes
		while(!queue.isEmpty())
		{
			HuffmanNode node = queue.poll();
			if(!node.isLeaf())
			{
				Assert.assertEquals('*', (char)node.getChar());
			} else
			{
				Assert.assertTrue(uniqueChars.contains(node.getChar()));
			}

			if(node != treeRoot)
			{
				Assert.assertTrue(node.getParentNode() != null);

				// check that the bit is set
				if(node == node.getParentNode().getLeftChild())
				{
					Assert.assertTrue(node.getBit());
				} else if(node == node.getParentNode().getRightChild())
				{
					Assert.assertFalse(node.getBit());
				} else
				{
					Assert.fail("shouldn't get here");
				}
			}
		}
	}

	@Test
	public void testBuildHuffmanTree()
	{
		// go throught the process of processing a string
		String inputString = "hello";
		// Reader r =  new StringReader(inputString);
		TreeSet<Character> uniqueChars = new TreeSet<Character>(
			Arrays.asList(
				new Character[]{'e', 'h', 'o', 'l'}
			)
		);

		List<CountNode> countNodes = Encoder.getUniqueCharacterCounts(inputString);// r);
		Assert.assertEquals(4, countNodes.size());

		// convert it to a HuffmanTree
		HuffmanTree tree = Encoder.buildHuffmanTree(countNodes);

		// check the tree
		HuffmanNode root = tree.getRoot();
		Assert.assertEquals(2, root.getChildren().size());

		// check the leaf nodes first
		List<HuffmanNode> leafNodes = tree.getLeafNodes();
		Assert.assertEquals(4, leafNodes.size());
		for(HuffmanNode leafNode : leafNodes)
		{
			Assert.assertTrue(leafNode.isLeaf());
			Assert.assertTrue(uniqueChars.contains(leafNode.getChar()));
		}

		// now check the structure
		HuffmanNode lNode = root.getLeftChild();
		Assert.assertNotEquals(null, lNode);
		Assert.assertEquals('l', (char)lNode.getChar());

		HuffmanNode rootRightChild = root.getRightChild();
		HuffmanNode oNode = rootRightChild.getLeftChild();
		Assert.assertNotEquals(null, rootRightChild);
		Assert.assertNotEquals(null, oNode);
		Assert.assertEquals('o', (char)oNode.getChar());

		HuffmanNode rootRightRightChild = rootRightChild.getRightChild();
		HuffmanNode eNode = rootRightRightChild.getLeftChild();
		HuffmanNode hNode = rootRightRightChild.getRightChild();
		Assert.assertNotEquals(null, rootRightRightChild);
		Assert.assertNotEquals(null, eNode);
		Assert.assertNotEquals(null, hNode);
		Assert.assertEquals('e', (char)eNode.getChar());
		Assert.assertEquals('h', (char)hNode.getChar());


		// finally check the encodings
		for(HuffmanNode leafNode : leafNodes)
		{
			String correctEncoding = null;
			switch(leafNode.getChar())
			{
				case 'l':
					correctEncoding = "0";
					break;
				case 'o':
					correctEncoding = "10";
					break;
				case 'e':
					correctEncoding = "110";
					break;
				case 'h':
					correctEncoding = "111";
					break;
				default:
					Assert.fail("shouldn't get here");
			}
			Assert.assertEquals(correctEncoding,
				leafNode.getCharacterEncoding());
		}
	}
}
