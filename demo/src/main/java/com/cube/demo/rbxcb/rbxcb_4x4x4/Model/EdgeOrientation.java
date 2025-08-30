package com.cube.demo.rbxcb.rbxcb_4x4x4.Model;

import java.util.Arrays;

public class EdgeOrientation implements Cloneable {
    private byte[] val;

    public static byte[] SOLVED_VAL = {3, 3, 3, 3, 2, 2, -2, -2, -3, -3, -3, -3};

    @Override
    public EdgeOrientation clone() {
        EdgeOrientation edgeOrientation;
        try{
            edgeOrientation = (EdgeOrientation) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        edgeOrientation.setVal(this.val.clone());
        return edgeOrientation;
    }

    public EdgeOrientation() {
        this.val = EdgeOrientation.SOLVED_VAL.clone();
    }

    public EdgeOrientation(EdgeOrientation e) {
        this.val = e.val.clone();
    }

    public EdgeOrientation(byte[] val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "EdgeOrientation{\n" +
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
