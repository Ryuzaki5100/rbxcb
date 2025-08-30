package com.cube.demo.rbxcb.rbxcb_4x4x4.Model;

import java.util.Arrays;

public class CenterPos implements Cloneable {
    private byte[] val;

    public static byte[] SOLVED_VAL = {
            0, 0, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 5, 5
    };

    @Override
    public CenterPos clone() {
        CenterPos centerPos;
        try{
            centerPos = (CenterPos) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        centerPos.setVal(this.val.clone());
        return centerPos;
    }

    public CenterPos() {
        this.val = CenterPos.SOLVED_VAL.clone();
    }

    public CenterPos(CenterPos e) {
        this.val = e.val.clone();
    }

    public CenterPos(byte[] val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "CenterPos{\n" +
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
