import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RandomizedQueueTest {
	private RandomizedQueue<Integer> subject;

	@BeforeEach
	public void setUp() {
		subject = new RandomizedQueue<>();
	}

	@Test
	public void enqueue_Null_ThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> subject.enqueue(null));
	}

	@Test
	public void sample_emptyQueue_ThrowsNSEE() {
		assertTrue(subject.isEmpty());

		assertThrows(NoSuchElementException.class, () -> subject.sample());
	}

	@Test
	public void dequeue_emptyQueue_ThrowsNSEE() {
		assertTrue(subject.isEmpty());

		assertThrows(NoSuchElementException.class, () -> subject.dequeue());
	}

	@Test
	public void iteratorNext_NoMoreItems_ThrowsNSEE() {
		assertTrue(subject.isEmpty());

		assertThrows(NoSuchElementException.class, () -> subject.iterator().next());
	}

	@Test
	public void isEmpty_emptyQueue_true() {
		assertTrue(subject.isEmpty());
	}

	@Test
	public void isEmpty_notEmptyQueue_false() {
		subject.enqueue(1);

		assertFalse(subject.isEmpty());
	}

	@Test
	public void size_emptyQueue_0() {
		assertEquals(0, subject.size());
	}

	@Test
	public void size_notEmptyQueue_2() {
		subject.enqueue(1);

		subject.enqueue(2);

		assertEquals(2, subject.size());
	}

	@Test
	public void enqueue_singleItem_OK() {
		subject.enqueue(1);

		Integer item = subject.sample();

		assertEquals(1, item);
	}

	@Test
	public void dequeue_singleItem_OK() {
		subject.enqueue(1);

		Integer dequeuedItem = subject.dequeue();

		assertEquals(1, dequeuedItem);

		assertTrue(subject.isEmpty());
	}

	@Test
	public void sample_OK() {
		subject.enqueue(1);

		Integer item = subject.sample();

		assertEquals(1, item);

		assertFalse(subject.isEmpty());
	}

	@Test
	public void iterator_notEmpty_OK() {
		for (int i = 1; i <= 10; i++) {
			subject.enqueue(i);
		}

		Iterator<Integer> iterator = subject.iterator();

		int iteratorCount = 0;

		while (iterator.hasNext()) {
			Integer next = iterator.next();

			System.out.println(next);

			iteratorCount++;
		}

		assertEquals(10, iteratorCount);
	}
}
