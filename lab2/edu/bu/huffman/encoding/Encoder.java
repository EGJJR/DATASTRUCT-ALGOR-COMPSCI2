package edu.bu.huffman.encoding;


// SYSTEM IMPORTS
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet; // balanced binary search tree in Java


// JAVA PROJECT IMPORTS
import edu.bu.huffman.encoding.CountNode;
import edu.bu.huffman.utils.EncodingPair;
import edu.bu.huffman.utils.HuffmanNode;
import edu.bu.huffman.utils.HuffmanTree;


public class Encoder
	extends Object
{
	public static final String USAGE_MSG = "usage: java edu.bu.huffman.encoding.Encoder <input> -f\n" +
		"Required Args:\n" +
		"\t<input> the input to the program (a string)\n" +
		"Positional Args:\n" +
		"\t-f: if -f is specified, then <input> will be treated as a filepath" +
		" (the contents of which will be encoded),\n" +
		"\t    otherwise <input> will be treated as the string to encode";


	public static List<CountNode> getUniqueCharacterCounts(String inputString)
	{

		// ***************************************************
		// 			STAGE 1: Getting Unique Characters
		// ***************************************************
		// need to collect unique characters
		// since we don't know many data structures
		// this will be much slower than normal
		// but thats ok!
		// In the future, come back here and see if you can optimize
		// this by using more advanced data structures!
		Set<Character> uniqueChars = new TreeSet<Character>();
		for(int idx = 0; idx < inputString.length(); idx += 1)
		{
			uniqueChars.add(inputString.charAt(idx));
		}

		// ***************************************************
		// 			STAGE 2: Counting Frequencies
		// ***************************************************
		// now count number of occurances for each character

		// the list storing the counts
		List<CountNode> nodes = new ArrayList<CountNode>(uniqueChars.size());
		// here is a way to use an iterator within a for loop
		for(Iterator<Character> it = uniqueChars.iterator();
			it.hasNext();)
		{
			// create a CountNode for each character
			nodes.add(new CountNode(it.next()));
		}

		// repeat the loop from earlier
		for(int idx = 0; idx < inputString.length(); idx += 1)
		{
			int indexInList = nodes.indexOf(new CountNode(inputString.charAt(idx)));
			nodes.get(indexInList).incrementCount();
		}

		// for safety mark each pair as finalized
		// so we cannot change the count anymore
		for(CountNode node : nodes)
		{
			node.markFinalized();
		}

		return nodes;
	}

    /**
        TODO: complete me for lab2!
     */
	public static HuffmanNode countNodesToHuffmanNodes(CountNode root)
	{
		// do this recursively since the number of characters is finitely small
		HuffmanNode node = new HuffmanNode(root.getChar());

		return node;
	}


	public static HuffmanTree buildHuffmanTree(List<CountNode> nodes)
	{
		// ***************************************************
		// 			STAGE 1: Copy elements to new List
		// ***************************************************
		// since we are passing a shallow copy of the nodes
		// in as an argument, it is generally rude if your method
		// destroys that shallow copy. We don't know what the user
		// wants to do with their list, so don't mess with it!
		List<CountNode> orderedNodes = new LinkedList<CountNode>();
		for(CountNode n : nodes)
		{
			orderedNodes.add(n);
		}


		// ***************************************************
		// 			STAGE 2: Build Interior CountNodes
		// ***************************************************
		// For this algorithm we want to order nodes based
		// on their counts (and settle ties with the character value
		// if needed) so we need to make a custom comparator
		// since the CountNode (although it is comparable)
		// does not have comparison logic that uses the counts
		Comparator<CountNode> countComp = new Comparator<CountNode>()
		{
			@Override
			public int compare(CountNode a, CountNode b)
			{
				// return -1 if a < b
				// return  0 if a == b
				// return +1 if a > b

				// compare using counts and use chars to settle ties
				int order = Integer.compare(a.getCount(), b.getCount());
				if(order == 0)
				{
					order = Character.compare(a.getChar(), b.getChar());
				}
				return order;
			}
		};
		while(orderedNodes.size() > 1)
		{
			// sort the list by counts (adding 
			Collections.sort(orderedNodes, countComp);

			// create a parent node for the first 2 nodes
			// in the list
			CountNode newNode = new CountNode(
				'*',
				orderedNodes.get(0).getCount() +
					orderedNodes.get(1).getCount(),
				true
			);

			// will remove the first 2 elements from the list
			// can double this up with setting the children of
			// the new node (List.remove returns the removed element)
			newNode.setLeftChild(orderedNodes.remove(0));
			newNode.setRightChild(orderedNodes.remove(0)); // still remove(0)
														   // b/c of previous remove(0) call

			// add the new node to the list
			orderedNodes.add(newNode);
		}

		// ***************************************************
		// 			STAGE 3: Convert to HuffmanNodes
		// ***************************************************
		HuffmanNode rootNode = Encoder.countNodesToHuffmanNodes(orderedNodes.get(0));
		// System.out.println(rootNode.getChar());

		// ***************************************************
		// 			STAGE 4: Make a Valid HuffmanTree
		// ***************************************************
		HuffmanTree tree = new HuffmanTree(rootNode);
		tree.makeEncodings();

		return tree;
	}

	public static void main(String[] args) {
        if (args.length < 1 || args.length > 2) {
            System.out.println(Encoder.USAGE_MSG);
            System.exit(-1);
        }

        String input = args[0];
        boolean isInputAFile = false;

        if (args.length == 2 && args[1].equals("-f")) {
            isInputAFile = true;
        } else if (args.length != 1) {
            System.out.println(Encoder.USAGE_MSG);
            System.exit(-1);
        }

		// ***************************************************
		// 			STAGE 2: Deal with Input
		// ***************************************************
		String inputString = input;
		if(isInputAFile)
		{
			// read the contents of the file into a String
			try
			{
				Reader r = new FileReader(new File(input));

				StringBuilder fileContents = new StringBuilder();
				int character = -1;
				do
				{
					try
					{
						character = r.read();

						// Reader.read() returns -1 when out of characters
						if(character != -1)
						{
							fileContents.append((char)character);
						}
					} catch(IOException e)
					{
						e.printStackTrace();
						System.exit(-1);
					}
				} while(character != -1);

				inputString = fileContents.toString();
			} catch(FileNotFoundException e)
			{
				System.out.println("file: " + input +
					" could not be found!");
				System.exit(-1);
			}
		}
		

		// ***************************************************
		// 			STAGE 3: Create a HuffmanTree
		// ***************************************************
		HuffmanTree tree = Encoder.buildHuffmanTree(
                Encoder.getUniqueCharacterCounts(inputString)
        );


		// ***************************************************
		// 			STAGE 3: Get Huffman Encodings
		// ***************************************************
		// tree should be fully formed, so get encodings
		List<EncodingPair> charEncodings = tree.getAllEncodings();


		// ***************************************************
		// 			STAGE 4: Apply Encodings to input
		// ***************************************************
		// encode the input
		// this is slower than necessary, with better
		// data structures we can speed this up
		StringBuilder output = new StringBuilder();
		// TODO: complete me for lab2!


		// ***************************************************
		// 			STAGE 5: Deal with Encoded input
		// ***************************************************
		// we could write this to a file or something
		System.out.println(output.toString());
	}
}

