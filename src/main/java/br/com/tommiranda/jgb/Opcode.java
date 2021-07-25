package br.com.tommiranda.jgb;

public class Opcode {

    private int op;
    private String label;
    private int length;

    public Opcode(int op, String label, int length) {
        this.op = op;
        this.label = label;
        this.length = length;
    }

    public int getOp() {
        return op;
    }

    public void setOp(int op) {
        this.op = op;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
