package com.cube.demo.rbxcb.rbxcb_3x3x3.Masks._2StageMask;

import com.cube.demo.rbxcb.rbxcb_3x3x3.Cube;
import com.cube.demo.rbxcb.rbxcb_3x3x3.Masks.StageMasker;

import java.util.ArrayList;

public class Stage1 implements StageMasker {
    public ArrayList<Integer> mask(Cube c) {
        StageMasker sm = new com.cube.demo.rbxcb.rbxcb_3x3x3.Masks._3StageMask.Stage1();
        return sm.mask(c);
    }
}
