package br.com.tommiranda.jgb;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws IOException {
        byte[] rom = Files.readAllBytes(Path.of("DMG_ROM.bin"));

        int[] uRom = new int[rom.length];
        for (int i = 0; i < rom.length; i++) {
            uRom[i] = Byte.toUnsignedInt(rom[i]);
        }

        int pc = 0;
        while (pc < uRom.length) {
            Opcode opcode = OpcodeTable.table.get(uRom[pc++]);

            if (opcode.getLength() == 1) {
                System.out.println(opcode.getLabel());
            } else if (opcode.getLength() == 2) {
                int data = uRom[pc++];

                String label = opcode.getLabel()
                                     .replace("u8", "")
                                     .replace("i8", "");

                String hexData = StringUtils.leftPad(Integer.toHexString(data).toUpperCase(), 4, "0");

                System.out.println(label + hexData);
            } else if(opcode.getLength() == 3) {
                int lsb = uRom[pc++];
                int msb = uRom[pc++];

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
