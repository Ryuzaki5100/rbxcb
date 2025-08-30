package com.cube.demo.rbxcb.rbxcb_4x4x4.Model;

import java.util.Arrays;

public class CenterOrientation implements Cloneable {
    private byte[] val;

    public static byte[] SOLVED_VAL = {
            3, 3, 3, 3, 2, 2, 1, 1, -2, -2, -1, -1, 2, 2, 1, 1, -2, -2, -1, -1, -3, -3, -3, -3
    };

    @Override
    public CenterOrientation clone() {
        CenterOrientation centerOrientation;
        try{
            centerOrientation = (CenterOrientation) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        centerOrientation.setVal(this.val.clone());
        return centerOrientation;
    }

    public CenterOrientation() {
        this.val = CenterOrientation.SOLVED_VAL.clone();
    }

    public CenterOrientation(CenterOrientation c) {
        this.val = c.val.clone();
    }

    public CenterOrientation(byte[] val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "CenterOrientation{\n" +
                "val=" + Arrays.toString(val) +
                "\n}";
    }

    public byte[] getVal() {
        return val;
    }

    public void setVal(int i, byte val) {
        this.val[i] = val;
    }

    public void setVal(byte[] val) {
        this.val = val;
    }
}
