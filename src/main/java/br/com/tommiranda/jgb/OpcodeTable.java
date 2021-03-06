package br.com.tommiranda.jgb;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class OpcodeTable {

    private static CPU cpu = GameBoy.cpu;
    private static Bus bus = GameBoy.bus;
    public static Map<Integer, Opcode> cbTable = createCBTable();
    public static Map<Integer, Opcode> table = createTable();

    private static Map<Integer, Opcode> createTable() {
        Map<Integer, Opcode> table = new HashMap<>();

        AtomicInteger lsb = new AtomicInteger();
        AtomicInteger msb = new AtomicInteger();

        table.put(0x00, new Opcode(0x00, "NOP", 1, new Runnable[] {}));
        table.put(0x01, new Opcode(0x01, "LD BC,u16", 3, new Runnable[] {
                () -> cpu.setC(bus.readByte(cpu.getPCInc())),
                () -> cpu.setB(bus.readByte(cpu.getPCInc()))
        }));
        table.put(0x02, new Opcode(0x02, "LD (BC),A", 1, new Runnable[] {() -> bus.writeByte(cpu.getBC(), cpu.getA())}));
        table.put(0x03, new Opcode(0x03, "INC BC", 1));
        table.put(0x04, new Opcode(0x04, "INC B", 1, new Runnable[] {() -> cpu.setB(INC(cpu.getB()))}));
        table.put(0x05, new Opcode(0x05, "DEC B", 1, new Runnable[] {() -> cpu.setB(DEC(cpu.getB()))}));
        table.put(0x06, new Opcode(0x06, "LD B,u8", 2, new Runnable[] {() -> cpu.setB(bus.readByte(cpu.getPCInc()))}));
        table.put(0x07, new Opcode(0x07, "RLCA", 1, new Runnable[] {() -> {
            RLC(cpu.getA());
            cpu.setZeroFlag(0);
        }}));
        table.put(0x08, new Opcode(0x08, "LD (u16),SP", 3));
        table.put(0x09, new Opcode(0x09, "ADD HL,BC", 1, new Runnable[] {() -> ADD_HL(cpu.getBC())}));
        table.put(0x0A, new Opcode(0x0A, "LD A,(BC)", 1));
        table.put(0x0B, new Opcode(0x0B, "DEC BC", 1));
        table.put(0x0C, new Opcode(0x0C, "INC C", 1, new Runnable[] {() -> cpu.setC(INC(cpu.getC()))}));
        table.put(0x0D, new Opcode(0x0D, "DEC C", 1, new Runnable[] {() -> cpu.setB(DEC(cpu.getC()))}));
        table.put(0x0E, new Opcode(0x0E, "LD C,u8", 2, new Runnable[] {() -> cpu.setC(bus.readByte(cpu.getPCInc()))}));
        table.put(0x0F, new Opcode(0x0F, "RRCA", 1, new Runnable[] {() -> {
            RRC(cpu.getA());
            cpu.setZeroFlag(0);
        }}));
        table.put(0x10, new Opcode(0x10, "STOP", 2, new Runnable[] {}));
        table.put(0x11, new Opcode(0x11, "LD DE,u16", 3, new Runnable[] {
                () -> cpu.setE(bus.readByte(cpu.getPCInc())),
                () -> cpu.setD(bus.readByte(cpu.getPCInc()))
        }));
        table.put(0x12, new Opcode(0x12, "LD (DE),A", 1, new Runnable[] {() -> bus.writeByte(cpu.getDE(), cpu.getA())}));
        table.put(0x13, new Opcode(0x13, "INC DE", 1));
        table.put(0x14, new Opcode(0x14, "INC D", 1, new Runnable[] {() -> cpu.setD(INC(cpu.getD()))}));
        table.put(0x15, new Opcode(0x15, "DEC D", 1, new Runnable[] {() -> cpu.setD(DEC(cpu.getD()))}));
        table.put(0x16, new Opcode(0x16, "LD D,u8", 2, new Runnable[] {() -> cpu.setD(bus.readByte(cpu.getPCInc()))}));
        table.put(0x17, new Opcode(0x17, "RLA", 1, new Runnable[] {() -> {
            RL(cpu.getA());
            cpu.setZeroFlag(0);
        }}));
        table.put(0x18, new Opcode(0x18, "JR i8", 2));
        table.put(0x19, new Opcode(0x19, "ADD HL,DE", 1, new Runnable[] {() -> ADD_HL(cpu.getDE())}));
        table.put(0x1A, new Opcode(0x1A, "LD A,(DE)", 1));
        table.put(0x1B, new Opcode(0x1B, "DEC DE", 1));
        table.put(0x1C, new Opcode(0x1C, "INC E", 1, new Runnable[] {() -> cpu.setE(INC(cpu.getE()))}));
        table.put(0x1D, new Opcode(0x1D, "DEC E", 1, new Runnable[] {() -> cpu.setE(DEC(cpu.getE()))}));
        table.put(0x1E, new Opcode(0x1E, "LD E,u8", 2, new Runnable[] {() -> cpu.setE(bus.readByte(cpu.getPCInc()))}));
        table.put(0x1F, new Opcode(0x1F, "RRA", 1, new Runnable[] {() -> {
            RR(cpu.getA());
            cpu.setZeroFlag(0);
        }}));
        table.put(0x20, new Opcode(0x20, "JR NZ,i8", 2));
        table.put(0x21, new Opcode(0x21, "LD HL,u16", 3, new Runnable[] {
                () -> cpu.setL(bus.readByte(cpu.getPCInc())),
                () -> cpu.setH(bus.readByte(cpu.getPCInc()))
        }));
        table.put(0x22, new Opcode(0x22, "LD (HL+),A", 1));
        table.put(0x23, new Opcode(0x23, "INC HL", 1));
        table.put(0x24, new Opcode(0x24, "INC H", 1, new Runnable[] {() -> cpu.setH(INC(cpu.getH()))}));
        table.put(0x25, new Opcode(0x25, "DEC H", 1, new Runnable[] {() -> cpu.setH(DEC(cpu.getH()))}));
        table.put(0x26, new Opcode(0x26, "LD H,u8", 2, new Runnable[] {() -> cpu.setH(bus.readByte(cpu.getPCInc()))}));
        table.put(0x27, new Opcode(0x27, "DAA", 1));
        table.put(0x28, new Opcode(0x28, "JR Z,i8", 2));
        table.put(0x29, new Opcode(0x29, "ADD HL,HL", 1, new Runnable[] {() -> ADD_HL(cpu.getHL())}));
        table.put(0x2A, new Opcode(0x2A, "LD A,(HL+)", 1));
        table.put(0x2B, new Opcode(0x2B, "DEC HL", 1));
        table.put(0x2C, new Opcode(0x2C, "INC L", 1, new Runnable[] {() -> cpu.setL(INC(cpu.getL()))}));
        table.put(0x2D, new Opcode(0x2D, "DEC L", 1, new Runnable[] {() -> cpu.setL(DEC(cpu.getL()))}));
        table.put(0x2E, new Opcode(0x2E, "LD L,u8", 2, new Runnable[] {() -> cpu.setL(bus.readByte(cpu.getPCInc()))}));
        table.put(0x2F, new Opcode(0x2F, "CPL", 1));
        table.put(0x30, new Opcode(0x30, "JR NC,i8", 2));
        table.put(0x31, new Opcode(0x31, "LD SP,u16", 3, new Runnable[] {
                () -> cpu.setC(bus.readByte(cpu.getPCInc())),
                () -> {
                    msb.set(bus.readByte(cpu.getPCInc()));
                    cpu.setSP(ByteUtils.to16Bit(msb.get(), lsb.get()));
                }
        }));
        table.put(0x32, new Opcode(0x32, "LD (HL-),A", 1));
        table.put(0x33, new Opcode(0x33, "INC SP", 1));
        table.put(0x34, new Opcode(0x34, "INC (HL)", 1));
        table.put(0x35, new Opcode(0x35, "DEC (HL)", 1));
        table.put(0x36, new Opcode(0x36, "LD (HL),u8", 2));
        table.put(0x37, new Opcode(0x37, "SCF", 1));
        table.put(0x38, new Opcode(0x38, "JR C,i8", 2));
        table.put(0x39, new Opcode(0x39, "ADD HL,SP", 1, new Runnable[] {() -> ADD_HL(cpu.getSP())}));
        table.put(0x3A, new Opcode(0x3A, "LD A,(HL-)", 1));
        table.put(0x3B, new Opcode(0x3B, "DEC SP", 1));
        table.put(0x3C, new Opcode(0x3C, "INC A", 1, new Runnable[] {() -> cpu.setA(INC(cpu.getA()))}));
        table.put(0x3D, new Opcode(0x3D, "DEC A", 1, new Runnable[] {() -> cpu.setA(DEC(cpu.getA()))}));
        table.put(0x3E, new Opcode(0x3E, "LD A,u8", 2, new Runnable[] {() -> cpu.setA(bus.readByte(cpu.getPCInc()))}));
        table.put(0x3F, new Opcode(0x3F, "CCF", 1));
        table.put(0x40, new Opcode(0x40, "LD B,B", 1, new Runnable[] {() -> cpu.setB(cpu.getB())}));
        table.put(0x41, new Opcode(0x41, "LD B,C", 1, new Runnable[] {() -> cpu.setB(cpu.getC())}));
        table.put(0x42, new Opcode(0x42, "LD B,D", 1, new Runnable[] {() -> cpu.setB(cpu.getD())}));
        table.put(0x43, new Opcode(0x43, "LD B,E", 1, new Runnable[] {() -> cpu.setB(cpu.getE())}));
        table.put(0x44, new Opcode(0x44, "LD B,H", 1, new Runnable[] {() -> cpu.setB(cpu.getH())}));
        table.put(0x45, new Opcode(0x45, "LD B,L", 1, new Runnable[] {() -> cpu.setB(cpu.getL())}));
        table.put(0x46, new Opcode(0x46, "LD B,(HL)", 1, new Runnable[] {() -> cpu.setB(bus.readByte(cpu.getHL()))}));
        table.put(0x47, new Opcode(0x47, "LD B,A", 1, new Runnable[] {() -> cpu.setA(cpu.getC())}));
        table.put(0x48, new Opcode(0x48, "LD C,B", 1, new Runnable[] {() -> cpu.setC(cpu.getB())}));
        table.put(0x49, new Opcode(0x49, "LD C,C", 1, new Runnable[] {() -> cpu.setC(cpu.getC())}));
        table.put(0x4A, new Opcode(0x4A, "LD C,D", 1, new Runnable[] {() -> cpu.setC(cpu.getD())}));
        table.put(0x4B, new Opcode(0x4B, "LD C,E", 1, new Runnable[] {() -> cpu.setC(cpu.getE())}));
        table.put(0x4C, new Opcode(0x4C, "LD C,H", 1, new Runnable[] {() -> cpu.setC(cpu.getH())}));
        table.put(0x4D, new Opcode(0x4D, "LD C,L", 1, new Runnable[] {() -> cpu.setC(cpu.getL())}));
        table.put(0x4E, new Opcode(0x4E, "LD C,(HL)", 1, new Runnable[] {() -> cpu.setC(bus.readByte(cpu.getHL()))}));
        table.put(0x4F, new Opcode(0x4F, "LD C,A", 1, new Runnable[] {() -> cpu.setC(cpu.getA())}));
        table.put(0x50, new Opcode(0x50, "LD D,B", 1, new Runnable[] {() -> cpu.setD(cpu.getB())}));
        table.put(0x51, new Opcode(0x51, "LD D,C", 1, new Runnable[] {() -> cpu.setD(cpu.getC())}));
        table.put(0x52, new Opcode(0x52, "LD D,D", 1, new Runnable[] {() -> cpu.setD(cpu.getD())}));
        table.put(0x53, new Opcode(0x53, "LD D,E", 1, new Runnable[] {() -> cpu.setD(cpu.getE())}));
        table.put(0x54, new Opcode(0x54, "LD D,H", 1, new Runnable[] {() -> cpu.setD(cpu.getH())}));
        table.put(0x55, new Opcode(0x55, "LD D,L", 1, new Runnable[] {() -> cpu.setD(cpu.getL())}));
        table.put(0x56, new Opcode(0x56, "LD D,(HL)", 1, new Runnable[] {() -> cpu.setD(bus.readByte(cpu.getHL()))}));
        table.put(0x57, new Opcode(0x57, "LD D,A", 1, new Runnable[] {() -> cpu.setD(cpu.getA())}));
        table.put(0x58, new Opcode(0x58, "LD E,B", 1, new Runnable[] {() -> cpu.setE(cpu.getB())}));
        table.put(0x59, new Opcode(0x59, "LD E,C", 1, new Runnable[] {() -> cpu.setE(cpu.getC())}));
        table.put(0x5A, new Opcode(0x5A, "LD E,D", 1, new Runnable[] {() -> cpu.setE(cpu.getD())}));
        table.put(0x5B, new Opcode(0x5B, "LD E,E", 1, new Runnable[] {() -> cpu.setE(cpu.getE())}));
        table.put(0x5C, new Opcode(0x5C, "LD E,H", 1, new Runnable[] {() -> cpu.setE(cpu.getH())}));
        table.put(0x5D, new Opcode(0x5D, "LD E,L", 1, new Runnable[] {() -> cpu.setE(cpu.getL())}));
        table.put(0x5E, new Opcode(0x5E, "LD E,(HL)", 1, new Runnable[] {() -> cpu.setE(bus.readByte(cpu.getHL()))}));
        table.put(0x5F, new Opcode(0x5F, "LD E,A", 1, new Runnable[] {() -> cpu.setE(cpu.getA())}));
        table.put(0x60, new Opcode(0x60, "LD H,B", 1, new Runnable[] {() -> cpu.setH(cpu.getB())}));
        table.put(0x61, new Opcode(0x61, "LD H,C", 1, new Runnable[] {() -> cpu.setH(cpu.getC())}));
        table.put(0x62, new Opcode(0x62, "LD H,D", 1, new Runnable[] {() -> cpu.setH(cpu.getD())}));
        table.put(0x63, new Opcode(0x63, "LD H,E", 1, new Runnable[] {() -> cpu.setH(cpu.getE())}));
        table.put(0x64, new Opcode(0x64, "LD H,H", 1, new Runnable[] {() -> cpu.setH(cpu.getH())}));
        table.put(0x65, new Opcode(0x65, "LD H,L", 1, new Runnable[] {() -> cpu.setH(cpu.getL())}));
        table.put(0x66, new Opcode(0x66, "LD H,(HL)", 1, new Runnable[] {() -> cpu.setH(bus.readByte(cpu.getHL()))}));
        table.put(0x67, new Opcode(0x67, "LD H,A", 1, new Runnable[] {() -> cpu.setH(cpu.getA())}));
        table.put(0x68, new Opcode(0x68, "LD L,B", 1, new Runnable[] {() -> cpu.setL(cpu.getB())}));
        table.put(0x69, new Opcode(0x69, "LD L,C", 1, new Runnable[] {() -> cpu.setL(cpu.getC())}));
        table.put(0x6A, new Opcode(0x6A, "LD L,D", 1, new Runnable[] {() -> cpu.setL(cpu.getD())}));
        table.put(0x6B, new Opcode(0x6B, "LD L,E", 1, new Runnable[] {() -> cpu.setL(cpu.getE())}));
        table.put(0x6C, new Opcode(0x6C, "LD L,H", 1, new Runnable[] {() -> cpu.setL(cpu.getH())}));
        table.put(0x6D, new Opcode(0x6D, "LD L,L", 1, new Runnable[] {() -> cpu.setL(cpu.getL())}));
        table.put(0x6E, new Opcode(0x6E, "LD L,(HL)", 1, new Runnable[] {() -> cpu.setL(bus.readByte(cpu.getHL()))}));
        table.put(0x6F, new Opcode(0x6F, "LD L,A", 1, new Runnable[] {() -> cpu.setL(cpu.getA())}));
        table.put(0x70, new Opcode(0x70, "LD (HL),B", 1, new Runnable[] {() -> bus.writeByte(cpu.getHL(), cpu.getB())}));
        table.put(0x71, new Opcode(0x71, "LD (HL),C", 1, new Runnable[] {() -> bus.writeByte(cpu.getHL(), cpu.getC())}));
        table.put(0x72, new Opcode(0x72, "LD (HL),D", 1, new Runnable[] {() -> bus.writeByte(cpu.getHL(), cpu.getD())}));
        table.put(0x73, new Opcode(0x73, "LD (HL),E", 1, new Runnable[] {() -> bus.writeByte(cpu.getHL(), cpu.getE())}));
        table.put(0x74, new Opcode(0x74, "LD (HL),H", 1, new Runnable[] {() -> bus.writeByte(cpu.getHL(), cpu.getH())}));
        table.put(0x75, new Opcode(0x75, "LD (HL),L", 1, new Runnable[] {() -> bus.writeByte(cpu.getHL(), cpu.getL())}));
        table.put(0x76, new Opcode(0x76, "HALT", 1, new Runnable[] {}));
        table.put(0x77, new Opcode(0x77, "LD (HL),A", 1, new Runnable[] {() -> bus.writeByte(cpu.getHL(), cpu.getA())}));
        table.put(0x78, new Opcode(0x78, "LD A,B", 1, new Runnable[] {() -> cpu.setA(cpu.getB())}));
        table.put(0x79, new Opcode(0x79, "LD A,C", 1, new Runnable[] {() -> cpu.setA(cpu.getC())}));
        table.put(0x7A, new Opcode(0x7A, "LD A,D", 1, new Runnable[] {() -> cpu.setA(cpu.getD())}));
        table.put(0x7B, new Opcode(0x7B, "LD A,E", 1, new Runnable[] {() -> cpu.setA(cpu.getE())}));
        table.put(0x7C, new Opcode(0x7C, "LD A,H", 1, new Runnable[] {() -> cpu.setA(cpu.getH())}));
        table.put(0x7D, new Opcode(0x7D, "LD A,L", 1, new Runnable[] {() -> cpu.setA(cpu.getL())}));
        table.put(0x7E, new Opcode(0x7E, "LD A,(HL)", 1, new Runnable[] {() -> cpu.setA(bus.readByte(cpu.getHL()))}));
        table.put(0x7F, new Opcode(0x7F, "LD A,A", 1, new Runnable[] {() -> cpu.setA(cpu.getA())}));
        table.put(0x80, new Opcode(0x80, "ADD A,B", 1, new Runnable[] {() -> ADD(cpu.getB())}));
        table.put(0x81, new Opcode(0x81, "ADD A,C", 1, new Runnable[] {() -> ADD(cpu.getC())}));
        table.put(0x82, new Opcode(0x82, "ADD A,D", 1, new Runnable[] {() -> ADD(cpu.getD())}));
        table.put(0x83, new Opcode(0x83, "ADD A,E", 1, new Runnable[] {() -> ADD(cpu.getE())}));
        table.put(0x84, new Opcode(0x84, "ADD A,H", 1, new Runnable[] {() -> ADD(cpu.getH())}));
        table.put(0x85, new Opcode(0x85, "ADD A,L", 1, new Runnable[] {() -> ADD(cpu.getL())}));
        table.put(0x86, new Opcode(0x86, "ADD A,(HL)", 1, new Runnable[] {() -> ADD(bus.readByte(cpu.getHL()))}));
        table.put(0x87, new Opcode(0x87, "ADD A,A", 1, new Runnable[] {() -> ADD(cpu.getA())}));
        table.put(0x88, new Opcode(0x88, "ADC A,B", 1, new Runnable[] {() -> ADC(cpu.getB())}));
        table.put(0x89, new Opcode(0x89, "ADC A,C", 1, new Runnable[] {() -> ADC(cpu.getC())}));
        table.put(0x8A, new Opcode(0x8A, "ADC A,D", 1, new Runnable[] {() -> ADC(cpu.getD())}));
        table.put(0x8B, new Opcode(0x8B, "ADC A,E", 1, new Runnable[] {() -> ADC(cpu.getE())}));
        table.put(0x8C, new Opcode(0x8C, "ADC A,H", 1, new Runnable[] {() -> ADC(cpu.getH())}));
        table.put(0x8D, new Opcode(0x8D, "ADC A,L", 1, new Runnable[] {() -> ADC(cpu.getL())}));
        table.put(0x8E, new Opcode(0x8E, "ADC A,(HL)", 1, new Runnable[] {() -> ADC(bus.readByte(cpu.getHL()))}));
        table.put(0x8F, new Opcode(0x8F, "ADC A,A", 1, new Runnable[] {() -> ADC(cpu.getA())}));
        table.put(0x90, new Opcode(0x90, "SUB A,B", 1, new Runnable[] {() -> SUB(cpu.getB())}));
        table.put(0x91, new Opcode(0x91, "SUB A,C", 1, new Runnable[] {() -> SUB(cpu.getC())}));
        table.put(0x92, new Opcode(0x92, "SUB A,D", 1, new Runnable[] {() -> SUB(cpu.getD())}));
        table.put(0x93, new Opcode(0x93, "SUB A,E", 1, new Runnable[] {() -> SUB(cpu.getE())}));
        table.put(0x94, new Opcode(0x94, "SUB A,H", 1, new Runnable[] {() -> SUB(cpu.getH())}));
        table.put(0x95, new Opcode(0x95, "SUB A,L", 1, new Runnable[] {() -> SUB(cpu.getL())}));
        table.put(0x96, new Opcode(0x96, "SUB A,(HL)", 1, new Runnable[] {() -> SUB(bus.readByte(cpu.getHL()))}));
        table.put(0x97, new Opcode(0x97, "SUB A,A", 1, new Runnable[] {() -> SUB(cpu.getA())}));
        table.put(0x98, new Opcode(0x98, "SBC A,B", 1, new Runnable[] {() -> SBC(cpu.getB())}));
        table.put(0x99, new Opcode(0x99, "SBC A,C", 1, new Runnable[] {() -> SBC(cpu.getC())}));
        table.put(0x9A, new Opcode(0x9A, "SBC A,D", 1, new Runnable[] {() -> SBC(cpu.getD())}));
        table.put(0x9B, new Opcode(0x9B, "SBC A,E", 1, new Runnable[] {() -> SBC(cpu.getE())}));
        table.put(0x9C, new Opcode(0x9C, "SBC A,H", 1, new Runnable[] {() -> SBC(cpu.getH())}));
        table.put(0x9D, new Opcode(0x9D, "SBC A,L", 1, new Runnable[] {() -> SBC(cpu.getL())}));
        table.put(0x9E, new Opcode(0x9E, "SBC A,(HL)", 1, new Runnable[] {() -> SBC(bus.readByte(cpu.getHL()))}));
        table.put(0x9F, new Opcode(0x9F, "SBC A,A", 1, new Runnable[] {() -> SBC(cpu.getA())}));
        table.put(0xA0, new Opcode(0xA0, "AND A,B", 1, new Runnable[] {() -> AND(cpu.getB())}));
        table.put(0xA1, new Opcode(0xA1, "AND A,C", 1, new Runnable[] {() -> AND(cpu.getC())}));
        table.put(0xA2, new Opcode(0xA2, "AND A,D", 1, new Runnable[] {() -> AND(cpu.getD())}));
        table.put(0xA3, new Opcode(0xA3, "AND A,E", 1, new Runnable[] {() -> AND(cpu.getE())}));
        table.put(0xA4, new Opcode(0xA4, "AND A,H", 1, new Runnable[] {() -> AND(cpu.getH())}));
        table.put(0xA5, new Opcode(0xA5, "AND A,L", 1, new Runnable[] {() -> AND(cpu.getL())}));
        table.put(0xA6, new Opcode(0xA6, "AND A,(HL)", 1, new Runnable[] {() -> AND(bus.readByte(cpu.getHL()))}));
        table.put(0xA7, new Opcode(0xA7, "AND A,A", 1, new Runnable[] {() -> AND(cpu.getA())}));
        table.put(0xA8, new Opcode(0xA8, "XOR A,B", 1, new Runnable[] {() -> XOR(cpu.getB())}));
        table.put(0xA9, new Opcode(0xA9, "XOR A,C", 1, new Runnable[] {() -> XOR(cpu.getC())}));
        table.put(0xAA, new Opcode(0xAA, "XOR A,D", 1, new Runnable[] {() -> XOR(cpu.getD())}));
        table.put(0xAB, new Opcode(0xAB, "XOR A,E", 1, new Runnable[] {() -> XOR(cpu.getE())}));
        table.put(0xAC, new Opcode(0xAC, "XOR A,H", 1, new Runnable[] {() -> XOR(cpu.getH())}));
        table.put(0xAD, new Opcode(0xAD, "XOR A,L", 1, new Runnable[] {() -> XOR(cpu.getL())}));
        table.put(0xAE, new Opcode(0xAE, "XOR A,(HL)", 1, new Runnable[] {() -> XOR(bus.readByte(cpu.getHL()))}));
        table.put(0xAF, new Opcode(0xAF, "XOR A,A", 1, new Runnable[] {() -> XOR(cpu.getA())}));
        table.put(0xB0, new Opcode(0xB0, "OR A,B", 1, new Runnable[] {() -> OR(cpu.getB())}));
        table.put(0xB1, new Opcode(0xB1, "OR A,C", 1, new Runnable[] {() -> OR(cpu.getC())}));
        table.put(0xB2, new Opcode(0xB2, "OR A,D", 1, new Runnable[] {() -> OR(cpu.getD())}));
        table.put(0xB3, new Opcode(0xB3, "OR A,E", 1, new Runnable[] {() -> OR(cpu.getE())}));
        table.put(0xB4, new Opcode(0xB4, "OR A,H", 1, new Runnable[] {() -> OR(cpu.getH())}));
        table.put(0xB5, new Opcode(0xB5, "OR A,L", 1, new Runnable[] {() -> OR(cpu.getL())}));
        table.put(0xB6, new Opcode(0xB6, "OR A,(HL)", 1, new Runnable[] {() -> OR(bus.readByte(cpu.getHL()))}));
        table.put(0xB7, new Opcode(0xB7, "OR A,A", 1, new Runnable[] {() -> OR(cpu.getA())}));
        table.put(0xB8, new Opcode(0xB8, "CP A,B", 1, new Runnable[] {() -> CP(cpu.getB())}));
        table.put(0xB9, new Opcode(0xB9, "CP A,C", 1, new Runnable[] {() -> CP(cpu.getC())}));
        table.put(0xBA, new Opcode(0xBA, "CP A,D", 1, new Runnable[] {() -> CP(cpu.getD())}));
        table.put(0xBB, new Opcode(0xBB, "CP A,E", 1, new Runnable[] {() -> CP(cpu.getE())}));
        table.put(0xBC, new Opcode(0xBC, "CP A,H", 1, new Runnable[] {() -> CP(cpu.getH())}));
        table.put(0xBD, new Opcode(0xBD, "CP A,L", 1, new Runnable[] {() -> CP(cpu.getL())}));
        table.put(0xBE, new Opcode(0xBE, "CP A,(HL)", 1, new Runnable[] {() -> CP(bus.readByte(cpu.getHL()))}));
        table.put(0xBF, new Opcode(0xBF, "CP A,A", 1, new Runnable[] {() -> CP(cpu.getA())}));
        table.put(0xC0, new Opcode(0xC0, "RET NZ", 1));
        table.put(0xC1, new Opcode(0xC1, "POP BC", 1));
        table.put(0xC2, new Opcode(0xC2, "JP NZ,u16", 3));
        table.put(0xC3, new Opcode(0xC3, "JP u16", 3));
        table.put(0xC4, new Opcode(0xC4, "CALL NZ,u16", 3));
        table.put(0xC5, new Opcode(0xC5, "PUSH BC", 1));
        table.put(0xC6, new Opcode(0xC6, "ADD A,u8", 2, new Runnable[] {() -> ADD(bus.readByte(cpu.getPCInc()))}));
        table.put(0xC7, new Opcode(0xC7, "RST 00h", 1));
        table.put(0xC8, new Opcode(0xC8, "RET Z", 1));
        table.put(0xC9, new Opcode(0xC9, "RET", 1));
        table.put(0xCA, new Opcode(0xCA, "JP Z,u16", 3));
        table.put(0xCB, new Opcode(0xCB, "PREFIX CB", 1, new Runnable[] {}));
        table.put(0xCC, new Opcode(0xCC, "CALL Z,u16", 3));
        table.put(0xCD, new Opcode(0xCD, "CALL u16", 3));
        table.put(0xCE, new Opcode(0xCE, "ADC A,u8", 2, new Runnable[] {() -> ADC(bus.readByte(cpu.getPCInc()))}));
        table.put(0xCF, new Opcode(0xCF, "RST 08h", 1));
        table.put(0xD0, new Opcode(0xD0, "RET NC", 1));
        table.put(0xD1, new Opcode(0xD1, "POP DE", 1));
        table.put(0xD2, new Opcode(0xD2, "JP NC,u16", 3));
        table.put(0xD3, new Opcode(0xD3, "UNUSED", 1, new Runnable[] {}));
        table.put(0xD4, new Opcode(0xD4, "CALL NC,u16", 3));
        table.put(0xD5, new Opcode(0xD5, "PUSH DE", 1));
        table.put(0xD6, new Opcode(0xD6, "SUB A,u8", 2, new Runnable[] {() -> SUB(bus.readByte(cpu.getPCInc()))}));
        table.put(0xD7, new Opcode(0xD7, "RST 10h", 1));
        table.put(0xD8, new Opcode(0xD8, "RET C", 1));
        table.put(0xD9, new Opcode(0xD9, "RETI", 1));
        table.put(0xDA, new Opcode(0xDA, "JP C,u16", 3));
        table.put(0xDB, new Opcode(0xDB, "UNUSED", 1, new Runnable[] {}));
        table.put(0xDC, new Opcode(0xDC, "CALL C,u16", 3));
        table.put(0xDD, new Opcode(0xDD, "UNUSED", 1, new Runnable[] {}));
        table.put(0xDE, new Opcode(0xDE, "SBC A,u8", 2));
        table.put(0xDF, new Opcode(0xDF, "RST 18h", 1));
        table.put(0xE0, new Opcode(0xE0, "LD (FF00+u8),A", 2));
        table.put(0xE1, new Opcode(0xE1, "POP HL", 1));
        table.put(0xE2, new Opcode(0xE2, "LD (FF00+C),A", 1));
        table.put(0xE3, new Opcode(0xE3, "UNUSED", 1, new Runnable[] {}));
        table.put(0xE4, new Opcode(0xE4, "UNUSED", 1, new Runnable[] {}));
        table.put(0xE5, new Opcode(0xE5, "PUSH HL", 1));
        table.put(0xE6, new Opcode(0xE6, "AND A,u8", 2, new Runnable[] {() -> AND(bus.readByte(cpu.getPCInc()))}));
        table.put(0xE7, new Opcode(0xE7, "RST 20h", 1));
        table.put(0xE8, new Opcode(0xE8, "ADD SP,i8", 2));
        table.put(0xE9, new Opcode(0xE9, "JP HL", 1));
        table.put(0xEA, new Opcode(0xEA, "LD (u16),A", 3));
        table.put(0xEB, new Opcode(0xEB, "UNUSED", 1, new Runnable[] {}));
        table.put(0xEC, new Opcode(0xEC, "UNUSED", 1, new Runnable[] {}));
        table.put(0xED, new Opcode(0xED, "UNUSED", 1, new Runnable[] {}));
        table.put(0xEE, new Opcode(0xEE, "XOR A,u8", 2, new Runnable[] {() -> XOR(bus.readByte(cpu.getPCInc()))}));
        table.put(0xEF, new Opcode(0xEF, "RST 28h", 1));
        table.put(0xF0, new Opcode(0xF0, "LD A,(FF00+u8)", 2));
        table.put(0xF1, new Opcode(0xF1, "POP AF", 1));
        table.put(0xF2, new Opcode(0xF2, "LD A,(FF00+C)", 1));
        table.put(0xF3, new Opcode(0xF3, "DI", 1));
        table.put(0xF4, new Opcode(0xF4, "UNUSED", 1, new Runnable[] {}));
        table.put(0xF5, new Opcode(0xF5, "PUSH AF", 1));
        table.put(0xF6, new Opcode(0xF6, "OR A,u8", 2, new Runnable[] {() -> OR(bus.readByte(cpu.getPCInc()))}));
        table.put(0xF7, new Opcode(0xF7, "RST 30h", 1));
        table.put(0xF8, new Opcode(0xF8, "LD HL,SP+i8", 2));
        table.put(0xF9, new Opcode(0xF9, "LD SP,HL", 1));
        table.put(0xFA, new Opcode(0xFA, "LD A,(u16)", 3));
        table.put(0xFB, new Opcode(0xFB, "EI", 1));
        table.put(0xFC, new Opcode(0xFC, "UNUSED", 1, new Runnable[] {}));
        table.put(0xFD, new Opcode(0xFD, "UNUSED", 1, new Runnable[] {}));
        table.put(0xFE, new Opcode(0xFE, "CP A,u8", 2, new Runnable[] {() -> CP(bus.readByte(cpu.getPCInc()))}));
        table.put(0xFF, new Opcode(0xFF, "RST 38h", 1));

        return table;
    }

    private static Map<Integer, Opcode> createCBTable() {
        Map<Integer, Opcode> table = new HashMap<>();

        AtomicInteger temp = new AtomicInteger();

        table.put(0x00, new Opcode(0x00, "RLC B", 2, new Runnable[] {() -> cpu.setB(RLC(cpu.getB()))}));
        table.put(0x01, new Opcode(0x01, "RLC C", 2, new Runnable[] {() -> cpu.setC(RLC(cpu.getC()))}));
        table.put(0x02, new Opcode(0x02, "RLC D", 2, new Runnable[] {() -> cpu.setD(RLC(cpu.getD()))}));
        table.put(0x03, new Opcode(0x03, "RLC E", 2, new Runnable[] {() -> cpu.setE(RLC(cpu.getE()))}));
        table.put(0x04, new Opcode(0x04, "RLC H", 2, new Runnable[] {() -> cpu.setH(RLC(cpu.getH()))}));
        table.put(0x05, new Opcode(0x05, "RLC L", 2, new Runnable[] {() -> cpu.setL(RLC(cpu.getL()))}));
        table.put(0x06, new Opcode(0x06, "RLC (HL)", 2, new Runnable[] {
                () -> temp.set(bus.readByte(cpu.getHL())),
                () -> bus.writeByte(cpu.getHL(), RLC(temp.intValue()))
        }));
        table.put(0x07, new Opcode(0x07, "RLC A", 2, new Runnable[] {() -> cpu.setA(RLC(cpu.getA()))}));
        table.put(0x08, new Opcode(0x08, "RRC B", 2, new Runnable[] {() -> cpu.setB(RRC(cpu.getB()))}));
        table.put(0x09, new Opcode(0x09, "RRC C", 2, new Runnable[] {() -> cpu.setC(RRC(cpu.getC()))}));
        table.put(0x0A, new Opcode(0x0A, "RRC D", 2, new Runnable[] {() -> cpu.setD(RRC(cpu.getD()))}));
        table.put(0x0B, new Opcode(0x0B, "RRC E", 2, new Runnable[] {() -> cpu.setE(RRC(cpu.getE()))}));
        table.put(0x0C, new Opcode(0x0C, "RRC H", 2, new Runnable[] {() -> cpu.setH(RRC(cpu.getH()))}));
        table.put(0x0D, new Opcode(0x0D, "RRC L", 2, new Runnable[] {() -> cpu.setL(RRC(cpu.getL()))}));
        table.put(0x0E, new Opcode(0x0E, "RRC (HL)", 2, new Runnable[] {
                () -> temp.set(bus.readByte(cpu.getHL())),
                () -> bus.writeByte(cpu.getHL(), RRC(temp.intValue()))
        }));
        table.put(0x0F, new Opcode(0x0F, "RRC A", 2, new Runnable[] {() -> cpu.setA(RRC(cpu.getA()))}));
        table.put(0x10, new Opcode(0x10, "RL B", 2, new Runnable[] {() -> cpu.setB(RL(cpu.getB()))}));
        table.put(0x11, new Opcode(0x11, "RL C", 2, new Runnable[] {() -> cpu.setC(RL(cpu.getC()))}));
        table.put(0x12, new Opcode(0x12, "RL D", 2, new Runnable[] {() -> cpu.setD(RL(cpu.getD()))}));
        table.put(0x13, new Opcode(0x13, "RL E", 2, new Runnable[] {() -> cpu.setE(RL(cpu.getE()))}));
        table.put(0x14, new Opcode(0x14, "RL H", 2, new Runnable[] {() -> cpu.setH(RL(cpu.getH()))}));
        table.put(0x15, new Opcode(0x15, "RL L", 2, new Runnable[] {() -> cpu.setL(RL(cpu.getL()))}));
        table.put(0x16, new Opcode(0x16, "RL (HL)", 2));
        table.put(0x17, new Opcode(0x17, "RL A", 2, new Runnable[] {() -> cpu.setA(RL(cpu.getA()))}));
        table.put(0x18, new Opcode(0x18, "RR B", 2, new Runnable[] {() -> cpu.setB(RR(cpu.getB()))}));
        table.put(0x19, new Opcode(0x19, "RR C", 2, new Runnable[] {() -> cpu.setC(RR(cpu.getC()))}));
        table.put(0x1A, new Opcode(0x1A, "RR D", 2, new Runnable[] {() -> cpu.setD(RR(cpu.getD()))}));
        table.put(0x1B, new Opcode(0x1B, "RR E", 2, new Runnable[] {() -> cpu.setE(RR(cpu.getE()))}));
        table.put(0x1C, new Opcode(0x1C, "RR H", 2, new Runnable[] {() -> cpu.setH(RR(cpu.getH()))}));
        table.put(0x1D, new Opcode(0x1D, "RR L", 2, new Runnable[] {() -> cpu.setL(RR(cpu.getL()))}));
        table.put(0x1E, new Opcode(0x1E, "RR (HL)", 2, new Runnable[] {
                () -> temp.set(bus.readByte(cpu.getHL())),
                () -> bus.writeByte(cpu.getHL(), RR(temp.intValue()))
        }));
        table.put(0x1F, new Opcode(0x1F, "RR A", 2, new Runnable[] {() -> cpu.setA(RR(cpu.getA()))}));
        table.put(0x20, new Opcode(0x20, "SLA B", 2, new Runnable[] {() -> cpu.setB(SLA(cpu.getB()))}));
        table.put(0x21, new Opcode(0x21, "SLA C", 2, new Runnable[] {() -> cpu.setB(SLA(cpu.getB()))}));
        table.put(0x22, new Opcode(0x22, "SLA D", 2, new Runnable[] {() -> cpu.setD(SLA(cpu.getD()))}));
        table.put(0x23, new Opcode(0x23, "SLA E", 2, new Runnable[] {() -> cpu.setE(SLA(cpu.getE()))}));
        table.put(0x24, new Opcode(0x24, "SLA H", 2, new Runnable[] {() -> cpu.setH(SLA(cpu.getH()))}));
        table.put(0x25, new Opcode(0x25, "SLA L", 2, new Runnable[] {() -> cpu.setL(SLA(cpu.getL()))}));
        table.put(0x26, new Opcode(0x26, "SLA (HL)", 2, new Runnable[] {
                () -> temp.set(bus.readByte(cpu.getHL())),
                () -> bus.writeByte(cpu.getHL(), SLA(temp.intValue()))
        }));
        table.put(0x27, new Opcode(0x27, "SLA A", 2, new Runnable[] {() -> cpu.setA(SLA(cpu.getA()))}));
        table.put(0x28, new Opcode(0x28, "SRA B", 2, new Runnable[] {() -> cpu.setB(SRA(cpu.getB()))}));
        table.put(0x29, new Opcode(0x29, "SRA C", 2, new Runnable[] {() -> cpu.setC(SRA(cpu.getC()))}));
        table.put(0x2A, new Opcode(0x2A, "SRA D", 2, new Runnable[] {() -> cpu.setD(SRA(cpu.getD()))}));
        table.put(0x2B, new Opcode(0x2B, "SRA E", 2, new Runnable[] {() -> cpu.setE(SRA(cpu.getE()))}));
        table.put(0x2C, new Opcode(0x2C, "SRA H", 2, new Runnable[] {() -> cpu.setH(SRA(cpu.getH()))}));
        table.put(0x2D, new Opcode(0x2D, "SRA L", 2, new Runnable[] {() -> cpu.setL(SRA(cpu.getL()))}));
        table.put(0x2E, new Opcode(0x2E, "SRA (HL)", 2, new Runnable[] {
                () -> temp.set(bus.readByte(cpu.getHL())),
                () -> bus.writeByte(cpu.getHL(), SRA(temp.intValue()))
        }));
        table.put(0x2F, new Opcode(0x2F, "SRA A", 2, new Runnable[] {() -> cpu.setA(SRA(cpu.getA()))}));
        table.put(0x30, new Opcode(0x30, "SWAP B", 2, new Runnable[] {() -> cpu.setB(SWAP(cpu.getB()))}));
        table.put(0x31, new Opcode(0x31, "SWAP C", 2, new Runnable[] {() -> cpu.setC(SWAP(cpu.getC()))}));
        table.put(0x32, new Opcode(0x32, "SWAP D", 2, new Runnable[] {() -> cpu.setD(SWAP(cpu.getD()))}));
        table.put(0x33, new Opcode(0x33, "SWAP E", 2, new Runnable[] {() -> cpu.setE(SWAP(cpu.getE()))}));
        table.put(0x34, new Opcode(0x34, "SWAP H", 2, new Runnable[] {() -> cpu.setH(SWAP(cpu.getH()))}));
        table.put(0x35, new Opcode(0x35, "SWAP L", 2, new Runnable[] {() -> cpu.setL(SWAP(cpu.getL()))}));
        table.put(0x36, new Opcode(0x36, "SWAP (HL)", 2, new Runnable[] {
                () -> temp.set(bus.readByte(cpu.getHL())),
                () -> bus.writeByte(cpu.getHL(), SWAP(temp.intValue()))
        }));
        table.put(0x37, new Opcode(0x37, "SWAP A", 2, new Runnable[] {() -> cpu.setA(SWAP(cpu.getA()))}));
        table.put(0x38, new Opcode(0x38, "SRL B", 2, new Runnable[] {() -> cpu.setB(SRL(cpu.getB()))}));
        table.put(0x39, new Opcode(0x39, "SRL C", 2, new Runnable[] {() -> cpu.setC(SRL(cpu.getC()))}));
        table.put(0x3A, new Opcode(0x3A, "SRL D", 2, new Runnable[] {() -> cpu.setD(SRL(cpu.getD()))}));
        table.put(0x3B, new Opcode(0x3B, "SRL E", 2, new Runnable[] {() -> cpu.setE(SRL(cpu.getE()))}));
        table.put(0x3C, new Opcode(0x3C, "SRL H", 2, new Runnable[] {() -> cpu.setH(SRL(cpu.getH()))}));
        table.put(0x3D, new Opcode(0x3D, "SRL L", 2, new Runnable[] {() -> cpu.setL(SRL(cpu.getL()))}));
        table.put(0x3E, new Opcode(0x3E, "SRL (HL)", 2, new Runnable[] {
                () -> temp.set(bus.readByte(cpu.getHL())),
                () -> bus.writeByte(cpu.getHL(), SRL(temp.intValue()))
        }));
        table.put(0x3F, new Opcode(0x3F, "SRL A", 2, new Runnable[] {() -> cpu.setA(SRL(cpu.getA()))}));
        table.put(0x40, new Opcode(0x40, "BIT 0,B", 2, new Runnable[] {() -> BIT(cpu.getB(), 0)}));
        table.put(0x41, new Opcode(0x41, "BIT 0,C", 2, new Runnable[] {() -> BIT(cpu.getC(), 0)}));
        table.put(0x42, new Opcode(0x42, "BIT 0,D", 2, new Runnable[] {() -> BIT(cpu.getD(), 0)}));
        table.put(0x43, new Opcode(0x43, "BIT 0,E", 2, new Runnable[] {() -> BIT(cpu.getE(), 0)}));
        table.put(0x44, new Opcode(0x44, "BIT 0,H", 2, new Runnable[] {() -> BIT(cpu.getH(), 0)}));
        table.put(0x45, new Opcode(0x45, "BIT 0,L", 2, new Runnable[] {() -> BIT(cpu.getL(), 0)}));
        table.put(0x46, new Opcode(0x46, "BIT 0,(HL)", 2, new Runnable[] {() -> BIT(bus.readByte(cpu.getHL()), 0)}));
        table.put(0x47, new Opcode(0x47, "BIT 0,A", 2, new Runnable[] {() -> BIT(cpu.getA(), 0)}));
        table.put(0x48, new Opcode(0x48, "BIT 1,B", 2, new Runnable[] {() -> BIT(cpu.getB(), 1)}));
        table.put(0x49, new Opcode(0x49, "BIT 1,C", 2, new Runnable[] {() -> BIT(cpu.getC(), 1)}));
        table.put(0x4A, new Opcode(0x4A, "BIT 1,D", 2, new Runnable[] {() -> BIT(cpu.getD(), 1)}));
        table.put(0x4B, new Opcode(0x4B, "BIT 1,E", 2, new Runnable[] {() -> BIT(cpu.getE(), 1)}));
        table.put(0x4C, new Opcode(0x4C, "BIT 1,H", 2, new Runnable[] {() -> BIT(cpu.getH(), 1)}));
        table.put(0x4D, new Opcode(0x4D, "BIT 1,L", 2, new Runnable[] {() -> BIT(cpu.getL(), 1)}));
        table.put(0x4E, new Opcode(0x4E, "BIT 1,(HL)", 2, new Runnable[] {() -> BIT(bus.readByte(cpu.getHL()), 1)}));
        table.put(0x4F, new Opcode(0x4F, "BIT 1,A", 2, new Runnable[] {() -> BIT(cpu.getA(), 1)}));
        table.put(0x50, new Opcode(0x50, "BIT 2,B", 2, new Runnable[] {() -> BIT(cpu.getB(), 2)}));
        table.put(0x51, new Opcode(0x51, "BIT 2,C", 2, new Runnable[] {() -> BIT(cpu.getC(), 2)}));
        table.put(0x52, new Opcode(0x52, "BIT 2,D", 2, new Runnable[] {() -> BIT(cpu.getD(), 2)}));
        table.put(0x53, new Opcode(0x53, "BIT 2,E", 2, new Runnable[] {() -> BIT(cpu.getE(), 2)}));
        table.put(0x54, new Opcode(0x54, "BIT 2,H", 2, new Runnable[] {() -> BIT(cpu.getH(), 2)}));
        table.put(0x55, new Opcode(0x55, "BIT 2,L", 2, new Runnable[] {() -> BIT(cpu.getL(), 2)}));
        table.put(0x56, new Opcode(0x56, "BIT 2,(HL)", 2, new Runnable[] {() -> BIT(bus.readByte(cpu.getHL()), 2)}));
        table.put(0x57, new Opcode(0x57, "BIT 2,A", 2, new Runnable[] {() -> BIT(cpu.getA(), 2)}));
        table.put(0x58, new Opcode(0x58, "BIT 3,B", 2, new Runnable[] {() -> BIT(cpu.getB(), 3)}));
        table.put(0x59, new Opcode(0x59, "BIT 3,C", 2, new Runnable[] {() -> BIT(cpu.getC(), 3)}));
        table.put(0x5A, new Opcode(0x5A, "BIT 3,D", 2, new Runnable[] {() -> BIT(cpu.getD(), 3)}));
        table.put(0x5B, new Opcode(0x5B, "BIT 3,E", 2, new Runnable[] {() -> BIT(cpu.getE(), 3)}));
        table.put(0x5C, new Opcode(0x5C, "BIT 3,H", 2, new Runnable[] {() -> BIT(cpu.getH(), 3)}));
        table.put(0x5D, new Opcode(0x5D, "BIT 3,L", 2, new Runnable[] {() -> BIT(cpu.getL(), 3)}));
        table.put(0x5E, new Opcode(0x5E, "BIT 3,(HL)", 2, new Runnable[] {() -> BIT(bus.readByte(cpu.getHL()), 3)}));
        table.put(0x5F, new Opcode(0x5F, "BIT 3,A", 2, new Runnable[] {() -> BIT(cpu.getA(), 3)}));
        table.put(0x60, new Opcode(0x60, "BIT 4,B", 2, new Runnable[] {() -> BIT(cpu.getB(), 4)}));
        table.put(0x61, new Opcode(0x61, "BIT 4,C", 2, new Runnable[] {() -> BIT(cpu.getC(), 4)}));
        table.put(0x62, new Opcode(0x62, "BIT 4,D", 2, new Runnable[] {() -> BIT(cpu.getD(), 4)}));
        table.put(0x63, new Opcode(0x63, "BIT 4,E", 2, new Runnable[] {() -> BIT(cpu.getE(), 4)}));
        table.put(0x64, new Opcode(0x64, "BIT 4,H", 2, new Runnable[] {() -> BIT(cpu.getH(), 4)}));
        table.put(0x65, new Opcode(0x65, "BIT 4,L", 2, new Runnable[] {() -> BIT(cpu.getL(), 4)}));
        table.put(0x66, new Opcode(0x66, "BIT 4,(HL)", 2, new Runnable[] {() -> BIT(bus.readByte(cpu.getHL()), 4)}));
        table.put(0x67, new Opcode(0x67, "BIT 4,A", 2, new Runnable[] {() -> BIT(cpu.getA(), 4)}));
        table.put(0x68, new Opcode(0x68, "BIT 5,B", 2, new Runnable[] {() -> BIT(cpu.getB(), 5)}));
        table.put(0x69, new Opcode(0x69, "BIT 5,C", 2, new Runnable[] {() -> BIT(cpu.getC(), 5)}));
        table.put(0x6A, new Opcode(0x6A, "BIT 5,D", 2, new Runnable[] {() -> BIT(cpu.getD(), 5)}));
        table.put(0x6B, new Opcode(0x6B, "BIT 5,E", 2, new Runnable[] {() -> BIT(cpu.getE(), 5)}));
        table.put(0x6C, new Opcode(0x6C, "BIT 5,H", 2, new Runnable[] {() -> BIT(cpu.getH(), 5)}));
        table.put(0x6D, new Opcode(0x6D, "BIT 5,L", 2, new Runnable[] {() -> BIT(cpu.getL(), 5)}));
        table.put(0x6E, new Opcode(0x6E, "BIT 5,(HL)", 2, new Runnable[] {() -> BIT(bus.readByte(cpu.getHL()), 5)}));
        table.put(0x6F, new Opcode(0x6F, "BIT 5,A", 2, new Runnable[] {() -> BIT(cpu.getA(), 5)}));
        table.put(0x70, new Opcode(0x70, "BIT 6,B", 2, new Runnable[] {() -> BIT(cpu.getB(), 6)}));
        table.put(0x71, new Opcode(0x71, "BIT 6,C", 2, new Runnable[] {() -> BIT(cpu.getC(), 6)}));
        table.put(0x72, new Opcode(0x72, "BIT 6,D", 2, new Runnable[] {() -> BIT(cpu.getD(), 6)}));
        table.put(0x73, new Opcode(0x73, "BIT 6,E", 2, new Runnable[] {() -> BIT(cpu.getE(), 6)}));
        table.put(0x74, new Opcode(0x74, "BIT 6,H", 2, new Runnable[] {() -> BIT(cpu.getH(), 6)}));
        table.put(0x75, new Opcode(0x75, "BIT 6,L", 2, new Runnable[] {() -> BIT(cpu.getL(), 6)}));
        table.put(0x76, new Opcode(0x76, "BIT 6,(HL)", 2, new Runnable[] {() -> BIT(bus.readByte(cpu.getHL()), 6)}));
        table.put(0x77, new Opcode(0x77, "BIT 6,A", 2, new Runnable[] {() -> BIT(cpu.getA(), 6)}));
        table.put(0x78, new Opcode(0x78, "BIT 7,B", 2, new Runnable[] {() -> BIT(cpu.getB(), 7)}));
        table.put(0x79, new Opcode(0x79, "BIT 7,C", 2, new Runnable[] {() -> BIT(cpu.getC(), 7)}));
        table.put(0x7A, new Opcode(0x7A, "BIT 7,D", 2, new Runnable[] {() -> BIT(cpu.getD(), 7)}));
        table.put(0x7B, new Opcode(0x7B, "BIT 7,E", 2, new Runnable[] {() -> BIT(cpu.getE(), 7)}));
        table.put(0x7C, new Opcode(0x7C, "BIT 7,H", 2, new Runnable[] {() -> BIT(cpu.getH(), 7)}));
        table.put(0x7D, new Opcode(0x7D, "BIT 7,L", 2, new Runnable[] {() -> BIT(cpu.getL(), 7)}));
        table.put(0x7E, new Opcode(0x7E, "BIT 7,(HL)", 2, new Runnable[] {() -> BIT(bus.readByte(cpu.getHL()), 7)}));
        table.put(0x7F, new Opcode(0x7F, "BIT 7,A", 2, new Runnable[] {() -> BIT(cpu.getA(), 7)}));
        table.put(0x80, new Opcode(0x80, "RES 0,B", 2, new Runnable[] {() -> cpu.setB(RES(cpu.getB(), 0))}));
        table.put(0x81, new Opcode(0x81, "RES 0,C", 2, new Runnable[] {() -> cpu.setC(RES(cpu.getC(), 0))}));
        table.put(0x82, new Opcode(0x82, "RES 0,D", 2, new Runnable[] {() -> cpu.setD(RES(cpu.getD(), 0))}));
        table.put(0x83, new Opcode(0x83, "RES 0,E", 2, new Runnable[] {() -> cpu.setE(RES(cpu.getE(), 0))}));
        table.put(0x84, new Opcode(0x84, "RES 0,H", 2, new Runnable[] {() -> cpu.setH(RES(cpu.getH(), 0))}));
        table.put(0x85, new Opcode(0x85, "RES 0,L", 2, new Runnable[] {() -> cpu.setL(RES(cpu.getL(), 0))}));
        table.put(0x86, new Opcode(0x86, "RES 0,(HL)", 2, new Runnable[] {
                () -> temp.set(bus.readByte(cpu.getHL())),
                () -> bus.writeByte(cpu.getHL(), RES(temp.intValue(), 0))
        }));
        table.put(0x87, new Opcode(0x87, "RES 0,A", 2, new Runnable[] {() -> cpu.setA(RES(cpu.getA(), 0))}));
        table.put(0x88, new Opcode(0x88, "RES 1,B", 2, new Runnable[] {() -> cpu.setB(RES(cpu.getB(), 1))}));
        table.put(0x89, new Opcode(0x89, "RES 1,C", 2, new Runnable[] {() -> cpu.setC(RES(cpu.getC(), 1))}));
        table.put(0x8A, new Opcode(0x8A, "RES 1,D", 2, new Runnable[] {() -> cpu.setD(RES(cpu.getD(), 1))}));
        table.put(0x8B, new Opcode(0x8B, "RES 1,E", 2, new Runnable[] {() -> cpu.setE(RES(cpu.getE(), 1))}));
        table.put(0x8C, new Opcode(0x8C, "RES 1,H", 2, new Runnable[] {() -> cpu.setH(RES(cpu.getH(), 1))}));
        table.put(0x8D, new Opcode(0x8D, "RES 1,L", 2, new Runnable[] {() -> cpu.setL(RES(cpu.getL(), 1))}));
        table.put(0x8E, new Opcode(0x8E, "RES 1,(HL)", 2, new Runnable[] {
                () -> temp.set(bus.readByte(cpu.getHL())),
                () -> bus.writeByte(cpu.getHL(), RES(temp.intValue(), 1))
        }));
        table.put(0x8F, new Opcode(0x8F, "RES 1,A", 2, new Runnable[] {() -> cpu.setA(RES(cpu.getA(), 1))}));
        table.put(0x90, new Opcode(0x90, "RES 2,B", 2, new Runnable[] {() -> cpu.setB(RES(cpu.getB(), 2))}));
        table.put(0x91, new Opcode(0x91, "RES 2,C", 2, new Runnable[] {() -> cpu.setC(RES(cpu.getC(), 2))}));
        table.put(0x92, new Opcode(0x92, "RES 2,D", 2, new Runnable[] {() -> cpu.setD(RES(cpu.getD(), 2))}));
        table.put(0x93, new Opcode(0x93, "RES 2,E", 2, new Runnable[] {() -> cpu.setE(RES(cpu.getE(), 2))}));
        table.put(0x94, new Opcode(0x94, "RES 2,H", 2, new Runnable[] {() -> cpu.setH(RES(cpu.getH(), 2))}));
        table.put(0x95, new Opcode(0x95, "RES 2,L", 2, new Runnable[] {() -> cpu.setL(RES(cpu.getL(), 2))}));
        table.put(0x96, new Opcode(0x96, "RES 2,(HL)", 2, new Runnable[] {
                () -> temp.set(bus.readByte(cpu.getHL())),
                () -> bus.writeByte(cpu.getHL(), RES(temp.intValue(), 2))
        }));
        table.put(0x97, new Opcode(0x97, "RES 2,A", 2, new Runnable[] {() -> cpu.setA(RES(cpu.getA(), 2))}));
        table.put(0x98, new Opcode(0x98, "RES 3,B", 2, new Runnable[] {() -> cpu.setB(RES(cpu.getB(), 3))}));
        table.put(0x99, new Opcode(0x99, "RES 3,C", 2, new Runnable[] {() -> cpu.setC(RES(cpu.getC(), 3))}));
        table.put(0x9A, new Opcode(0x9A, "RES 3,D", 2, new Runnable[] {() -> cpu.setD(RES(cpu.getD(), 3))}));
        table.put(0x9B, new Opcode(0x9B, "RES 3,E", 2, new Runnable[] {() -> cpu.setE(RES(cpu.getE(), 3))}));
        table.put(0x9C, new Opcode(0x9C, "RES 3,H", 2, new Runnable[] {() -> cpu.setH(RES(cpu.getH(), 3))}));
        table.put(0x9D, new Opcode(0x9D, "RES 3,L", 2, new Runnable[] {() -> cpu.setL(RES(cpu.getL(), 3))}));
        table.put(0x9E, new Opcode(0x9E, "RES 3,(HL)", 2, new Runnable[] {
                () -> temp.set(bus.readByte(cpu.getHL())),
                () -> bus.writeByte(cpu.getHL(), RES(temp.intValue(), 3))
        }));
        table.put(0x9F, new Opcode(0x9F, "RES 3,A", 2, new Runnable[] {() -> cpu.setA(RES(cpu.getA(), 3))}));
        table.put(0xA0, new Opcode(0xA0, "RES 4,B", 2, new Runnable[] {() -> cpu.setB(RES(cpu.getB(), 4))}));
        table.put(0xA1, new Opcode(0xA1, "RES 4,C", 2, new Runnable[] {() -> cpu.setC(RES(cpu.getC(), 4))}));
        table.put(0xA2, new Opcode(0xA2, "RES 4,D", 2, new Runnable[] {() -> cpu.setD(RES(cpu.getD(), 4))}));
        table.put(0xA3, new Opcode(0xA3, "RES 4,E", 2, new Runnable[] {() -> cpu.setE(RES(cpu.getE(), 4))}));
        table.put(0xA4, new Opcode(0xA4, "RES 4,H", 2, new Runnable[] {() -> cpu.setH(RES(cpu.getH(), 4))}));
        table.put(0xA5, new Opcode(0xA5, "RES 4,L", 2, new Runnable[] {() -> cpu.setL(RES(cpu.getL(), 4))}));
        table.put(0xA6, new Opcode(0xA6, "RES 4,(HL)", 2, new Runnable[] {
                () -> temp.set(bus.readByte(cpu.getHL())),
                () -> bus.writeByte(cpu.getHL(), RES(temp.intValue(), 4))
        }));
        table.put(0xA7, new Opcode(0xA7, "RES 4,A", 2, new Runnable[] {() -> cpu.setA(RES(cpu.getA(), 4))}));
        table.put(0xA8, new Opcode(0xA8, "RES 5,B", 2, new Runnable[] {() -> cpu.setB(RES(cpu.getB(), 5))}));
        table.put(0xA9, new Opcode(0xA9, "RES 5,C", 2, new Runnable[] {() -> cpu.setC(RES(cpu.getC(), 5))}));
        table.put(0xAA, new Opcode(0xAA, "RES 5,D", 2, new Runnable[] {() -> cpu.setD(RES(cpu.getD(), 5))}));
        table.put(0xAB, new Opcode(0xAB, "RES 5,E", 2, new Runnable[] {() -> cpu.setE(RES(cpu.getE(), 5))}));
        table.put(0xAC, new Opcode(0xAC, "RES 5,H", 2, new Runnable[] {() -> cpu.setH(RES(cpu.getH(), 5))}));
        table.put(0xAD, new Opcode(0xAD, "RES 5,L", 2, new Runnable[] {() -> cpu.setL(RES(cpu.getL(), 5))}));
        table.put(0xAE, new Opcode(0xAE, "RES 5,(HL)", 2, new Runnable[] {
                () -> temp.set(bus.readByte(cpu.getHL())),
                () -> bus.writeByte(cpu.getHL(), RES(temp.intValue(), 5))
        }));
        table.put(0xAF, new Opcode(0xAF, "RES 5,A", 2, new Runnable[] {() -> cpu.setA(RES(cpu.getA(), 5))}));
        table.put(0xB0, new Opcode(0xB0, "RES 6,B", 2, new Runnable[] {() -> cpu.setB(RES(cpu.getB(), 6))}));
        table.put(0xB1, new Opcode(0xB1, "RES 6,C", 2, new Runnable[] {() -> cpu.setC(RES(cpu.getC(), 6))}));
        table.put(0xB2, new Opcode(0xB2, "RES 6,D", 2, new Runnable[] {() -> cpu.setD(RES(cpu.getD(), 6))}));
        table.put(0xB3, new Opcode(0xB3, "RES 6,E", 2, new Runnable[] {() -> cpu.setE(RES(cpu.getE(), 6))}));
        table.put(0xB4, new Opcode(0xB4, "RES 6,H", 2, new Runnable[] {() -> cpu.setH(RES(cpu.getH(), 6))}));
        table.put(0xB5, new Opcode(0xB5, "RES 6,L", 2, new Runnable[] {() -> cpu.setL(RES(cpu.getL(), 6))}));
        table.put(0xB6, new Opcode(0xB6, "RES 6,(HL)", 2, new Runnable[] {
                () -> temp.set(bus.readByte(cpu.getHL())),
                () -> bus.writeByte(cpu.getHL(), RES(temp.intValue(), 6))
        }));
        table.put(0xB7, new Opcode(0xB7, "RES 6,A", 2, new Runnable[] {() -> cpu.setA(RES(cpu.getA(), 6))}));
        table.put(0xB8, new Opcode(0xB8, "RES 7,B", 2, new Runnable[] {() -> cpu.setB(RES(cpu.getB(), 7))}));
        table.put(0xB9, new Opcode(0xB9, "RES 7,C", 2, new Runnable[] {() -> cpu.setC(RES(cpu.getC(), 7))}));
        table.put(0xBA, new Opcode(0xBA, "RES 7,D", 2, new Runnable[] {() -> cpu.setD(RES(cpu.getD(), 7))}));
        table.put(0xBB, new Opcode(0xBB, "RES 7,E", 2, new Runnable[] {() -> cpu.setE(RES(cpu.getE(), 7))}));
        table.put(0xBC, new Opcode(0xBC, "RES 7,H", 2, new Runnable[] {() -> cpu.setH(RES(cpu.getH(), 7))}));
        table.put(0xBD, new Opcode(0xBD, "RES 7,L", 2, new Runnable[] {() -> cpu.setL(RES(cpu.getL(), 7))}));
        table.put(0xBE, new Opcode(0xBE, "RES 7,(HL)", 2, new Runnable[] {
                () -> temp.set(bus.readByte(cpu.getHL())),
                () -> bus.writeByte(cpu.getHL(), RES(temp.intValue(), 7))
        }));
        table.put(0xBF, new Opcode(0xBF, "RES 7,A", 2, new Runnable[] {() -> cpu.setA(RES(cpu.getA(), 7))}));
        table.put(0xC0, new Opcode(0xC0, "SET 0,B", 2, new Runnable[] {() -> cpu.setB(SET(cpu.getB(), 0))}));
        table.put(0xC1, new Opcode(0xC1, "SET 0,C", 2, new Runnable[] {() -> cpu.setC(SET(cpu.getC(), 0))}));
        table.put(0xC2, new Opcode(0xC2, "SET 0,D", 2, new Runnable[] {() -> cpu.setD(SET(cpu.getD(), 0))}));
        table.put(0xC3, new Opcode(0xC3, "SET 0,E", 2, new Runnable[] {() -> cpu.setE(SET(cpu.getE(), 0))}));
        table.put(0xC4, new Opcode(0xC4, "SET 0,H", 2, new Runnable[] {() -> cpu.setH(SET(cpu.getH(), 0))}));
        table.put(0xC5, new Opcode(0xC5, "SET 0,L", 2, new Runnable[] {() -> cpu.setL(SET(cpu.getL(), 0))}));
        table.put(0xC6, new Opcode(0xC6, "SET 0,(HL)", 2, new Runnable[] {
                () -> temp.set(bus.readByte(cpu.getHL())),
                () -> bus.writeByte(cpu.getHL(), SET(temp.intValue(), 0))
        }));
        table.put(0xC7, new Opcode(0xC7, "SET 0,A", 2, new Runnable[] {() -> cpu.setA(SET(cpu.getA(), 0))}));
        table.put(0xC8, new Opcode(0xC8, "SET 1,B", 2, new Runnable[] {() -> cpu.setB(SET(cpu.getB(), 1))}));
        table.put(0xC9, new Opcode(0xC9, "SET 1,C", 2, new Runnable[] {() -> cpu.setC(SET(cpu.getC(), 1))}));
        table.put(0xCA, new Opcode(0xCA, "SET 1,D", 2, new Runnable[] {() -> cpu.setD(SET(cpu.getD(), 1))}));
        table.put(0xCB, new Opcode(0xCB, "SET 1,E", 2, new Runnable[] {() -> cpu.setE(SET(cpu.getE(), 1))}));
        table.put(0xCC, new Opcode(0xCC, "SET 1,H", 2, new Runnable[] {() -> cpu.setH(SET(cpu.getH(), 1))}));
        table.put(0xCD, new Opcode(0xCD, "SET 1,L", 2, new Runnable[] {() -> cpu.setL(SET(cpu.getL(), 1))}));
        table.put(0xCE, new Opcode(0xCE, "SET 1,(HL)", 2, new Runnable[] {
                () -> temp.set(bus.readByte(cpu.getHL())),
                () -> bus.writeByte(cpu.getHL(), SET(temp.intValue(), 1))
        }));
        table.put(0xCF, new Opcode(0xCF, "SET 1,A", 2, new Runnable[] {() -> cpu.setA(SET(cpu.getA(), 1))}));
        table.put(0xD0, new Opcode(0xD0, "SET 2,B", 2, new Runnable[] {() -> cpu.setB(SET(cpu.getB(), 2))}));
        table.put(0xD1, new Opcode(0xD1, "SET 2,C", 2, new Runnable[] {() -> cpu.setC(SET(cpu.getC(), 2))}));
        table.put(0xD2, new Opcode(0xD2, "SET 2,D", 2, new Runnable[] {() -> cpu.setD(SET(cpu.getD(), 2))}));
        table.put(0xD3, new Opcode(0xD3, "SET 2,E", 2, new Runnable[] {() -> cpu.setE(SET(cpu.getE(), 2))}));
        table.put(0xD4, new Opcode(0xD4, "SET 2,H", 2, new Runnable[] {() -> cpu.setH(SET(cpu.getH(), 2))}));
        table.put(0xD5, new Opcode(0xD5, "SET 2,L", 2, new Runnable[] {() -> cpu.setL(SET(cpu.getL(), 2))}));
        table.put(0xD6, new Opcode(0xD6, "SET 2,(HL)", 2, new Runnable[] {
                () -> temp.set(bus.readByte(cpu.getHL())),
                () -> bus.writeByte(cpu.getHL(), SET(temp.intValue(), 2))
        }));
        table.put(0xD7, new Opcode(0xD7, "SET 2,A", 2, new Runnable[] {() -> cpu.setA(SET(cpu.getA(), 2))}));
        table.put(0xD8, new Opcode(0xD8, "SET 3,B", 2, new Runnable[] {() -> cpu.setB(SET(cpu.getB(), 3))}));
        table.put(0xD9, new Opcode(0xD9, "SET 3,C", 2, new Runnable[] {() -> cpu.setC(SET(cpu.getC(), 3))}));
        table.put(0xDA, new Opcode(0xDA, "SET 3,D", 2, new Runnable[] {() -> cpu.setD(SET(cpu.getD(), 3))}));
        table.put(0xDB, new Opcode(0xDB, "SET 3,E", 2, new Runnable[] {() -> cpu.setE(SET(cpu.getE(), 3))}));
        table.put(0xDC, new Opcode(0xDC, "SET 3,H", 2, new Runnable[] {() -> cpu.setH(SET(cpu.getH(), 3))}));
        table.put(0xDD, new Opcode(0xDD, "SET 3,L", 2, new Runnable[] {() -> cpu.setL(SET(cpu.getL(), 3))}));
        table.put(0xDE, new Opcode(0xDE, "SET 3,(HL)", 2, new Runnable[] {
                () -> temp.set(bus.readByte(cpu.getHL())),
                () -> bus.writeByte(cpu.getHL(), SET(temp.intValue(), 3))
        }));
        table.put(0xDF, new Opcode(0xDF, "SET 3,A", 2, new Runnable[] {() -> cpu.setA(SET(cpu.getA(), 3))}));
        table.put(0xE0, new Opcode(0xE0, "SET 4,B", 2, new Runnable[] {() -> cpu.setB(SET(cpu.getB(), 4))}));
        table.put(0xE1, new Opcode(0xE1, "SET 4,C", 2, new Runnable[] {() -> cpu.setC(SET(cpu.getC(), 4))}));
        table.put(0xE2, new Opcode(0xE2, "SET 4,D", 2, new Runnable[] {() -> cpu.setD(SET(cpu.getD(), 4))}));
        table.put(0xE3, new Opcode(0xE3, "SET 4,E", 2, new Runnable[] {() -> cpu.setE(SET(cpu.getE(), 4))}));
        table.put(0xE4, new Opcode(0xE4, "SET 4,H", 2, new Runnable[] {() -> cpu.setH(SET(cpu.getH(), 4))}));
        table.put(0xE5, new Opcode(0xE5, "SET 4,L", 2, new Runnable[] {() -> cpu.setL(SET(cpu.getL(), 4))}));
        table.put(0xE6, new Opcode(0xE6, "SET 4,(HL)", 2, new Runnable[] {
                () -> temp.set(bus.readByte(cpu.getHL())),
                () -> bus.writeByte(cpu.getHL(), SET(temp.intValue(), 4))
        }));
        table.put(0xE7, new Opcode(0xE7, "SET 4,A", 2, new Runnable[] {() -> cpu.setA(SET(cpu.getA(), 4))}));
        table.put(0xE8, new Opcode(0xE8, "SET 5,B", 2, new Runnable[] {() -> cpu.setB(SET(cpu.getB(), 5))}));
        table.put(0xE9, new Opcode(0xE9, "SET 5,C", 2, new Runnable[] {() -> cpu.setC(SET(cpu.getC(), 5))}));
        table.put(0xEA, new Opcode(0xEA, "SET 5,D", 2, new Runnable[] {() -> cpu.setD(SET(cpu.getD(), 5))}));
        table.put(0xEB, new Opcode(0xEB, "SET 5,E", 2, new Runnable[] {() -> cpu.setE(SET(cpu.getE(), 5))}));
        table.put(0xEC, new Opcode(0xEC, "SET 5,H", 2, new Runnable[] {() -> cpu.setH(SET(cpu.getH(), 5))}));
        table.put(0xED, new Opcode(0xED, "SET 5,L", 2, new Runnable[] {() -> cpu.setL(SET(cpu.getL(), 5))}));
        table.put(0xEE, new Opcode(0xEE, "SET 5,(HL)", 2, new Runnable[] {
                () -> temp.set(bus.readByte(cpu.getHL())),
                () -> bus.writeByte(cpu.getHL(), SET(temp.intValue(), 5))
        }));
        table.put(0xEF, new Opcode(0xEF, "SET 5,A", 2, new Runnable[] {() -> cpu.setA(SET(cpu.getA(), 5))}));
        table.put(0xF0, new Opcode(0xF0, "SET 6,B", 2, new Runnable[] {() -> cpu.setB(SET(cpu.getB(), 6))}));
        table.put(0xF1, new Opcode(0xF1, "SET 6,C", 2, new Runnable[] {() -> cpu.setC(SET(cpu.getC(), 6))}));
        table.put(0xF2, new Opcode(0xF2, "SET 6,D", 2, new Runnable[] {() -> cpu.setD(SET(cpu.getD(), 6))}));
        table.put(0xF3, new Opcode(0xF3, "SET 6,E", 2, new Runnable[] {() -> cpu.setE(SET(cpu.getE(), 6))}));
        table.put(0xF4, new Opcode(0xF4, "SET 6,H", 2, new Runnable[] {() -> cpu.setH(SET(cpu.getH(), 6))}));
        table.put(0xF5, new Opcode(0xF5, "SET 6,L", 2, new Runnable[] {() -> cpu.setL(SET(cpu.getL(), 6))}));
        table.put(0xF6, new Opcode(0xF6, "SET 6,(HL)", 2, new Runnable[] {
                () -> temp.set(bus.readByte(cpu.getHL())),
                () -> bus.writeByte(cpu.getHL(), SET(temp.intValue(), 6))
        }));
        table.put(0xF7, new Opcode(0xF7, "SET 6,A", 2, new Runnable[] {() -> cpu.setA(SET(cpu.getA(), 6))}));
        table.put(0xF8, new Opcode(0xF8, "SET 7,B", 2, new Runnable[] {() -> cpu.setB(SET(cpu.getB(), 7))}));
        table.put(0xF9, new Opcode(0xF9, "SET 7,C", 2, new Runnable[] {() -> cpu.setC(SET(cpu.getC(), 7))}));
        table.put(0xFA, new Opcode(0xFA, "SET 7,D", 2, new Runnable[] {() -> cpu.setD(SET(cpu.getD(), 7))}));
        table.put(0xFB, new Opcode(0xFB, "SET 7,E", 2, new Runnable[] {() -> cpu.setE(SET(cpu.getE(), 7))}));
        table.put(0xFC, new Opcode(0xFC, "SET 7,H", 2, new Runnable[] {() -> cpu.setH(SET(cpu.getH(), 7))}));
        table.put(0xFD, new Opcode(0xFD, "SET 7,L", 2, new Runnable[] {() -> cpu.setL(SET(cpu.getL(), 7))}));
        table.put(0xFE, new Opcode(0xFE, "SET 7,(HL)", 2, new Runnable[] {
                () -> temp.set(bus.readByte(cpu.getHL())),
                () -> bus.writeByte(cpu.getHL(), SET(temp.intValue(), 7))
        }));
        table.put(0xFF, new Opcode(0xFF, "SET 7,A", 2, new Runnable[] {() -> cpu.setA(SET(cpu.getA(), 7))}));

        return table;
    }

    private static int RLC(int value) {
        int result = ((value << 1) | (value >> 7)) & 0xFF;
        cpu.setZeroFlag(result == 0 ? 1 : 0);
        cpu.setSubtractionFlag(0);
        cpu.setHalfCarryFlag(0);
        cpu.setCarryFlag((value & (0x01 << 7)) == (0x01 << 7) ? 1 : 0);

        return result;
    }

    private static int RRC(int value) {
        int result = ((value >> 1) | (value << 7));
        cpu.setZeroFlag(result == 0 ? 1 : 0);
        cpu.setSubtractionFlag(0);
        cpu.setHalfCarryFlag(0);
        cpu.setCarryFlag((value & 0b00000001) == 1 ? 1 : 0);
        return result;
    }

    private static int RL(int value) {
        int result = ((value << 1) | (cpu.getCarryFlag() == 1 ? 1 : 0));
        cpu.setZeroFlag(result == 0 ? 1 : 0);
        cpu.setSubtractionFlag(0);
        cpu.setHalfCarryFlag(0);
        cpu.setCarryFlag((value & (0b00000001 << 7)) == (0b00000001 << 7) ? 1 : 0);
        return result;
    }

    private static int RR(int value) {
        int result = ((value >> 1) | (cpu.getCarryFlag() == 1 ? 1 << 7 : 0));
        cpu.setZeroFlag(result == 0 ? 1 : 0);
        cpu.setSubtractionFlag(0);
        cpu.setHalfCarryFlag(0);
        cpu.setCarryFlag((value & 1) == 1 ? 1 : 0);
        return result;
    }

    private static int SLA(int value) {
        int result = value << 1;
        cpu.setZeroFlag(result == 0 ? 1 : 0);
        cpu.setSubtractionFlag(0);
        cpu.setHalfCarryFlag(0);
        cpu.setCarryFlag((value & (1 << 7)) == (1 << 7) ? 1 : 0);
        return result;
    }

    private static int SRA(int value) {
        int result = (value >> 1) | (value & 0b10000000);
        cpu.setZeroFlag(result == 0 ? 1 : 0);
        cpu.setSubtractionFlag(0);
        cpu.setHalfCarryFlag(0);
        cpu.setCarryFlag((value & 1) == 1 ? 1 : 0);
        return result;
    }

    private static int SWAP(int value) {
        int result = (value & 0b11110000) >> 4 | (value & 0b00001111) << 4;
        cpu.setZeroFlag(result == 0 ? 1 : 0);
        cpu.setSubtractionFlag(0);
        cpu.setHalfCarryFlag(0);
        cpu.setCarryFlag(0);
        return result;
    }

    private static int SRL(int value) {
        int result = value >> 1;
        cpu.setZeroFlag(result == 0 ? 1 : 0);
        cpu.setSubtractionFlag(0);
        cpu.setHalfCarryFlag(0);
        cpu.setCarryFlag((value & 0b00000001) != 0 ? 1 : 0);
        return result;
    }

    private static void ADD_HL(int value) {
        int result = cpu.getHL() + value;
        int mask = 0b00001111_11111111;
        cpu.setZeroFlag(0);
        cpu.setHalfCarryFlag(((cpu.getHL() & mask) + (value & mask)) > mask ? 1 : 0);
        cpu.setCarryFlag(result >> 16 != 0 ? 1 : 0);
        cpu.setHL(result);
    }

    private static int INC(int value) {
        int result = value + 1;
        cpu.setZeroFlag(result == 0 ? 1 : 0);
        cpu.setSubtractionFlag(0);
        cpu.setHalfCarryFlag((value & 0b00001111) == 0b00001111 ? 1 : 0);

        return result;
    }

    private static int DEC(int value) {
        int result = value - 1;
        cpu.setZeroFlag(result == 0 ? 1 : 0);
        cpu.setSubtractionFlag(1);
        cpu.setHalfCarryFlag((value & 0b00001111) == 0b00000000 ? 1 : 0);

        return result;
    }

    private static void ADD(int value) {
        int result = cpu.getA() + value;
        cpu.setZeroFlag(result == 0 ? 1 : 0);
        cpu.setSubtractionFlag(0);
        cpu.setHalfCarryFlag((cpu.getA() & 0b00001111) + (value & 0b00001111) > 0b00001111 ? 1 : 0);
        cpu.setCarryFlag(hasCarry(result) ? 1 : 0);
        cpu.setA(result);
    }

    private static void ADC(int value) {
        int carry = cpu.getCarryFlag();
        int result = (cpu.getA() + value + carry) & 0b11111111;
        cpu.setZeroFlag(result == 0 ? 1 : 0);
        cpu.setSubtractionFlag(0);
        cpu.setHalfCarryFlag((cpu.getA() & 0b00001111) + (value & 0b00001111) + carry > 0b00001111 ? 1 : 0);
        cpu.setCarryFlag(hasCarry(cpu.getA() + value + carry) ? 1 : 0);
        cpu.setA(result);
    }

    private static void SUB(int value) {
        int result = cpu.getA() - value;
        cpu.setZeroFlag(result == 0 ? 1 : 0);
        cpu.setSubtractionFlag(1);
        cpu.setHalfCarryFlag((value & 0b00001111) > (cpu.getA() & 0b00001111) ? 1 : 0);
        cpu.setCarryFlag(hasCarry(result) ? 1 : 0);
        cpu.setA(result);
    }

    private static void SBC(int value) {
        int carry = cpu.getCarryFlag();
        int result = cpu.getA() - value - carry;
        cpu.setZeroFlag((result & 0b11111111) == 0 ? 1 : 0);
        cpu.setSubtractionFlag(1);
        cpu.setHalfCarryFlag(((cpu.getA() ^ value ^ (result & 0b11111111)) & (1 << 4)) != 0 ? 1 : 0);
        cpu.setCarryFlag(result < 0 ? 1 : 0);
        cpu.setA(result);
    }

    private static void AND(int value) {
        cpu.setA(cpu.getA() & value);
        cpu.setZeroFlag(cpu.getA() == 0 ? 1 : 0);
        cpu.setSubtractionFlag(0);
        cpu.setHalfCarryFlag(1);
        cpu.setCarryFlag(0);
    }

    private static void XOR(int value) {
        cpu.setA(cpu.getA() ^ value);
        cpu.setZeroFlag(cpu.getA() == 0 ? 1 : 0);
        cpu.setSubtractionFlag(0);
        cpu.setHalfCarryFlag(0);
        cpu.setCarryFlag(0);
    }

    private static void OR(int value) {
        cpu.setA(cpu.getA() | value);
        cpu.setZeroFlag(cpu.getA() == 0 ? 1 : 0);
        cpu.setSubtractionFlag(0);
        cpu.setHalfCarryFlag(0);
        cpu.setCarryFlag(0);
    }

    private static void CP(int value) {
        int result = cpu.getA() - value;
        cpu.setZeroFlag(result == 0 ? 1 : 0);
        cpu.setSubtractionFlag(1);
        cpu.setHalfCarryFlag((0b00001111 & value) > (0b00001111 & cpu.getA()) ? 1 : 0);
        cpu.setCarryFlag(hasCarry(result) ? 1 : 0);
    }

    private static boolean hasCarry(int value) {
        return (value >> 8) != 0;
    }

    private static void BIT(int value, int bitPosition) {
        cpu.setZeroFlag(((value >> bitPosition) & 0b00000001) == 0 ? 1 : 0);
        cpu.setSubtractionFlag(0);
        cpu.setHalfCarryFlag(1);
    }

    private static int RES(int value, int bitPosition) {
        return value & ~(1 << bitPosition);
    }

    private static int SET(int value, int bitPosition) {
        return value | (1 << bitPosition);
    }
}
