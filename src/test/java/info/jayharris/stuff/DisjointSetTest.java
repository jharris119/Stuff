package info.jayharris.stuff;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class DisjointSetTest {

    DisjointSet<String> test;

    @Before
    public void setUp() throws Exception {
        test = new DisjointSet();
        test.makeSet("aardvark");
        test.makeSet("antelope");
        test.makeSet("anaconda");
        test.makeSet("bear");
        test.makeSet("beetle");
        test.makeSet("boar");
        test.makeSet("beagle");
        test.makeSet("cat");
        test.makeSet("cod");
    }

    @Test
    public void testUnion() throws Exception {
        test.union("aardvark", "anaconda");
        test.union("antelope", "aardvark");
        test.union("bear", "boar");
        test.union("beetle", "beagle");
        test.union("beagle", "boar");
        test.union("cat", "cod");
        assertEquals(test.find("anaconda"), test.find("antelope"));
        assertEquals(test.find("beetle"), test.find("bear"));
        assertEquals(test.find("cat"), test.find("cod"));
    }

    @Test
    public void testFind() throws Exception {
        test.union("aardvark", "anaconda");
        test.union("antelope", "aardvark");
        test.union("bear", "boar");
        test.union("beetle", "beagle");
        test.union("beagle", "boar");
        test.union("cat", "cod");
        assertEquals(test.find("anaconda"), test.find("antelope"));
        assertEquals(test.find("beetle"), test.find("bear"));
        assertEquals(test.find("cat"), test.find("cod"));
    }
}