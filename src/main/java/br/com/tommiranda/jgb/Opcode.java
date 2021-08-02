package br.com.tommiranda.jgb;

public class Opcode {

    private final int op;
    private final String label;
    private final int length;
    private final Runnable[] steps;

    public Opcode(int op, String label, int length) {
        this.op = op;
        this.label = label;
        this.length = length;
        this.steps = new Runnable[]{};
    }

    public Opcode(int op, String label, int length, Runnable[] steps) {
        this.op = op;
        this.label = label;
        this.length = length;
        this.steps = steps;
    }

    public int getOp() {
        return op;
    }

    public String getLabel() {
        return label;
    }

    public int getLength() {
        return length;
    }

    public Runnable[] getSteps() {
        return steps;
    }
}
