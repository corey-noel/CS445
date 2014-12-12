import java.util.NoSuchElementException;


public class SListIterator<T> {
	private Node firstNode;
	private int numberOfEntries;
	
	public SListIterator() {
		firstNode = null;
		numberOfEntries = 0;
	}
	
	public void addToFirst(T aData) {
		firstNode = new Node(aData, firstNode);
		numberOfEntries++;
	}
	
	public T getEntry(int givenPosition) {
		T result = null;
		
		if((givenPosition >= 1) && (givenPosition <= numberOfEntries))
		{
			result = (getNodeAt(givenPosition)).data;
		}
		
		return result;
	}
	
	private Node getNodeAt(int givenPosition) {
		Node currentNode = firstNode;
		
		for(int counter = 1; counter < givenPosition; counter++)
		{
			currentNode = currentNode.next;
		}
		
		return currentNode;
	}
	
	public Iterator<T> getIterator() {
		return new IteratorForSList();
	}
	
	private class IteratorForSList implements Iterator<T> {
        private Node nextNode;
		
		private IteratorForSList() {
            nextNode = firstNode;
		}
		
		public boolean hasNext() {
			return nextNode != null;
		}
		
		public T next() {
            Node temp = nextNode;
			nextNode = nextNode.next;
            return temp.data;
		}
		
		public T remove()
		{
			throw new UnsupportedOperationException("remove() is not supported by this iterator");
		}
	}
	
	private class Node {
		private T data;
		private Node next;
		
		private Node(T aData, Node nextNode) {
			data = aData;
			next = nextNode;
		}
	}
}
