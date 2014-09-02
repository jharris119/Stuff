package info.jayharris.stuff;

import com.google.common.collect.Maps;

import java.util.HashMap;

public class DisjointSet<E> {

    private HashMap<E, Node> forest;
    private int trees;

    public DisjointSet() {
        forest = Maps.newHashMap();
    }

    public void makeSet(E data) {
        if (forest.containsKey(data)) {
            return;
        }
        forest.put(data, new Node(data));
        ++trees;
    }

    public E find(E data) {
        Node node = forest.get(data);
        if (node == null) {
            return null;
        }
        return find(node).data;
    }

    private Node find(Node node) {
        if (node.parent != node) {
            node.parent = find(node.parent);
        }
        return node.parent;
    }

    public void union(E x, E y) {
        Node nx = forest.get(x),
             ny = forest.get(y);
        if (nx == null || ny == null) {
            return;
        }
        union(nx, ny);
    }

    private void union(Node x, Node y) {
        Node xroot = find(x),
             yroot = find(y);
        if (xroot == yroot) {
            return;
        }

        if (xroot.rank < yroot.rank) {
            xroot.parent = yroot;
        }
        else if (yroot.rank < xroot.rank) {
            yroot.parent = xroot;
        }
        else {
            yroot.parent = xroot;
            xroot.rank += 1;
        }
        --trees;
    }

    public int countSets() {
        return trees;
    }

    class Node {
        final E data;
        Node parent;
        int rank;

        Node(E data) {
            this.data = data;
            this.parent = this;
            this.rank = 0;
        }
    }
}
