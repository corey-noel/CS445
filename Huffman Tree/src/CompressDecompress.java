
public class CompressDecompress {
	/**
	 * Get a string representing a Huffman tree where its root node is root
	 * @param root the root node of a Huffman tree
	 * @return a string representing a Huffman tree
	 */
	public static String getTreeString(final BinaryNodeInterface<Character> root) {
		String result = "";
        if (root == null)
            return result;

        if (root.hasLeftChild() || root.hasRightChild()) {
            result += "I";

            if (root.hasLeftChild())
                result += getTreeString(root.getLeftChild());

            if (root.hasRightChild())
                result += getTreeString(root.getRightChild());
        } else if (root.getData() != null)
            result += "L" + root.getData();

		return result;
	}

	/**
	 * Compress the message using Huffman tree represented by treeString
	 * @param root the root node of a Huffman tree
	 * @param message the message to be compressed
	 * @return a string representing compressed message.
	 */
	public static String compress(final BinaryNodeInterface<Character> root, final String message) {
		String compressed = "";

        for (char c : message.toCharArray())
            compressed += getPathTo(root, c);

		return compressed;
	}
	
	/**
	 * Decompress the message using Huffman tree represented by treeString
	 * @param treeString the string represents the Huffman tree of the
	 * compressed message
	 * @param message the compressed message to be decompressed
	 * @return a string representing decompressed message
	 */
	public static String decompress(final String treeString, final String message) {
		BinaryNodeInterface<Character> root = getStringTree(treeString);
        BinaryNodeInterface<Character> currentNode = root;
        String result = "";

        for (char c : message.toCharArray()) {
            if (c == '0')
                currentNode = currentNode.getLeftChild();
            else
                currentNode = currentNode.getRightChild();

            if (currentNode.isLeaf()) {
                result += currentNode.getData();
                currentNode = root;
            }
        }

		return result;
	}

    private static String getPathTo(BinaryNodeInterface<Character> root, char c) {
        if (root.isLeaf() && root.getData() == c)
            return "";

        String result;
        if (root.hasLeftChild()) {
            result = getPathTo(root.getLeftChild(), c);
            if (!result.equals("-"))
                return "0" + result;
        }

        if (root.hasRightChild()) {
            result = getPathTo(root.getRightChild(), c);
            if (!result.equals("-"))
                return "1" + result;
        }

        return "-";
    }

    private static BinaryNodeInterface<Character> getStringTree(String tree) {
        if (tree.length() == 0)
            return new BinaryNode<Character>();

        BinaryNodeInterface<Character> root = new BinaryNode<Character>();

        if (tree.charAt(0) == 'L') {
            root.setData(tree.charAt(1));
            return root;
        }

        tree = tree.substring(1);
        root.setLeftChild(getStringTree(tree));

        int leftNodes = root.getLeftChild().getNumberOfNodes();
        for (int i = 0; i < leftNodes; i++) {
            if (tree.charAt(0) == 'L')
                tree = tree.substring(2);
            else
                tree = tree.substring(1);
        }

        root.setRightChild(getStringTree(tree));

        return root;
    }
}