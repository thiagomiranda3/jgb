package br.com.tommiranda.jgb;

public class CPU {

    private int AF;
    private int BC;
    private int DE;
    private int HL;
    private int SP;
    private int PC;

    public CPU() {
    }

    public CPU(int AF, int BC, int DE, int HL, int SP, int PC) {
        this.AF = AF;
        this.BC = BC;
        this.DE = DE;
        this.HL = HL;
        this.SP = SP;
        this.PC = PC;
    }

    public int getZeroFlag() {
        return (AF >> 7) & 0x01;
    }

    public void setZeroFlag(int zeroFlag) {
        AF |= (zeroFlag & 0b10000000);
    }

    public int getSubtractionFlag() {
        return (AF >> 6) & 0x01;
    }

    public void setSubtractionFlag(int subtractionFlag) {
        AF |= (subtractionFlag & 0b01000000);
    }

    public int getHalfCarryFlag() {
        return (AF >> 5) & 0x01;
    }

    public void setHalfCarryFlag(int halfCarryFlag) {
        AF |= (halfCarryFlag & 0b00100000);
    }

    public int getCarryFlag() {
        return (AF >> 4) & 0x01;
    }

    public void setCarryFlag(int carryFlag) {
        AF |= (carryFlag & 0b00010000);
    }

    public int getA() {
        return AF & 0xFF00;
    }

    public void setA(int A) {
        AF |= 0xFF00 & A;
    }

    public int getB() {
        return BC & 0xFF00;
    }

    public void setB(int B) {
        BC |= 0xFF00 & B;
    }

    public int getC() {
        return BC & 0xFF;
    }

    public void setC(int C) {
        BC |= 0xFF & C;
    }

    public int getD() {
        return DE & 0xFF00;
    }

    public void setD(int D) {
        DE |= 0xFF00 & D;
    }

    public int getE() {
        return DE & 0xFF;
    }

    public void setE(int E) {
        DE |= 0xFF & E;
    }

    public int getH() {
        return HL & 0xFF00;
    }

    public void setH(int H) {
        HL |= 0xFF00 & H;
    }

    public int getL() {
        return HL & 0xFF;
    }

    public void setL(int L) {
        HL |= 0xFF & L;
    }

    public int getAF() {
        return AF;
    }

    public void setAF(int AF) {
        this.AF = AF;
    }

    public int getBC() {
        return BC;
    }

    public void setBC(int BC) {
        this.BC = BC;
    }

    public int getDE() {
        return DE;
    }

    public void setDE(int DE) {
        this.DE = DE;
    }

    public int getHL() {
        return HL;
    }

    public void setHL(int HL) {
        this.HL = HL;
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
}
