package com.cube.demo.rbxcb.rbxcb_3x3x3.Masks._3StageMask;

import com.cube.demo.rbxcb.rbxcb_3x3x3.Model.Cube;
import com.cube.demo.rbxcb.rbxcb_3x3x3.Masks._4StageMask.Stage3;
import com.cube.demo.rbxcb.rbxcb_3x3x3.Masks.StageMasker;

import java.util.ArrayList;

public class Stage2 implements StageMasker {
    public ArrayList<Integer> mask(Cube c) {
        StageMasker sm = new Stage3();
        return sm.mask(c);
    }
}
