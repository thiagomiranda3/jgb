package br.com.tommiranda.jgb;

public class ByteUtils {

    public static int to16Bit(int msb, int lsb) {
        return (msb << 8) | lsb;
    }
}
