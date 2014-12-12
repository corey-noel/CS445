/**
 * AUTHORED BY COREY NOEL
 */

public class FrequencyBag<T> {
    Node<Pair<T, Integer>> firstNode;
    int numEntries, numUniqueEntries;

    public FrequencyBag() {
        firstNode = null;
        numEntries = 0;
        numUniqueEntries = 0;
    }

    /**
     * Adds new entry into this frequency bag.
     * @param aData the data to be added into this frequency bag.
     */
    public void add(T aData) {
        if (firstNode == null) {
            firstNode = new Node<Pair<T, Integer>>(null, new Pair<T, Integer>(aData, 1));
            numEntries++;
            numUniqueEntries++;
        } else {
            Node<Pair<T, Integer>> currentNode = firstNode;
            for (int i = 1; i <= numUniqueEntries; i++) {
                if (currentNode.get.first.equals(aData)) {
                    currentNode.get.second++;
                    numEntries++;
                    return;
                }
                currentNode = currentNode.next;
            }
            firstNode = new Node<Pair<T, Integer>>(firstNode, new Pair<T, Integer>(aData, 1));
            numEntries++;
            numUniqueEntries++;
        }
    }

    /**
     * Gets the number of occurrences of aData in this frequency bag.
     * @param aData the data to be checked for its number of occurrences.
     * @return the number of occurrences of aData in this frequency bag.
     */
    public int getFrequencyOf(T aData) {
        if (numUniqueEntries == 0)
            return 0;

        Node<Pair<T, Integer>> currentNode = firstNode;

        for (int i = 1; i <= numUniqueEntries; i++) {
            if (currentNode.get.first.equals(aData))
                return currentNode.get.second;

            if (currentNode.next == null)
                return 0;

            currentNode = currentNode.next;
        }

        return 0;
    }

    /**
     * Gets the maximum number of occurrences in this frequency bag.
     * @return the maximum number of occurrences of an entry in this
     * frequency bag.
     */
    public int getMaxFrequency() {
        if (numUniqueEntries == 0)
            return 0;

        Node<Pair<T, Integer>> currentNode = firstNode;
        int max = firstNode.get.second;

        for (int i = 1; i <= numUniqueEntries; i++) {
            if (currentNode.get.second > max)
                max = currentNode.get.second;

            if (currentNode.next == null)
                return max;

            currentNode = currentNode.next;
        }

        return max;
    }

    /**
     * Gets the probability of aData
     * @param aData the specific data to get its probability.
     * @return the probability of aData
     */
    public double getProbabilityOf(T aData) {
        return (double)getFrequencyOf(aData) / (double)numEntries;
    }

    /**
     * Empty this bag.
     */
    public void clear() {
        firstNode = null;
        numEntries = 0;
        numUniqueEntries = 0;
    }

    /**
     * Returns a string representation of the FrequencyBag
     * @return A String representation of the FrequencyBag
     */
    public String toString() {
        String result = "[";

        if (firstNode == null)
            return result += "]" + " 0 Entries";

        Node<Pair<T, Integer>> currentNode = firstNode;
        for (int i = 1; i <= numUniqueEntries; i++) {
            result += "(" + currentNode.get.first + ", " + currentNode.get.second + ")";
            currentNode = currentNode.next;
        }

        return result += "] " + numEntries + " entries (" + numUniqueEntries + " unique)" ;
    }

    /**
     * Gets the number of entries in this bag.
     * @return the number of entries in this bag.
     */
    public int size() {
        return numEntries;
    }

    private class Node<E> {
        private Node<E> next;
        private E get;

        private Node(Node<E> nextNode, E data) {
            next = nextNode;
            get = data;
        }
    }

    private class Pair<E, F> {
        private E first;
        private F second;

        private Pair(E fr, F sn) {
            first = fr;
            second = sn;
        }
    }
}