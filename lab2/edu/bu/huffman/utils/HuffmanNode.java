package edu.bu.huffman.utils;


// SYSTEM IMPORTS
import java.util.ArrayList;
import java.util.List;


// JAVA PROJECT IMPORTS


public class HuffmanNode
	extends Object
{
	private final Character character; // interior nodes will be null
	private Boolean isThisNodeLeftChildOfParent; // init null
	private String characterEncoding;
	private boolean isCharacterEncodingFinalized;

	private HuffmanNode leftChild;
	private HuffmanNode rightChild;
	private HuffmanNode parentNode;

	public HuffmanNode(Character c,
					   Boolean b)
	{
		this.character = c;
		this.isThisNodeLeftChildOfParent = b;
		this.leftChild = null;
		this.rightChild = null;
		this.parentNode = null;
		this.characterEncoding = null;
		this.isCharacterEncodingFinalized = false;
	}

	public HuffmanNode(Character c)
	{
		this(c, null);
	}

	public final Character getChar() { return this.character; }
	public final Boolean getBit() { return this.isThisNodeLeftChildOfParent; }
	public HuffmanNode getLeftChild() { return this.leftChild; }
	public HuffmanNode getRightChild() { return this.rightChild; }
	public HuffmanNode getParentNode() { return this.parentNode; }
	public final String getCharacterEncoding() { return this.characterEncoding; }
	public boolean isFinalized() { return this.isCharacterEncodingFinalized; }

	public List<HuffmanNode> getChildren()
	{
		List<HuffmanNode> children = new ArrayList<HuffmanNode>(2);
		if(this.getLeftChild() != null)
		{
			children.add(this.getLeftChild());
		}
		if(this.getRightChild() != null)
		{
			children.add(this.getRightChild());
		}
		return children;
	}

	private void setBit(boolean b) { this.isThisNodeLeftChildOfParent = b; }
	private void setLeftChild(HuffmanNode n) { this.leftChild = n; }
	private void setRightChild(HuffmanNode n) { this.rightChild = n; }
	private void setParentNode(HuffmanNode n) { this.parentNode = n; }
	public void setCharacterEncoding(String s)
	{
		if(!this.isFinalized())
		{
			this.characterEncoding = s;
		}
	}
	private void setIsCharacterEncodingFinalized(boolean b) { this.isCharacterEncodingFinalized = b; }

	public void markFinalized()
	{
		this.setIsCharacterEncodingFinalized(true);
	}

	public void addLeftChild(HuffmanNode n)
	{
		n.setParentNode(this);
		this.setLeftChild(n);
		n.setBit(true);
	}

	public void addRightChild(HuffmanNode n)
	{
		n.setParentNode(this);
		this.setRightChild(n);
		n.setBit(false);
	}

	public boolean isLeaf()
	{
		return this.getLeftChild() == null &&
			this.getRightChild() == null;
	}
}