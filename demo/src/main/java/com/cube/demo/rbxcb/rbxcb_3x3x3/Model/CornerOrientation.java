package com.cube.demo.rbxcb.rbxcb_3x3x3.Model;

import java.util.Arrays;

public class CornerOrientation implements Cloneable {
    private byte[] val;

    public static byte[] SOLVED_VAL = {3, 3, 3, 3, -3, -3, -3, -3};

    @Override
    public CornerOrientation clone() {
        CornerOrientation cornerOrientation;
        try {
            cornerOrientation = (CornerOrientation) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        cornerOrientation.setVal(this.val.clone());
        return cornerOrientation;
    }

    public CornerOrientation() {
        this.val = CornerOrientation.SOLVED_VAL.clone();
    }

    public CornerOrientation(CornerOrientation c) {
        this.val = c.val.clone();
    }

    public CornerOrientation(byte[] val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "CornerOrientation{\n" +
                "val=" + Arrays.toString(val) +
                "\n}";
    }

    public byte[] getVal() {
        return val;
    }

    public void setVal(int i, byte val) {
        this.val[i] = val;
    }

    private void setVal(byte[] val) {
        this.val = val;
    }
}
