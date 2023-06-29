
final class Node<Item> {
	private Item item;

	private Node<Item> next;

	private Node<Item> previous;

	Item getItem() {
		return this.item;
	}

	Node<Item> getNext() {
		return this.next;
	}

	Node<Item> getPrevious() {
		return this.previous;
	}

	void setPrevious(Node<Item> previous) {
		this.previous = previous;
	}

	void setItem(Item item) {
		this.item = item;
	}

	void setNext(Node<Item> next) {
		this.next = next;
	}

}
