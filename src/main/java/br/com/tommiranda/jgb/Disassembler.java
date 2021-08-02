package br.com.tommiranda.jgb;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class Disassembler {

    public static void run(int[] rom) throws IOException {
        int pc = 0;

        while (pc < rom.length) {
            Opcode opcode = OpcodeTable.table.get(rom[pc++]);

            if (opcode.getOp() == 0xCB) {
                opcode = OpcodeTable.cbTable.get(rom[pc++]);
                System.out.println(opcode.getLabel());
            } else if (opcode.getLength() == 1) {
                System.out.println(opcode.getLabel());
            } else if (opcode.getLength() == 2) {
                int data = rom[pc++];

                String label = opcode.getLabel()
                        .replace("u8", "")
                        .replace("i8", "");

                String hexData = "$" + StringUtils.leftPad(Integer.toHexString(data), 4, "0");

                System.out.println(label + hexData);
            } else if (opcode.getLength() == 3) {
                int lsb = rom[pc++];
                int msb = rom[pc++];

                int data = (msb << 8) | lsb;

                String label = opcode.getLabel()
                        .replace("u16", "")
                        .replace("i16", "");

                String hexData = "$" + StringUtils.leftPad(Integer.toHexString(data), 4, "0");

                System.out.println(label + hexData);
            }
        }
    }
}
