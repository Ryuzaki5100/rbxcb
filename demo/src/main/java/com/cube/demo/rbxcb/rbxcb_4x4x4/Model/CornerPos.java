package com.cube.demo.rbxcb.rbxcb_4x4x4.Model;

import java.util.Arrays;

public class CornerPos implements Cloneable {
    private int[] val;

    public static int[] SOLVED_VAL = {0, 1, 2, 3, 4, 5, 6, 7};

    @Override
    public CornerPos clone() {
        return new CornerPos(this.val.clone());
    }

    public CornerPos() {
        this.val = CornerPos.SOLVED_VAL.clone();
    }

    public CornerPos(CornerPos c){
        this.val = c.val.clone();
    }

    public CornerPos(int[] val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "CornerPos{\n" +
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
