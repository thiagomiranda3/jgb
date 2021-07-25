package br.com.tommiranda.jgb;

import java.util.HashMap;
import java.util.Map;

public class OpcodeTable {

    private Map<Integer, Opcode> table = createTable();

    private Map<Integer, Opcode> createTable() {
        Map<Integer, Opcode> table = new HashMap<>();

        table.put(0xA, new Opcode(0xA, "LD A,(BC)", 1));

        table.put(0x00, new Opcode(0x00, "NOP", 1));
        table.put(0x01, new Opcode(0x01, "LD BC,u16", 3));
        table.put(0x02, new Opcode(0x02, "LD (BC),A", 1));
        table.put(0x03, new Opcode(0x03, "INC BC", 1));
        table.put(0x04, new Opcode(0x04, "INC B", 1));
        table.put(0x05, new Opcode(0x05, "DEC B", 1));
        table.put(0x06, new Opcode(0x06, "LD B,", 2));
        table.put(0x07, new Opcode(0x07, "RLCA", 1));
        table.put(0x08, new Opcode(0x08, "LD (u16),SP", 3));
        table.put(0x09, new Opcode(0x09, "ADD HL,BC", 1));
        table.put(0x0A, new Opcode(0x0A, "LD A,(BC)", 1));
        table.put(0x0B, new Opcode(0x0B, "DEC BC", 1));
        table.put(0x0C, new Opcode(0x0C, "INC C", 1));
        table.put(0x0D, new Opcode(0x0D, "DEC C", 1));
        table.put(0x0E, new Opcode(0x0E, "LD C,u8", 2));
        table.put(0x0F, new Opcode(0x0F, "RRCA", 1));
        table.put(0x10, new Opcode(0x10, "STOP", 2));
        table.put(0x11, new Opcode(0x11, "LD DE,u16", 3));
        table.put(0x12, new Opcode(0x12, "LD (DE),A", 1));
        table.put(0x13, new Opcode(0x13, "INC DE", 1));
        table.put(0x14, new Opcode(0x14, "INC D", 1));
        table.put(0x15, new Opcode(0x15, "DEC D", 1));
        table.put(0x16, new Opcode(0x16, "LD D,u8", 2));
        table.put(0x17, new Opcode(0x17, "RLA", 1));
        table.put(0x18, new Opcode(0x18, "JR i8", 2));
        table.put(0x19, new Opcode(0x19, "ADD HL,DE", 1));
        table.put(0x1A, new Opcode(0x1A, "LD A,(DE)", 1));
        table.put(0x1B, new Opcode(0x1B, "DEC DE", 1));
        table.put(0x1C, new Opcode(0x1C, "INC E", 1));
        table.put(0x1D, new Opcode(0x1D, "DEC E", 1));
        table.put(0x1E, new Opcode(0x1E, "LD E,u8", 2));
        table.put(0x1F, new Opcode(0x1F, "RRA", 1));
        table.put(0x20, new Opcode(0x20, "JR NZ,i8", 2));
        table.put(0x21, new Opcode(0x21, "LD HL,u16", 3));
        table.put(0x22, new Opcode(0x22, "LD (HL+),A", 1));
        table.put(0x23, new Opcode(0x23, "INC HL", 1));
        table.put(0x24, new Opcode(0x24, "INC H", 1));
        table.put(0x25, new Opcode(0x25, "DEC H", 1));
        table.put(0x26, new Opcode(0x26, "LD H,u8", 2));
        table.put(0x27, new Opcode(0x27, "DAA", 1));
        table.put(0x28, new Opcode(0x28, "JR Z,i8", 2));
        table.put(0x29, new Opcode(0x29, "ADD HL,HL", 1));
        table.put(0x2A, new Opcode(0x2A, "LD A,(HL+)", 1));
        table.put(0x2B, new Opcode(0x2B, "DEC HL", 1));
        table.put(0x2C, new Opcode(0x2C, "INC L", 1));
        table.put(0x2D, new Opcode(0x2D, "DEC L", 1));
        table.put(0x2E, new Opcode(0x2E, "LD L,u8", 2));
        table.put(0x2F, new Opcode(0x2F, "CPL", 1));
        table.put(0x30, new Opcode(0x30, "JR NC,i8", 2));
        table.put(0x31, new Opcode(0x31, "LD SP,u16", 3));
        table.put(0x32, new Opcode(0x32, "LD (HL-),A", 1));
        table.put(0x33, new Opcode(0x33, "INC SP", 1));
        table.put(0x34, new Opcode(0x34, "INC (HL)", 1));
        table.put(0x35, new Opcode(0x35, "DEC (HL)", 1));
        table.put(0x36, new Opcode(0x36, "LD (HL),u8", 1));
        table.put(0x37, new Opcode(0x37, "SCF", 1));

        return table;
    }
}
