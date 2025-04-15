package com.cube.demo.rbxcb.rbxcb_4x4x4.Model;

import java.util.Arrays;

public class CenterPos implements Cloneable {
    private int[] val;

    public static int[] SOLVED_VAL = {
            0, 0, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 5, 5
    };

    @Override
    public CenterPos clone() {
        return new CenterPos(this.val.clone());
    }

    public CenterPos() {
        this.val = EdgePos.SOLVED_VAL.clone();
    }

    public CenterPos(CenterPos e) {
        this.val = e.val.clone();
    }

    public CenterPos(int[] val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "CenterPos{\n" +
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
