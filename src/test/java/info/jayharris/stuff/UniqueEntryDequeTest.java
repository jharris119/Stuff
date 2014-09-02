package info.jayharris.stuff;

import info.jayharris.stuff.UniqueEntryDeque;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class UniqueEntryDequeTest {

    UniqueEntryDeque<String> test;

    @Before
    public void setUp() {
        test = new UniqueEntryDeque<>();
        test.add("first");
        test.add("second");
        test.add("third");
    }

    @Test
    public void testAdd() throws Exception {
        assertTrue(test.add("fourth"));

        ArrayDeque<String> expected = new ArrayDeque<String>() {{
            this.add("first");
            this.add("second");
            this.add("third");
            this.add("fourth");
        }};
        assertTrue(test.equals(expected));

        assertFalse(test.add("second"));

        assertTrue(test.equals(expected));
    }

    @Test
    public void testAddFirst() throws Exception {
        test.addFirst("first");

        ArrayDeque<String> expected = new ArrayDeque<String>() {{
            this.add("first");
            this.add("second");
            this.add("third");
        }};
        assertTrue(test.equals(expected));

        test.addFirst("zeroth");
        expected.addFirst("zeroth");
        assertTrue(test.equals(expected));
    }

    @Test
    public void testAddLast() throws Exception {
        test.addLast("third");

        ArrayDeque<String> expected = new ArrayDeque<String>() {{
            this.add("first");
            this.add("second");
            this.add("third");
        }};
        assertTrue(test.equals(expected));

        test.addLast("fourth");
        expected.addLast("fourth");
        assertTrue(test.equals(expected));
    }

    @Test
    public void testPush() throws Exception {
        test.push("first");

        ArrayDeque<String> expected = new ArrayDeque<String>() {{
            this.add("first");
            this.add("second");
            this.add("third");
        }};
        assertTrue(test.equals(expected));

        test.push("zeroth");
        expected.addFirst("zeroth");
        assertTrue(test.equals(expected));
    }

    @Test
    public void testAddAll() throws Exception {
        assertTrue(test.addAll(new ArrayList<String>() {{
            this.add("first");
            this.add("fourth");
            this.add("fifth");
            this.add("third");
            this.add("sixth");
        }}));

        ArrayDeque<String> expected = new ArrayDeque<String>() {{
            this.add("first");
            this.add("second");
            this.add("third");
            this.add("fourth");
            this.add("fifth");
            this.add("sixth");
        }};
        assertTrue(test.equals(expected));

        assertFalse(test.addAll(new ArrayList<String>() {{
            this.add("third");
            this.add("fifth");
            this.add("sixth");
        }}));
        assertTrue(test.equals(expected));
    }

    @Test
    public void testOffer() throws Exception {
        assertTrue(test.offer("fourth"));

        ArrayDeque<String> expected = new ArrayDeque<String>() {{
            this.add("first");
            this.add("second");
            this.add("third");
            this.add("fourth");
        }};
        assertTrue(test.equals(expected));

        assertFalse(test.offer("second"));

        assertTrue(test.equals(expected));
    }

    @Test
    public void testOfferFirst() throws Exception {
        assertFalse(test.offerFirst("first"));

        ArrayDeque<String> expected = new ArrayDeque<String>() {{
            this.add("first");
            this.add("second");
            this.add("third");
        }};
        assertTrue(test.equals(expected));

        assertTrue(test.offerFirst("zeroth"));
        expected.addFirst("zeroth");
        assertTrue(test.equals(expected));
    }

    @Test
    public void testOfferLast() throws Exception {
        assertFalse(test.offerLast("third"));

        ArrayDeque<String> expected = new ArrayDeque<String>() {{
            this.add("first");
            this.add("second");
            this.add("third");
        }};
        assertTrue(test.equals(expected));

        assertTrue(test.offerLast("fourth"));
        expected.addLast("fourth");
        assertTrue(test.equals(expected));
    }

    @Test
    public void testRemove() throws Exception {
        assertEquals("first", test.remove());

        ArrayDeque<String> expected = new ArrayDeque<String>() {{
            this.add("second");
            this.add("third");
        }};
        assertTrue(test.equals(expected));

        test.addFirst("first");
        expected.addFirst("first");
        assertTrue(test.equals(expected));

        assertEquals("first", test.remove());
        assertEquals("second", test.remove());
        assertEquals("third", test.remove());
        try {
            test.remove();
            fail("Expected NoSuchElementException when remove()-ing from empty deque");
        }
        catch (NoSuchElementException e) { }
        assertTrue(test.isEmpty());
    }

    @Test
    public void testRemoveFirst() throws Exception {
        assertEquals("first", test.removeFirst());

        ArrayDeque<String> expected = new ArrayDeque<String>() {{
            this.add("second");
            this.add("third");
        }};
        assertTrue(test.equals(expected));

        test.addFirst("first");
        expected.addFirst("first");
        assertTrue(test.equals(expected));

        assertEquals("first", test.removeFirst());
        assertEquals("second", test.removeFirst());
        assertEquals("third", test.removeFirst());
        try {
            test.removeFirst();
            fail("Expected NoSuchElementException when remove()-ing from empty deque");
        }
        catch (NoSuchElementException e) { }
        assertTrue(test.isEmpty());
    }

    @Test
    public void testRemoveLast() throws Exception {
        assertEquals("third", test.removeLast());

        ArrayDeque<String> expected = new ArrayDeque<String>() {{
            this.add("first");
            this.add("second");
        }};
        assertTrue(test.equals(expected));

        test.addLast("third");
        expected.addLast("third");
        assertTrue(test.equals(expected));

        assertEquals("third", test.removeLast());
        assertEquals("second", test.removeLast());
        assertEquals("first", test.removeLast());
        try {
            test.removeLast();
            fail("Expected NoSuchElementException when remove()-ing from empty deque");
        }
        catch (NoSuchElementException e) { }
        assertTrue(test.isEmpty());
    }

    @Test
    public void testPop() throws Exception {
        assertEquals("first", test.pop());

        ArrayDeque<String> expected = new ArrayDeque<String>() {{
            this.add("second");
            this.add("third");
        }};
        assertTrue(test.equals(expected));

        test.addFirst("first");
        expected.addFirst("first");
        assertTrue(test.equals(expected));

        assertEquals("first", test.pop());
        assertEquals("second", test.pop());
        assertEquals("third", test.pop());
        try {
            test.pop();
            fail("Expected NoSuchElementException when pop()-ing from empty deque");
        }
        catch (NoSuchElementException e) { }
        assertTrue(test.isEmpty());
    }

    @Test
    public void testRemoveElement() throws Exception {
        assertTrue(test.remove("second"));

        ArrayDeque<String> expected = new ArrayDeque<String>() {{
            this.add("first");
            this.add("third");
        }};
        assertTrue(test.equals(expected));

        assertFalse(test.remove("second"));
        assertTrue(test.add("second"));
    }

    @Test
    public void testRemoveAll() throws Exception {
        assertTrue(test.removeAll(new ArrayList<String>() {{
            this.add("first");
            this.add("fourth");
            this.add("fifth");
            this.add("third");
            this.add("sixth");
        }}));

        ArrayDeque<String> expected = new ArrayDeque<String>() {{
            this.add("second");
        }};
        assertTrue(test.equals(expected));

        assertFalse(test.removeAll(new ArrayList<String>() {{
            this.add("third");
            this.add("sixth");
        }}));
        assertTrue(test.equals(expected));

        assertTrue(test.add("third"));
    }

    @Test
    public void testRetainAll() throws Exception {
        assertTrue(test.retainAll(new ArrayList<String>() {{
            this.add("first");
            this.add("fourth");
            this.add("fifth");
            this.add("third");
            this.add("sixth");
        }}));

        ArrayDeque<String> expected = new ArrayDeque<String>() {{
            this.add("first");
            this.add("third");
        }};
        assertTrue(test.equals(expected));

        assertFalse(test.retainAll(new ArrayList<String>() {{
            this.add("first");
            this.add("third");
            this.add("sixth");
        }}));
        assertTrue(test.equals(expected));

        assertTrue(test.add("second"));
    }

    @Test
    public void testPoll() throws Exception {
        assertEquals("first", test.poll());

        ArrayDeque<String> expected = new ArrayDeque<String>() {{
            this.add("second");
            this.add("third");
        }};
        assertTrue(test.equals(expected));

        assertTrue(test.offerFirst("first"));
        expected.addFirst("first");
        assertTrue(test.equals(expected));

        assertEquals("first", test.poll());
        assertEquals("second", test.poll());
        assertEquals("third", test.poll());
        assertNull(test.poll());
        assertTrue(test.isEmpty());
    }

    @Test
    public void testPollFirst() throws Exception {
        assertEquals("first", test.pollFirst());

        ArrayDeque<String> expected = new ArrayDeque<String>() {{
            this.add("second");
            this.add("third");
        }};
        assertTrue(test.equals(expected));

        assertTrue(test.offerFirst("first"));
        expected.addFirst("first");
        assertTrue(test.equals(expected));

        assertEquals("first", test.pollFirst());
        assertEquals("second", test.pollFirst());
        assertEquals("third", test.pollFirst());
        assertNull(test.pollFirst());
        assertTrue(test.isEmpty());
    }

    @Test
    public void testPollLast() throws Exception {
        assertEquals("third", test.pollLast());

        ArrayDeque<String> expected = new ArrayDeque<String>() {{
            this.add("first");
            this.add("second");
        }};
        assertTrue(test.equals(expected));

        test.addLast("third");
        expected.addLast("third");
        assertTrue(test.equals(expected));

        assertEquals("third", test.pollLast());
        assertEquals("second", test.pollLast());
        assertEquals("first", test.pollLast());
        assertNull(test.pollLast());
        assertTrue(test.isEmpty());
    }

    @Test
    public void testClear() throws Exception {
        test.clear();
        assertTrue(test.isEmpty());

        assertTrue(test.add("first"));
        assertTrue(test.add("second"));
        assertTrue(test.add("third"));
    }

    @Test
    public void testIterator() throws Exception {
        Iterator<String> iter = test.iterator();

        assertTrue(iter.hasNext());
        assertEquals("first", iter.next());

        assertTrue(iter.hasNext());
        assertEquals("second", iter.next());

        iter.remove();  // remove "second"
        assertTrue(iter.hasNext());
        assertEquals("third", iter.next());

        assertTrue(test.add("second"));
    }

    @Test
    @Ignore
    public void testDescendingIterator() throws Exception {
        // TODO: implement this
    }

    @Test
    public void testRemoveFirstOccurrence() throws Exception {
        assertTrue(test.removeFirstOccurrence("third"));

        ArrayDeque<String> expected = new ArrayDeque<String>() {{
            this.add("first");
            this.add("second");
        }};
        assertTrue(test.equals(expected));

        assertTrue(test.add("third"));
        expected.add("third");

        assertFalse(test.removeFirstOccurrence("ninth"));
        assertTrue(test.equals(expected));
    }

    @Test
    public void testRemoveLastOccurrence() throws Exception {
        assertTrue(test.removeLastOccurrence("second"));

        ArrayDeque<String> expected = new ArrayDeque<String>() {{
            this.add("first");
            this.add("third");
        }};
        assertTrue(test.equals(expected));

        assertTrue(test.add("second"));
        expected.add("second");

        assertFalse(test.removeLastOccurrence("ninth"));
        assertTrue(test.equals(expected));
    }
}