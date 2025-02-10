package edu.bu.huffman.utils;


public class EncodingPair
	extends Object
	implements Comparable<EncodingPair>
{
	private final char character;
	private final String characterEncoding;

	public EncodingPair(char c,
					    String encoding)
	{
		this.character = c;
		this.characterEncoding = encoding;
	}

	public final char getChar() { return this.character; }
	public final String getEncoding() { return this.characterEncoding; }

	@Override
	public String toString() { return "(" + this.getChar() + ": " + this.getEncoding() + ")"; }

	// order EncodingPair objects according to their counts
	@Override
	public int compareTo(EncodingPair other)
	{
		// return -1 if this < other
		// return  0 if this == other
		// return +1 if this > other
		return Character.compare(this.getChar(), other.getChar());
	}

	@Override
	public boolean equals(Object other)
	{
		boolean isEqual = false;
		if(other instanceof EncodingPair)
		{
			isEqual = this.getChar() == ((EncodingPair)other).getChar();
		}
		return isEqual;
	}
}

