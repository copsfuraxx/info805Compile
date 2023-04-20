package fr.usmb.m1isc.compilation.tp;

public class Node {
    private static int idWhile = 1;
    private static int idIf = 1;

    private int id;

    public TypeNode type;
    public Object value;
    public Node fil1;
    public Node fil2;

    public Node() {
        type = TypeNode.NONE;
        value = null;
        fil1 = null;
        fil2 = null;
    }

    public Node(Object value) {
        type = TypeNode.NONE;
        this.value = value;
        fil1 = new Node();
        fil2 = new Node();
    }

    public Node(Object value, Node fils, Node fils2) {
        type = TypeNode.NONE;
        this.value = value;
        this.fil1 = fils;
        this.fil2 = fils2;
    }

    public Node(TypeNode type,Object value) {
        this.type = type;
        this.value = value;
        fil1 = new Node();
        fil2 = new Node();
        switch (type) {
            case WHILE -> id = idWhile++;
            case COMPARATOR -> id = idIf++;
        }
    }

    public Node(TypeNode type, Node fils, Node fils2) {
        this.type = type;
        this.value = type;
        this.fil1 = fils;
        this.fil2 = fils2;
    }

    public Node(TypeNode type, Object value, Node fils, Node fils2) {
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
        switch (this.type) {
            case PV :
                return this.fil1.toString() + this.fil2.toString();
            case LET:
                return this.fil2.toString() +
                        "\tmov " + this.fil1.value + ", eax\n";
            case VAR:
                return "\tmov eax, " + this.value + "\n";
            case VALUE:
                return "\tmov eax, " + this.value + "\n";
            case OPERATOR:
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
                    case "%" :
                        op = "\tmov ecx,eax\n" +
                                "\tdiv ecx,ebx\n" +
                                "\tmul ecx,ebx\n" +
                                "\tsub eax,ecx\n";
                        break;
                }
                return this.fil1.toString() +
                        "\tpush eax\n" +
                        this.fil2.toString()  +
                        "\tpop ebx\n" +
                        op;
            case COMPARATOR:
                String cp = "";
                switch (this.value.toString()) {
                    case "=" :
                        cp = "";
                        break;
                    case ">" :
                        cp = "";
                        break;
                    case ">=" :
                        cp = "";
                        break;
                }
                return this.fil1.toString() +
                        "\tpush eax\n" +
                        this.fil2.toString()  +
                        "\tpop ebx\n" +
                        cp;
            default:
                return "";
        }
    }

    private void toString(String indent) {
        if (value != null) {
            if (fil1 != null) fil1.toString(indent + "    ");
            System.out.println(indent + value);
            if (fil2 != null)fil2.toString(indent + "    ");
        }
    }
}
