package io;

import java.io.Serializable;

class Data implements Serializable {
    private int n;

    public Data(int n) {
        this.n = n;
    }

    public String toString() {
        return Integer.toString(n);
    }
}
