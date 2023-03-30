package fr.usmb.m1isc.compilation.tp;

public class Node {

    public enum typeNode{
        PV,
        LET,
        NONE,
        VALUE,
        VAR,
        PLUS,
        MOINS,
        DIV,
        MUL,
        OPERATOR
    };
    public typeNode type;
    public Object value;
    public Node fil1;
    public Node fil2;

    public Node() {
        type = typeNode.NONE;
        value = null;
        fil1 = null;
        fil2 = null;
    }

    public Node(Object value) {
        type = typeNode.NONE;
        this.value = value;
        fil1 = new Node();
        fil2 = new Node();
    }

    public Node(Object value, Node fils, Node fils2) {
        type = typeNode.NONE;
        this.value = value;
        this.fil1 = fils;
        this.fil2 = fils2;
    }

    public Node(typeNode type,Object value) {
        this.type = type;
        this.value = value;
        fil1 = new Node();
        fil2 = new Node();
    }

    public Node(typeNode type, Node fils, Node fils2) {
        this.type = type;
        this.value = type;
        this.fil1 = fils;
        this.fil2 = fils2;
    }

    public Node(typeNode type, Object value, Node fils, Node fils2) {
        this.type = type;
        this.value = value;
        this.fil1 = fils;
        this.fil2 = fils2;
    }

    public void print() {
        toString("");
    }

    @Override
    public String toString() {
        if(this.type == typeNode.PV) {
            return this.fil1.toString() + this.fil2.toString();
        } else if (this.type == typeNode.LET) {
            return this.fil2.toString() +
                    "\tmov " + this.fil1.value + ", eax\n";
        } else if (this.type == typeNode.VALUE || this.type == typeNode.VAR) {
            return "\tmov eax, " + this.value + "\n";
        } else if(this.type == typeNode.MUL || this.type == typeNode.DIV ||
                this.type == typeNode.PLUS || this.type == typeNode.MOINS) {
            String op = "";
            switch (this.value.toString()) {
                case "*" :
                    op = "\tmul eax, ebx\n";
                    break;
                case "/" :
                    op = "\tdiv ebx, eax\n" +
                            "\tmov eax, ebx\n";
                    break;
                case "+" :
                    op = "\tadd eax, ebx\n";
                    break;
                case "-" :
                    op = "\tsub ebx, eax\n" +
                            "\tmov eax, ebx\n";
                    break;
            }
            return this.fil1.toString() +
                    "\tpush eax\n" +
                    this.fil2.toString()  +
                    "\tpop ebx\n" +
                    op;
        }
        return "";
    }

    private void toString(String indent) {
        if (value != null) {
            if (fil1 != null) fil1.toString(indent + "    ");
            System.out.println(indent + value);
            if (fil2 != null)fil2.toString(indent + "    ");
        }
    }
}
