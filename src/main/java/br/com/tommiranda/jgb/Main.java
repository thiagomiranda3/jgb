package br.com.tommiranda.jgb;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws IOException {
        //byte[] rom = Files.readAllBytes(Path.of("DMG_ROM.bin"));
        byte[] rom = Files.readAllBytes(Path.of("Tetris.gb"));

        int[] uRom = new int[rom.length];
        for (int i = 0; i < rom.length; i++) {
            uRom[i] = Byte.toUnsignedInt(rom[i]);
        }

        GameBoy.bus.writeRom(uRom);
    }
}
