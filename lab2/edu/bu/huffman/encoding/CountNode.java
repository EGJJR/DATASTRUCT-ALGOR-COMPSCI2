package edu.bu.huffman.encoding;


public class CountNode
	extends Object
	implements Comparable<CountNode>
{
	// the "final" keyword in Java is used to specify
	// that this value cannot change once it is assigned
	// (and also MUST be assigned in the constructor(s))
	// in other languages this would be called "const"
	private final char character;
	// one important difference between marking something
	// as "final" (as opposed to not providing a setter for it)
	// is that not even inside the class can we change this
	// value. The compiler won't let us assign to this value
	// outside of the constructor, and even then can only be
	// assigned once (i.e. initialized) in the constructor.
	// we will assign '*' to the character of interior nodes


	// the frequency that the character appears in the text
	// that we are encoding.
	// While we technically need to store frequencies,
	// we're going to be comparing frequencies to each other
	// and it will be totally fine to use the count instead of
	// the frequency since they all share the same denominator value
	// (the number of characters in the text we're encoding)
	// just a small hack to save some space (double takes more space than int)
	private int characterCount;


	// this will be a flag soley for safety.
	// Once we finish counting frequencies, we don't want
	// to accidentally adjust them. This will initially be False
	// but when we decide that we're done, we can set this to True
	// and then not be allowed to adjust the frequencies any more.
	private boolean isFinalized;


	// the left and right children of this node
	private CountNode leftChild;
	private CountNode rightChild;

	public CountNode(char c,
					 int initCount,
					 boolean isFinalized)
	{
		this.character = c;
		this.characterCount = initCount;
		this.isFinalized = isFinalized;
		this.leftChild = null;
		this.rightChild = null;
	}

	public CountNode(char c)
	{
		this(c, 0, false);
	}

	public final char getChar() { return this.character; }
	// no setter for this.character b/c it is "final"
	public final boolean isFinalized() { return this.isFinalized; }
	public int getCount() { return this.characterCount; }
	public CountNode getLeftChild() { return this.leftChild; }
	public CountNode getRightChild() { return this.rightChild; }

	private void setCount(int i) { this.characterCount = i; }
	private void setIsFinalized(boolean b) { this.isFinalized = b; }
	public void setLeftChild(CountNode c) { this.leftChild = c; }
	public void setRightChild(CountNode c) { this.rightChild = c; }

	public void markFinalized() { this.setIsFinalized(true); }

	// counts should only be incremented one at a time,
	// and only if the pair is not finalized
	public void incrementCount()
	{
		if(!this.isFinalized())
		{
			this.setCount(this.getCount() + 1);
		}
	}

	// if for some reason we want to get the actual frequency
	public double getFrequency(double total)
	{
		return ((double)this.getCount()) / total;
	}

	@Override
	public String toString() { return "(" + this.getChar() + ": " + this.getCount() + ")"; }

	// order CountNode objects according to their counts
	@Override
	public int compareTo(CountNode other)
	{
		// compare using the character
		return Character.compare(this.getChar(), other.getChar());
	}

	@Override
	public boolean equals(Object other) {
    if (this == other) {
        return true;
    }

    if (!(other instanceof CountNode)) {
        return false;
    }

    CountNode otherNode = (CountNode) other; // Cast 'other' to CountNode

    boolean isEqual = false;
    // Compare attributes here
    isEqual = this.getChar() == otherNode.getChar();
    // Add more comparisons if needed

    return isEqual;
}
}