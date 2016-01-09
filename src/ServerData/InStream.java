import java.nio.ByteBuffer;

/**
 * The Stream class rewritten to use ByteBuffer.
 * @author Advocatus, MITB, Blakeman8192.
 */
public final class InStream {

    private ByteBuffer buffer;

    public InStream(ByteBuffer buffer) {
	this.buffer = buffer;
    }

    public ByteBuffer getBuffer() {
	return buffer;
    }

    public byte readSignedByte() {
	return buffer.get();
    }

    public byte readSignedByteA() {
	return (byte) (buffer.get() - 128);
    }

    public byte readSignedByteC() {
	return (byte) (-buffer.get());
    }

    public byte readSignedByteS() {
	return (byte) (128 - buffer.get());
    }

    public int readUnsignedByte() {
	return buffer.get() & 0xff;
    }

    public int readUnsignedByteA() {
	return buffer.get() - 128 & 0xff;
    }

    public int readUnsignedByteC() {
	return -buffer.get() & 0xff;
    }

    public int readUnsignedByteS() {
	return 128 - buffer.get() & 0xff;
    }

    public int readSignedWord() {
	int value = 0;
	value |= readUnsignedByte() << 8;
	value |= readUnsignedByte();
	return value;
    }

    public int readSignedWordBigEndian() {
	int value = 0;
	value |= readUnsignedByte();
	value |= readUnsignedByte() << 8;
	return value;
    }

    public int readSignedWordA() {
	int value = 0;
	value |= readUnsignedByte() << 8;
	value |= readUnsignedByteA();
	return value;
    }

    public int readSignedWordBigEndianA() {
	int value = 0;
	value |= readUnsignedByteA();
	value |= readUnsignedByte() << 8;
	return value;
    }

    public int readUnsignedWord() {
	int value = 0;
	value |= readUnsignedByte() << 8;
	value |= readUnsignedByte();
	return value & 0xffff;
    }

    public int readUnsignedWordBigEndian() {
	int value = 0;
	value |= readUnsignedByte();
	value |= readUnsignedByte() << 8;
	return value & 0xffff;
    }

    public int readUnsignedWordA() {
	int value = 0;
	value |= readUnsignedByte() << 8;
	value |= readUnsignedByteA();
	return value & 0xffff;
    }

    public int readUnsignedWordBigEndianA() {
	int value = 0;
	value |= readUnsignedByteA();
	value |= readUnsignedByte() << 8;
	return value & 0xffff;
    }

    public int readDWord() {
	long value = 0;
	value |= readUnsignedByte() << 24;
	value |= readUnsignedByte() << 16;
	value |= readUnsignedByte() << 8;
	value |= readUnsignedByte();
	return (int) value;
    }

    public int readDWord_v1() {
	long value = 0;
	value |= readUnsignedByte() << 8;
	value |= readUnsignedByte();
	value |= readUnsignedByte() << 24;
	value |= readUnsignedByte() << 16;
	return (int) value;
    }

    public int readDWord_v2() {
	long value = 0;
	value |= readUnsignedByte() << 16;
	value |= readUnsignedByte() << 24;
	value |= readUnsignedByte();
	value |= readUnsignedByte() << 8;
	return (int) value;
    }

    public long readQWord() {
	long l = (long) readDWord() & 0xffffffffL;
	long l1 = (long) readDWord() & 0xffffffffL;
	return (l << 32) + l1;
    }

    /**
     * Reads a RuneScape string value.
     * 
     * @return the string
     * @author blakeman8192
     */
    public String readString() {
	byte temp;
	StringBuilder b = new StringBuilder();
	while ((temp = (byte) readSignedByte()) != 10) {
	    b.append((char) temp);
	}
	return b.toString();
    }

    public void readBytes(byte abyte0[], int i, int j) {
	for (int k = j; k < j + i; k++) {
	    abyte0[k] = buffer.get();
	}
    }

    public void readBytes_reverse(byte abyte0[], int i, int size) {
	for (int k = (i + size - 1); k >= i; k--) {
	    abyte0[k] = buffer.get();
	}
    }

    public void readBytes_reverseA(byte abyte0[], int i, int size) {
	for (int k = (i + size - 1); k >= i; k--) {
	    abyte0[k] = readSignedByteA();
	}
    }
}