package edu.bu.huffman.utils;


// SYSTEM IMPORTS
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;


// JAVA PROJECT IMPORTS
import edu.bu.huffman.utils.EncodingPair;
import edu.bu.huffman.utils.HuffmanNode;


public class HuffmanTree
	extends Object
{

	private HuffmanNode root;

	public HuffmanTree(HuffmanNode n)
	{
		this.root = n;
	}

	// getting the root is public for testing purposes
	// but marked final so you can't change it
	public final HuffmanNode getRoot() { return this.root; }

	public void makeEncodings()
	{
		// bits were already set when the nodes were created
		// (i.e. in HuffmanNode.addLeftChild() &&
		//  HuffmanNode.addRightChild()) so we don't need to
		// worry about setting them. Instead, we need to
		// calculate the encoding for each leaf node (i.e. char
		// in our corpus). The encoding for a character is
		// calculated from the path it takes to get to that node
		// from the root. We basically glue the bits together
		// to get a binary representation for that char.
		// If we calculate the path by starting at a leaf node
		// and using the parent link to go up to the root,
		// we will be enumerating the bits in reverse order,
		// so don't forget to reverse the encoding at the end!
		List<HuffmanNode> leafNodes = this.getLeafNodes();

		// for every leaf node, calculate its encoding
		for(HuffmanNode leafNode : leafNodes)
		{
			// a StringBuilder is how we programatticaly construct
			// a String in Java. This reduces the amount of memory
			// required. String concatination produces a new
			// String every time the concatination occurs.
			// Think of a StringBuilder as a LinkedList
			// of string "parts" that will eventually be assembled
			// together into one massive String while using
			// less memory
			StringBuilder builder = new StringBuilder();

			// walk backwards to the root of the tree
			HuffmanNode currentNode = leafNode;
			while(currentNode != this.getRoot())
			{
				builder.append(currentNode.getBit() ? 0 : 1);
				currentNode = currentNode.getParentNode();
			}

			leafNode.setCharacterEncoding(builder.reverse()
				.toString());
			leafNode.markFinalized(); // done! Don't let us
									  // accidentally change the
									  // encoding somehow!
		}
	}

	public final List<HuffmanNode> getLeafNodes()
	{
		List<HuffmanNode> leafNodes = new LinkedList<HuffmanNode>();

		// rather than be recursive, do this iteratively
		Queue<HuffmanNode> queue = new LinkedList<HuffmanNode>();
		queue.add(this.getRoot());

		while(!queue.isEmpty())
		{
			HuffmanNode node = queue.poll();
			if(node.isLeaf())
			{
				leafNodes.add(node);
			} else
			{
				queue.addAll(node.getChildren());
			}
		}

		return leafNodes;
	}

	public List<EncodingPair> getAllEncodings()
	{
		List<HuffmanNode> leafNodes = this.getLeafNodes();
		List<EncodingPair> encodings = new ArrayList<EncodingPair>(leafNodes.size());

		// for every leaf node, calculate its encoding
		for(HuffmanNode leafNode : leafNodes)
		{
			encodings.add(
				new EncodingPair(
					leafNode.getChar(),
					leafNode.getCharacterEncoding()
				)
			);
		}
		
		return encodings;
	}
}

