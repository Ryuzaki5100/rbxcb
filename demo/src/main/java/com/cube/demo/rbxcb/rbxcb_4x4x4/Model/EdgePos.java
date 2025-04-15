package com.cube.demo.rbxcb.rbxcb_4x4x4.Model;

import java.util.Arrays;

public class EdgePos implements Cloneable {
    private int[] val;

    public static int[] SOLVED_VAL = {
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11,
            12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23
    };

    @Override
    public EdgePos clone() {
        return new EdgePos(this.val.clone());
    }

    public EdgePos() {
        this.val = EdgePos.SOLVED_VAL.clone();
    }

    public EdgePos(EdgePos e) {
        this.val = e.val.clone();
    }

    public EdgePos(int[] val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "EdgePos{\n" +
                "val=" + Arrays.toString(val) +
                "\n}";
    }

    public int[] getVal() {
        return val;
    }

    public void setVal(int i, int val) {
        this.val[i] = val;
    }
}
