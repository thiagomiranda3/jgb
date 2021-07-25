package br.com.tommiranda.jgb;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();

        String json = Files.readString(Path.of("instructions.json"));

        Map<String, Object> instructions = gson.fromJson(json, Map.class);

        List<Object> unprefixed = (List<Object>) instructions.get("Unprefixed");

        int opcode = 0;
        for (Object i : unprefixed) {
            Map<String, Object> inst = (Map<String, Object>) i;

            String hexOp = StringUtils.leftPad(Integer.toHexString(opcode).toUpperCase(), 2, "0");
            System.out.println("table.put(0x" + hexOp + ", new Opcode(0x" + hexOp + ", \"" + inst.get("Name") + "\", " + (int) (double) inst.get("Length") + "));");

            opcode++;
        }
    }

    public static void readROM(String[] args) throws IOException {
        byte[] rom = Files.readAllBytes(Path.of("DMG_ROM.bin"));

        int[] uRom = new int[rom.length];
        for (int i = 0; i < rom.length; i++) {
            uRom[i] = Byte.toUnsignedInt(rom[i]);
        }

        System.out.println(uRom);
    }
}
