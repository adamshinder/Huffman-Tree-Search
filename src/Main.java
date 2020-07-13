import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.soap.Node;

public class Main {
	private final String FILENAME = "preamble.txt";
	ArrayList<HuffmanNode> priorityQueue = new ArrayList<>();

	public Main() {
		String rawText = loadFile(FILENAME);
		System.out.println("Original String: ");
		System.out.println(rawText);
		saveFile(FILENAME, rawText);
		int freq[] = new int[256];

		// int inOrder = 0 ;

		for (int i = 0; i < rawText.length(); i++) {
			char c = rawText.charAt(i); // 'A'
			int asciValue = (int) c; // 65
			freq[asciValue]++; // 0,0,0,0,....., 1,
		}

		// sort the Queue in order
		for (int i = 0; i < freq.length; i++) {
			if (freq[i] > 0); // if it has anything in cell i, (char)(i) must have occurred
			{
				// instantiate a new Node whose character is (char)i, whose freq is freq[i]
				HuffmanNode t = new HuffmanNode((char) i, freq[i]);
				// insert it into the Queue
				insertIntoQueue(t); // connects the node "t" into the insertIntoQueue
			}

		}
		System.out.println(priorityQueue);
		ArrayList<HuffmanNode> COPY = new ArrayList<>();
		COPY.addAll(priorityQueue); // before it gets messed up
		makeTree(priorityQueue);
		printlevelOrder(priorityQueue.get(0), "");
		String ret = Encode(rawText, COPY);
		saveFile("OUTPUT", ret);

	}

	public void insertIntoQueue(HuffmanNode node) {
		// add node to the list such that the list is in order by frequency
		// priorityQueue.add (node); //node is the item
		int i = 0;
		for (i = 0; i < priorityQueue.size(); i++) {
			if (priorityQueue.get(i).freq > node.freq) // a dot represents 's
				break;
		}
		if (node.freq != 0) // == for comparison - = is to set a value
		{
			priorityQueue.add(i, node); // node is the item--> list.add (index,item)
		}
	}

	public void makeTree(ArrayList<HuffmanNode> priorityQueue) {
		HuffmanNode node1 = priorityQueue.remove(0);// a node was taken out of queue, it was set as node1
		HuffmanNode node2 = priorityQueue.remove(0);
		HuffmanNode root = new HuffmanNode(node1.freq + node2.freq);
		root.left = node1;
		root.right = node2;
		insertIntoQueue(root);
		if (priorityQueue.size() > 1) {
			makeTree(priorityQueue);
		}
		// System.out.println("Root: ") ;
		// System.out.println(root);
	}

	public void printlevelOrder(HuffmanNode node, String d) {
		// if there is no child - a leaf -, stop
		// go left, record a 0
		// if no left, go back check for right
		// go right, record a 1
		// if there is no child, print trail current node
		// printlevelOrder (HuffmanNode node, String d)

		if (node.left == null && node.right == null) // if its a leaf
		{
			System.out.println(node.c + ":" + d);
			node.encoded = d;
			return;
		}
		printlevelOrder(node.left, d + "0");
		printlevelOrder(node.right, d + "1");
	}

	public String Encode(String r, ArrayList<HuffmanNode> COPY) {
		String e = "";

		for (int i = 0; i < r.length(); i++) {
			char k = r.charAt(i);
			for (int y = 0; y < COPY.size(); y++) {
				if (k == COPY.get(y).c) {
					e += (COPY.get(y).encoded); // the second dot refers to a variable tied to a node
				}
			}
		}
		System.out.print(e);
		return e;
	}

	/*
	 * 
	 * 
	 * } if (root == null) return; if (level == 1) System.out.println(root.data);
	 * else if (level > 1) { printlevelOrder(root.left);
	 * //printGivenLevel(root.left, level-1); printlevelOrder(root.right, level-1);
	 * 
	 * 
	 * }
	 */

	private String loadFile(String fName) {
		String ret = "";

		try {
			Scanner fileReader = new Scanner(new File(fName));

			while (fileReader.hasNextLine())
				ret += fileReader.nextLine();

			fileReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	private void saveFile(String fName, String contents) {
		try {
			PrintWriter out = new PrintWriter("output_" + fName);
			out.println(contents);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new Main();
	}

}