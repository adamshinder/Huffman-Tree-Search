public class HuffmanNode
{
	char c = '*';
	int freq = 0;
	String encoded = ""; 
	HuffmanNode left = null, right = null;
	
	public HuffmanNode(char c, int freq)
	{
		this.c = c;
		this.freq = freq;
	}
	
	
	public HuffmanNode(int freq)
	{
		this.freq = freq;
	}
	
	public String toString()
	{
		return "("+c+","+freq+")";
	}

	
	
}