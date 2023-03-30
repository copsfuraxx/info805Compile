package fr.usmb.m1isc.compilation.tp;

public class Node {
    public Object value;
    public Node fil1;
    public Node fil2;

    public Node() {
        value = null;
        fil1 = null;
        fil2 = null;
    }

    public Node(Object value) {
        this.value = value;
        fil1 = new Node();
        fil2 = new Node();
    }

    public Node(Object value, Node fils, Node fils2) {
        this.value = value;
        this.fil1 = fils;
        this.fil2 = fils2;
    }

    public void print() {
        toString("");
    }

    private void toString(String indent) {
        if (value != null) {
            if (fil1 != null) fil1.toString(indent + "    ");
            System.out.println(indent + value);
            if (fil2 != null)fil2.toString(indent + "    ");
        }
    }
}
