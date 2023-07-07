import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first;

    private Node<Item> last;

    private int size;

    public Deque() {

    }

    private class Node<T> {
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

    private class DequeIterator implements Iterator<Item> {
        private Node<Item> nextNode = first;

        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        @Override
        public Item next() {
            if (nextNode != null) {
                Node<Item> next = nextNode.getNext();

                Item current = nextNode.getItem();

                nextNode = next;

                return current;
            } else {
                throw new NoSuchElementException("Iterator is empty");
            }

        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Forbidden operation");
        }

    }

    public boolean isEmpty() {
        return first == null && last == null;
    }

    public int size() {
        return this.size;
    }

    public void addFirst(Item item) {
        checkArgumentNotNull(item);
        if (first == null) {
            first = new Node<>();

            first.setItem(item);

            first.setNext(last);

            if (last == null) {
                last = first;
            }
        } else {
            Node<Item> newFirst = new Node<>();

            newFirst.setItem(item);

            newFirst.setNext(first);

            first.setPrevious(newFirst);

            first = newFirst;
        }

        size++;

    }

    public void addLast(Item item) {
        checkArgumentNotNull(item);
        if (last == null) {
            last = new Node<>();

            last.setItem(item);

            last.setPrevious(first);

            if (first == null) {
                first = last;
            }
        } else {
            Node<Item> newLast = new Node<>();

            newLast.setItem(item);

            newLast.setPrevious(last);

            last.setNext(newLast);

            last = newLast;
        }

        size++;
    }

    public Item removeFirst() {
        throwExceptionIfIsEmpty();

        Node<Item> next = first.getNext();

        Item itemToRemove = first.getItem();

        first = next;

        if (next == null) {
            last = null;
        }

        if (first != null) {
            first.setPrevious(null);
        }

        size--;

        return itemToRemove;
    }

    public Item removeLast() {
        throwExceptionIfIsEmpty();

        Node<Item> previous = last.getPrevious();

        Item itemToRemove = last.getItem();

        last = previous;

        if (previous == null) {
            first = null;
        }

        if (last != null) {
            last.setNext(null);
        }

        size--;

        return itemToRemove;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();

        deque.addFirst("A");

        deque.addLast("B");

        deque.addFirst("C");

        deque.addLast("D");

        deque.removeFirst();

        deque.removeLast();

        int dequeSize = deque.size();

        System.out.println(dequeSize);

        boolean isEmpty = deque.isEmpty();

        System.out.println(isEmpty);

        Iterator<String> iterator = deque.iterator();

        while (iterator.hasNext()) {
            String next = iterator.next();

            System.out.println(next);

        }
    }

    private void checkArgumentNotNull(Item argument) {
        if (argument == null) {
            throw new IllegalArgumentException("Passed argument can`t be null");
        }
    }

    private void throwExceptionIfIsEmpty() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("Remove method can`t be called on empty deque");
        }
    }
}
