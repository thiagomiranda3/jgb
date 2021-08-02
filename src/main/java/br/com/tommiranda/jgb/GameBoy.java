package br.com.tommiranda.jgb;

public class GameBoy {

    public static Bus bus = new Bus(new Cartridge());
    public static CPU cpu = new CPU(0x01B0, 0x0013, 0x00D8, 0x014D, 0xFFFE, 0x0100);
}
