package br.com.tommiranda.jgb;

public class Bus {

    private final Cartridge cartridge;

    public Bus(Cartridge cartridge) {
        this.cartridge = cartridge;
    }

    public void writeRom(int[] rom) {
        cartridge.rom = rom;
    }

    public int readByte(int addr) {
        return cartridge.rom[addr];
    }

    public void writeByte(int addr, int value) {
        if (addr < 0x8000) {
            return;
        }

        cartridge.rom[addr] = value;
    }
}
