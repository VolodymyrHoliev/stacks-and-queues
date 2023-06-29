import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class DequeTest {
	private Deque<String> subject;

	private DequeFieldExtractor fieldExtractor = new DequeFieldExtractor();

	@Before
	public void init() {
		subject = new Deque<String>();
	}

	@Test
	public void addFirst_Null_ThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> subject.addFirst(null));
	}

	@Test
	public void addFirst_OK() {
		String expected = "A";

		subject.addFirst(expected);

		String value = fieldExtractor.getFirstItem(subject);

		assertEquals(expected, value);
	}

	@Test
	public void addFirst_MultipleCalls_OK() {
		String lastAddedString = "B";

		subject.addFirst("A");

		subject.addFirst("B");

		subject.addFirst(lastAddedString);

		String value = fieldExtractor.getFirstItem(subject);

		assertEquals(lastAddedString, value);

	}

	@Test
	public void addLast_Null_ThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> subject.addLast(null));
	}

	@Test
	public void addLast_OK() {
		String expected = "A";

		subject.addLast(expected);

		String value = fieldExtractor.getLastItem(subject);

		assertEquals(expected, value);
	}

	@Test
	public void addLast_MultipleCalls_OK() {
		String lastAddedString = "B";

		subject.addLast("A");

		subject.addLast("B");

		subject.addLast(lastAddedString);

		String value = fieldExtractor.getLastItem(subject);

		assertEquals(lastAddedString, value);

	}

	@Test
	public void addFirstAddLast_MultipleCallsMixed_OK() {
		String firstAddedString = "A";

		String lastAddedString = "B";

		subject.addFirst("0");

		subject.addLast("C");

		subject.addFirst(firstAddedString);

		subject.addLast(lastAddedString);

		String firstString = fieldExtractor.getFirstItem(subject);

		String lastString = fieldExtractor.getLastItem(subject);

		assertEquals(firstAddedString, firstString);

		assertEquals(lastAddedString, lastString);
	}

	@Test
	public void removeFirst_EmptyDeque_ThrowsNSEE() {
		assertThrows(NoSuchElementException.class, () -> subject.removeFirst());
	}

	@Test
	public void removeFirst_NotEmptyDeque_OK() {
		subject.addFirst("A");

		subject.addLast("B");

		subject.addFirst("C");

		String removedElement = subject.removeFirst();

		assertEquals("C", removedElement);

		String firstItem = fieldExtractor.getFirstItem(subject);

		assertEquals("A", firstItem);

		String lastItem = fieldExtractor.getLastItem(subject);

		assertEquals("B", lastItem);
	}

	@Test
	public void removeLast_EmptyDeque_ThrowsNSEE() {
		assertThrows(NoSuchElementException.class, () -> subject.removeLast());
	}

	@Test
	public void removeLast_NotEmptyDeque_OK() {
		subject.addLast("A");

		subject.addLast("B");

		subject.addFirst("C");

		String removedElement = subject.removeLast();

		assertEquals("B", removedElement);

		String firstItem = fieldExtractor.getFirstItem(subject);

		assertEquals("C", firstItem);

		String lastItem = fieldExtractor.getLastItem(subject);

		assertEquals("A", lastItem);
	}

	@Test
	public void iterator_emptyDeque_hasNext_False() {
		boolean hasNext = subject.iterator().hasNext();

		assertFalse(hasNext);
	}

	@Test
	public void iterator_hasNext_notEmptyDeque_True() {
		subject.addFirst("A");

		boolean hasNext = subject.iterator().hasNext();

		assertTrue(hasNext);
	}

	@Test
	public void iterator_next_emptyDeque_null() {
		Object next = subject.iterator().next();

		assertNull(next);
	}

	@Test
	public void iterator_next_notEmptyDeque_OK() {
		subject.addFirst("A");

		subject.addFirst("B");

		subject.addFirst("C");

		StringBuilder stringBuilder = new StringBuilder();

		Iterator<String> iterator = subject.iterator();

		while (iterator.hasNext()) {
			String next = (String) iterator.next();

			stringBuilder.append(next);
		}

		assertEquals("CBA", stringBuilder.toString());

	}

	@Test
	public void iterator_remove_UOE() {
		assertThrows(UnsupportedOperationException.class, () -> subject.iterator().remove());
	}
	
	@Test
	public void main_OK() {
		assertDoesNotThrow(()-> Deque.main(null));
	}

}
