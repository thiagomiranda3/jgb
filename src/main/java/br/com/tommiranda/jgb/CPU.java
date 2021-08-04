package br.com.tommiranda.jgb;

public class CPU {

    private int A;
    private int F;
    private int B;
    private int C;
    private int D;
    private int E;
    private int H;
    private int L;
    private int SP;
    private int PC;

    public CPU() {
    }

    public CPU(int AF, int BC, int DE, int HL, int SP, int PC) {
        this.A = (AF >> 8) & 0xFF;
        this.F = AF & 0xFF;
        this.B = (BC >> 8) & 0xFF;
        this.C = BC & 0xFF;
        this.D = (DE >> 8) & 0xFF;
        this.E = DE & 0xFF;
        this.H = (HL >> 8) & 0xFF;
        this.L = HL & 0xFF;
        this.SP = SP;
        this.PC = PC;
    }

    public int getZeroFlag() {
        return (F >> 7) & 0x01;
    }

    public void setZeroFlag(int zeroFlag) {
        F |= (zeroFlag & 0b10000000);
    }

    public int getSubtractionFlag() {
        return (F >> 6) & 0x01;
    }

    public void setSubtractionFlag(int subtractionFlag) {
        F |= (subtractionFlag & 0b01000000);
    }

    public int getHalfCarryFlag() {
        return (F >> 5) & 0x01;
    }

    public void setHalfCarryFlag(int halfCarryFlag) {
        F |= (halfCarryFlag & 0b00100000);
    }

    public int getCarryFlag() {
        return (F >> 4) & 0x01;
    }

    public void setCarryFlag(int carryFlag) {
        F |= (carryFlag & 0b00010000);
    }

    public int getA() {
        return A;
    }

    public void setA(int A) {
        this.A = A;
    }

    public int getF() {
        return F;
    }

    public void setF(int F) {
        this.F = F;
    }

    public int getB() {
        return B;
    }

    public void setB(int B) {
        this.B = B;
    }

    public int getC() {
        return C;
    }

    public void setC(int C) {
        this.C = C;
    }

    public int getD() {
        return D;
    }

    public void setD(int D) {
        this.D = D;
    }

    public int getE() {
        return E;
    }

    public void setE(int E) {
        this.E = E;
    }

    public int getH() {
        return H;
    }

    public void setH(int H) {
        this.H = H;
    }

    public int getL() {
        return L;
    }

    public void setL(int L) {
        this.L = L;
    }

    public int getSP() {
        return SP;
    }

    public void setSP(int SP) {
        this.SP = SP;
    }

    public int getPC() {
        return PC;
    }

    public int getPCInc() {
        return PC++;
    }

    public void setPC(int PC) {
        this.PC = PC;
    }

    public void setHL(int HL) {
        this.H = (HL >> 8) & 0xFF;
        this.L = L & 0xFF;
    }

    public int getAF() {
        return (A << 8) | F;
    }

    public int getBC() {
        return (B << 8) | C;
    }

    public int getDE() {
        return (D << 8) | E;
    }

    public int getHL() {
        return (H << 8) | L;
    }
}
