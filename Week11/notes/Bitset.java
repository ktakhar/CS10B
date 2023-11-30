import java.util.*;

class Bitset {
    private byte[] byteArray;
    private int maxSize;

    public Bitset() {
        maxSize = 0;
        byteArray = null;
    }

    public Bitset(int size) {
        maxSize = size;
        int nbyte = (size + 7) / 8;
        byteArray = new byte[nbyte];
    }

    public Bitset(Bitset setA) {
        maxSize = setA.maxSize;
        int nbyte = setA.byteArray.length;
        byteArray = new byte[nbyte];
        System.arraycopy(setA.byteArray, 0, byteArray, 0, setA.byteArray.length);
    }

    private void setBit(int n) {
        int whichByte = n / 8;
        int whichBit = n % 8;
        byteArray[whichByte] |= (1 << whichBit);
    }

    private boolean getBit(int n) {
        int whichByte = n / 8;
        int whichBit = n % 8;
        return ((byteArray[whichByte] & (1 << whichBit)) != 0);
    }

    private void clearBit(int n) {
        int whichByte = n / 8;
        int whichBit = n % 8;
        byteArray[whichByte] &= ((1 << whichBit) ^ 255);
    }

    public void clear() {
        if (byteArray == null)
            error("clear: Can't clear a set that hasn't been constructed!");
        for (int i = 0; i < byteArray.length; i++)
            byteArray[i] = 0;
    }

    public void setSize(int size) {
        maxSize = size;
        int nbyte = (size + 7) / 8;
        byteArray = new byte[nbyte];
    }

    public boolean member(int i) {
        if (i >= maxSize) return false;
        return (getBit(i));
    }

    public boolean contains(int i) {
        return member(i);
    }

    public void include(int i) {
        if (i >= maxSize)
            error("include: " + i + "  is too large to fit inside the set");
        setBit(i);
    }

    public void exclude(int i) {
        if (i >= maxSize)
            error("exclude: " + i + "  is too large be inside the set");
        clearBit(i);
    }

    Bitset getSet(Bitset setA) {
        if (byteArray.length < setA.byteArray.length)
            error("getSet: source set larger than dest. set");
        clear();
        int nbyte = setA.byteArray.length;
        for (int i = 0; i < nbyte; i++)
            byteArray[i] = setA.byteArray[i];
        return this;
    }

    public Bitset union(Bitset setB) {
        Bitset temp = new Bitset(this.maxSize > setB.maxSize ? this : setB);
        int nbyte = Math.min(byteArray.length, setB.byteArray.length);
        for (int i = 0; i < nbyte; i++)
            temp.byteArray[i] = (byte) (byteArray[i] | setB.byteArray[i]);
        return temp;
    }

    public Bitset difference(Bitset setB) {
        Bitset temp = new Bitset(this);
        int nbyte = Math.min(byteArray.length, setB.byteArray.length);
        for (int i = 0; i < nbyte; i++)
            temp.byteArray[i] = (byte) (byteArray[i] & (setB.byteArray[i] ^ 255));
        return temp;
    }

    public Bitset intersect(Bitset setB) {
        Bitset temp = new Bitset(Math.min(this.maxSize, setB.maxSize));
        int nbyte = Math.min(byteArray.length, setB.byteArray.length);
        for (int i = 0; i < nbyte; i++)
            temp.byteArray[i] = (byte) (byteArray[i] & setB.byteArray[i]);
        return temp;
    }

    public boolean equals(Bitset setB) {
        int nbyte = Math.min(byteArray.length, setB.byteArray.length);
        for (int i = 0; i < nbyte; i++) {
            if (byteArray[i] != setB.byteArray[i]) return false;
        }
        if (byteArray.length > nbyte) {
            for (int i = nbyte; i < byteArray.length; i++) {
                if (byteArray[i] != 0) return false;
            }
        }
        if (setB.byteArray.length > nbyte) {
            for (int i = nbyte; i < setB.byteArray.length; i++) {
                if (setB.byteArray[i] != 0) return false;
            }
        }
        return true;
    }

    public boolean isNull() {
        for (int i = 0; i < byteArray.length; i++) {
            if (byteArray[i] != 0) return false;
        }
        return true;
    }

    public void readSet(Scanner in) {
        clear();
        while (in.hasNextInt()) {
            int n = in.nextInt();
            if (n >= 0 && n < maxSize) include(n);
        }
        in.next();
    }

    public String toString() {
        String str = "{  ";
        for (int i = 0; i < maxSize; i++) {
            if (member(i)) str += i + "  ";
        }
        return str + "}";
    }

    private void error(String msg) {
        System.out.print(" " + msg);
        System.exit(1);
    }
}