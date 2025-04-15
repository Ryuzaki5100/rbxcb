package com.cube.demo.rbxcb.rbxcb_4x4x4.Model;

import java.util.Arrays;

public class CenterOrientation implements Cloneable {
    private int[] val;

    public static int[] SOLVED_VAL = {
            3, 3, 3, 3, 2, 2, 1, 1, -2, -2, -1, -1, 2, 2, 1, 1, -2, -2, -1, -1, -3, -3, -3, -3
    };

    @Override
    public CenterOrientation clone() {
        return new CenterOrientation(this.val.clone());
    }

    public CenterOrientation() {
        this.val = CenterOrientation.SOLVED_VAL.clone();
    }

    public CenterOrientation(CenterOrientation c) {
        this.val = c.val.clone();
    }

    public CenterOrientation(int[] val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "CenterOrientation{\n" +
                "val=" + Arrays.toString(val) +
                "\n}";
    }

    public int[] getVal() {
        return val;
    }

    public void setVal(int i, byte val) {
        this.val[i] = val;
    }
}
