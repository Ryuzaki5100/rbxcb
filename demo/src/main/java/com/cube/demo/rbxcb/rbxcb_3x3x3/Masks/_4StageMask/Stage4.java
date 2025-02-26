package com.cube.demo.rbxcb.rbxcb_3x3x3.Masks._4StageMask;

import com.cube.demo.rbxcb.rbxcb_3x3x3.Cube;
import com.cube.demo.rbxcb.rbxcb_3x3x3.Masks.StageMasker;

import java.util.ArrayList;

public class Stage4 implements StageMasker {
    public ArrayList<Integer> mask(Cube c) {
        ArrayList<Integer> mask = new ArrayList<>();

        for (byte i : c.getEdge().getEdgePos().getVal())
            mask.add((int) i);

        for (byte i : c.getCorner().getCornerPos().getVal())
            mask.add((int) i);

        return mask;
    }
}
