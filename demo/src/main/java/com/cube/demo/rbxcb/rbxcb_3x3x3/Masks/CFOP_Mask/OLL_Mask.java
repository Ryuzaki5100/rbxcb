package com.cube.demo.rbxcb.rbxcb_3x3x3.Masks.CFOP_Mask;

import com.cube.demo.rbxcb.rbxcb_3x3x3.Masks.StageMasker;
import com.cube.demo.rbxcb.rbxcb_3x3x3.Model.*;

import java.util.ArrayList;
import java.util.Arrays;

public class OLL_Mask implements StageMasker {

    public ArrayList<Integer> mask(Cube c) {
        ArrayList<Integer> edgePosMask = new ArrayList<>();
        ArrayList<Integer> edgeOrientationMask = new ArrayList<>();
        ArrayList<Integer> cornerPosMask = new ArrayList<>();
        ArrayList<Integer>cornerOrientationMask = new ArrayList<>();

        ArrayList<Integer> mask = new ArrayList<>();

        EdgePos edgePos = c.getEdge().getEdgePos();
        EdgeOrientation edgeOrientation = c.getEdge().getEdgeOrientation();

        CornerPos cornerPos = c.getCorner().getCornerPos();
        CornerOrientation cornerOrientation = c.getCorner().getCornerOrientation();

        for (int i = 0; i < 12; i++) {
            edgePosMask.add(0);
            edgeOrientationMask.add(0);
        }

        for (int i = 0; i < 8; i++) {
            cornerPosMask.add(0);
            cornerOrientationMask.add(0);
        }

        for (int i = 0; i < 12; i++) {
            if (i > 3)
                edgePosMask.set((int) edgePos.getVal()[i], i);
            edgeOrientationMask.set((int) edgePos.getVal()[i], (int) edgeOrientation.getVal()[i]);
        }

        for (int i = 0; i < 8; i++) {
            if (i > 3)
                cornerPosMask.set((int) cornerPos.getVal()[i], i);
            cornerOrientationMask.set((int) cornerPos.getVal()[i], (int) cornerOrientation.getVal()[i]);
        }

        mask.addAll(edgePosMask);
        mask.addAll(cornerPosMask);
        mask.addAll(edgeOrientationMask);
        mask.addAll(cornerOrientationMask);

        return mask;
    }
}
