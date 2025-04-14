package com.cube.demo.rbxcb.rbxcb_3x3x3.Masks.CFOP_Mask;

import com.cube.demo.rbxcb.rbxcb_3x3x3.Masks.StageMasker;
import com.cube.demo.rbxcb.rbxcb_3x3x3.Model.*;

import java.util.ArrayList;

public class PLL_Mask implements StageMasker {
    public ArrayList<Integer> mask(Cube c) {
        ArrayList<Integer> mask = new ArrayList<>();

        EdgePos edgePos = c.getEdge().getEdgePos();
        EdgeOrientation edgeOrientation = c.getEdge().getEdgeOrientation();

        CornerPos cornerPos = c.getCorner().getCornerPos();
        CornerOrientation cornerOrientation = c.getCorner().getCornerOrientation();

        for (int i = 0; i < 12; i++) {
            mask.add((int) edgePos.getVal()[i]);
            mask.add((int) edgeOrientation.getVal()[i]);
        }

        for (int i = 0; i < 8; i++) {
            mask.add((int) cornerPos.getVal()[i]);
            mask.add((int) cornerOrientation.getVal()[i]);
        }

        return mask;
    }
}
