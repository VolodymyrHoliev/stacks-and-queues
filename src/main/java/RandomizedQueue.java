import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] items;

	private int itemsCount = 0;

	public RandomizedQueue() {
		items = createArray(10);
	}

	public RandomizedQueue(int capacity) {
		items = createArray(capacity);
	}

	private class RandomizedQueueIterator implements Iterator<Item> {
		private Item[] iteratorItems = createArray(itemsCount);

		private int iteratorLastIndex = 0;

		private RandomizedQueueIterator() {
			for (int i = 0; i < itemsCount; i++) {
				Item item = items[i];

				if (item != null) {
					iteratorItems[i] = item;
				}
			}

			iteratorLastIndex = iteratorItems.length - 1;

		}

		@Override
		public boolean hasNext() {
			return iteratorLastIndex >= 0;
		}

		@Override
		public Item next() {
			if (!this.hasNext()) {
				throw new NoSuchElementException("No more items in queue");
			}
			Item nextItem = null;

			int nextItemIndex = 0;

			if (iteratorLastIndex != 0) {
				nextItemIndex = getRandomIndex(iteratorLastIndex + 1);
				
				nextItem = iteratorItems[nextItemIndex];

				iteratorItems[nextItemIndex] = iteratorItems[iteratorLastIndex];

				iteratorItems[iteratorLastIndex] = null;

			} else {
				nextItem = iteratorItems[0];
			}

			iteratorLastIndex--;

			return nextItem;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("remove() is unsupported");
		}
	}

	public boolean isEmpty() {
		return itemsCount == 0;
	}

	public int size() {
		return itemsCount;
	}

	public void enqueue(Item item) {
		checkItemNotNull(item);

		if (itemsCount == items.length) {
			resize(items.length * 2);
		}

		items[itemsCount] = item;

		itemsCount++;
	}

	public Item dequeue() {
		checkItemsNotEmpty();

		if (itemsCount == items.length / 4) {
			resize(items.length / 2);
		}

		int randomIndex = getRandomIndex(itemsCount);

		Item randomItem = items[randomIndex];

		items[randomIndex] = items[itemsCount - 1];
		
		items[itemsCount - 1] = null;

		itemsCount--;

		return randomItem;
	}

	public Item sample() {
		checkItemsNotEmpty();

		int randomIndex = getRandomIndex(itemsCount);

		return items[randomIndex];
	}

	@Override
	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator();
	}

	private void checkItemNotNull(Item item) {
		if (item == null) {
			throw new IllegalArgumentException("Can't add null to the queue");
		}
	}

	@SuppressWarnings("unchecked")
	private Item[] createArray(int size) {
		return (Item[]) new Object[size];
	}

	private void resize(int capacity) {
		Item[] newArray = createArray(capacity);

		int index = 0;

		for (Item item : items) {
			if (item != null) {
				newArray[index] = item;

				index++;
			}
		}

		items = newArray;
	}

	private int getRandomIndex(int topLimit) {
		return StdRandom.uniformInt(topLimit);
	}

	private void checkItemsNotEmpty() {
		if (itemsCount == 0) {
			throw new NoSuchElementException("Queue is empty");
		}
	}

	public static void main(String[] args) {
		RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();

		System.out.println("Created RandomizedQueue instance using no args constructor");

		randomizedQueue.enqueue(1);

		randomizedQueue.enqueue(2);

		System.out.println("Added 2 items");

		Integer item = randomizedQueue.sample();

		System.out.println("Sampled item : " + item);

		Integer dequeuedItem = randomizedQueue.dequeue();

		System.out.println("Dequeued item : " + dequeuedItem);

		randomizedQueue = new RandomizedQueue<>(10);

		System.out.println("Created RandomizedQueue instance using constructor with capacity argument");

		for (int i = 1; i <= 10; i++) {
			randomizedQueue.enqueue(i);

			System.out.println("Equeued " + i);
		}

		System.out.println("isEmpty = " + randomizedQueue.isEmpty());

		System.out.println("size = " + randomizedQueue.size());

		Iterator<Integer> iterator = randomizedQueue.iterator();

		System.out.println("Launching iterator");

		while (iterator.hasNext()) {
			Integer next = iterator.next();

			System.out.println("Next item : " + next);
		}
	}

}
